package etsisi.poo;

import java.util.ArrayList;
import java.util.Iterator;

public class Ticket {
    private ArrayList<Product> products;
    private Catalog catalog;

    //Constantes
    private static final int MAX_PRODUCTOS = 100;

    private static final String NEW_TICKET = "ticket new: ok\n";
    private static final String ADD_TICKET = "ticket add: ok\n";
    private static final String EMPTY_TICKET = "It's empty";
    private static final String PRINT_TICKET = "ticket print: ok\n";
    private static final String PRODUCT_NOT_EXISTS = "The product doesn't exist in this ticket";
    private static final String NO_MORE_PRODUCTS = "You can't add more products";
    private static final String PRODUCT_NOT_FOUND = "Product with id %d not found";
    private static final String PRODUCT_REMOVED = "Product with id %d removed";
    private static final String PRODUCT_DOES_NOT_EXIST = "Product with id %d does not exist";
    private static final String TOTAL_PRICE = "Total price: %.2f";
    private static final String TOTAL_DISCOUNT = "Total discount: %.2f";
    private static final String FINAL_PRICE = "Final Price: %.2f";

    public Ticket(Catalog catalog) {
        this.products = new ArrayList<>();
        this.catalog = catalog;
    }

    public void newTicket() {
        products.clear();
        System.out.println(NEW_TICKET);
    }

    public void addProduct(int productId, int cant) {
        if (!catalog.existProduct(productId)) {
            System.out.println(String.format(PRODUCT_DOES_NOT_EXIST, productId));
        } else if (products.size() + cant > MAX_PRODUCTOS) {
            System.out.println(NO_MORE_PRODUCTS);
        } else {
            Product product = catalog.getProduct(productId);
            for (int i = 0; i < cant; i++) {
                products.add(product);
            }
            printTicketWithoutOk();
            System.out.println(ADD_TICKET);
        }
    }

    public void removeProduct(int productId) {
        if (!catalog.existProduct(productId)) {
            System.out.println(PRODUCT_NOT_EXISTS);
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
                System.out.println(String.format(PRODUCT_REMOVED, productId));
                printTicketWithoutOk();
            } else {
                System.out.println(String.format(PRODUCT_NOT_FOUND, productId));
            }
        }
    }

    public double getTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public double totalWithDiscount() {
       if (products.isEmpty()) {
            return 0;
        }
        double total = 0;
        for (Product product : products) {
            Category category = product.getCategory();
            int cont = 0;
            for (Product p : products) {
                if (p.getCategory() == category) {
                    cont++;
                }
            }
            if (cont > 1) {
                double discount = category.getDiscount();
                total += product.getPrice() * discount;
            }
        }
        return total;
    }

    public double getFinalPrice() {
        return getTotal() - totalWithDiscount();
    }

    public void printTicketWithoutOk() {
        if (products.isEmpty()) {
            System.out.println(EMPTY_TICKET);
        } else {
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
            double totalDiscount = totalWithDiscount();
            double finalPrice = getFinalPrice();

            System.out.println(String.format(TOTAL_PRICE, totalPrice));
            System.out.println(String.format(TOTAL_DISCOUNT, totalDiscount));
            System.out.println(String.format(FINAL_PRICE, finalPrice));
        }
    }

    public void printTicketWithOk() {
        printTicketWithoutOk();
        System.out.println(PRINT_TICKET);
    }
}