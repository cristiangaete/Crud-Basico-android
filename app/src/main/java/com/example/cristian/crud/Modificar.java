package com.example.cristian.crud;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Modificar extends AppCompatActivity {
    EditText edtmontos2, edtdescrips2;
    Button modificar;
    Button eliminar;
    int id;
    int monto;
    String descrip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        Bundle b= getIntent().getExtras();
        if(b!=null){
            id = b.getInt("ID");
            monto = b.getInt("Monto");
            descrip = b.getString("Descrip");

        }

        edtmontos2 = (EditText) findViewById(R.id.editText3);
        edtdescrips2 = (EditText) findViewById(R.id.editText4);

        edtmontos2.setText(String.valueOf(monto));
        edtdescrips2.setText(descrip);

        modificar = (Button) findViewById(R.id.button3);
        eliminar = (Button) findViewById(R.id.button4);

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eliminar(id);
                onBackPressed();
            }
        });

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Modificar(id, Integer.parseInt(edtmontos2.getText().toString()),edtdescrips2.getText().toString());
                onBackPressed();
            }
        });


    }

    private void Modificar(int Id, int Monto, String Descrip){
        dbHelper baseHelper = new dbHelper(this, "demo", null,1);
        SQLiteDatabase db = baseHelper.getWritableDatabase();

        String sql = "update tbl_datos set monto='"+ Monto +"',descrip='"+ Descrip +"'where id="+Id;
        db.execSQL(sql);
        db.close();
    }
    private void Eliminar(int Id){
        dbHelper baseHelper = new dbHelper(this, "demo", null,1);
        SQLiteDatabase db = baseHelper.getWritableDatabase();

        String sql = "delete from tbl_datos where id="+Id;
        db.execSQL(sql);
        db.close();
    }
}
