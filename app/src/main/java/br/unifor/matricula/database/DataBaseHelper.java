package br.unifor.matricula.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.unifor.matricula.model.Disciplina;
import br.unifor.matricula.model.Matricula;
import br.unifor.matricula.model.Usuario;

/**
 * Created by koitim on 11/12/16.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

  private static final String DATABASE_NAME = "matricula.db";
  private static final int DATABASE_VERSION = 4;

  public DataBaseHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    // Usuario
    String sql = "CREATE TABLE usuario (id INTEGER PRIMARY KEY, nome TEXT, email TEXT, senha TEXT);";
    db.execSQL(sql);

    // Disciplina
    sql = "CREATE TABLE disciplina (id INTEGER PRIMARY KEY, nome TEXT, descricao TEXT);";
    db.execSQL(sql);
    importDisciplinas(db);

    // Matricula
    sql = "CREATE TABLE matricula (id INTEGER PRIMARY KEY, id_usuario INTEGER, id_disciplina INTEGER);";
    db.execSQL(sql);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    // Matricula
    String sql = "DROP TABLE IF EXISTS " + Matricula.TABELA;
    db.execSQL(sql);
    // Disciplina
    sql = "DROP TABLE IF EXISTS " + Disciplina.TABELA;
    db.execSQL(sql);
    // Usuario
    sql = "DROP TABLE IF EXISTS " + Usuario.TABELA;
    db.execSQL(sql);

    onCreate(db);

  }

  private void importDisciplinas(SQLiteDatabase db) {
    String sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N610','Cálculo I');";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N605','Informática e sociedade');";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N501','Introdução a computação');";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N573','Lógica de programação');";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N096','Matemática discreta')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N094','Álgebra linear e geometria analítica')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N611','Cálculo II')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('T922','Programação orientada a objetos')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N579','Projeto de interface')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N532','Sistemas lógicos e digitais')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N620','Administração, empreendimento e inovação')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N607','Arquitetura e organização de computadores')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('T923','Estrutura de dados')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N097','Lógica matemática')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N524','Técnicas de programação')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N617','Cálculo numérico')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N673','Fundamentos de banco de dados')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N618','Probabilidade e estatística')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N554','Tecnologias internet I')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N583','Teoria dos grafos')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N672','Engenharia de requisitos e teste de SW')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N565','Gestão de tecnologia da informação')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N421','Produção de trabalhos científicos')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N584','Projeto e análise de algoritmos')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('T952','Sistemas operacionais')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N592','Tecnicas de implementação de sistemas de BD')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N562','Análise e projeto de sistemas II')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N593','Paradigmas de linguagens de programação')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N564','Pesquisa operacional')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('T958','Redes de computadores')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N587','Teroria dos automatos e linguagens formais')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N588','Computabilidade')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N521','Computação gráfica')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N517','Engenharia de SW')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N519','Inteligência artificial')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N595','Redes de computadores II')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N596','Trabalho de conclusão do curso I')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N525','Compiladores I')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N534','Gerência de projetos')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N589','Processamento de imagens')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N598','Sistemas distribuídos')";
    db.execSQL(sql);
    sql = "INSERT INTO disciplina (nome, descricao) VALUES ('N597','Trabalho de conclusão do curso II')";
    db.execSQL(sql);
  }
}
