package etsisi.poo.Commands;

import etsisi.poo.TicketController;

public class TicketListCommand implements ICommand {
    private final TicketController ticketController;

    public TicketListCommand(TicketController ticketController) {
        this.ticketController = ticketController;
    }

    public String execute(String[] args) {
        if (args != null && args.length > 0) {
            return "Usage: ticket list";

        }
        ticketController.listTickets();
        return "";

    }

    public String getPrimerArgumento() {
        return "ticket";
    }

    public String getSegundoArgumento() {
        return "list";
    }


}
