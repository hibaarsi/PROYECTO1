package etsisi.poo;

public class TicketController {
    private TicketModel ticketModel;
    private Catalog catalog;
    private UserController users;

    public TicketController(TicketModel ticketModel, Catalog catalog, UserController user) {
        this.ticketModel = ticketModel;
        this.catalog = catalog;
        this.users = user;

    }

}
