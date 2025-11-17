package etsisi.poo.Commands;


import etsisi.poo.Catalog;
import etsisi.poo.Category;
import etsisi.poo.ProductCustomizable;

public class ProdAddCustomizableCommand implements ICommand {

    private final Catalog catalog;

    public ProdAddCustomizableCommand(Catalog catalog) {
        this.catalog = catalog;
    }
    public String getPrimerArgumento(){
        return "prod";
    }
    public String getSegundoArgumento(){
        return"addCustomizable";
    }
    public String execute(String[] args) {
        if (args.length != 7) {
            System.out.println("Usage: prod addCustomizable <id> \"<name>\" <category> <price> <maxTexts>");
            return null;
        }

        try {
            int id = Integer.parseInt(args[2]);
            String name = args[3].replace("\"", "");
            Category category = Category.valueOf(args[4].toUpperCase());
            double price = Double.parseDouble(args[5]);
            int maxTexts = Integer.parseInt(args[6]);

            if (!isCustomizableCategory(category)) {
                System.out.println("This category cannot be customizable.");
                return name;
            }

            ProductCustomizable custom = new ProductCustomizable(id, name, category, price, maxTexts);
            catalog.addProduct(custom);
            System.out.println("Added customizable product correctly: " + custom);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error adding customizable product.");
        }
        return null;
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
}