package com.aula.mobile.aula.sqlite.pesquisaeleitoral.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aula.mobile.aula.R;


public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "eleicao.db";
    private Context mContext;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(mContext.getString(R.string.sql_create_table_cand));
        db.execSQL(mContext.getString(R.string.sql_create_tables_cat));
        db.execSQL(mContext.getString(R.string.sql_create_tables_urna));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
