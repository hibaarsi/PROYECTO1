package etsisi.poo.Commands;
import etsisi.poo.Catalog;
import etsisi.poo.Product;

public class ProdUpdateCommand {
    private Catalog catalog;

    public ProdUpdateCommand(Catalog catalog) {
        this.catalog = catalog;
    }
    public void execute(String[] args) {
        if (args.length != 4) {//tienen q ser 4 arg
            System.out.println("Not valid");
            return;
        }

        try {
            int id = Integer.parseInt(args[1]); //id del producto a entero
            String field = args[2].toUpperCase(); //nombre del campo
            String value = args[3].replace("\"", "");

            Product product = catalog.getProduct(id);
            if (product == null) {
                System.out.printf("Product with id %d does not exist%n", id);
                return;
            }

            boolean ok = catalog.updateProduct(id, field, value);//actualiza el producto en el catalog
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
    }
}
