package etsisi.poo.Commands.ProdCommands;

import etsisi.poo.Catalog;
import etsisi.poo.Commands.ICommand;
import etsisi.poo.Product;

public class ProdUpdateCommand implements ICommand {
    private final Catalog catalog;

    public ProdUpdateCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public String getPrimerArgumento() {
        return "prod";
    }

    @Override
    public String getSegundoArgumento() {
        return "update";
    }

    @Override
    public String execute(String[] args) {
        if (args.length != 5) {
            return "Not valid";
        }

        try {
            int id = Integer.parseInt(args[2]);
            String field = args[3].toUpperCase();
            String value = args[4].replace("\"", "");

            Product product = catalog.getProduct(id);
            if (product == null) {
                return "Product with id " + id + " does not exist";
            }

            boolean ok = catalog.updateProduct(id, field, value);
            if (ok) {
                System.out.println(product);
                System.out.println("prod update: ok\n");
            } else {
                switch (field) {
                    case "CATEGORY":
                        System.out.printf("Invalid category: %s%n", value);
                        break;
                    case "PRICE":
                        System.out.printf("Enter a positive value for the price: %s%n", value);
                        break;
                    default:
                        System.out.printf("Unknown field: %s%n", field);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format");

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid argument");
        }

        return null;
    }
}
