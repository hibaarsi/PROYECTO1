package etsisi.poo.Commands.ProdCommands;

import etsisi.poo.*;
import java.time.*;

public class ProdAddEventCommand extends AbstractProdAddCommand {
    private final EventType eventType;
    private final String secondArg;

    public ProdAddEventCommand(Catalog catalog, EventType eventType, String secondArg) {
        super(catalog);
        this.eventType = eventType;
        this.secondArg = secondArg;
    }

    @Override
    public String getPrimerArgumento() {
        return "prod";
    }

    @Override
    public String getSegundoArgumento() {
        return secondArg;
    }

    @Override
    protected Product createProduct(String[] args) {
        int id = parseId(args[2]);
        String name = parseName(args[3]);
        double price = parsePrice(args[4]);
        LocalDate date = LocalDate.parse(args[5]);
        int maxPeople = Integer.parseInt(args[6]);

        LocalDateTime dateTime = date.atTime(LocalTime.MAX);

        return new EventProducts(
                id,
                name,
                price,
                dateTime,
                maxPeople,
                eventType
        );
    }

    @Override
    protected String getOkMessage() {
        return "prod " + secondArg + ": ok\n";
    }
}