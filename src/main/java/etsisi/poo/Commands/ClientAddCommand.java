package etsisi.poo.Commands;

import etsisi.poo.Cashier;
import etsisi.poo.Client;
import etsisi.poo.UserController;

public class ClientAddCommand implements ICommand {
    private UserController userController;

    public ClientAddCommand(UserController userController) {
        this.userController = userController;
    }
    public String getPrimerArgumento(){
        return "client";
    }
    public String getSegundoArgumento(){
        return"add";
    }
    public String execute(String[] args) {
        if (args.length < 6) { //mirar
            return "Use: clientadd <name> <DNI> <email> <UW>";
        }
        //guarda los datos del cliente
        String name = args[2];
        String dni = args[3];
        String email = args[4];
        String uw = args[5];

        // Buscar el cajero asociado
        Cashier cashier = null;
        for (Cashier c : userController.getCashiersSortedByName()) {
            if (cashier == null && c.getID().equals(uw)) { //si no se ha encontrado o no coincide el UW
                cashier = c;//se guarda
            }
        }

        if (cashier == null) {//si no se ha encontrado
            return "Cashier not found";
        }

        Client client = userController.createClient(name, email, dni, cashier);
        if (client != null) {//lo añadimos al mapa
            userController.addClient(client);
            return "Client added";
        } else return "Client could not be created";

    }
}
