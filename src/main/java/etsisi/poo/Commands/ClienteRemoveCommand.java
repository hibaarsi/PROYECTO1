package etsisi.poo.Commands;

import etsisi.poo.Client;
import etsisi.poo.UserController;

public class ClienteRemoveCommand implements ICommand {
    private UserController userController;
    public String getPrimerArgumento(){
        return "client";
    }
    public String getSegundoArgumento(){
        return"remove";
    }

    public ClienteRemoveCommand(UserController userController){
        this.userController=userController;
    }

    public String execute(String[]args){
        if(args.length<3){
            return "Need the clienteremove <DNI>";
        }

        String dni=args[2];

        boolean found = false;

        for (Client c : userController.getClientsSortedByName()) {//recorre la lista de cliente nombre, para ver si existe el DNI puesto
            if (!found && c.getID().equals(dni)) {
                found = true; //existe
            }
        }
        if (!found) {
            return "Client not found.";
        }

        userController.removeClient(dni);
     return "Client removed";

    }
}
