package br.unifor.matricula.interfaces;

/**
 * Created by koitim on 10/12/2016.
 */

public interface OnLoginInteractionListener {
    boolean validarNome(String nome);
    boolean validarEmail(String email);
    boolean validarSenha(String senha);
    //TODO: Mudar tipo do retorno quando criar os modelos de classes para usar USUARIO
    boolean validarLogin(String email, String senha);
    //TODO: Mudar parâmetros quando criar os modelos de classes para usar USUARIO
    void login(String email, String senha);
    void exibirCadastro();
    void exibirLogin();
    //TODO: Mudar tipo do retorno quando criar os modelos de classes para usar USUARIO
    boolean cadastrar(String nome, String email, String senha);
}
