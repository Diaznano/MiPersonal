package com.eci.mipersonaltrainer;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GimnasioDB";


    String CREATE_USUARIO_TABLE = "CREATE TABLE usuarios( " +
            "nombre TEXT, "+
            "dni INTEGER PRIMARY KEY, "+
            "fechaNac BLOB, "+
            "peso REAL, "+
            "altura INTEGER, "+
            "email TEXT )";

    // create usuarios table

    String CREATE_ADMIN_TABLE = "CREATE TABLE admin( " +
            "usuario TEXT PRIMARY KEY, "+
            "contrasena TEXT )";

    public AdminSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table



        /*
        String CREATE_RUTINA_TABLE = "CREATE TABLE rutinas( "+
                "objetivo TEXT PRIMARY KEY, "+
                "rutina BLOB )";
        // create rutinas table

        String CREATE_DIETA_TABLE = "CREATE TABLE dietas( "+
                "nroDieta INTEGER PRIMARY KEY, "+
                "edadD INTEGER, "+
                "edadH INTEGER, "+
                "alturaD INTEGER, "+
                "alturaH INTEGER, "+
                "pesoD INTEGER, "+
                "pesoH INTEGER, "+
                "objetivo TEXT, "+
                "dieta BLOB )";


                */

        db.execSQL(CREATE_USUARIO_TABLE);
        db.execSQL(CREATE_ADMIN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS administradores");


        // create fresh usuarios table
        db.execSQL(CREATE_USUARIO_TABLE);
        db.execSQL(CREATE_ADMIN_TABLE);
        onCreate(db);
    }


}
