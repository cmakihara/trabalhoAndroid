package com.aula.mobile.aula.sqlite.pesquisaeleitoral.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.aula.mobile.aula.sqlite.pesquisaeleitoral.entity.Voto;

import java.util.ArrayList;
import java.util.List;

public class VotoHelper {

    private DbHelper dbHelper;
    public static final String TABLE_VOTO = "urna";

    public VotoHelper(Context context) {
        dbHelper = new DbHelper(context);
    }

    public List<Voto> getList(int idCategoria) {
        List<Voto> list = new ArrayList<>(0);
        Cursor cursor = dbHelper.getReadableDatabase()
                .rawQuery("SELECT * FROM urna WHERE idCategoria ="+ idCategoria , null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int ID = cursor.getInt(cursor.getColumnIndex("id"));
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                String categoria = cursor.getString(cursor.getColumnIndex("categoria"));
                int idCategoriaVoto = cursor.getInt(cursor.getColumnIndex("idCategoria"));
                String estado = cursor.getString(cursor.getColumnIndex("estado"));
                int voto = cursor.getInt(cursor.getColumnIndex("voto"));
                list.add(new Voto(ID,nome,categoria, idCategoria, estado, voto));
                cursor.moveToNext();
            }
        }
        return list;
    }

    public void add() {
        insert("Ciro Gomes","Presidente",1,"BR",0);
        insert("Fernando Haddad","Presidente",1,"BR",0);
        insert("Geraldo Alckmin","Presidente",1,"BR",0);
        insert("Jair Bolsonaro","Presidente",1,"BR",0);

        insert("Cida","Governador",2,"PR",0);
        insert("João Arruda","Governador",2,"PR",0);
        insert("Ratinho JR","Governador",2,"PR",0);

        insert("Doria","Governador",3,"SP",0);
        insert("Márcio  ","Governador",3,"SP",0);

        insert("Beto Richa","Senador",4,"PR",0);
        insert("Oriovisto","Senador",4,"PR",0);
        insert("Requião","Senador",4,"PR",0);
    }

    private void insert(String nome,String categoria, int idCategoria, String estado, int voto) {
        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("categoria", categoria);
        values.put("idCategoria", idCategoria);
        values.put("estado", estado);
        values.put("voto", voto);
        dbHelper.getWritableDatabase().insert(TABLE_VOTO, null, values);
    }

    public int count() {
        Cursor mCount = dbHelper.getReadableDatabase()
                .rawQuery("select count(*) from " + TABLE_VOTO, null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        mCount.close();
        return count;
    }

    public void votar(String nome) {
        ContentValues values = new ContentValues();
        values.put("voto" , 5);
        dbHelper.getWritableDatabase().update("urna",values,"nome = '"+nome+"'", null);

    }
}
