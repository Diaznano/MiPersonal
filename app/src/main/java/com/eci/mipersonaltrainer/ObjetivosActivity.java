package com.eci.mipersonaltrainer;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class ObjetivosActivity extends AppCompatActivity {
    private info.hoang8f.widget.FButton bCambiar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objetivos);
        bCambiar = (info.hoang8f.widget.FButton) findViewById(R.id.bCambiarContra);
    }


    public void abreVistaImpresion(View view){
        Intent intent = new Intent(this, ImpresionActivity.class);
        startActivity(intent);
    }



    public void Salud(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        Bundle bundle = getIntent().getExtras();


        registro.put("nombre",bundle.getString("nombre"));
        registro.put("dni",bundle.getString("dni"));
        registro.put("fechaNac",bundle.getString("fechaNac"));
        registro.put("peso",bundle.getString("peso"));
        registro.put("altura",bundle.getString("peso"));
        registro.put("email",bundle.getString("email"));
        bd.insert("usuarios", null, registro);
        bd.close();

        Toast.makeText(this, "Operación exitosa", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ImpresionActivity.class);
        startActivity(intent);
    }
}
