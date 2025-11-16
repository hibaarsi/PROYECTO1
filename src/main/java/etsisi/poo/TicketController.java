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
    public void listTickets(){
        if (!tickets.isEmpty()){
            for (TicketModel t: tickets.values()){
                System.out.println("ticketId= "+t.getId()+
                        ", status=" + t.getTicketStatus());
            }
        }else{
            System.out.println("Ticket is empty");
            return;
        }
    }
}
