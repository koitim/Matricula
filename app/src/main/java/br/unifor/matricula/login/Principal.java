package br.unifor.matricula.login;

import android.content.Intent;
import android.os.Bundle;

import br.unifor.matricula.BaseActivity;
import br.unifor.matricula.R;
import br.unifor.matricula.dao.UsuarioDAO;
import br.unifor.matricula.interfaces.OnLoginInteractionListener;
import br.unifor.matricula.model.Usuario;

public class Principal extends BaseActivity implements OnLoginInteractionListener {

  private static final int LOGIN = 1;
  private static final int CADASTRO = 2;

  private int telaAtual;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login_principal_layout);
    exibirLogin();
  }

  @Override
  protected void carregaFragmento(int tela) {
    switch (tela) {
      case LOGIN:
        fragmento = new Login();
        break;
      case CADASTRO:
        fragmento = new CadastroUsuario();
        break;
    }
  }

  @Override
  public boolean validarNome(String nome) {
    return !nome.trim().equals("");
  }

  @Override
  public boolean validarEmail(String email) {
    return !email.trim().equals("") && email.contains("@");
  }

  @Override
  public boolean validarSenha(String senha) {
    return !senha.trim().equals("");
  }

  @Override
  public void onBackPressed() {
    if (telaAtual == LOGIN) {
      super.onBackPressed();
    } else {
      exibirLogin();
    }
  }

  @Override
  public int validarLogin(String email, String senha) {
    UsuarioDAO usuarioDAO = new UsuarioDAO(this);
    Long idUsuario = usuarioDAO.getIdUsuario(email, senha);
    return (idUsuario == null?0:idUsuario.intValue());
  }

  @Override
  public void login(int idUsuario) {
    Intent it = new Intent(this, br.unifor.matricula.matricula.Principal.class);
    it.putExtra(getString(R.string.usuario_param), idUsuario);
    startActivity(it);
    finish();
  }

  @Override
  public void exibirCadastro() {
    telaAtual = CADASTRO;
    exibeTela(R.id.activity_login, CADASTRO);
  }

  @Override
  public void exibirLogin() {
    telaAtual = LOGIN;
    exibeTela(R.id.activity_login, LOGIN);
  }

  @Override
  public boolean cadastrar(String nome, String email, String senha) {
    UsuarioDAO usuarioDAO = new UsuarioDAO(this);
    if (usuarioDAO.existeEmail(email)) {
      return false;
    }
    usuarioDAO.insert(new Usuario(nome, email, senha));
    return true;
  }
}
