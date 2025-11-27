package etsisi.poo.Commands;

public class ExitCommand implements ICommand {
    @Override
    public String getPrimerArgumento() {
        return "exit";
    }

    @Override
    public String getSegundoArgumento() {
        return null;
    }

    @Override
    public String execute(String[] args) {
       System.out.println("Closing application.\nGoodbye\n");
       return null;
    }
}
