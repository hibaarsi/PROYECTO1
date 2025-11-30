package etsisi.poo;

import java.util.ArrayList;
import java.util.List;

public class ElementoTicket {
    private final Product product;
    private final int quantity;
    private final ArrayList<String> personalizados;

    public ElementoTicket(Product product, int quantity, ArrayList<String> personalizados) {
        this.product = product;
        this.quantity = quantity;
        this.personalizados = personalizados;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public ArrayList<String> getPersonalizados() {
        return personalizados;
    }

}
