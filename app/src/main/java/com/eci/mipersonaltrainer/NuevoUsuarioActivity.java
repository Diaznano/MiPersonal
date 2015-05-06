package com.eci.mipersonaltrainer;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NuevoUsuarioActivity extends AppCompatActivity {

    private EditText etNomApe, etAltura, etPeso, etEmail, etFecha, etDni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);
        etNomApe = (EditText)findViewById(R.id.etNom);
        etAltura = (EditText)findViewById(R.id.etAltura);
        etPeso = (EditText)findViewById(R.id.etPeso);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etFecha = (EditText)findViewById(R.id.etFecha);
        etDni = (EditText)findViewById(R.id.etDni);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nuevo_usuario, menu);
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

    public void alta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombre = etNomApe.getText().toString();
        String dni = etDni.getText().toString();
        String email = etEmail.getText().toString();
        String fecha = etFecha.getText().toString();
        String peso = etPeso.getText().toString();
        String altura = etAltura.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("dni", dni);
        registro.put("fechaNac", fecha);
        registro.put("peso", peso);
        registro.put("altura", altura);
        registro.put("email", email);
        bd.insert("usuarios", null, registro);
        bd.close();
        etNomApe.setText("");
        etDni.setText("");
        etFecha.setText("");
        etPeso.setText("");
        etAltura.setText("");
        etEmail.setText("");
        Toast.makeText(this, "Operación exitosa",Toast.LENGTH_SHORT).show();
        try
        {
            Intent i = new Intent(this, ObjetivosActivity.class );
            startActivity(i);
    } catch(Exception ex){
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
        }
    }
    public void abreVistaObjetivos(View view){
        Intent i = new Intent(this, ObjetivosActivity.class );
        startActivity(i);
    }
    public void onClik(View view){
        alta(view);
        abreVistaObjetivos(view);
    }
}
