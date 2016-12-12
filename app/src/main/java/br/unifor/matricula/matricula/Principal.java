package br.unifor.matricula.matricula;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import br.unifor.matricula.BaseActivity;
import br.unifor.matricula.R;
import br.unifor.matricula.dao.MatriculaDAO;
import br.unifor.matricula.dao.UsuarioDAO;
import br.unifor.matricula.interfaces.OnMatriculaInteractionListener;
import br.unifor.matricula.model.Disciplina;
import br.unifor.matricula.model.Matricula;
import br.unifor.matricula.model.Usuario;

public class Principal extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener, OnMatriculaInteractionListener {

  private static final int HOME = 1;
  public static final int LISTA_DISCIPLINAS = 2;
  public static final int MINHA_MATRICULA = 3;
  private static final int MATRICULAR = 4;

  private int telaAtual;

  private Usuario usuario;
  private Disciplina disciplinaAMatricular;

  private TextView tvOla;
  private NavigationView navigationView;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle(getString(R.string.app_name));

    navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    Intent it = getIntent();
    int idUsuario = it.getIntExtra(getString(R.string.usuario_param), 0);
    UsuarioDAO usuarioDAO = new UsuarioDAO(this);
    usuario = usuarioDAO.find(idUsuario);

    tvOla = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_ola);
    tvOla.setText(getString(R.string.ola) + usuario.getNome() + "!");

    exibirHome();
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      switch (telaAtual) {
        case HOME:
          logout();
          break;
        case MATRICULAR:
          exibirListaDisciplinas();
          break;
        default:
          exibirHome();
      }
    }
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    int id = item.getItemId();
    switch (id) {
      case R.id.nav_home:
        exibirHome();
        break;
      case R.id.nav_lista_disciplinas:
        exibirListaDisciplinas();
        break;
      case R.id.nav_minha_matricula:
        exibirMinhaMatricula();
        break;
      case R.id.nav_logout:
        logout();
    }
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  private void logout() {
    //TODO: Limpar variáveis por segurança???
    AlertDialog.Builder builder = new AlertDialog.Builder(this)
        .setCancelable(false)
        .setMessage(R.string.pergunta_sair)
        .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            Intent it = new Intent(Principal.this, br.unifor.matricula.login.Principal.class);
            startActivity(it);
            finish();

            dialogInterface.dismiss();
          }
        })
        .setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            marcarMenu(telaAtual);
            dialogInterface.dismiss();
          }
        });
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  private void marcarMenu(int tela) {
    switch (tela) {
      case HOME:
        navigationView.setCheckedItem(R.id.nav_home);
        break;
      case LISTA_DISCIPLINAS:
        navigationView.setCheckedItem(R.id.nav_lista_disciplinas);
        break;
      case MINHA_MATRICULA:
        navigationView.setCheckedItem(R.id.nav_minha_matricula);
        break;
    }
  }

  @Override
  protected void carregaFragmento(int tela) {
    switch (tela) {
      case HOME:
        fragmento = new Home();
        break;
      case LISTA_DISCIPLINAS:
        fragmento = new ListaDisciplinas();
        break;
      case MINHA_MATRICULA:
        fragmento = new MinhaMatricula();
        break;
      case MATRICULAR:
        fragmento = new Matricular();
        break;
    }
    telaAtual = tela;
  }

  @Override
  public Usuario getUsuario() {
    return usuario;
  }

  @Override
  public Disciplina getDisciplinaAMatricular() {
    return disciplinaAMatricular;
  }

  @Override
  public void exibirHome() {
    marcarMenu(R.id.nav_home);
    exibeTela(R.id.content_main, HOME);
  }

  @Override
  public void exibirMatricular(Disciplina disciplina) {
    disciplinaAMatricular = disciplina;
    exibeTela(R.id.content_main, MATRICULAR);
  }

  private void exibirListaDisciplinas() {
    marcarMenu(R.id.nav_lista_disciplinas);
    exibeTela(R.id.content_main, LISTA_DISCIPLINAS);
  }

  @Override
  public void exibirMinhaMatricula() {
    marcarMenu(R.id.nav_minha_matricula);
    exibeTela(R.id.content_main, MINHA_MATRICULA);
  }

  @Override
  public void Matricular(Usuario usuario, Disciplina disciplina) {
    MatriculaDAO matriculaDAO = new MatriculaDAO(this);
    matriculaDAO.insert(new Matricula(usuario, disciplina));
  }

  @Override
  public int getTelaAtual() {
    return telaAtual;
  }
}
