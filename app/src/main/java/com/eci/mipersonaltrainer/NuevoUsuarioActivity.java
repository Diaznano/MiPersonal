package com.eci.mipersonaltrainer;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

@ContentView(R.layout.activity_nuevo_usuario)

public class NuevoUsuarioActivity extends RoboActivity {

    private com.beardedhen.androidbootstrap.BootstrapEditText etNomApe, etAltura, etPeso, etEmail, etFecha, etDni;
    private String evalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        etNomApe = (com.beardedhen.androidbootstrap.BootstrapEditText) findViewById(R.id.etNom);
        etAltura = (com.beardedhen.androidbootstrap.BootstrapEditText) findViewById(R.id.etAltura);
        etPeso = (com.beardedhen.androidbootstrap.BootstrapEditText) findViewById(R.id.etContraseña);
        etEmail = (com.beardedhen.androidbootstrap.BootstrapEditText) findViewById(R.id.etEmail);
        etFecha = (com.beardedhen.androidbootstrap.BootstrapEditText) findViewById(R.id.etFecha);
        etDni = (com.beardedhen.androidbootstrap.BootstrapEditText) findViewById(R.id.etDni);


    }


    //VALIDAR FORMATO dd/mm/yyyy

    public static boolean validacion(String fecha) {

        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String hoy = formato.format(fechaActual);
        String[] dat1 = fecha.split("/");
        String[] dat2 = hoy.split("/");
        int ano = Integer.parseInt(dat2[2]) - Integer.parseInt(dat1[2]);
        return Integer.parseInt(dat2[2]) > Integer.parseInt(dat1[2]);
    }

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

    public void focus(final com.beardedhen.androidbootstrap.BootstrapEditText e){
        e.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){e.setDefault();
                    if(e == etNomApe)e.setHint("Nombre y Apellido");
                    if(e == etAltura)e.setHint("Altura");
                    if(e == etEmail)e.setHint("Email");
                    if(e == etPeso)e.setHint("Peso");
                    if(e == etDni)e.setHint("DNI (Sin .)");
                    if(e == etFecha)e.setHint("Fecha de Nacimiento (Ej. 20/06/1987)");
                }
            }
        });
    }
    public void alta(View v) {
        boolean b = true, c = true, d = true, e = true, f = true, g = true, h = true;
        String nombre = etNomApe.getText().toString();
        String dni = etDni.getText().toString();
        String email = etEmail.getText().toString();
        String fecha = etFecha.getText().toString();
        // int a = edad(fecha); CALCULO EDAD
        String peso = etPeso.getText().toString();
        String altura = etAltura.getText().toString();


        if (nombre.length() == 0 || dni.length() == 0 || fecha.length() == 0
                || peso.length() == 0 || altura.length() == 0) {
            Toast.makeText(this, "Debe completrar los campos faltantes", Toast.LENGTH_SHORT).show();
        }

        focus(etNomApe);
        focus(etPeso);
        focus(etAltura);
        focus(etDni);
        focus(etEmail);
        focus(etFecha);
            if (nombre.length() == 0) {
                etNomApe.setDanger();
                etNomApe.setHint("Completar NOMBRE");
                e = false;
            } else {
                if ((nombre.matches("[a-z A-Z]*") && e) && !nombre.matches("[ ]+")) {
                    etNomApe.setDefault();
                } else {
                    etNomApe.setText("");
                    etNomApe.setDanger();
                    etNomApe.setHint("Nombre invalido");
                    e = false;
                }
            }

        if (dni.length() == 0) {
            etDni.setDanger();
            etDni.setHint("Completar DNI");
            d = false;
        } else {
            if (!dni.matches("[0-9]*") || (dni.length() >= 10 || dni.length() <= 6) || !d) {                                                                  //Validacion de DNI
                etDni.setHint("DNI Invalido");
                etDni.setDanger();
                etDni.setText("");
                d = false;
            } else {
                etDni.setDefault();
            }
        }

        if (!email.matches("[a-zA-Z0-9._-]*@[a-z]*+[.][a-z]*+") || email.length() <= 0 || !c) {                                                                 //Validacion de E-Mail
                etEmail.setHint("E-MAIL invalido");
                etEmail.setDanger();
                etEmail.setText("");
                c = false;
        } else
                etEmail.setDefault();


        if (fecha.length() == 0) {
            etFecha.setDanger();
            etFecha.setHint("Completar FECHA");
            f = false;
        } else {
            if (!validarFecha(fecha) || !f || !validacion(fecha)) {                                                                  //Validacion formato fecha
                etFecha.setText("");
                etFecha.setDanger();
                etFecha.setHint("Fecha Invalida");
                f = false;
            } else
                etFecha.setDefault();
        }

        if (peso.length() == 0) {
            etPeso.setDanger();
            etPeso.setHint("Completar PESO");
            h = false;
        } else {
            if ((peso.length() != 3 && peso.length() != 2) || !peso.matches("[0-9]*") || (Integer.parseInt(peso) > 600) || !h) {                                                                  //Validacion Peso

                etPeso.setText("");
                etPeso.setDanger();
                etPeso.setHint("Peso Invalido");
                h = false;
            } else
                etPeso.setDefault();
        }

        if (altura.length() == 0) {
            etAltura.setDanger();
            etAltura.setHint("Completar ALTURA");
            g = false;
        } else {
            if ((altura.length() != 3 && altura.length() != 2) || !altura.matches("[0-9]*") ||(Integer.parseInt(altura) > 270) || !g) {                                                                  //Validacion Altura
                etAltura.setText("");
                etAltura.setDanger();
                etAltura.setHint("Altura Invalida");
                g = false;
            } else
                etAltura.setDefault();
        }

        b = false;


        if (c && d && e && f && g && h) {
                           Intent i = new Intent(this, ObjetivosActivity.class);
                i.putExtra("nombre", nombre);
                i.putExtra("dni", dni);
                i.putExtra("fechaNac", fecha);
                i.putExtra("peso", peso);
                i.putExtra("altura", altura);
                i.putExtra("email", email);
                startActivity(i);
           }else Toast.makeText(this,"EL Dni Ingresado ya existe", Toast.LENGTH_SHORT).show();
        }
    }




