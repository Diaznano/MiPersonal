package com.eci.mipersonaltrainer;


import android.content.ContentValues;
import android.content.Intent;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_usuario_ya_registrado)
public class UsuarioYaRegistradoActivity extends RoboActivity {
    private com.beardedhen.androidbootstrap.BootstrapEditText eDni,eNom,eAlt,ePeso,eFecha,eEmail;
    private info.hoang8f.widget.FButton boton,botonC;
    @InjectView(R.id.tvKg) TextView tvKg;
    @InjectView(R.id.tvCm) TextView tvCm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boton = (info.hoang8f.widget.FButton)findViewById(R.id.bGuardarCambios);
        botonC = (info.hoang8f.widget.FButton)findViewById(R.id.bContinuar);
        eDni = (com.beardedhen.androidbootstrap.BootstrapEditText)findViewById(R.id.etDni);
        eNom = (com.beardedhen.androidbootstrap.BootstrapEditText)findViewById(R.id.etNom);
        eAlt = (com.beardedhen.androidbootstrap.BootstrapEditText)findViewById(R.id.etAltura);
        ePeso = (com.beardedhen.androidbootstrap.BootstrapEditText)findViewById(R.id.etPeso);
        eFecha = (com.beardedhen.androidbootstrap.BootstrapEditText)findViewById(R.id.etFecha);
        eEmail = (com.beardedhen.androidbootstrap.BootstrapEditText)findViewById(R.id.etEmail);
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

        modificar(eNom, this);
        modificar(eAlt, this);
        modificar(ePeso, this);
        modificar(eEmail, this);
        modificar(eFecha, this);

        try{
        Cursor fila = bd.rawQuery(
                "select nombre,fechaNac,peso,altura,email from usuarios where dni=" + dni, null);
        if (fila.moveToFirst()) {
            eNom.setText(fila.getString(0));
            eFecha.setText(fila.getString(1));
            ePeso.setText(fila.getString(2));
            eAlt.setText(fila.getString(3));
            eEmail.setText(fila.getString(4));
            botonC.setEnabled(true);
            tvCm.setVisibility(View.VISIBLE);
            tvKg.setVisibility(View.VISIBLE);
            eNom.setVisibility(View.VISIBLE);
            eFecha.setVisibility(View.VISIBLE);
            ePeso.setVisibility(View.VISIBLE);
            eAlt.setVisibility(View.VISIBLE);
            eEmail.setVisibility(View.VISIBLE);

        } else{
            Toast.makeText(this, "No existe un usuario con dicho DNI",
                    Toast.LENGTH_SHORT).show();
            eNom.setText("");
            eFecha.setText("");
            ePeso.setText("");
            eAlt.setText("");
            eEmail.setText("");
            eNom.setVisibility(View.INVISIBLE);
            eFecha.setVisibility(View.INVISIBLE);
            ePeso.setVisibility(View.INVISIBLE);
            eAlt.setVisibility(View.INVISIBLE);
            eEmail.setVisibility(View.INVISIBLE);
            tvCm.setVisibility(View.INVISIBLE);
            tvKg.setVisibility(View.INVISIBLE);
            botonC.setEnabled(false);
        }
        bd.close();
        }catch(Exception ex){
            Toast.makeText(this,"Error debe ingresar un DNI",Toast.LENGTH_SHORT).show();
        }

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
    public static boolean validacion(String fecha) {

        Date fechaActual = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String hoy = formato.format(fechaActual);
        String[] dat1 = fecha.split("/");
        String[] dat2 = hoy.split("/");
        int ano = Integer.parseInt(dat2[2]) - Integer.parseInt(dat1[2]);
        if (Integer.parseInt(dat2[2]) > Integer.parseInt(dat1[2]))
            return true;
        else
            return false;
    }

