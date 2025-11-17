package etsisi.poo;

import etsisi.poo.Commands.*;


public class CLI {


    public void run(){
        Catalog catalog=new Catalog();
        UserController userController=new UserController(new TicketController());
        TicketController ticketController=new TicketController();
        CommandController commandController=new CommandController();
        registerCommands(commandController, userController, ticketController,catalog);



    }
    private void registerCommands(CommandController commandController, UserController userController, TicketController ticketController,Catalog catalog){
        commandController.registerCommand(new ProdAddCommand(catalog));
    }
    public static void printFromString(String message){
        System.out.println(message);
    }
}
