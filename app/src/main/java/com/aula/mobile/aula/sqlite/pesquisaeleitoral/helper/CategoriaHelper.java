package com.aula.mobile.aula.sqlite.pesquisaeleitoral.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.aula.mobile.aula.sqlite.pesquisaeleitoral.helper.DbHelper;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.entity.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaHelper {

    private DbHelper dbHelper;
    public static final String TABLE_CATEGORIA = "categoria";

    public CategoriaHelper(Context context) {
        dbHelper = new DbHelper(context);
    }

    public List<Categoria> getList() {
        List<Categoria> list = new ArrayList<>(0);
        Cursor cursor = dbHelper.getReadableDatabase()
                .rawQuery("SELECT * FROM categoria", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int ID = cursor.getInt(cursor.getColumnIndex("id"));
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                String estado = cursor.getString(cursor.getColumnIndex("estado"));
                list.add(new Categoria(ID,nome,estado));
                cursor.moveToNext();
            }
        }
        return list;
    }

    public void add() {
       insert("Presidente","BR");
       insert("Governador","PR");
       insert("Governador","SP");
       insert("Senador","PR");
    }

    private void insert(String nome, String estado) {
        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("estado", estado);
        dbHelper.getWritableDatabase().insert(TABLE_CATEGORIA, null, values);
    }

    public int count() {
        Cursor mCount = dbHelper.getReadableDatabase()
                .rawQuery("select count(*) from " + TABLE_CATEGORIA, null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        mCount.close();
        return count;
    }
}
