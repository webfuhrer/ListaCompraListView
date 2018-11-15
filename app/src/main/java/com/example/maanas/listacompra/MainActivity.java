package com.example.maanas.listacompra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText et_producto, et_cantidad;
Spinner spn_comercio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarVistas();
    }

    private void cargarVistas() {
        et_producto=findViewById(R.id.et_producto);
        et_cantidad=findViewById(R.id.et_cantidad);
        spn_comercio=findViewById(R.id.spn_comercio);
    }

    public void grabar(View v)//Este es un método llamado por el botón de grabar
    {
        String nombre=et_producto.getText().toString();
        String cantidad=et_cantidad.getText().toString();
        String comercio=(String)spn_comercio.getSelectedItem();
        if (nombre.equals("") || cantidad.equals("") || comercio.equals(""))
        {//Si algún campo no se ha rellenado, salata el toast
            Toast.makeText(this, "Por favor, rellene los datos", Toast.LENGTH_LONG).show();

        }
        else //Están rellenos todos los campos
        {
            Producto p=new Producto(nombre, comercio, Integer.parseInt(cantidad));
            AccesoBD db=new AccesoBD(this, 1);
            db.grabar(p);
        }



    }
    public void listar(View v)
    {
       Intent i=new Intent(this, ActivityListar.class);
       startActivity(i);
    }
}
