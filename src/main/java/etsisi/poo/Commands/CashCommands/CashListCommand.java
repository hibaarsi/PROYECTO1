package etsisi.poo.Commands.CashCommands;

import etsisi.poo.Commands.ICommand;
import etsisi.poo.UserController;

public class CashListCommand implements ICommand {
    private UserController userController;

    public CashListCommand(UserController userController) {
        this.userController = userController;
    }

    public String getPrimerArgumento() {
        return "cash";
    }

    public String getSegundoArgumento() {
        return "list";
    }

    public String execute(String[] args) {

        System.out.println("Cash:");

        userController.listCashier();

        return "cash list: ok\n";
    }
}
