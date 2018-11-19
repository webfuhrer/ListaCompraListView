        package com.example.maanas.listacompra;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ActivityListar extends AppCompatActivity {
    //Spinner spn_lista;
    ListView lv_vista;
    Button btn_borrar;
    Spinner spn_filtro;
    MiAdaptador adaptador=null;
    Context contexto;
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
        adaptador=new MiAdaptador(lista_productos, this);

        lv_vista.setAdapter(adaptador);
      //  spn_lista.setAdapter(adaptador);
    }

    private void cargarVistas() {
        spn_filtro=findViewById(R.id.spn_filtro);
        lv_vista=findViewById(R.id.lv_lista);
        btn_borrar=findViewById(R.id.btn_borrar);
        contexto=this;
        spn_filtro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String comercio=(String) spn_filtro.getSelectedItem();
                AccesoBD bd=new AccesoBD(contexto, 1);

                ArrayList<Producto> lista_productos=bd.listarPorComercio(comercio);
                adaptador.setLista_productos(lista_productos);
                adaptador.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Entetrarse de qué productos se han chequeado.
                ArrayList<Long> lista_chequeados=adaptador.getLista_productos_chequeados();
                AccesoBD bd=new AccesoBD(contexto, 1);
                bd.borrar(lista_chequeados);
                ArrayList<Producto> lista_productos=bd.listar();
                adaptador.setLista_productos(lista_productos);
                adaptador.notifyDataSetChanged();
                // ArrayAdapter adaptador=new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, lista_productos);
                //ArrayAdapter adaptador=new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, lista_productos);


                lv_vista.setAdapter(adaptador);
            }
        });
      //  spn_lista=findViewById(R.id.spn_lista);

    }
}
