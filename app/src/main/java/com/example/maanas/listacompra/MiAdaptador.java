package com.example.maanas.listacompra;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MiAdaptador extends BaseAdapter  {
    ArrayList<Producto> lista_productos;
    Context contexto;

    public void setLista_productos(ArrayList<Producto> lista_productos) {
        //Pongo este setter parq poder desde ActivityListar darle la nueva lista de productos.
        this.lista_productos = lista_productos;
    }

    ArrayList<Long> lista_productos_chequeados=new ArrayList<>();
    public MiAdaptador(ArrayList<Producto> lista_productos, Context contexto) {
        this.lista_productos = lista_productos;
        this.contexto = contexto;
    }

    public ArrayList<Long> getLista_productos_chequeados() {
        return lista_productos_chequeados;
    }

    @Override
    public int getCount() {
        return lista_productos.size();
    }

    @Override
    public Object getItem(int position) {
        return lista_productos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(contexto);
        View v=inflater.inflate(R.layout.vista_producto, parent, false);
        TextView tv_producto=v.findViewById(R.id.tv_producto);
        TextView tv_comercio=v.findViewById(R.id.tv_comercio);
        TextView tv_cantidad=v.findViewById(R.id.tv_cantidad);
        final CheckBox chk_borrar=v.findViewById(R.id.chk_borrar);
        final Producto p=lista_productos.get(position);
        tv_producto.setText(p.getNombre());
        tv_cantidad.setText(String.valueOf(p.getCantidad()));
        tv_comercio.setText(p.getComercio());
        chk_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean chequeado= chk_borrar.isChecked();
                //Log.d("checkbox",String.valueOf(chequeado) + p.getId());
                if (chequeado==true) {
                    annadirProducto(p.getId());
                }
                else
                {
                    sacarProducto(p.getId());
                }
            }
        });
        /*btn_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                AlertDialog.Builder builder=new AlertDialog.Builder(contexto);
                builder.setTitle("Borrado");
                builder.setMessage("¿Realmente desea borrar el producto?"+p.getNombre());
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AccesoBD bd=new AccesoBD(contexto, 1);
                        bd.borrar(p);
                        lista_productos=bd.listar();
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancelar", null);
                AlertDialog alerta=builder.create();
                alerta.show();
            }
        });*/
        return v;
    }

    private void sacarProducto(long id) {
        lista_productos_chequeados.remove(id);
    }

    private void annadirProducto(long id) {
        lista_productos_chequeados.add(id);
    }

}
