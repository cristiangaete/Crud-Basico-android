package com.example.cristian.crud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Cristian on 28/10/2017.
 */

public class dbHelper extends SQLiteOpenHelper {

    String consulta2 = "CREATE TABLE tbl_datos (id INTEGER  PRIMARY KEY AUTOINCREMENT, monto INTEGER, descrip TEXT)";
    public dbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(consulta2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
