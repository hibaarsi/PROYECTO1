package etsisi.poo;

import etsisi.poo.Commands.*;

public class CLI {


    public void run(){
        Catalog catalog=new Catalog();

    }
   /* private void registerCommands(CommandController commandController){
        commandController.registerCommand(new ClientAddCommand());
        commandController.registerCommand(new ClienteRemoveCommand());
        commandController.registerCommand(new ClientListCommand());
        commandController.registerCommand(new CashAddCommand());
        commandController.registerCommand(new CashRemoveCommand());
        commandController.registerCommand(new CashListCommand());
        commandController.registerCommand(new TicketListCommand());
    }*/
    public static void printFromString(String message){
        System.out.println(message);
    }
}
