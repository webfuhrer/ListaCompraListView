package com.example.maanas.listacompra;

public class Producto {
    private String nombre, comercio;
    private int cantidad;
    private long id;

    public Producto(String nombre, String comercio, int cantidad, long id) {
        this.nombre = nombre;
        this.comercio = comercio;
        this.cantidad = cantidad;
        this.id = id;
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

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return nombre+", "+cantidad+", "+comercio;
    }
}