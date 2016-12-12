package br.unifor.matricula.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.unifor.matricula.database.DataBaseHelper;
import br.unifor.matricula.model.IModel;

/**
 * Created by koitim on 11/12/16.
 */

public abstract class GenericDAO<T extends IModel> {

  protected SQLiteDatabase mDatabase;

  public GenericDAO(Context context) {
    DataBaseHelper dbHelper = new DataBaseHelper(context);
    this.mDatabase = dbHelper.getWritableDatabase();
  }

  // Método para inserção no banco
  public abstract void insert(T obj);

  // Método para atualização no banco
  public abstract void update(T obj);

  // Método para remover do banco
  public abstract void delete(T obj);

  public abstract T find(int id);

  public abstract List<T> findAll();

  public abstract ContentValues getContentValues(T obj);
}
