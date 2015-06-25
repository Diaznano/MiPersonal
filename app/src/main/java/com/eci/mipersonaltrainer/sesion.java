package com.eci.mipersonaltrainer;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import roboguice.activity.RoboActivity;


public class Sesion extends RoboActivity {

    private com.beardedhen.androidbootstrap.BootstrapEditText etUsuario,etContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);
        etUsuario = (com.beardedhen.androidbootstrap.BootstrapEditText) findViewById(R.id.etUsuario);
        etContraseña = (com.beardedhen.androidbootstrap.BootstrapEditText) findViewById(R.id.etContraseña);


    }

    public void validad(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String usuario = etUsuario.getText().toString();
        String contraseña = etContraseña.getText().toString();

        try {
            Cursor fila = bd.rawQuery(
                    "select contrasena from admin where usuario=" + usuario, null);
            if (contraseña.equals(fila.getString(0))) {
                Intent i = new Intent(this, OpcionesPanel.class);
                i.putExtra("usuario",usuario);
                startActivity(i);
            } else Toast.makeText(this, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
        }catch(Exception ex){
            Toast.makeText(this,"Error debe ingresar un usuario",Toast.LENGTH_SHORT).show();}

    }

}

