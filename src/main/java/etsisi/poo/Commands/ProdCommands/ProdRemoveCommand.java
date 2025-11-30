package etsisi.poo.Commands.ProdCommands;

import etsisi.poo.Catalog;
import etsisi.poo.Commands.ICommand;
import etsisi.poo.Product;

public class ProdRemoveCommand implements ICommand {
    private final Catalog catalog;

    public ProdRemoveCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public String getPrimerArgumento() {
        return "prod";
    }

    @Override
    public String getSegundoArgumento() {
        return "remove";
    }

    @Override
    public String execute(String[] args) {
        if (args.length != 3) {
            return "prod remove <id>";
        }
        try {
            int id = Integer.parseInt(args[2]);//el arg a numero entero, seria el ID
            Product removed = catalog.removeProduct(id); //intenta eliminar

            if (removed == null) {
                System.out.printf("Product with id %d does not exist%n", id);
            } else {
                System.out.println(removed);
                return "prod remove: ok\n";
            }

        } catch (NumberFormatException e) { //en caso de que el ID no es un num valido
            return "Invalid ID format";
        }

        return null;
    }
}
