package etsisi.poo.Commands;

import etsisi.poo.Catalog;
import etsisi.poo.Category;
import etsisi.poo.RegularProduct;

public class ProdAddCommand {
    private Catalog catalog;

    public ProdAddCommand(Catalog catalog) {
        this.catalog = catalog;
    }
    public void execute(String[] args) {
        if (args.length != 5) {//tiene 5 arg
            System.out.println("Not valid");
            return;
        }

        try {
            int id = Integer.parseInt(args[1]);//Id del product a entero
            String name = args[2].replace("\"", "");
            Category category = Category.valueOf(args[3].toUpperCase());
            double price = Double.parseDouble(args[4]);

            RegularProduct p = new RegularProduct(id, name, category, price);
            boolean ok = catalog.addProduct(p);
            if (ok) {
                System.out.println(p);
                System.out.println("prod add: ok\n");
            } else {
                System.out.printf("Product with id %d already exists or limit reached%n", id);
            }

        } catch (Exception e) {
            System.out.println("Invalid command");
        }
    }
}
