package etsisi.poo.Commands;

import etsisi.poo.Client;
import etsisi.poo.UserController;

public class ClienteRemoveCommand {
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

    public void execute(String[]args){
        if(args.length<1){
            System.out.println("Need the clienteremove <DNI>");
            return;
        }

        String dni=args[0];

        boolean found = false;

        for (Client c : userController.getClientsSortedByName()) {//recorre la lista de cliente nombre, para ver si existe el DNI puesto
            if (!found && c.getID().equals(dni)) {
                found = true; //existe
            }
        }
        if (!found) {
            System.out.println("Client not found.");
            return;
        }

        userController.removeClient(dni);
        System.out.println("Client removed");

    }
}
