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


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
            SQLiteDatabase db = admin.getWritableDatabase();
            ContentValues registro = new ContentValues();
            Intent i = new Intent(this, Sesion.class);
            int x = 1;
            i.putExtra("band",x);
            registro.put("usuario", "nano");
            registro.put("contrasena", "123");
            db.insert("admin", null, registro);
            db.close();
            startActivity(i);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public void abreVistaNuevoUsuario (View view){
        Intent i = new Intent(this, NuevoUsuarioActivity.class );
        startActivity(i);
    }
    public void abreVistaUsuarioYaRegistrado (View view){
        Intent i = new Intent(this, UsuarioYaRegistradoActivity.class );
        startActivity(i);
    }
}
