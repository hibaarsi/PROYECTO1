package etsisi.poo.Commands;

import etsisi.poo.Catalog;
import etsisi.poo.Product;

import java.util.Map;

public class ProdListCommand implements ICommand{

    private final Catalog catalog;

    public ProdListCommand(Catalog catalog) {
        this.catalog = catalog;
    }
    public String getPrimerArgumento(){
        return "prod";
    }
    public String getSegundoArgumento(){
        return"list";
    }

    public String execute(String[] args) {
        Map<Integer, Product> products = catalog.getProducts(); //copia del mapa de productos

        boolean isEmpty = products.isEmpty();
        if (isEmpty) {
            System.out.println("There are no products in the catalog.");
        } else {
            System.out.println("Product list in the catalog:");
            for (Product product : products.values()) { //mostrar
                System.out.println("- " + product);
            }
        }
        return null;
    }
}
