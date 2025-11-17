package etsisi.poo.Commands;

import etsisi.poo.Catalog;
import etsisi.poo.ProductFood;

import java.time.*;

public class ProdAddFoodCommand implements ICommand{

    private final Catalog catalog;

    public ProdAddFoodCommand(Catalog catalog) {
        this.catalog = catalog;
    }
    public String getPrimerArgumento(){
        return "prod";
    }
    public String getSegundoArgumento(){
        return"addFood";
    }
    public String execute(String[] args) {
        if (args.length != 7) {
            System.out.println("Usage: prod addFood <id> \"<name>\" <price> <yyyy-MM-dd> <maxPeople>");
            return null;
        }

        try {
            int id = Integer.parseInt(args[2]);
            String name = args[3].replace("\"", "");
            double price = Double.parseDouble(args[4]);
            LocalDate date = LocalDate.parse(args[5]);
            int maxPeople = Integer.parseInt(args[6]);

            LocalDateTime eventDate = date.atTime(LocalTime.MAX);

            ProductFood food = new ProductFood(id, name, price, eventDate, maxPeople);
            catalog.addProduct(food);
            System.out.println("Added food product correctly: " + food);

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid numeric value.");
        } catch (Exception e) {
            System.out.println("Error adding food product: " + e.getMessage());
        }
        return null;
    }
}