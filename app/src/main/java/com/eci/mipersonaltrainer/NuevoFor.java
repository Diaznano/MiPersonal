package com.eci.mipersonaltrainer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import roboguice.activity.RoboActivity;


public class NuevoFor extends RoboActivity {
    private Integer[] altura = new Integer[150], peso= new Integer[400], edad = new Integer[80];
    private String[] objetivos = {"Deportes", "Salud", "Fitness"};
    private info.hoang8f.widget.FButton bGuardar, bVolver;

    public ArrayAdapter llenar(int z){
        int ed=6,al=70,pe=20;
        if(z==0){
        for (int i = 0; i < 150; i++){
            altura[i] = al;
            al++;
        }
            return new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, altura);
        }
        if(z==1){
        for(int i = 0; i < 400; i++){
            peso[i] = pe;
            pe++;
        }
            return new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, peso);
        }
        if(z==2){
        for(int i = 0; i < 80; i++){
            edad[i] = ed;
            ed++;
        }
            return new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, edad);
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_for);
        Bundle bundle = getIntent().getExtras();
        int x = bundle.getInt("band");

        bGuardar = (info.hoang8f.widget.FButton) findViewById(R.id.bGuardarCambios);
        bVolver = (info.hoang8f.widget.FButton) findViewById(R.id.bVolver);
        TextView tvEdad = (TextView) findViewById(R.id.tvEdad);
        TextView tvTitulo = (TextView) findViewById(R.id.tvTitulo);
        TextView tvAltura = (TextView) findViewById(R.id.tvAltura);
        TextView tvPeso = (TextView) findViewById(R.id.tvPeso);
        TextView tvDesde1 = (TextView) findViewById(R.id.tvDesde1);
        TextView tvDesde2 = (TextView) findViewById(R.id.tvDesde2);
        TextView tvDesde3 = (TextView) findViewById(R.id.tvDesde3);
        TextView tvHasta1 = (TextView) findViewById(R.id.tvHasta1);
        TextView tvHasta2 = (TextView) findViewById(R.id.tvHasta2);
        TextView tvHasta3 = (TextView) findViewById(R.id.tvHasta3);
        Spinner lvEdadDesde = (Spinner) findViewById(R.id.lvEdadDesde);
        Spinner lvEdadHasta = (Spinner) findViewById(R.id.lvEdadHasta);
        Spinner lvAlturaDesde = (Spinner) findViewById(R.id.lvAlturaDesde);
        Spinner lvAlturaHasta = (Spinner) findViewById(R.id.lvAlturaHasta);
        Spinner lvPesoDesde = (Spinner) findViewById(R.id.lvPesoDesde);
        Spinner lvPesoHasta = (Spinner) findViewById(R.id.lvPesoHasta);
        Spinner lvObjetivos = (Spinner) findViewById(R.id.lvObjetivos);

        lvEdadDesde.setAdapter(llenar(2));
        lvEdadHasta.setAdapter(llenar(2));
        lvPesoDesde.setAdapter(llenar(1));
        lvPesoHasta.setAdapter(llenar(1));
        lvAlturaDesde.setAdapter(llenar(0));
        lvAlturaHasta.setAdapter(llenar(0));
        lvObjetivos.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, objetivos));

         if(x == 0){
            tvTitulo.setText("Nueva Rutina");
            tvEdad.setVisibility(View.INVISIBLE);
            tvAltura.setVisibility(View.INVISIBLE);
            tvPeso.setVisibility(View.INVISIBLE);
            tvDesde1.setVisibility(View.INVISIBLE);
            tvDesde2.setVisibility(View.INVISIBLE);
            tvDesde3.setVisibility(View.INVISIBLE);
            tvHasta1.setVisibility(View.INVISIBLE);
            tvHasta2.setVisibility(View.INVISIBLE);
            tvHasta3.setVisibility(View.INVISIBLE);
            lvEdadDesde.setVisibility(View.INVISIBLE);
            lvEdadHasta.setVisibility(View.INVISIBLE);
            lvPesoDesde.setVisibility(View.INVISIBLE);
            lvAlturaDesde.setVisibility(View.INVISIBLE);
            lvAlturaHasta.setVisibility(View.INVISIBLE);
            lvPesoHasta.setVisibility(View.INVISIBLE);

        }

        if(x == 1){
            tvTitulo.setText("Nueva Dieta");
            tvEdad.setVisibility(View.VISIBLE);
            tvAltura.setVisibility(View.VISIBLE);
            tvPeso.setVisibility(View.VISIBLE);
            tvDesde1.setVisibility(View.VISIBLE);
            tvDesde2.setVisibility(View.VISIBLE);
            tvDesde3.setVisibility(View.VISIBLE);
            tvHasta1.setVisibility(View.VISIBLE);
            tvHasta2.setVisibility(View.VISIBLE);
            tvHasta3.setVisibility(View.VISIBLE);
            lvEdadDesde.setVisibility(View.VISIBLE);
            lvEdadHasta.setVisibility(View.VISIBLE);
            lvPesoDesde.setVisibility(View.VISIBLE);
            lvAlturaDesde.setVisibility(View.VISIBLE);
            lvAlturaHasta.setVisibility(View.VISIBLE);
            lvPesoHasta.setVisibility(View.VISIBLE);
        }

    }


    public void volver(View v){
        Intent i = new Intent(this,OpcionesPanel.class);
        startActivity(i);
    }

    public void guardar(View v){

    }
}
