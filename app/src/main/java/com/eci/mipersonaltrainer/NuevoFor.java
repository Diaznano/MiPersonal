package com.eci.mipersonaltrainer;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import roboguice.activity.RoboActivity;


public class NuevoFor extends RoboActivity {
    private Integer[] altura = new Integer[150], peso= new Integer[400], edad = new Integer[80];
    private String[] objetivos = {"Deportes", "Salud", "Fitness"};
    private TextView tvEdad,tvTitulo, tvAltura, tvPeso, tvDesde1, tvDesde2, tvDesde3, tvHasta1, tvHasta2, tvHasta3;
    private info.hoang8f.widget.FButton bGuardar, bVolver;
    private Spinner lvEdadDesde, lvEdadHasta, lvPesoDesde, lvPesoHasta, lvAlturaDesde, lvAlturaHasta, lvObjetivos;


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
        tvEdad = (TextView) findViewById(R.id.tvEdad);
        tvTitulo = (TextView) findViewById(R.id.tvTitulo);
        tvAltura = (TextView) findViewById(R.id.tvAltura);
        tvPeso = (TextView) findViewById(R.id.tvPeso);
        tvDesde1 = (TextView) findViewById(R.id.tvDesde1);
        tvDesde2 = (TextView) findViewById(R.id.tvDesde2);
        tvDesde3 = (TextView) findViewById(R.id.tvDesde3);
        tvHasta1 = (TextView) findViewById(R.id.tvHasta1);
        tvHasta2 = (TextView) findViewById(R.id.tvHasta2);
        tvHasta3 = (TextView) findViewById(R.id.tvHasta3);
        lvEdadDesde = (Spinner) findViewById(R.id.lvEdadDesde);
        lvEdadHasta = (Spinner) findViewById(R.id.lvEdadHasta);
        lvAlturaDesde = (Spinner) findViewById(R.id.lvAlturaDesde);
        lvAlturaHasta = (Spinner) findViewById(R.id.lvAlturaHasta);
        lvPesoDesde = (Spinner) findViewById(R.id.lvPesoDesde);
        lvPesoHasta = (Spinner) findViewById(R.id.lvPesoHasta);
        lvObjetivos = (Spinner) findViewById(R.id.lvObjetivos);

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

    public void buscador(View v){
        controlar(lvEdadDesde, lvEdadHasta);
        controlar(lvPesoDesde, lvPesoHasta);
        controlar(lvAlturaDesde, lvAlturaHasta);
        Toast.makeText(this, "Putazo", Toast.LENGTH_SHORT).show();
    }

    public void controlar(final Spinner xD, final Spinner xH){
        final String hD = xD.getSelectedItem().toString();
        xH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            int hH = Integer.valueOf(adapterView.getItemAtPosition(position).toString());
            if(Integer.valueOf(hD) > hH){
                Toast.makeText(adapterView.getContext(), "Seleccione un nro mayor a "+ hD,Toast.LENGTH_SHORT).show();
                xH.setFocusable(true);
                xH.requestFocus();
                xH.performClick();
            }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void guardar(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Bundle bundle = getIntent().getExtras();
        int x = bundle.getInt("band");
        boolean a=true,b=true,c=true;
        controlar(lvEdadDesde, lvEdadHasta);
        controlar(lvPesoDesde,lvPesoHasta);
        controlar(lvAlturaDesde,lvAlturaHasta);

        ContentValues registro = new ContentValues();
        if(x == 0){
            /*String objetivos = lvObjetivos.getSelectedItem().toString();
            registro.put("objetivos", objetivos);
            bd.insert("rutinas", null, registro);
            bd.close();*/
            Toast.makeText(this,"Guardado Exitoso",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, OpcionesPanel.class);
            startActivity(i);
        }
        if(x == 1){
            int edadD = Integer.valueOf(lvEdadDesde.getSelectedItem().toString());
            int edadH = Integer.valueOf(lvEdadHasta.getSelectedItem().toString());
            int pesoD = Integer.valueOf(lvPesoDesde.getSelectedItem().toString());
            int pesoH = Integer.valueOf(lvPesoHasta.getSelectedItem().toString());
            int alturaD = Integer.valueOf(lvAlturaDesde.getSelectedItem().toString());
            int alturaH = Integer.valueOf(lvAlturaHasta.getSelectedItem().toString());
            String objetivos = lvObjetivos.getSelectedItem().toString();

            if(edadD > edadH)
                a=false;
            if(pesoD > pesoH)
                b=false;
            if(alturaD > alturaH)
                c=false;
            if(a && b && c){
                /*registro.put("edadD",edadD);
                registro.put("edadH",edadH);
                registro.put("alturaD",alturaD);
                registro.put("alhurah",alturaH);
                registro.put("pesoD",pesoD);
                registro.put("pesoD",pesoH);
                registro.put("objetivos",objetivos);
                bd.insert("dietas", null, registro);
                bd.close();*/
                Toast.makeText(this,"Guardado Exitoso",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, OpcionesPanel.class);
                startActivity(i);
            }else Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }

    }
}
