package etsisi.poo;

import java.util.ArrayList;

public class Ticket {
    private ArrayList<Producto> productos;
    private static final int MAX_PRODUCTOS=100;

    public Ticket() {
        this.productos = new ArrayList<>();
    }
    public void addProducto(Producto producto) {
        if (productos.size() >= MAX_PRODUCTOS) throw new IllegalArgumentException("No se pueden añadir mas productos");
        productos.add(producto);
    }
    public void newTicket() {
        productos.clear();
    }
    public void imprimirTicket(){

    }

}
