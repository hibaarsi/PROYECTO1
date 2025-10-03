package etsisi.poo;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    private static final String CLOSE_APP = "Closing application\n" + "Goodbye!";
    private static final String FIRST_MESSAGE = "Welcome to the ticket module App.\n" +
            "Ticket module. Type 'help' to see commands.";
    private static final String SPACE = "    ";

    public static void main(String[] args) {
        App app = new App();
        app.init();
        app.start();
        app.end();
    }

    private void init() {
        System.out.println(FIRST_MESSAGE);
    }

    private void start() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {

            System.out.print("tUPM>");
            String comand = sc.nextLine();

            if (System.getenv("fileinput") != null &&
                    System.getenv("fileinput").equals("true"))
                System.out.println(comand);
            String[] sepparatedComand = comand.split(" ");

            switch (sepparatedComand[0]) {
                case "help":
                    printHelp();
                    break;
                case "prod":

                    break;
                case "ticket":

                    break;
                case "echo":

                    String text = sc.nextLine();
                    System.out.println("<echo> " + "<" + text + ">");
                    break;

                case "exit":
                    continuar = false;

                    break;
                default:

                    break;
            }
        }
    }

    private void end() {
        System.out.println(CLOSE_APP);
    }

    private void printHelp() {
        System.out.println("Commands:");
        System.out.println(SPACE + "prod add <id> \"<name>\" <category> <price>");
        System.out.println(SPACE + "prod list");
        System.out.println(SPACE + "prod update <id> NAME|CATEGORY|PRICE <value>");
        System.out.println(SPACE + "prod remove <id>");
        System.out.println(SPACE + "ticket new");
        System.out.println(SPACE + "ticket add <prodId> <quantity>");
        System.out.println(SPACE + "ticket remove <prodId>");
        System.out.println(SPACE + "ticket print");
        System.out.println(SPACE + "echo \"<texto>\"");
        System.out.println(SPACE + "help");
        System.out.println(SPACE + "exit\n\n");

        System.out.println(SPACE + "Categories: MERCH, STATIONERY, CLOTHES, BOOK, ELECTRONICS \n" +
                "Discounts if there are ≥2 units in the category: MERCH 0%, STATIONERY 5%, CLOTHES 7%, BOOK 10%, \n" +
                "ELECTRONICS 3%.");
        System.out.println();

    }
}


