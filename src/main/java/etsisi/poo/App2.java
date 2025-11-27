package etsisi.poo;

public class App2 {
    public static void main(String[] args) {
        CLI cli = new CLI();
        if (args.length > 0) {
            cli.runfromFile(args[0]);
        } else {
            cli.run();
        }
    }
}
