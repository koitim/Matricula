package br.unifor.matricula.interfaces;

/**
 * Created by koitim on 10/12/2016.
 */

public interface OnLoginInteractionListener {
    boolean validarNome(String nome);
    boolean validarEmail(String email);
    boolean validarSenha(String senha);
    int validarLogin(String email, String senha);
    void login(int idUsuario);
    void exibirCadastro();
    void exibirLogin();
    boolean cadastrar(String nome, String email, String senha);
}
