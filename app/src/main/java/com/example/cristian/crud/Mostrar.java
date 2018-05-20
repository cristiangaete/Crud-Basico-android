package com.example.cristian.crud;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Mostrar extends AppCompatActivity {

    ListView lst;
    ArrayList<String>listado;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        CargarListado();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        lst = (ListView) findViewById(R.id.lista);
        CargarListado();
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Mostrar.this,listado.get(position),Toast.LENGTH_SHORT).show();
                int clave=Integer.parseInt(listado.get(position).split(" ")[0]);
                int monto = Integer.parseInt(listado.get(position).split(" ")[1]);
                String descrip = listado.get(position).split(" ")[2];
                Intent intent = new Intent(Mostrar.this, Modificar.class );
                intent.putExtra("ID",clave);
                intent.putExtra("Monto",monto);
                intent.putExtra("Descrip",descrip);
                startActivity(intent);
            }


        });

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return  super.onOptionsItemSelected(item);
    }

    private void CargarListado(){
        listado = ListarDatos();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listado);
        lst.setAdapter(adapter);
    }

    private ArrayList<String> ListarDatos(){
        ArrayList<String>datos = new ArrayList<String>();
        dbHelper baseHelper = new dbHelper(this, "demo", null,1);
        SQLiteDatabase db = baseHelper.getReadableDatabase();
        String sql = "select id, monto, descrip from tbl_datos";
        Cursor c = db.rawQuery(sql,null);
        if(c.moveToFirst())
        {
            do {
                String linea = c.getInt(0)+" "+ c.getInt(1)+" "+c.getString(2);
                datos.add(linea);

            }while (c.moveToNext());

        }
        db.close();
        return datos;

    }
}
