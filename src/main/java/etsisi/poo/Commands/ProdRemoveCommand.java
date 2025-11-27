package etsisi.poo.Commands;

import etsisi.poo.Catalog;
import etsisi.poo.Product;

public class ProdRemoveCommand implements ICommand {
    private Catalog catalog;

    public ProdRemoveCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    public String getPrimerArgumento() {
        return "prod";
    }

    public String getSegundoArgumento() {
        return "remove";
    }

    public String execute(String[] args) {
        if (args.length != 3) {
            System.out.println("Not valid");
            return null;
        }

        try {
            int id = Integer.parseInt(args[2]);//el arg a numero entero, seria el ID
            Product removed = catalog.removeProduct(id); //intenta eliminar

            if (removed == null) {
                System.out.printf("Product with id %d does not exist%n", id);
            } else {
                System.out.println(removed);
                System.out.println("prod remove: ok");
            }

        } catch (NumberFormatException e) {//en caso de que el ID no es un num valido
            System.out.println("Invalid ID format");
        }
        return null;
    }
}
