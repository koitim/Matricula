package br.unifor.matricula.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.unifor.matricula.model.Disciplina;
import br.unifor.matricula.model.Matricula;
import br.unifor.matricula.model.Usuario;

/**
 * Created by koitim on 11/12/16.
 */

public class DisciplinaDAO extends GenericDAO<Disciplina> {

  public DisciplinaDAO(Context context) {
    super(context);
  }

  @Override
  public void insert(Disciplina obj) {
    mDatabase.insert(Disciplina.TABELA, null, getContentValues(obj));
  }

  @Override
  public void update(Disciplina obj) {
    mDatabase.update(Disciplina.TABELA, getContentValues(obj), "id = ?", new String[]{obj.getId().toString()});
  }

  @Override
  public void delete(Disciplina obj) {
    mDatabase.delete(Disciplina.TABELA, "id = ?", new String[]{obj.getId().toString()});
  }

  @Override
  public Disciplina find(int id) {
    Disciplina disciplina;

    Cursor cursor = mDatabase.query(
        Disciplina.TABELA, null, Disciplina.ID+"=?1", new String[]{String.valueOf(id)}, null, null, null
    );
    if (cursor.getCount() != 1) {
      return null;
    }
    cursor.moveToFirst();
    disciplina = new Disciplina(
        cursor.getLong(cursor.getColumnIndex(Disciplina.ID)),
        cursor.getString(cursor.getColumnIndex(Disciplina.NOME)),
        cursor.getString(cursor.getColumnIndex(Disciplina.DESCRICAO))
    );
    return disciplina;
  }

  @Override
  public List<Disciplina> findAll() {
    Disciplina disciplina;
    List<Disciplina> disciplinaList = new ArrayList();
    String sql = "SELECT * FROM " + Disciplina.TABELA + " ORDER BY " + Disciplina.DESCRICAO;
    Cursor cursor = mDatabase.rawQuery(sql, null);
    while (cursor.moveToNext()) {
      disciplina = new Disciplina(
          cursor.getLong(cursor.getColumnIndex(Disciplina.ID)),
          cursor.getString(cursor.getColumnIndex(Disciplina.NOME)),
          cursor.getString(cursor.getColumnIndex(Disciplina.DESCRICAO))
      );
      disciplinaList.add(disciplina);
    }
    return disciplinaList;
  }

  public List<Disciplina> findDisciplianasNaoMatriculadas(Context context, Usuario usuario) {
    List<Disciplina> disciplinas = findAll();
    MatriculaDAO matriculaDAO = new MatriculaDAO(context);
    List<Matricula> todasMatriculasUsuario = matriculaDAO.find(usuario);
    for (Matricula matricula : todasMatriculasUsuario) {
      if (disciplinas.contains(matricula.getDisciplina())) {
        disciplinas.remove(matricula.getDisciplina());
      }
    }
    return disciplinas;
  }

  @Override
  public ContentValues getContentValues(Disciplina obj) {
    ContentValues contentValues = new ContentValues();

    contentValues.put(Disciplina.ID, obj.getId());
    contentValues.put(Disciplina.NOME, obj.getNome());
    contentValues.put(Disciplina.DESCRICAO, obj.getDescricao());

    return contentValues;
  }
}
