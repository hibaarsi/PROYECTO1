package etsisi.poo.Commands;

import etsisi.poo.Catalog;
import etsisi.poo.Product;

public abstract class AbstractProdAddCommand implements ICommand {

    protected final Catalog catalog;

    public AbstractProdAddCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    protected int parseId(String idStr) {     //protected para que solo se use en este paquete
        try {
            return Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid ID: must be an integer.");
        }
    }

    protected String parseName(String nameStr) {
        return nameStr.replace("\"", "");
    }

    protected double parsePrice(String priceStr) {
        try {
            double value = Double.parseDouble(priceStr);
            if (value <= 0) throw new IllegalArgumentException("Price must be positive.");
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid price: must be a number.");
        }
    }

    protected abstract Product createProduct(String[] args);

    protected abstract String getOkMessage();

    @Override
    public String execute(String[] args) {
        try {
            Product product = createProduct(args);
            boolean ok = catalog.addProduct(product);

            if (ok) {
                System.out.println(product);
                System.out.println(getOkMessage());
            } else {
                System.out.println("Product could not be added");
            }

        } catch (Exception e) {
            System.out.println("Error processing ->" + getPrimerArgumento() + " " + getSegundoArgumento() +
                    " ->" + e.getMessage());
        }
        return null;
    }
}