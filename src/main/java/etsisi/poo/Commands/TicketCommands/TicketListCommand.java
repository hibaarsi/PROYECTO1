package etsisi.poo.Commands.TicketCommands;

import etsisi.poo.Commands.ICommand;
import etsisi.poo.TicketController;
import etsisi.poo.TicketModel;

import java.util.List;

public class TicketListCommand implements ICommand {
    private final TicketController ticketController;

    public TicketListCommand(TicketController ticketController) {
        this.ticketController = ticketController;
    }
    public String getPrimerArgumento() {
        return "ticket";
    }

    public String getSegundoArgumento() {
        return "list";
    }

    /*public String execute(String[] args) {
        if (args == null || args.length != 2) {
            System.out.println("Usage: ticket list");
            return null;
        }
        ticketController.listTickets();
        return "ticket list: ok\n";

    }*/
    public String execute(String[] args) {
        ticketController.listAllTickets();
        return "ticket list: ok\n";
    }




}
