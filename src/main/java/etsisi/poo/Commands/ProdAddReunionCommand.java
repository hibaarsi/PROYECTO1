package etsisi.poo.Commands;

import etsisi.poo.Catalog;
import etsisi.poo.ProductReunion;

import java.time.*;

public class ProdAddReunionCommand {

    private final Catalog catalog;

    public ProdAddReunionCommand(Catalog catalog) {
        this.catalog = catalog;

    }

    public void execute(String[] args) {
        if (args.length != 7) {
            System.out.println("Usage: prod addMeeting <id> \"<name>\" <price> <yyyy-MM-dd> <maxPeople>");
            return;
        }

        try {
            int id = Integer.parseInt(args[2]);
            String name = args[3].replace("\"", "");
            double price = Double.parseDouble(args[4]);
            LocalDate date = LocalDate.parse(args[5]);
            int maxPeople = Integer.parseInt(args[6]);

            LocalDateTime eventDate = date.atTime(LocalTime.MAX);

            ProductReunion reunion = new ProductReunion(id, name, price, eventDate, maxPeople);
            catalog.addProduct(reunion);
            System.out.println("Added reunion product correctly: " + reunion);

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid numeric value.");
        } catch (Exception e) {
            System.out.println("Error adding reunion product: " + e.getMessage());
        }
    }
}