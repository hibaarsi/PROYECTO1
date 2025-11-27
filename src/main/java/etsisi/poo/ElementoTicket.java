package etsisi.poo;

import java.util.List;

public class ElementoTicket {
    private final Product product;
    private final int quantity;

    public ElementoTicket(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

}