    public void modificar(final com.beardedhen.androidbootstrap.BootstrapEditText e, final android.content.Context v){
        e.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (b) {
                    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(v);
                    SQLiteDatabase bd = admin.getWritableDatabase();
                    String dni = eDni.getText().toString();

                    try {
                        Cursor fila = bd.rawQuery(
                                "select nombre,fechaNac,peso,altura,email from usuarios where dni=" + dni, null);
                        if (fila.moveToFirst()) {

                            if (!eNom.getText().toString().equals(fila.getString(0)) ||
                                    !eFecha.getText().toString().equals(fila.getString(1)) ||
                                    !ePeso.getText().toString().equals(fila.getString(2)) ||
                                    !eAlt.getText().toString().equals(fila.getString(3)) ||
                                    !eEmail.getText().toString().equals(fila.getString(4))){
                                    boton.setEnabled(true);
                                    botonC.setEnabled(false);
                            }
                                else {
                                boton.setEnabled(false);
                                botonC.setEnabled(true);
                                    }
                            }
                        bd.close();
                    } catch (Exception ex) {
                        //.
                    }
                }
            }
        });
    }

    public void focus(final com.beardedhen.androidbootstrap.BootstrapEditText e){
        e.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){e.setDefault();
                    if(e == eNom)e.setHint("Nombre y Apellido");
                    if(e == eAlt){e.setHint("Altura");}
                    if(e == eEmail){e.setHint("Email");}
                    if(e == ePeso){e.setHint("Peso");}
                    if(e == eDni){e.setHint("DNI Sin .");}
                    if(e == eFecha){e.setHint("Fecha de Nacimiento (Ej. 20/06/1987)");}
                }
            }
        });
    }

    public void Continuar(View v){
        if(eDni.length() != 0){
        Intent i = new Intent(this, ImpresionActivity.class);
        startActivity(i);
        }
    }
    public void modificacion(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        boolean c=true,d=true,e=true,f=true,g=true,h=true;
        String dni = eDni.getText().toString();
        String nom = eNom.getText().toString();
        String fecha = eFecha.getText().toString();
        String peso = ePeso.getText().toString();
        String alt = eAlt.getText().toString();
        String email = eEmail.getText().toString();
        ContentValues registro = new ContentValues();

        focus(eDni);
        focus(eNom);
        focus(eFecha);
        focus(ePeso);
        focus(eAlt);
        focus(eEmail);

        if(dni.length() != 0) {
            if (nom.matches("[a-z A-Z]*") && e && !nom.matches("[ ]+")) {
                eNom.setDefault();
            } else {
                eNom.setText("");
                eNom.setDanger();
                eNom.setHint("Nombre invalido");
                e = false;
            }

            if (!dni.matches("[0-9]*") || (dni.length() >= 10 || dni.length() <= 6) || !d) {                                                                  //Validacion de DNI
                eDni.setHint("DNI Invalido");
                eDni.setDanger();
                eDni.setText("");
                d = false;
            }else eDni.setDefault();

            if (!email.matches("[a-zA-Z0-9._-]*@[a-z]*.[a-z]*+") || email.length() <= 0 || !c) {                                                                 //Validacion de E-Mail
                eEmail.setHint("E-MAIL invalido");
                eEmail.setDanger();
                eEmail.setText("");
                c = false;
            }else eEmail.setDefault();

            if (!validarFecha(fecha) || !f || !validacion(fecha)) {                                                                  //Validacion formato fecha
                eFecha.setText("");
                eFecha.setDanger();
                eFecha.setHint("Fecha Invalida");
                f = false;
            }else eFecha.setDefault();

            if (Integer.parseInt(alt) > 270 || (alt.length() != 3 && alt.length() != 2) || !alt.matches("[0-9]*") || !g) {                                                                  //Validacion Altura
                eAlt.setText("");
                eAlt.setDanger();
                eAlt.setHint("Altura Invalida");
                g = false;
            }else eAlt.setDefault();

            if ((peso.length() != 3 && peso.length() != 2) || !peso.matches("[0-9]*") || Integer.parseInt(peso) > 600 || !h) {                                                                  //Validacion Peso

                ePeso.setText("");
                ePeso.setDanger();
                ePeso.setHint("Peso Invalido");
                h = false;
            }else ePeso.setDefault();
        }else{
            Toast.makeText(this,"Debe ingresar un DNI",Toast.LENGTH_SHORT).show();
        }

    if (c && d && e && f && g && h){
        try
        {   registro.put("nombre", nom);
            registro.put("dni", dni);
            registro.put("fechaNac", fecha);
            registro.put("peso", peso);
            registro.put("altura", alt);
            registro.put("email", email);
            int cant = bd.update("usuarios", registro, "dni=" + dni, null);
            bd.close();
            if (cant == 1) {
                Toast.makeText(this, "Se modificaron los datos", Toast.LENGTH_SHORT)
                        .show();
                Intent i = new Intent(this, ImpresionActivity.class);
                startActivity(i);
            }
            else
                Toast.makeText(this, "Surgió un error",
                        Toast.LENGTH_SHORT).show();
        } catch(Exception ex){
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }
    }
}

}


