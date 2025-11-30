package etsisi.poo.Commands.CashCommands;

import etsisi.poo.Cashier;
import etsisi.poo.Commands.ICommand;
import etsisi.poo.TicketModel;
import etsisi.poo.UserController;

public class CashTicketsCommand implements ICommand {
    private final UserController userController;

    public CashTicketsCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public String getPrimerArgumento() {
        return "cash";
    }

    @Override
    public String getSegundoArgumento() {
        return "tickets";
    }

    @Override
    public String execute(String[] args) {

        if (args.length < 3) { //comprueba si paso el id del cajero
            return "Usage: cash tickets <UW>";
        }
        String uw = args[2]; //id
        Cashier cashier = userController.getCashier(uw);

        if (cashier == null) {
            return "Cashier not found";
        }
        System.out.println("Tickets: ");

        // lista de tickets del cajero
        if (cashier.getTickets().isEmpty()) {
            return "cash tickets: ok\n";
        }

        for (TicketModel t : cashier.getTickets()) {
            System.out.println("  " + t.getId() + "->" + t.getTicketStatus());
        }

        return "cash tickets: ok\n";
    }
}
