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

public class MatriculaDAO extends GenericDAO<Matricula> {

  private Context context;

  public MatriculaDAO(Context context) {
    super(context);
    this.context = context;
  }

  @Override
  public void insert(Matricula obj) {
    mDatabase.insert(Matricula.TABELA, null, getContentValues(obj));
  }

  @Override
  public void update(Matricula obj) {
    mDatabase.update(Matricula.TABELA, getContentValues(obj), "id = ?", new String[]{obj.getId().toString()});
  }

  @Override
  public void delete(Matricula obj) {
    mDatabase.delete(Matricula.TABELA, "id = ?", new String[]{obj.getId().toString()});
  }

  @Override
  public Matricula find(int id) {
    Matricula matricula = null;

    Cursor cursor = mDatabase.query(
        Matricula.TABELA, null, Matricula.ID+"=?1", new String[]{String.valueOf(id)}, null, null, null
    );
    if (cursor.getCount() != 1) {
      return null;
    }
    cursor.moveToFirst();

    UsuarioDAO usuarioDAO = new UsuarioDAO(context);
    Usuario usuario = usuarioDAO.find((int) cursor.getLong(cursor.getColumnIndex(Matricula.USUARIO)));

    DisciplinaDAO disciplinaDAO = new DisciplinaDAO(context);
    Disciplina disciplina = disciplinaDAO.find((int) cursor.getLong(cursor.getColumnIndex(Matricula.DISCIPLINA)));

    matricula = new Matricula(
        cursor.getLong(cursor.getColumnIndex(Matricula.ID)), usuario,disciplina
    );
    return matricula;
  }

  @Override
  public List<Matricula> findAll() {
    Matricula matricula = null;
    List<Matricula> matriculaList = new ArrayList();

    String sql = "SELECT * FROM " + Matricula.TABELA;

    Cursor cursor = mDatabase.rawQuery(sql, null);
    UsuarioDAO usuarioDAO = new UsuarioDAO(context);
    DisciplinaDAO disciplinaDAO = new DisciplinaDAO(context);
    Usuario usuario;
    Disciplina disciplina;

    while (cursor.moveToNext()) {
      usuario = usuarioDAO.find((int) cursor.getLong(cursor.getColumnIndex(Matricula.USUARIO)));
      disciplina = disciplinaDAO.find((int) cursor.getLong(cursor.getColumnIndex(Matricula.DISCIPLINA)));
      matricula = new Matricula(
          cursor.getLong(cursor.getColumnIndex(Matricula.ID)), usuario, disciplina);
      matriculaList.add(matricula);
    }
    return matriculaList;
  }

  public List<Matricula> find(Usuario usuario) {
    Matricula matricula = null;
    List<Matricula> matriculaList = new ArrayList();

    Cursor cursor = mDatabase.query(
        Matricula.TABELA, null, Matricula.USUARIO+"=?1", new String[]{String.valueOf(usuario.getId())}, null, null, null
    );
    DisciplinaDAO disciplinaDAO = new DisciplinaDAO(context);
    Disciplina disciplina;

    while (cursor.moveToNext()) {
      disciplina = disciplinaDAO.find((int) cursor.getLong(cursor.getColumnIndex(Matricula.DISCIPLINA)));
      matricula = new Matricula(
          cursor.getLong(cursor.getColumnIndex(Matricula.ID)), usuario, disciplina);
      matriculaList.add(matricula);
    }
    return matriculaList;
  }

  @Override
  public ContentValues getContentValues(Matricula obj) {
    ContentValues contentValues = new ContentValues();

    contentValues.put(Matricula.ID, obj.getId());
    contentValues.put(Matricula.USUARIO, obj.getUsuario().getId());
    contentValues.put(Matricula.DISCIPLINA, obj.getDisciplina().getId());

    return contentValues;
  }
}
