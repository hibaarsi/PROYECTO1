package etsisi.poo.Commands;

import etsisi.poo.*;

public class ProdAddCommand extends AbstractProdAddCommand {
    public ProdAddCommand(Catalog catalog) {
        super(catalog);
    }

    public String getPrimerArgumento() {
        return "prod";
    }

    public String getSegundoArgumento() {
        return "add";
    }

    @Override
    protected Product createProduct(String[] args) {
        int id = parseId(args[2]);
        String name = parseName(args[3]);
        Category category = Category.valueOf(args[4].toUpperCase());
        double price = parsePrice(args[5]);

        if (args.length == 7) { //si es personalizable
            int maxPersonal = parseId(args[6]);
            return new ProductPersonalized(id, name, category, price, maxPersonal);
        }

        return new RegularProduct(id, name, category, price);
    }

    @Override
    protected String getOkMessage() {
        return "prod add: ok\n";
    }
}
