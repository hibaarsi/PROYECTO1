package etsisi.poo.Commands;

import etsisi.poo.Client;
import etsisi.poo.UserController;

public class ClienteRemoveCommand implements ICommand {
    private UserController userController;

    public String getPrimerArgumento() {
        return "client";
    }

    public String getSegundoArgumento() {
        return "remove";
    }

    public ClienteRemoveCommand(UserController userController) {
        this.userController = userController;
    }

    public String execute(String[] args) {
        if (args.length < 3) {
            return "client remove <DNI>";
        }


        String dni = args[2];
        Client client = userController.getClient(dni);

        if (client == null) {
            return "Client not found";
        }

        userController.removeClient(dni);

        return "client remove: ok\n";

    }
}
