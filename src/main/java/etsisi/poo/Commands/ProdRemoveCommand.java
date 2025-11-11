package etsisi.poo.Commands;

import etsisi.poo.Catalog;
import etsisi.poo.Product;

public class ProdRemoveCommand {
    private Catalog catalog;

    public ProdRemoveCommand(Catalog catalog) {
        this.catalog = catalog;
    }
    public void execute(String[] args) {
        if (args.length != 2) {//tiene que ser 2 argu
            System.out.println("Not valid");
            return;
        }

        try {
            int id = Integer.parseInt(args[1]);//el arg a numero entero, seria el ID
            Product removed = catalog.removeProduct(id); //intenta eliminar

            if (removed == null) {
                System.out.printf("Product with id %d does not exist%n", id);
            } else {
                System.out.println(removed);
                System.out.println("prod remove: ok\n");
            }

        } catch (NumberFormatException e) {//en caso de que el ID no es un num valido
            System.out.println("Invalid ID format");
        }
    }
}
