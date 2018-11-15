package com.example.maanas.listacompra;

public class Producto {
    private String nombre, comercio;
    private int cantidad;

    public Producto(String nombre, String comercio, int cantidad) {
        this.nombre = nombre;
        this.comercio = comercio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getComercio() {
        return comercio;
    }

    public int getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return nombre+", "+cantidad+", "+comercio;
    }
}