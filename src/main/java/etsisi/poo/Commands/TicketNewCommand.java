package etsisi.poo.Commands;

import etsisi.poo.*;

public class TicketNewCommand implements ICommand {
    private final TicketController ticketController;
    private final UserController userController;

    public TicketNewCommand(TicketController ticketController, UserController userController) {
        this.ticketController = ticketController;
        this.userController = userController;
    }

    public String getPrimerArgumento() {
        return "ticket";
    }

    public String getSegundoArgumento() {
        return "new";
    }

    public String execute(String[] args) {
        if(args.length!=4&&args.length!=5){
            return "Usage: ticket new <ticketId> <cashierId> <clientId>";
        }
        //cambiar
        String ticketid= null;
        String cashId;
        String userId;
        if(args.length==4){
            cashId=args[2];
            userId=args[3];
        }else{
            ticketid=args[2];
            cashId=args[3];
            userId=args[4];
        }
        Cashier cashier=userController.getCashier(cashId);
        Client client=userController.getClient(userId);
       if(cashier==null){
           return "Cashier ID not found";
       }
       if(client==null){
           return "Client ID not found";
       }
       TicketModel ticket= ticketController.newTicket(ticketid);
       ticketController.associateTicketToCashier(cashier,ticket);
       client.addTicket(ticket);
        System.out.println("ticket "+ticket.getId());
        System.out.println("Total price: 0.0\n" +
                "  Total discount: 0.0\n" +
                "  Final Price: 0.0");
       return "ticket new: ok";
    }
}
