package etsisi.poo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Ticket {
    private ArrayList<Product> products;
    private Catalog catalogo;
    private static final int MAX_PRODUCTOS = 100;

    public Ticket(Catalog catalogo) {
        this.products = new ArrayList<>();
        this.catalogo = catalogo;
    }

    public void newTicket() {
        products.clear();
        System.out.println("ticket new: ok");
    }

    public void addProduct(int productId, int cant) {
        if (!catalogo.existProduct(productId)) {
            throw new IllegalArgumentException("Product with id " + productId + " does not exist");
        }
        if (products.size() + cant >= MAX_PRODUCTOS)
            throw new IllegalArgumentException("You can't add more products");
        Product product = catalogo.getProduct(productId);
        for (int i = 0; i < cant; i++) {
            products.add(product);
        }
        System.out.println("Product added: " + product.getNombre() + " x " + cant);
        System.out.println(String.format("%.2f€",Total()));
    }

    public void removeProduct(int productId) {
        if (!catalogo.existProduct(productId)) {
            System.out.println("The product doesn't exist in this ticket");
        }else {
            int removed= 0;
            Iterator<Product> iterator = products.iterator();
            while (iterator.hasNext()) {
                Product p = iterator.next();
                if (p.getId() == productId) {
                    iterator.remove();
                    removed++;
                }
            }
            if(removed>0){
                System.out.println("Product with id "+ productId+" removed");
                System.out.println(String.format("%.2f€",Total()));

            } else System.out.println("Product with id "+ productId+" not found");
        }
    }

    public double Total() {
        if (products.isEmpty()) {
            return 0;
        }
        double total = 0;

        for (Product product : products) {
            Category category= product.getCategory();
            int contador = 0;
            // si son de la misma categoria
            for (Product p : products) {
                if (p.getCategory() == category) {
                    contador++;
                }
            }
            // Aplicar descuento si hay más de 1
            if (contador > 1) {
                double discount = category.getDiscount();
                total += product.getPrecio()*(1 - discount);
            } else {
                total += product.getPrecio();
            }
        }
        return total;
        }



    public void printTicket() {
        if (products.isEmpty()) {
            System.out.println("Esta vacio");
        }
        products.sort((p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()));

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            int cont = 0;

            for (int j = 0; j < products.size(); j++) {
                if (products.get(j).getCategory() == p.getCategory()) {
                    cont++;
                }
            }
            System.out.println("El producto " + p.getNombre() + " pertecene a la categoria " + p.getCategory() + " y hay en total: " + cont);
        }


    }

}