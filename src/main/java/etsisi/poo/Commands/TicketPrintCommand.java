package etsisi.poo.Commands;

import etsisi.poo.TicketController;
import etsisi.poo.TicketModel;

public class TicketPrintCommand implements ICommand {
    private final TicketController ticketController;

    public TicketPrintCommand(TicketController ticketController) {
        this.ticketController = ticketController;
    }



    public String execute(String[] args){
        if(args.length!=2){
            return "Usage: ticket print <ticketId> <cashierId>";

        }else{
            String ticketId=args[0];
            String cashierId=args[1];
            TicketModel ticket=ticketController.getTicket(ticketId);
            if (ticket==null)return "Ticket ID not found";
            if
        }
    }
    public String getPrimerArgumento(){
        return "ticket";
    }

    public String getSegundoArgumento(){
        return "print";
    }
}
