package br.unifor.matricula.login;

import android.content.Intent;
import android.os.Bundle;

import br.unifor.matricula.BaseActivity;
import br.unifor.matricula.R;
import br.unifor.matricula.interfaces.OnLoginInteractionListener;

public class Principal extends BaseActivity implements OnLoginInteractionListener {

    private static final int LOGIN = 1;
    private static final int CADASTRO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        exibeTela(LOGIN);
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
        //TODO: Implementar validação
        return true;
    }

    @Override
    public boolean validarEmail(String email) {
        //TODO: Implementar validação
        return true;
    }

    @Override
    public boolean validarSenha(String senha) {
        //TODO: Implementar validação
        return true;
    }

    @Override
    public boolean validarLogin(String email, String senha) {
        //TODO: Validar o login
        return true;
    }

    @Override
    public void login(String usuario, String senha) {
        Intent it = new Intent(this, br.unifor.matricula.matricula.Principal.class);
        //TODO: Passar o usuário
        it.putExtra("usuario", 1);
        startActivity(it);
        finish();
    }

    @Override
    public void exibirCadastro() {
        exibeTela(CADASTRO);
    }

    @Override
    public void exibirLogin() {
        exibeTela(LOGIN);
    }

    @Override
    public boolean cadastrar(String nome, String email, String senha) {
        //TODO: Efetuar o cadastro
        return true;
    }
}
