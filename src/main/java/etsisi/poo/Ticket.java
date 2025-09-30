package etsisi.poo;

import java.util.ArrayList;

public class Ticket {
    private ArrayList<Product> products;
    private static final int MAX_PRODUCTS=100;


    public Ticket() {
        this.products = new ArrayList<>();
    }
    public void addProduct(Product product) {
        if (products.size() >= MAX_PRODUCTS) throw new IllegalArgumentException("No se pueden añadir mas productos");
        products.add(product);
    }
    public void newTicket() {
        products.clear();
    }
    public void printTicket(){

    }

}
