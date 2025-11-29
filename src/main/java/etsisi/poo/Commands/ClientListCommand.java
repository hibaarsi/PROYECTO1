package etsisi.poo.Commands;

import etsisi.poo.Client;
import etsisi.poo.UserController;

import java.util.List;

public class ClientListCommand implements ICommand {
    private UserController userController;

    public String getPrimerArgumento() {
        return "client";
    }

    public String getSegundoArgumento() {
        return "list";
    }

    public ClientListCommand(UserController userController) {
        this.userController = userController;
    }

    public String execute(String[] args) {
        System.out.println("Client:");

        userController.listClients();

        return "client list: ok\n";
    }
}
