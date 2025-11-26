package etsisi.poo.Commands;


import etsisi.poo.Catalog;
import etsisi.poo.Category;
import etsisi.poo.Product;
import etsisi.poo.ProductCustomizable;

public class ProdAddCustomizableCommand extends AbstractProdAddCommand {
    public String getPrimerArgumento() {
        return "prod";
    }

    public String getSegundoArgumento() {
        return "addCustomizable";
    }

    public ProdAddCustomizableCommand(Catalog catalog) {
        super(catalog);
    }

    @Override
    protected Product createProduct(String[] args) {
        int id = parseId(args[2]);
        String name = parseName(args[3]);
        Category category = Category.valueOf(args[4].toUpperCase());
        double price = parsePrice(args[5]);
        int maxTexts = Integer.parseInt(args[6]);

        if (!isCustomizableCategory(category)) {
            throw new IllegalArgumentException("This category cannot be customized");
        }

        return new ProductCustomizable(id, name, category, price, maxTexts);
    }

    private boolean isCustomizableCategory(Category category) {
        switch (category) {
            case MERCH:
            case CLOTHES:
            case STATIONERY:
                return true;
            default:
                return false;
        }
    }

    @Override
    protected String getOkMessage() {
        return "prod addCustomizable: ok";
    }
}