package com.eci.mipersonaltrainer;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import roboguice.activity.RoboActivity;


public class CambioContra extends RoboActivity {
    private com.beardedhen.androidbootstrap.BootstrapEditText etContraRepetir, etContraActual, etContraNueva;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambio_contra);
        etContraActual = (com.beardedhen.androidbootstrap.BootstrapEditText) findViewById(R.id.etContraActual);
        etContraNueva = (com.beardedhen.androidbootstrap.BootstrapEditText) findViewById(R.id.etContraNueva);
        etContraRepetir = (com.beardedhen.androidbootstrap.BootstrapEditText) findViewById(R.id.etContraReptir);

    }



    public void confirmar(View v){
        Bundle bundle = getIntent().getExtras();
        String x = bundle.getString("usuario");
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String contraNueva = etContraNueva.getText().toString();
        String contraActual = etContraActual.getText().toString();
        String contraRepetir = etContraRepetir.getText().toString();
        ContentValues registro = new ContentValues();
        boolean b=true,c=true;

        try {
            Cursor fila = bd.rawQuery(
                    "select contrasena from admin where usuario=" + x, null);
            if(!contraActual.equals(1)){
                b=false;
                Toast.makeText(this, "Contraseña Incorrecta",Toast.LENGTH_SHORT).show();}

            if(!contraNueva.equals(contraRepetir)){
                c=false;
                Toast.makeText(this, "Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
            }

            if(c && b){
                registro.put("contrasena", contraRepetir);
                int cant = bd.update("admin", registro, "usuario=" + x, null);
                bd.close();
                if (cant == 1) {
                    Toast.makeText(this, "Se modificaron los datos", Toast.LENGTH_SHORT)
                            .show();
                    Intent i = new Intent(this, MainActivity.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(this, "Surgió un error",
                            Toast.LENGTH_SHORT).show();

            }
        }catch(Exception ex){
            Toast.makeText(this,"Error debe ingresar una contraseña",Toast.LENGTH_SHORT).show();}
    }

    public void atras(View v){
        Intent i = new Intent(this, OpcionesPanel.class);
        startActivity(i);

    }
}

