package com.eci.mipersonaltrainer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class ImpresionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impresion);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_impresion, menu);
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
    public void mensajeRutina(View view){
        Toast t1 = Toast.makeText(this,"Imprimiendo rutina.. dirigirse a recepción",Toast.LENGTH_LONG);
        t1.show();
        view.setEnabled(false);
    }
    public void mensajeDieta(View view){
        Toast t2 = Toast.makeText(this,"Imprimiendo dieta.. dirigirse a recepción",Toast.LENGTH_LONG);
        t2.show();
        view.setEnabled(false);
    }
}
