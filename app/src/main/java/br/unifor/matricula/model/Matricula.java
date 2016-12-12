package br.unifor.matricula.model;

/**
 * Created by koitim on 11/12/16.
 */

public class Matricula implements IModel {

  public static final String TABELA = "matricula";
  public static final String ID = "id";
  public static final String USUARIO = "id_usuario";
  public static final String DISCIPLINA = "id_disciplina";

  private Long id;
  private Usuario usuario;
  private Disciplina disciplina;

  public Matricula() {
  }

  public Matricula(Long id, Usuario usuario, Disciplina disciplina) {
    this.id = id;
    this.usuario = usuario;
    this.disciplina = disciplina;
  }

  public Matricula(Usuario usuario, Disciplina disciplina) {
    this(null, usuario, disciplina);
  }

  @Override
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Disciplina getDisciplina() {
    return disciplina;
  }

  public void setDisciplina(Disciplina disciplina) {
    this.disciplina = disciplina;
  }
}
