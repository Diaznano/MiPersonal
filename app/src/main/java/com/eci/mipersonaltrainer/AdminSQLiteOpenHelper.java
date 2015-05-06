package com.eci.mipersonaltrainer;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GimnasioDB";
    public AdminSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table
        String CREATE_USUARIO_TABLE = "CREATE TABLE usuarios( " +
                "nombre TEXT, "+
                "dni INTEGER PRIMARY KEY, "+
                "fechaNac BLOB, "+
                "peso REAL, "+
                "altura INTEGER, "+
                "email TEXT )";

        // create usuarios table
        db.execSQL(CREATE_USUARIO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS usuarios");

        // create fresh usuarios table
        this.onCreate(db);
    }


}
