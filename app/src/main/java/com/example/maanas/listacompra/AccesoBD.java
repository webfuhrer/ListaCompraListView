package com.example.maanas.listacompra;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class AccesoBD extends SQLiteOpenHelper {
    public AccesoBD(Context context, int version) {
        super(context,"mi_bd2", null, version);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_crea_tabla="CREATE TABLE lista_compra(id INTEGER PRIMARY KEY, producto TEXT, cantidad INT, comercio TEXT);";
        db.execSQL(query_crea_tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void grabar(Producto p)
    {
        String query_insert="INSERT INTO lista_compra(producto, cantidad, comercio) VALUES(?, ?, ?);";
        SQLiteDatabase db=getWritableDatabase();
        Object[] arguumentos={p.getNombre(), p.getCantidad(), p.getComercio()};
        db.execSQL(query_insert,arguumentos);
    }
    public  void borrar(ArrayList<Long> lista_ids) {
       // DELETE FROM lista_compra WHERE id IN (1, 2, 3, 4);
        for (Long id:lista_ids) {
            SQLiteDatabase sq=getWritableDatabase();
            String sql_borrar="DELETE FROM lista_compra"+" WHERE id="+id;
            sq.execSQL(sql_borrar);

        }


    }

    public ArrayList<Producto> listar() {
        ArrayList<Producto> lista_productos=new ArrayList<>();
        String query_seleccion="SELECT * FROM lista_compra";
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery(query_seleccion, null);
        while(c.moveToNext())
        {


            long id=c.getLong(0);//id de producto
            Log.d("mi_id", String.valueOf(id));
            int cantidad=c.getInt(2);
            String producto=c.getString(1);
            String comercio=c.getString(3);
            Producto p=new Producto(producto, comercio, cantidad, id);
            lista_productos.add(p);
        }
    return lista_productos;
    }
}
