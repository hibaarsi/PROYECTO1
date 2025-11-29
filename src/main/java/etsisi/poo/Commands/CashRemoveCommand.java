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
        //el código UW
        if (args.length < 3) {
            return "cash remove <UW>";
        }

        // Guardamos el código UW
        String uw = args[2];

        boolean encontrar = false;

        for (Cashier c : userController.getCashiersSortedByName()) {
            if (!encontrar && c.getID().equals(uw)) {
                encontrar = true;
            }
        }//cambiar, buscar por id en el hashmap

        if (!encontrar) {
            return "Cashier not found";
        }
        userController.removeCashier(uw);
        return "cash remove: ok";
    }

}
