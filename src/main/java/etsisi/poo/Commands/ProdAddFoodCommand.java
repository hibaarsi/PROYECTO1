package etsisi.poo.Commands;

import etsisi.poo.Catalog;
import etsisi.poo.Product;
import etsisi.poo.ProductFood;

import java.time.*;

public class ProdAddFoodCommand extends AbstractProdAddCommand {
    public ProdAddFoodCommand(Catalog catalog) {
        super(catalog);
    }

    public String getPrimerArgumento() {
        return "prod";
    }

    public String getSegundoArgumento() {
        return "addFood";
    }

    @Override
    protected Product createProduct(String[] args) {
        int id = Integer.parseInt(args[2]);
        String name = parseName(args[3]);
        double price = parsePrice(args[4]);
        LocalDate date = LocalDate.parse(args[5]);
        int maxPeople = Integer.parseInt(args[6]);
        LocalDateTime dateTime = date.atTime(LocalTime.MAX);

        return new ProductFood(id, name, price, dateTime, maxPeople);
    }

    @Override
    protected String getOkMessage() {
        return "prod addFood: ok\n";
    }

}