package br.unifor.matricula.matricula;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    Intent it = getIntent();
    int idUsuario = it.getIntExtra("usuario", 0);
    UsuarioDAO usuarioDAO = new UsuarioDAO(this);
    usuario = usuarioDAO.find(idUsuario);

    tvOla = (TextView) navigationView.getHeaderView(0).findViewById(R.id.tv_ola);
    tvOla.setText("Olá " + usuario.getNome() + "!");

    exibirHome();
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
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
/*              AlertDialog.Builder builder = new AlertDialog.Builder(this)
                  .setCancelable(false)
                  .setMessage("Voce realmente deseja sair?")
                  .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                            mEditTextNome.setText("");
                            mEditTextTelefone.setText("");
                            mEditTextEndereco.setText("");
                            mEditTextEmail.setText("");

                            dialogInterface.dismiss();

                        }
                    })
                    .setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                AlertDialog dialog = builder.create();
                dialog.show();*/
        Intent it = new Intent(this, br.unifor.matricula.login.Principal.class);
        startActivity(it);
        finish();
    };
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
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
    exibeTela(R.id.content_main, HOME);
  }

  @Override
  public void exibirMatricular(Disciplina disciplina) {
    disciplinaAMatricular = disciplina;
    exibeTela(R.id.content_main, MATRICULAR);
  }

  private void exibirListaDisciplinas() {
    exibeTela(R.id.content_main, LISTA_DISCIPLINAS);
  }

  @Override
  public void exibirMinhaMatricula() {
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
