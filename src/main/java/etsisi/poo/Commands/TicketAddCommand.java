package etsisi.poo.Commands;

import etsisi.poo.TicketController;

public class TicketAddCommand implements ICommand {
    private final TicketController ticketController;

    public TicketAddCommand(TicketController ticketController) {
        this.ticketController = ticketController;
    }
    public String execute(String[] args){
        if (args.length==6){//es que no ha puesto lo opcional de personalizacion
            String id= args[2];
            String cashId=args[3];
            String userId=args[4];

        }
        return null;
    }
    public String getPrimerArgumento(){
        return "ticket";
    }

    public String getSegundoArgumento(){
        return"add";
    }
}
