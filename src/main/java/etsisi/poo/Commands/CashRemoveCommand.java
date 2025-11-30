package etsisi.poo.Commands;

import etsisi.poo.Cashier;
import etsisi.poo.TicketController;
import etsisi.poo.UserController;

public class CashRemoveCommand implements ICommand {
    private UserController userController;
    private TicketController ticketController;

    // Al haber separado TicketController y UserController hay que llamar a los metodos
    // removeTicketsFromCashier y removeCashier de ambos controladores.

    public CashRemoveCommand(UserController userController, TicketController ticketController) {
        this.userController = userController;
        this.ticketController = ticketController;
    }

    public String getPrimerArgumento() {
        return "cash";
    }

    public String getSegundoArgumento() {
        return "remove";
    }

    public String execute(String [] args){
        if (args.length != 3){
            return "Usage: cash remove <id>";
        }
        String cashierId = args[2];
        Cashier cashier = userController.getCashier(cashierId);
        if (cashier == null){
            return "Cajero no encontrado";
        }
        ticketController.removeTicketsFromCashier(cashier);
        userController.removeCashier(cashierId);
        return "cash remove: ok";
    }

}
