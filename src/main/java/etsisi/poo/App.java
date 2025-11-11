package etsisi.poo;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    private static final String UNKNOWN_COMMAND = "Unknown command";
    private static final String CLOSE_APP = "Closing application\nGoodbye!";
    private static final String FIRST_MESSAGE = "Welcome to the ticket module App.\nTicket module. Type 'help' to see commands.";
    private static final String SPACE = "    ";
    private static final String PROMPT = "tUPM> ";
    private static final String FILE_NAME = "File name: ";
    private static final String FILE_NOT_FOUND = "Error: File with %s not found";
    private static final String ECHO_PREFIX = "echo ";
    private static final String EMPTY_ECHO = "echo \"\"";

    // Help command
    private static final String HELP_HEADER = "Commands:\n";
    private static final String HELP_PROD_ADD = "prod add <id> \"<name>\" <category> <price>\n";
    private static final String HELP_PROD_LIST = "prod list\n";
    private static final String HELP_PROD_UPDATE = "prod update <id> NAME|CATEGORY|PRICE <value>\n";
    private static final String HELP_PROD_REMOVE = "prod remove <id>\n";
    private static final String HELP_TICKET_NEW = "ticket new\n";
    private static final String HELP_TICKET_ADD = "ticket add <prodId> <quantity>\n";
    private static final String HELP_TICKET_REMOVE = "ticket remove <prodId>\n";
    private static final String HELP_TICKET_PRINT = "ticket print\n";
    private static final String HELP_ECHO = "echo \"<texto>\"\n";
    private static final String HELP_HELP = "help\n";
    private static final String HELP_EXIT = "exit\n\n\n";
    private static final String HELP_CATEGORIES =
            "Categories: MERCH, STATIONERY, CLOTHES, BOOK, ELECTRONICS \n";
    private static final String HELP_DISCOUNTS =
            "Discounts if there are ≥2 units in the category: MERCH 0%, STATIONERY 5%, CLOTHES 7%, BOOK 10%, \nELECTRONICS 3%.\n";
    private Catalog catalog;
    private Ticket ticket;


    public static void main(String[] args) {
        App app = new App();
        if (args.length > 0) {
            System.out.println(FILE_NAME + args[0]);
            app.init();
            app.leerArchivo(args[0]);
            app.end();
        } else {
            app.init();
            app.start();
            app.end();
        }
    }

    private void init() {
        System.out.println(FIRST_MESSAGE);
    }

    private void leerArchivo(String fileName) {
        catalog = new Catalog();
        ticket = new Ticket(catalog);
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufreader = new BufferedReader(fileReader)) {

            String command;
            while ((command = bufreader.readLine()) != null) {
                System.out.print(PROMPT);
                System.out.println(command);
                startCommand(command);
            }

        } catch (IOException e) {
            System.out.println(String.format(FILE_NOT_FOUND, fileName));
        }
    }

    private void start() {
        Scanner sc = new Scanner(System.in);
        catalog = new Catalog();
        ticket = new Ticket(catalog);

        boolean keepRunning = true;
        while (keepRunning) {
            System.out.print(PROMPT);
            String comand = sc.nextLine();
            keepRunning = startCommand(comand);
        }
        sc.close();
    }

    private void end() {
        System.out.println(CLOSE_APP);
    }

    private boolean startCommand(String command) {
        String[] separatedComand = command.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        switch (separatedComand[0]) {
            case "help":
                printHelp();
                break;

            case "prod":
                switch (separatedComand[1]) {
                    case "add":
                        if (separatedComand.length == 6) {
                            int id;
                            try {
                                id = Integer.parseInt(separatedComand[2]);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid ID " + separatedComand[2]);
                                break;
                            }
                            String name = separatedComand[3];
                            double price;
                            try {
                                price = Double.parseDouble(separatedComand[5]);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid Price " + separatedComand[5]);
                                break;
                            }
                            Category category;
                            try {
                                category = Category.valueOf(separatedComand[4].toUpperCase());
                            } catch (IllegalArgumentException e) {
                                System.out.println("Invalid Category " + separatedComand[4]);
                                break;
                            }
                            Product p = new RegularProduct(id, name, category, price);
                            catalog.addProduct(p);
                        } else {
                            System.out.println("Not valid\n");
                        }
                        break;
                    case "list":
                        //catalog.listProducts();
                        break;
                    case "update":
                        catalog.updateProduct(
                                Integer.parseInt(separatedComand[2]),
                                separatedComand[3],
                                separatedComand[4]);
                        break;
                    case "remove":
                        catalog.removeProduct(Integer.parseInt(separatedComand[2]));
                        break;
                    default:
                        unknownCommand();
                }
                break;

            case "ticket":
                switch (separatedComand[1]) {
                    case "new":
                        ticket.newTicket();
                        break;
                    case "add":
                        ticket.addProduct(
                                Integer.parseInt(separatedComand[2]),
                                Integer.parseInt(separatedComand[3]));
                        break;
                    case "remove":
                        ticket.removeProduct(Integer.parseInt(separatedComand[2]));
                        break;
                    case "print":
                        ticket.printTicketWithOk();
                        break;
                    default:
                        unknownCommand();
                }
                break;

            case "echo":
                if (separatedComand.length > 1) {
                    String text = String.join(" ",
                            Arrays.copyOfRange(separatedComand, 1, separatedComand.length));
                    System.out.println(ECHO_PREFIX + text + "\n");
                } else {
                    System.out.println(EMPTY_ECHO);
                }
                break;

            case "exit":
                return false;

            default:
                unknownCommand();
                break;
        }
        return true;
    }

    private void unknownCommand() {
        System.out.println(UNKNOWN_COMMAND);
    }

    private String helpSb() {
        StringBuilder sb = new StringBuilder();
        sb.append(HELP_HEADER)
                .append(SPACE).append(HELP_PROD_ADD)
                .append(SPACE).append(HELP_PROD_LIST)
                .append(SPACE).append(HELP_PROD_UPDATE)
                .append(SPACE).append(HELP_PROD_REMOVE)
                .append(SPACE).append(HELP_TICKET_NEW)
                .append(SPACE).append(HELP_TICKET_ADD)
                .append(SPACE).append(HELP_TICKET_REMOVE)
                .append(SPACE).append(HELP_TICKET_PRINT)
                .append(SPACE).append(HELP_ECHO)
                .append(SPACE).append(HELP_HELP)
                .append(SPACE).append(HELP_EXIT)
                .append(HELP_CATEGORIES)
                .append(HELP_DISCOUNTS);
        return sb.toString();
    }

    private void printHelp() {
        System.out.println(helpSb());
    }
}