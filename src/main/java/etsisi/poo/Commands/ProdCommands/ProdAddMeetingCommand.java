package etsisi.poo.Commands.ProdCommands;

import etsisi.poo.Catalog;
import etsisi.poo.Product;
import etsisi.poo.ProductMeeting;

import java.time.*;

public class ProdAddMeetingCommand extends AbstractProdAddCommand {
    public ProdAddMeetingCommand(Catalog catalog) {
        super(catalog);
    }
    @Override
    public String getPrimerArgumento() {
        return "prod";
    }
    @Override
    public String getSegundoArgumento() {
        return "addMeeting";
    }

    @Override
    protected Product createProduct(String[] args) {
        int id = Integer.parseInt(args[2]);
        String name = parseName(args[3]);
        double price = parsePrice(args[4]);
        LocalDate date = LocalDate.parse(args[5]);
        int maxPeople = Integer.parseInt(args[6]);
        LocalDateTime dateTime = date.atTime(LocalTime.MAX);

        return new ProductMeeting(id, name, price, dateTime, maxPeople);
    }

    @Override
    protected String getOkMessage() {
        return "prod addMeeting: ok\n";
    }

}