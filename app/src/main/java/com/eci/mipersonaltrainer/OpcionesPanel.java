package com.eci.mipersonaltrainer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class OpcionesPanel extends AppCompatActivity {

    private info.hoang8f.widget.FButton bNuevaRutina, bNuevaDieta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_panel);
        bNuevaRutina = (info.hoang8f.widget.FButton) findViewById(R.id.bNuevaRutina);
        bNuevaDieta = (info.hoang8f.widget.FButton) findViewById(R.id.bNuevaDieta);
    }

    public void cambiar(View v){
        Bundle bundle = getIntent().getExtras();
        String x = bundle.getString("usuario");
        Intent i = new Intent(this, CambioContra.class);
        i.putExtra("usuario", x);
        startActivity(i);
    }

    public void nuevaRutina(View v){
        Intent i = new Intent(this, NuevoFor.class);
        int x=0;
        i.putExtra("band", x);
        startActivity(i);
    }

    public void nuevaDieta(View v){
        Intent i = new Intent(this, NuevoFor.class);
        int x=1;
        i.putExtra("band", x);
        startActivity(i);
    }

    public void inicio(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
