package etsisi.poo;

import etsisi.poo.Commands.*;

import java.util.Scanner;


public class CLI {
    private CommandController commandController;
    private UserController userController;
    private TicketController ticketController;
    private Catalog catalog;

    public CLI() {
        this.commandController = new CommandController();
        this.userController = new UserController(new TicketController());
        this.ticketController = new TicketController();
        this.catalog = new Catalog();
        registerCommands(commandController, userController, ticketController, catalog);
    }
    public void run(){
        Scanner sc = new Scanner(System.in);

    }



    private void registerCommands(CommandController commandController, UserController userController, TicketController ticketController,Catalog catalog){
        commandController.registerCommand(new ProdAddCommand(catalog));
        commandController.registerCommand(new ProdAddCustomizableCommand(catalog));
        commandController.registerCommand(new ProdAddFoodCommand(catalog));
        commandController.registerCommand(new ProdAddReunionCommand(catalog));
        commandController.registerCommand(new ProdListCommand(catalog));
        commandController.registerCommand(new ProdRemoveCommand(catalog));
        commandController.registerCommand(new ProdUpdateCommand(catalog));
        commandController.registerCommand(new CashAddCommand(userController));
        commandController.registerCommand(new CashRemoveCommand(userController));
        commandController.registerCommand(new ClientAddCommand(userController));
        commandController.registerCommand(new ClienteRemoveCommand(userController));

    }
    public static void printFromString(String message){
        System.out.println(message);
    }
}
