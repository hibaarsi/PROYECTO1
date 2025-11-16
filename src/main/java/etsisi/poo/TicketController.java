package etsisi.poo;

import java.util.HashMap;
import java.util.Map;

public class TicketController {
    private Map<String,TicketModel> tickets;
    private UserController userController;
    public TicketController(UserController userController){
        this.tickets = new HashMap<>();
        this.userController = userController;
    }

    public void removeTicketsFromCashier(Cashier cashier){

    }
    public void newTicket(String ticketID, String cashierID, String userID){
        if (userController.getCashier(cashierID) == null){
            System.out.println("Cashier ID not found");
            return;
        }
        if (userController.getClient(userID) == null){
            System.out.println("User ID not found");
        }
        if (ticketID == null){
            tickets.put(ticketID,new TicketModel());
            userController.getCashier(cashierID).addTicket(tickets.get(ticketID));
            userController.getClient(userID).addTicket(tickets.get(ticketID));
        }else{
            tickets.put(ticketID,new TicketModel(ticketID));
            userController.getCashier(cashierID).addTicket(tickets.get(ticketID));
            userController.getClient(userID).addTicket(tickets.get(ticketID));
        }

    }
    public void listTickets(){}
    //mira a ver
}
