package br.unifor.matricula.model;

/**
 * Created by koitim on 11/12/16.
 */

public class Disciplina implements IModel {

  public static final String TABELA = "disciplina";
  public static final String ID = "id";
  public static final String NOME = "nome";
  public static final String DESCRICAO = "descricao";

  private Long id;
  private String nome;
  private String descricao;

  public Disciplina() {
  }

  public Disciplina(Long id, String nome, String descricao) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
  }

  public Disciplina(String nome, String descricao) {
    this(null, nome, descricao);
  }

  @Override
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  @Override
  public String toString() {
    return nome + " - " + descricao;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (!(obj instanceof Disciplina))
      return false;
    Disciplina disciplina = (Disciplina) obj;
    return disciplina.getNome().equals(this.nome);
  }
}
