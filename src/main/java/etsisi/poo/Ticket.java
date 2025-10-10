package etsisi.poo;

import java.util.ArrayList;
import java.util.Iterator;

public class Ticket {
    private ArrayList<Product> products;
    private Catalog catalog;
    private static final int MAX_PRODUCTOS = 100;

    public Ticket(Catalog catalog) {
        this.products = new ArrayList<>();
        this.catalog = catalog;
    }

    public void newTicket() {
        products.clear();
        System.out.println("ticket new: ok\n");
    }

    public void addProduct(int productId, int cant) {
        if (!catalog.existProduct(productId)) {
            System.out.println("Product with id " + productId + " does not exist");
        } else if (products.size() + cant > MAX_PRODUCTOS) {
            System.out.println("You can't add more products");
        } else {
            Product product = catalog.getProduct(productId);
            for (int i = 0; i < cant; i++) {
                products.add(product);
            }
            printTicketWithoutOk();
            System.out.println("ticket add:ok\n");
        }

    }

    public void removeProduct(int productId) {
        if (!catalog.existProduct(productId)) {
            System.out.println("The product doesn't exist in this ticket");
        } else {
            int removed = 0;
            Iterator<Product> iterator = products.iterator();
            while (iterator.hasNext()) {
                Product p = iterator.next();
                if (p.getId() == productId) {
                    iterator.remove();
                    removed++;
                }
            }
            if (removed > 0) {
                System.out.println("Product with id " + productId + " removed");
                printTicketWithoutOk();

            } else System.out.println("Product with id " + productId + " not found");
        }
    }

    public double getTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public double TotalwDiscount() {
        if (products.isEmpty()) {
            return 0;
        }
        double total = 0;
        for (Product product : products) {
            Category category = product.getCategory();
            int cont = 0;
            // si son de la misma categoria
            for (Product p : products) {
                if (p.getCategory() == category) {
                    cont++;
                }
            }
            // Aplicar descuento si hay más de 1
            if (cont > 1) {
                double discount = category.getDiscount();
                total += product.getPrice() * discount;
            }

        }
        return total;
    }

    public double getFinalPrice() {
        return getTotal() - TotalwDiscount();
    }


    public void printTicketWithoutOk() {
        if (products.isEmpty()) System.out.println("It's empty");
        else {
            products.sort((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
            for (Product product : products) {
                Category category = product.getCategory();
                int cont = 0;
                for (Product p : products) {
                    if (p.getCategory() == category) {
                        cont++;
                    }
                }
                if (cont > 1) {
                    double discount = product.getPrice() * category.getDiscount();
                    System.out.println(product + " **discount -" + discount);
                } else {
                    System.out.println(product);
                }
            }


            double totalPrice = getTotal();
            double totalDiscount = TotalwDiscount();
            double finalPrice = getFinalPrice();

            System.out.println("Total price: " + totalPrice);
            System.out.println("Total discount: " + totalDiscount);
            System.out.println("Final Price: " + finalPrice);
        }


    }
    public void printTicketWithOk(){
        printTicketWithoutOk();
        System.out.println("ticket print: ok\n");
    }


}