package etsisi.poo.Commands;

import etsisi.poo.Cashier;
import etsisi.poo.UserController;

public class CashRemoveCommand implements ICommand {
    private UserController userController;

    public CashRemoveCommand(UserController userController) {
        this.userController = userController;
    }
    public String getPrimerArgumento(){
        return "cash";
    }
    public String getSegundoArgumento(){
        return"remove";
    }

    public String execute(String[] args) {
        //el código UW
        if (args.length < 1) {
            return "Use cashremove <UW>";
        }

        // Guardamos el código UW
        String uw = args[0];

        boolean encontrar = false;
        for (Cashier c : userController.getCashiersSortedByName()) {
            if (!encontrar && c.getID().equals(uw)) {
                encontrar = true;
            }
        }

        if (!encontrar) {
            System.out.println("Cashier not found");
            return "Cashier not found";
        }
        userController.removeCashier(uw);
        return "Cashier removed";
    }

}
