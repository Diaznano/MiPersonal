package com.eci.mipersonaltrainer;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;


public class UsuarioYaRegistradoActivity extends AppCompatActivity {


    private EditText etDni,etN,etA,etP,etF,etE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_ya_registrado);
        etDni = (EditText)findViewById(R.id.etDni);
        etN = (EditText)findViewById(R.id.etNom);
        etA = (EditText)findViewById(R.id.etAltura);
        etP = (EditText)findViewById(R.id.etPeso);
        etF = (EditText)findViewById(R.id.etFecha);
        etE = (EditText)findViewById(R.id.etEmail);
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
        String dni = etDni.getText().toString();
        Cursor fila = bd.rawQuery(
                "select nombre,fechaNac,peso,altura,email from usuarios where dni=" + dni, null);
        if (fila.moveToFirst()) {
            etN.setText(fila.getString(0));
            etF.setText(fila.getString(1));
            etP.setText(fila.getString(2));
            etA.setText(fila.getString(3));
            etE.setText(fila.getString(4));
        } else
            Toast.makeText(this, "No existe un usuario con dicho DNI",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

}
