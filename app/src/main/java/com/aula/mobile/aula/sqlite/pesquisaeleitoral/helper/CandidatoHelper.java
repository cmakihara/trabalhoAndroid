package com.aula.mobile.aula.sqlite.pesquisaeleitoral.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.aula.mobile.aula.sqlite.pesquisaeleitoral.helper.DbHelper;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.entity.Candidato;

import java.util.ArrayList;
import java.util.List;

public class CandidatoHelper {

    private DbHelper dbHelper;
    public static final String TABLE_CANDIDATO = "candidato";

    public CandidatoHelper(Context context) {
        dbHelper = new DbHelper(context);
    }

    public List<Candidato> getList(int idCategoria) {
        List<Candidato> list = new ArrayList<>(0);
        Cursor cursor = dbHelper.getReadableDatabase()
                .rawQuery("SELECT * FROM candidato WHERE idCategoria = " + idCategoria + " ORDER BY nome ASC", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int ID = cursor.getInt(cursor.getColumnIndex("id"));
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                String partido = cursor.getString(cursor.getColumnIndex("partido"));
                list.add(new Candidato(ID, nome, partido));
                cursor.moveToNext();
            }
        }
        return list;
    }

    public void add() {
        insert("Jair Bolsonaro", "PSL", 1);
        insert("Fernando Haddad", "PT",1);
        insert("Ciro Gomes", "PDT", 1);
        insert("Geraldo Alckmin", "PSBD", 1);
        //
        insert("Ratinho JR", "PSD",2);
        insert("Cida", "PP", 2);
        insert("João Arruda", "MDB", 2);

        insert("Doria","PSDB",3);
        insert("Márcio França","PSB",3);

        insert("Oriovisto","PODE",4);
        insert("Beto Richa","PSDB",4);
        insert("Requião","MDB",4);
    }

    private void insert(String nome, String partido, int idCategoria) {
        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("partido", partido);
        values.put("idCategoria", idCategoria);
        dbHelper.getWritableDatabase().insert(TABLE_CANDIDATO, null, values);
    }

    public int count() {
        Cursor mCount = dbHelper.getReadableDatabase()
                .rawQuery("select count(*) from " + TABLE_CANDIDATO, null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        mCount.close();
        return count;
    }
}
