        package com.example.maanas.listacompra;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ActivityListar extends AppCompatActivity {
    //Spinner spn_lista;
    ListView lv_vista;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listar);
        cargarVistas();
        cargarDatos();
    }

    private void cargarDatos() {
        AccesoBD bd=new AccesoBD(this, 1);
        ArrayList<Producto> lista_productos=bd.listar();
       // ArrayAdapter adaptador=new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, lista_productos);
        //ArrayAdapter adaptador=new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, lista_productos);
        MiAdaptador adaptador=new MiAdaptador(lista_productos, this);

        lv_vista.setAdapter(adaptador);
      //  spn_lista.setAdapter(adaptador);
    }

    private void cargarVistas() {
        lv_vista=findViewById(R.id.lv_lista);
      //  spn_lista=findViewById(R.id.spn_lista);

    }
}
