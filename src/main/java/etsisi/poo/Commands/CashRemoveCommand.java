package etsisi.poo.Commands;

import etsisi.poo.Cashier;
import etsisi.poo.UserController;

public class CashRemoveCommand implements ICommand {
    private UserController userController;

    public CashRemoveCommand(UserController userController) {
        this.userController = userController;
    }

    public String getPrimerArgumento() {
        return "cash";
    }

    public String getSegundoArgumento() {
        return "remove";
    }

    public String execute(String[] args) {
        //el código uw
        if (args.length < 3) return "Need: cash remove <UW>";

        String uw = args[2];

        Cashier c = userController.getCashier(uw);
        if (c == null) return "Cashier not found";

        userController.removeCashier(uw);

        return "cash remove: ok\n";
    }

}
