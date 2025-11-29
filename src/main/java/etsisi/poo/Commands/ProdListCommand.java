package etsisi.poo.Commands;

import etsisi.poo.Catalog;
import etsisi.poo.Product;

import java.util.*;

public class ProdListCommand implements ICommand {

    private final Catalog catalog;

    public ProdListCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    public String getPrimerArgumento() {
        return "prod";
    }

    public String getSegundoArgumento() {
        return "list";
    }

    public String execute(String[] args) {
        Map<Integer, Product> products = catalog.getProducts(); //copia del mapa de productos

        if (products.isEmpty()) {
            System.out.println("There are no products in the catalog.\n");
        } else {
            System.out.println("Catalog: ");
            //El TreeMap ordena automáticamente las entradas por la clave (ID del producto).
            TreeMap<Integer, Product> organizedProducts = new TreeMap<>(products);

            for (Product p : organizedProducts.values()) {
                System.out.println("  " + p);
            }
            System.out.println("prod list: ok\n");
        }
        return null;
    }
}
