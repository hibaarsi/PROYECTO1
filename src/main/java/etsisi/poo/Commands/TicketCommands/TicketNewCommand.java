package etsisi.poo.Commands.TicketCommands;

import etsisi.poo.*;
import etsisi.poo.Commands.ICommand;

public class TicketNewCommand implements ICommand {
    private final TicketController ticketController;


    // Ahora no tiene userController
    public TicketNewCommand(TicketController ticketController) {
        this.ticketController = ticketController;
    }

    public String getPrimerArgumento() {
        return "ticket";
    }

    public String getSegundoArgumento() {
        return "new";
    }

    /*public String execute(String[] args) {
        if (args.length != 4 && args.length != 5) {
            return "Usage: ticket new <ticketId> <cashierId> <clientId>";
        }
        //cambiar
        String ticketid = null;
        String cashId;
        String userId;
        if (args.length == 4) {
            cashId = args[2];
            userId = args[3];
        } else {
            ticketid = args[2];
            cashId = args[3];
            userId = args[4];
        }
        Cashier cashier = userController.getCashier(cashId);
        Client client = userController.getClient(userId);
        if (cashier == null) {
            return "Cashier ID not found";
        }
        if (client == null) {
            return "Client ID not found";
        }
        TicketModel ticket = ticketController.newTicket(ticketid);
        ticketController.associateTicketToCashier(cashier, ticket);
        client.addTicket(ticket);
        System.out.println("ticket " + ticket.getId());
        System.out.println("Total price: 0.0\n" +
                "  Total discount: 0.0\n" +
                "  Final Price: 0.0");
        return "ticket new: ok\n";
    }*/
    public String execute(String[] args) {
        String ticketID = null;
        String cashierID;
        String clientID;
        if (args.length != 4 && args.length != 5) {
            return "Usage: ticket new [<id>] <cashId> <userId>";
        }
        try {
            if (args.length == 4) {
                cashierID = args[2];
                clientID = args[3];
            } else {
                ticketID = args[2];
                cashierID = args[3];
                clientID = args[4];
            }
            //ticketController.newTicket(ticketID, cashierID, clientID);
            TicketModel ticket = ticketController.newTicket(ticketID, cashierID, clientID);

            StringBuilder sb = new StringBuilder();
            sb.append("Ticket : ").append(ticket.getId()).append("\n");
            sb.append("  Total price: 0.0").append("\n");
            sb.append("  Total discount: 0.0").append("\n");
            sb.append("  Final Price: 0.0").append("\n");
            sb.append("ticket new: ok");

            return sb.toString();

        } catch (Exception e) {
            return e.getMessage();
        }
    }
}

