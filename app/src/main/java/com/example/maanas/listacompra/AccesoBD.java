package com.example.maanas.listacompra;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class AccesoBD extends SQLiteOpenHelper {
    public AccesoBD(Context context, int version) {
        super(context,"mi_bd", null, version);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_crea_tabla="CREATE TABLE lista_compra(producto TEXT, cantidad INT, comercio TEXT);";
        db.execSQL(query_crea_tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void grabar(Producto p)
    {
        String query_insert="INSERT INTO lista_compra VALUES(?, ?, ?);";
        SQLiteDatabase db=getWritableDatabase();
        Object[] arguumentos={p.getNombre(), p.getCantidad(), p.getComercio()};
        db.execSQL(query_insert,arguumentos);
    }
    public  void borrar(Producto p) {
        String sql_borrar="DELETE FROM lista_compra WHERE producto=? AND cantidad=? AND comercio=?";
        SQLiteDatabase sq=getWritableDatabase();
        Object[] argumentos={p.getNombre(), p.getCantidad(), p.getComercio()};
        sq.execSQL(sql_borrar, argumentos);

    }

    public ArrayList<Producto> listar() {
        ArrayList<Producto> lista_productos=new ArrayList<>();
        String query_seleccion="SELECT * FROM lista_compra";
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery(query_seleccion, null);
        while(c.moveToNext())
        {
            int cantidad=c.getInt(1);
            String producto=c.getString(0);
            String comercio=c.getString(2);
            Producto p=new Producto(producto, comercio, cantidad);
            lista_productos.add(p);
        }
    return lista_productos;
    }
}
