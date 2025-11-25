package etsisi.poo.Commands;

import etsisi.poo.Cashier;
import etsisi.poo.UserController;

public class CashTicketsCommand implements ICommand{
    private UserController userController;

    public CashTicketsCommand(UserController userController) {
        this.userController = userController;
    }
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
            System.out.println("Usage: cash tickets <UW>");
            return null;
        }


        String uw = args[2]; //id


        Cashier cashier = userController.getCashier(uw);

        if (cashier == null) {
            System.out.println("Cashier not found");
            return null;
        }

        // lista de tickets del cajero
        if (cashier.getTickets().isEmpty()) {
            System.out.println("This cashier has no tickets");
            return null;
        }

        System.out.println("Tickets of cashier " + cashier.getID() + ":");

        for (int i = 0; i < cashier.getTickets().size(); i++) {
            System.out.println("- " + cashier.getTickets().get(i).getId());
        }

        return null;
    }
}
