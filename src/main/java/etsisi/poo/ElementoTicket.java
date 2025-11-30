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

    @Override
    public String toString() {  //Prueba para los productos personalizados
        if (product instanceof ProductPersonalized && personalizados != null && !personalizados.isEmpty()) {

            return String.format(java.util.Locale.US,
                    "{class:ProductPersonalized, id:%d, name:'%s', category:%s, price:%.1f, maxPersonal:%d, personalizationList:%s}",
                    product.getId(),
                    product.getName().replace("\"", ""),
                    product.getCategory(),
                    product.getPrice(),
                    ((ProductPersonalized) product).getMaxPersonal(),
                    personalizados
            );
        }

        return product.toString();
    }

}
