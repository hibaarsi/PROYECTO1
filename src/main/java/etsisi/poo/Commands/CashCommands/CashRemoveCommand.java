package etsisi.poo.Commands.CashCommands;

import etsisi.poo.Cashier;
import etsisi.poo.Commands.ICommand;
import etsisi.poo.TicketController;
import etsisi.poo.UserController;

public class CashRemoveCommand implements ICommand {
    private final UserController userController;
    private final TicketController ticketController;

    // Al haber separado TicketController y UserController hay que llamar a los metodos
    // removeTicketsFromCashier y removeCashier de ambos controladores.

    public CashRemoveCommand(UserController userController, TicketController ticketController) {
        this.userController = userController;
        this.ticketController = ticketController;
    }
    @Override
    public String getPrimerArgumento() {
        return "cash";
    }
    @Override
    public String getSegundoArgumento() {
        return "remove";
    }
    @Override
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
        return "cash remove: ok\n";
    }

}
