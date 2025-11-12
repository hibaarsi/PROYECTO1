package etsisi.poo.Commands;

import etsisi.poo.Client;
import etsisi.poo.UserController;

import java.util.List;

public class ClientListCommand {
    private UserController userController;

    public ClientListCommand(UserController userController) {
        this.userController = userController;
    }
    public void execute(String[]args){
        List<Client> clients= userController.getClientsSortedByName();//lista cliente ordenada por nombre
        if(clients.isEmpty()){
            System.out.println("Its empty");
            return;
        }

        System.out.println("list of registerd clients:");
        for(Client c: clients){
            System.out.println(c);
        }
    }
}
