package etsisi.poo;

import etsisi.poo.Commands.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//revisar
public class CLI {
    private CommandController commandController;
    private UserController userController;
    private TicketController ticketController;
    private Catalog catalog;
    private final static String PROMPT = "tUPM> ";


    public CLI() {
        this.commandController = new CommandController();
        this.ticketController = new TicketController();
        this.userController = new UserController(ticketController);
        this.catalog = new Catalog();
        registerCommands();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.print(PROMPT);
            String command = sc.nextLine();
            String[] args = command.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            String primerArgumento = args[0];
            String segundoArgumento;
            if (args.length > 1) {
                segundoArgumento = args[1];
            } else {
                segundoArgumento = null;///""
            }
            keepRunning = startCommand(primerArgumento, segundoArgumento, args);
        }
        sc.close();
    }

    public void runfromFile(String fileName) {
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufreader = new BufferedReader(fileReader)) {

            String command;
            while ((command = bufreader.readLine()) != null) {
                System.out.print(PROMPT);
                System.out.println(command);
                String[] args = command.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                String primerArgumento = args[0];
                String segundoArgumento;
                if (args.length > 1) {
                    segundoArgumento = args[1];
                } else {
                    segundoArgumento =null;
                }
                startCommand(primerArgumento, segundoArgumento, args);
            }

        } catch (IOException e) {
            System.out.println("No encontrado tal archivo");
        }
    }

    public boolean startCommand(String primerArgumento, String segundoArgumento, String[] args) {
        return commandController.executeCommand(primerArgumento, segundoArgumento, args);
    }

    private void registerCommands() {
        commandController.registerCommand(new ProdAddCommand(catalog));
        commandController.registerCommand(new ProdAddFoodCommand(catalog));
        commandController.registerCommand(new ProdAddMeetingCommand(catalog));
        commandController.registerCommand(new ProdListCommand(catalog));
        commandController.registerCommand(new ProdRemoveCommand(catalog));
        commandController.registerCommand(new ProdUpdateCommand(catalog));

        commandController.registerCommand(new CashAddCommand(userController));
        commandController.registerCommand(new CashRemoveCommand(userController));
        commandController.registerCommand(new CashListCommand(userController));
        commandController.registerCommand(new CashTicketsCommand(userController));

        commandController.registerCommand(new ClientAddCommand(userController));
        commandController.registerCommand(new ClienteRemoveCommand(userController));
        commandController.registerCommand(new ClientListCommand(userController));
        commandController.registerCommand(new EchoCommand());
        commandController.registerCommand(new HelpCommand());
        commandController.registerCommand(new ExitCommand());

        commandController.registerCommand(new TicketNewCommand());
        commandController.registerCommand(new TicketListCommand(this.ticketController));

    }

    public static void printFromString(String message) {
        System.out.println(message);
    }
}
