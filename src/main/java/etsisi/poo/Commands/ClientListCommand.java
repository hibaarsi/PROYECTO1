package etsisi.poo.Commands;

import etsisi.poo.Client;
import etsisi.poo.UserController;

import java.util.List;

public class ClientListCommand implements ICommand{
    private UserController userController;
    public String getPrimerArgumento(){
        return "client";
    }
    public String getSegundoArgumento(){
        return"list";
    }

    public ClientListCommand(UserController userController) {
        this.userController = userController;
    }
    public String execute(String[]args){
        List<Client> clients= userController.getClientsSortedByName();//lista cliente ordenada por nombre
        if(clients.isEmpty()){
            System.out.println("Its empty");
            return "It is empty";
        }

        System.out.println("list of registerd clients:");
        for(Client c: clients){
            System.out.println(c);
        }
        return clients.toString() ;
    }
}
