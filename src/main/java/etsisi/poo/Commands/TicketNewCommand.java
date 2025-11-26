package etsisi.poo.Commands;

public class TicketNewCommand implements ICommand {
    public String getPrimerArgumento() {
        return "ticket";
    }

    public String getSegundoArgumento() {
        return "new";
    }

    public String execute(String[] args) {
    return null;//cambiar
    }
}
