package etsisi.poo.Commands;

import etsisi.poo.Cashier;
import etsisi.poo.UserController;

public class CashRemoveCommand {
    private UserController userController;

    public CashRemoveCommand(UserController userController) {
        this.userController = userController;
    }

    public void execute(String[] args) {
        //el código UW
        if (args.length < 1) {
            System.out.println("Use cashremove <UW>");
            return;
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
            return;
        }
        userController.removeCashier(uw);
        System.out.println("Cashier removed");
    }

}
