package etsisi.poo.Commands;

import etsisi.poo.TicketController;

public class TicketListCommand implements ICommand {
    private final TicketController ticketController;

    public TicketListCommand(TicketController ticketController) {
        this.ticketController = ticketController;
    }

    public String execute(String[] args) {
        if (args == null || args.length !=2) {
            System.out.println("Usage: ticket list");
            return null;
        }
        ticketController.listTickets();
        return "ticket list: ok";

    }

    public String getPrimerArgumento() {
        return "ticket";
    }

    public String getSegundoArgumento() {
        return "list";
    }


}
