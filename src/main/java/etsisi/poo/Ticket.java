package etsisi.poo;

import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private ArrayList<Product> productos;
    private Catalogo catalogo;
    private static final int MAX_PRODUCTOS=100;

    public Ticket(Catalogo catalogo) {
        this.productos = new ArrayList<>();
        this.catalogo = catalogo;
    }
    public void newTicket(){
        productos.clear();
    }
   public void addProduct(int productId,int cant){
        if(catalogo.existProduct(productId)){
            throw new IllegalArgumentException("Product with id " + productId + " does not exist");
        }
        if(productos.size() + cant >=MAX_PRODUCTOS)
            throw new IllegalArgumentException("You cant add more products");
        Product product = catalogo.getProduct(productId);
        for(int i=0;i<cant; i++) {
            productos.add(product);
        }
   }
   public void removeProduct(int productId){
        int contador=0;
        // comprobar por id y quitar todas las cantidades
   }
   //importe provisional, imprimir tickrt, updateticket,


}
