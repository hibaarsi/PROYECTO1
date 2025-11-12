package etsisi.poo.Commands;

import etsisi.poo.Cashier;
import etsisi.poo.Client;
import etsisi.poo.UserController;

public class ClientAddCommand {
    private UserController userController;

    public ClientAddCommand(UserController userController) {
        this.userController = userController;
    }
    public void execute(String[] args) {
        if (args.length < 4) {
            System.out.println("Use: clientadd <name> <email> <DNI> <UW_cashier>");
            return;
        }
        //guarda los datos del cliente
        String name = args[0];
        String email = args[1];
        String dni = args[2];
        String uw = args[3];

        // Buscar el cajero asociado
        Cashier cashier = null;
        for (Cashier c : userController.getCashiersSortedByName()) {
            if (cashier == null && c.getID().equals(uw)) { //si no se ha encontrado o no coincide el UW
                cashier = c;//se guarda
            }
        }

        if (cashier == null) {//si no se ha encontrado
            System.out.println("Cashier not found");
            return;
        }

        Client client = userController.createClient(name, email, dni, cashier);
        if (client != null) {//lo añadimos al mapa
            userController.addClient(client);
            System.out.println("Client added");
        } else{
            System.out.println("Client could not be created");

        }
    }
}
