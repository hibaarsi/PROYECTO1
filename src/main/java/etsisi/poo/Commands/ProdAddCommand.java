package etsisi.poo.Commands;

import etsisi.poo.Catalog;
import etsisi.poo.Category;
import etsisi.poo.Product;
import etsisi.poo.RegularProduct;

public class ProdAddCommand extends AbstractProdAddCommand {
    public ProdAddCommand(Catalog catalog) {
        super(catalog);
    }

    public String getPrimerArgumento() {
        return "prod ";
    }

    public String getSegundoArgumento() {
        return "add";
    }

    @Override
    protected Product createProduct(String[] args) {
        int id = parseId(args[1]);
        String name = parseName(args[2]);
        Category category = Category.valueOf(args[3].toUpperCase());
        double price = parsePrice(args[4]);

        return new RegularProduct(id, name, category, price);
    }
}
