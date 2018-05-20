package com.example.cristian.crud;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtmonto, edtdescrip;
    Button insertar, mostrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtmonto = (EditText) findViewById(R.id.editText);
        edtdescrip = (EditText) findViewById(R.id.editText2);

        insertar = (Button) findViewById(R.id.button);
        mostrar = (Button) findViewById(R.id.button2);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertar(Integer.parseInt(edtmonto.getText().toString()),edtdescrip.getText().toString());
            }
        });

        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Mostrar.class));
            }
        });

    }

    private void insertar(int Monto, String Descrip){



        dbHelper baseHelper = new dbHelper(this, "demo", null,1);
        SQLiteDatabase db = baseHelper.getWritableDatabase();
        try{
            ContentValues c = new ContentValues();
            c.put("monto",Monto);
            c.put("descrip",Descrip);

            db.insert("tbl_datos",null,c);
            db.close();
            Toast.makeText(this,"Resgistro insertado", Toast.LENGTH_SHORT).show();
        }catch (Exception e){

            Toast.makeText(this, "Error al insertar:"+ e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
}
