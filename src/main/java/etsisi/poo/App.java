package etsisi.poo;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    private static final String UNKNOWN_COMMAND = "Unknown command";
    private static final String CLOSE_APP = "Closing application\nGoodbye!";
    private static final String FIRST_MESSAGE = "Welcome to the ticket module App.\nTicket module. Type 'help' to see commands.";
    private static final String SPACE = "    ";

    public static void main(String[] args) {
        App app = new App();
        if (args.length > 0) {
            System.out.println("Nombre del fichero: " + args[0]);
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

    private void leerArchivo(String filename) {
        Catalog catalog = new Catalog();
        Ticket ticket = new Ticket(catalog);
        FileReader fileReader = null;
        BufferedReader bufreader = null;
        try {
            fileReader = new FileReader(filename);
            bufreader = new BufferedReader(fileReader);
            String command;
            while ((command = bufreader.readLine()) != null) {
                System.out.print("tUPM> ");
                System.out.println(command);
                startCommand(command, catalog, ticket);
            }
        } catch (IOException e) {
            System.out.println("Error:File with " + filename + " not found");
        }
    }

    private void start() {
        Scanner sc = new Scanner(System.in);
        Catalog catalog = new Catalog();
        Ticket ticket = new Ticket(catalog);

        boolean keepRunning = true;
        while (keepRunning) {

            System.out.print("tUPM> ");
            String comand = sc.nextLine();
            keepRunning = startCommand(comand, catalog, ticket);
        }
    }

    private void end() {
        System.out.println(CLOSE_APP);
    }

    private boolean startCommand(String command, Catalog catalog, Ticket ticket) {
        String[] separatedComand = command.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        switch (separatedComand[0]) {
            case "help":
                printHelp();
                break;
            case "prod":
                switch (separatedComand[1]) {
                    case "add":
                        Product p = new Product(Integer.parseInt(separatedComand[2]), separatedComand[3], Category.valueOf(separatedComand[4].toUpperCase()), Double.parseDouble(separatedComand[5]));
                        catalog.addProduct(p);
                        break;
                    case "list":
                        catalog.listProducts();
                        break;
                    case "update":
                        catalog.updateProduct(Integer.parseInt(separatedComand[2]), separatedComand[3], separatedComand[4]);
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
                        ticket.addProduct(Integer.parseInt(separatedComand[2]), Integer.parseInt(separatedComand[3]));
                        break;
                    case "remove":
                        ticket.removeProduct(Integer.parseInt(separatedComand[2]));
                        break;
                    case "print":
                        ticket.printTicket();
                        System.out.println("ticket print: ok\n");
                        break;
                    default:
                        unknownCommand();
                }


                break;
            case "echo":
                if (separatedComand.length > 1) {
                    String text = String.join(" ", Arrays.copyOfRange(separatedComand, 1, separatedComand.length));
                    System.out.println("echo " + text + "\n");
                } else {
                    System.out.println("echo \"\"");
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
        StringBuffer sb = new StringBuffer();

        sb.append("Commands:\n")
                .append(SPACE).append("prod add <id> \"<name>\" <category> <price>\n")
                .append(SPACE).append("prod list\n")
                .append(SPACE).append("prod update <id> NAME|CATEGORY|PRICE <value>\n")
                .append(SPACE).append("prod remove <id>\n")
                .append(SPACE).append("ticket new\n")
                .append(SPACE).append("ticket add <prodId> <quantity>\n")
                .append(SPACE).append("ticket remove <prodId>\n")
                .append(SPACE).append("ticket print\n")
                .append(SPACE).append("echo \"<texto>\"\n")
                .append(SPACE).append("help\n")
                .append(SPACE).append("exit\n\n\n")
                .append("Categories: MERCH, STATIONERY, CLOTHES, BOOK, ELECTRONICS \n")
                .append("Discounts if there are ≥2 units in the category: MERCH 0%, STATIONERY 5%, CLOTHES 7%, BOOK 10%, \n")
                .append("ELECTRONICS 3%.\n");

        return sb.toString();
    }

    private void printHelp() {
        System.out.println(helpSb());
    }
}


