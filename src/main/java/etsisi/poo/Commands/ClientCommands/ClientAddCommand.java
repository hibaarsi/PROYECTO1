package etsisi.poo.Commands.ClientCommands;

import etsisi.poo.Cashier;
import etsisi.poo.Client;
import etsisi.poo.Commands.ICommand;
import etsisi.poo.UserController;

public class ClientAddCommand implements ICommand {
    private UserController userController;

    public ClientAddCommand(UserController userController) {
        this.userController = userController;
    }

    public String getPrimerArgumento() {
        return "client";
    }

    public String getSegundoArgumento() {
        return "add";
    }

    public String execute(String[] args) {
        if (args.length != 6) {
            return "Use: client add \"<name>\" <DNI> <email> <UW_cashier>";
        }
        //guarda los datos del cliente
        String name = args[2].replace("\"", "");
        String dni = args[3];
        String email = args[4];
        String uw = args[5];

        // Buscar el cajero asociado
        Cashier cashier = userController.getCashier(uw);
        if (cashier == null) {
            return "Cashier not found";
        }

        Client client = userController.createClient(name, email, dni, cashier);

        if (client == null) {
            return "Client could not be created";
        }

        userController.addClient(client);

        System.out.println(client);
        return "client add: ok\n";

    }
}
