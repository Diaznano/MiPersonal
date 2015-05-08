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

import java.text.SimpleDateFormat;
import java.util.Date;

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

    // ------- CALCULAR EDAD --------

    /* public int edad(String fecha_nac){
        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String hoy = formato.format(fechaActual);
        String[] dat1 = fecha_nac.split("/");
        String[] dat2 = hoy.split("/");
        int ano = Integer.parseInt(dat2[2]) - Integer.parseInt(dat1[2]);
        int mes = Integer.parseInt(dat2[1]) - Integer.parseInt(dat1[1]);
        if(mes < 0) {
            ano = ano - 1;
        } else if(mes == 0) {
            int dia = Integer.parseInt(dat2[0]) - Integer.parseInt(dat1[0]);
            if(dia > 0)
                ano = ano - 1;
        }
        return ano;
    } */

    public void alta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        boolean n,d,e,f,p,a;
        String nombre = etNomApe.getText().toString();
        String dni = etDni.getText().toString();
        String email = etEmail.getText().toString();
        String fecha = etFecha.getText().toString();
        // int a = edad(fecha); CALCULO EDAD
        String peso = etPeso.getText().toString();
        String altura = etAltura.getText().toString();
        /*ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("dni", dni);
        registro.put("fechaNac", fecha);
        registro.put("peso", peso);
        registro.put("altura", altura);
        registro.put("email", email);
        bd.insert("usuarios", null, registro);
        bd.close();*/ //*********Pasar como parametro el registro al siguiente activity y guardar en la base de datos con el objetivo
        if(nombre.length() == 0 || dni.length() == 0 || email.length() == 0 || fecha.length() == 0
                || peso.length() == 0 || altura.length() == 0){
            Toast.makeText(this,"debe completrar los campos",Toast.LENGTH_SHORT).show();

        }

        try
        {

            etNomApe.setText("");
            etDni.setText("");
            etFecha.setText("");
            etPeso.setText("");
            etAltura.setText("");
            etEmail.setText("");
            Intent i = new Intent(this, ObjetivosActivity.class );
            startActivity(i);
            Toast.makeText(this, "Operación exitosa",Toast.LENGTH_SHORT).show();
    } catch(Exception ex){
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }
    }

}
