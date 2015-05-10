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
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    //VALIDAR FORMATO dd/mm/yyyy

    public static boolean validarFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
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
        boolean b=true,c=true,d=true,e=true,f=true,g=true,h=true;
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
                || peso.length() == 0 || altura.length() == 0) {
            Toast.makeText(this, "Debe completrar los campos faltantes", Toast.LENGTH_SHORT).show();
            if (nombre.length() == 0) {
                etNomApe.setHint("Completar NOMBRE");
                e=false;
            }
            if (dni.length() == 0) {
                etDni.setHint("Completar DNI");
                d=false;
            }
            if (email.length() == 0) {
                etEmail.setHint("Completar E-MAIL");
                c=false;
            }
            if (fecha.length() == 0) {
                etFecha.setHint("Completar FECHA");
                f=false;
            }
            if (peso.length() == 0) {
                etPeso.setHint("Completar PESO");
                h=false;
            }
            if (altura.length() == 0) {
                etAltura.setHint("Completar ALTURA");
                g=false;
            }
            b=false;
            }
        if (b==true){
            if (nombre.matches("[a-z A-Z]*") && e==true) {
            } else {
                etNomApe.setText("");
                etNomApe.setHint("Nombre invalido");
                e = false;
            }
            if (dni.matches("[0-9]*") && (dni.length() < 10 && dni.length() > 6) && d == true) {
            } else {                                                                  //Validacion de DNI
                etDni.setHint("DNI Invalido");
                etDni.setText("");
                d = false;
            }
            if (email.matches("[a-zA-Z0-9._-]*@[a-z]*.[a-z]*+") && email.length() > 0 && c == true) {
            } else {                                                                 //Validacion de E-Mail
                etEmail.setHint("E-MAIL invalido");
                etEmail.setText("");
                c = false;
            }
            if (validarFecha(fecha) && f == true) {
            } else {                                                                  //Validacion formato fecha
                etFecha.setText("");
                etFecha.setHint("Fecha Invalida");
                f = false;
            }
            if ((altura.length() == 3 || altura.length() == 2) && altura.matches("[0-9]*") && g == true) {
              } else {                                                                  //Validacion Altura
                etAltura.setText("");
                etAltura.setHint("Altura Invalida");
                g = false;
            }
            if ((peso.length() == 3 || peso.length() == 2) && peso.matches("[0-9]*") && h == true) {
            } else {                                                                  //Validacion Peso
                etPeso.setText("");
                etPeso.setHint("Peso Invalido");
                h = false;
            }    }


        if (b==true && c==true && d==true && e==true && f==true && g==true && h==true){
        try
        {
            etNomApe.setText("");
            etDni.setText("");
            etFecha.setText("");
            etPeso.setText("");
            etAltura.setText("");
            etEmail.setText("");
            etNomApe.setHint("Nombre y Apellido");
            etEmail.setHint("Email");
            etPeso.setHint("Peso");
            etAltura.setHint("Altura");
            etFecha.setHint("Fecha de Nacimiento (Ej. 10/06/1987)");
            etDni.setHint("DNI (Sin .)");
            /*tvAltura.setText("");
            tvPeso.setText("");
            tvEmail.setText("");
            tvDni.setText("");
            tvFecha.setText("");
            tvNom.setText("");*/
            Intent i = new Intent(this, ObjetivosActivity.class );
            startActivity(i);
            Toast.makeText(this, "Operación exitosa",Toast.LENGTH_SHORT).show();
    } catch(Exception ex){
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }
        }
    }

}
