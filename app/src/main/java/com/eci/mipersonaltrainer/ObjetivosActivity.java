package com.eci.mipersonaltrainer;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class ObjetivosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objetivos);
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_objetivos, menu);
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
    }*/
    public void abreVistaImpresion(View view){
        Intent intent = new Intent(this, ImpresionActivity.class);
        startActivity(intent);
    }
    public void Salud(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase bd = admin.getWritableDatabase();

        ContentValues registro = new ContentValues();
        Bundle bundle = getIntent().getExtras();


        registro.put("nombre",bundle.getString("nombre"));
        registro.put("dni",bundle.getString("dni"));
        registro.put("fechaNac",bundle.getString("fechaNac"));
        registro.put("peso",bundle.getString("peso"));
        registro.put("altura",bundle.getString("peso"));
        registro.put("email",bundle.getString("email"));
        bd.insert("usuarios", null, registro);
        bd.close();

        Toast.makeText(this, "Operación exitosa", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ImpresionActivity.class);
        startActivity(intent);
    }
}
