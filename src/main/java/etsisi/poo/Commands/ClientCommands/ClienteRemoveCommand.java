package etsisi.poo.Commands.ClientCommands;

import etsisi.poo.Client;
import etsisi.poo.Commands.ICommand;
import etsisi.poo.UserController;

public class ClienteRemoveCommand implements ICommand {
    private final UserController userController;

    public ClienteRemoveCommand(UserController userController) {
        this.userController = userController;
    }

    @Override
    public String getPrimerArgumento() {
        return "client";
    }

    @Override
    public String getSegundoArgumento() {
        return "remove";
    }

    @Override
    public String execute(String[] args) {
        if (args.length != 3) {
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
