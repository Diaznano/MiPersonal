package com.eci.mipersonaltrainer;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_usuario_ya_registrado)
public class UsuarioYaRegistradoActivity extends RoboActivity {


    @InjectView(R.id.etDni) EditText eDni;
    @InjectView(R.id.etNom) EditText eNom;
    @InjectView(R.id.etAltura) EditText eAlt;
    @InjectView(R.id.etPeso) EditText ePeso;
    @InjectView(R.id.etFecha) EditText eFecha;
    @InjectView(R.id.etEmail) EditText eEmail;
    @InjectView(R.id.tvKg) TextView tvKg;
    @InjectView(R.id.tvCm) TextView tvCm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_usuario_ya_registrado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void consultaporcodigo(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String dni = eDni.getText().toString();
        Cursor fila = bd.rawQuery(
                "select nombre,fechaNac,peso,altura,email from usuarios where dni=" + dni, null);
        if (fila.moveToFirst()) {
            eNom.setText(fila.getString(0));
            eFecha.setText(fila.getString(1));
            ePeso.setText(fila.getString(2));
            eAlt.setText(fila.getString(3));
            eEmail.setText(fila.getString(4));
            tvCm.setVisibility(View.VISIBLE);
            tvKg.setVisibility(View.VISIBLE);
        } else
            Toast.makeText(this, "No existe un usuario con dicho DNI",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }
    public void modificacion(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String dni = eDni.getText().toString();
        String nom = eNom.getText().toString();
        String fecha = eFecha.getText().toString();
        String peso = ePeso.getText().toString();
        String alt = eAlt.getText().toString();
        String email = eEmail.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("nombre", nom);
        registro.put("dni", dni);
        registro.put("fechaNac", fecha);
        registro.put("peso", peso);
        registro.put("altura", alt);
        registro.put("email", email);
        int cant = bd.update("usuarios", registro, "dni=" + dni, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "Se modificaron los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "Surgió un error",
                    Toast.LENGTH_SHORT).show();
    }

}


