package etsisi.poo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketController {
    private Map<String,TicketModel> tickets;
    public TicketController(){
        this.tickets = new HashMap<>();
    }

    public void removeTicketsFromCashier(Cashier cashier){
        List<String> remove= new ArrayList<>();
        for (TicketModel ticket: tickets.values()){
            if (ticket.getCashier() == cashier){
                remove.add(ticket.getId());
            }
        }
        for (String id: remove){
            tickets.remove(id);
        }
    }
   /* public void newTicket(String ticketID, String cashierID, String userID){
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

    }*/
    public TicketModel newTicket(String id){
        TicketModel ticket;
        if(id==null) ticket = new TicketModel();
        else ticket = new TicketModel(id);
        tickets.put(ticket.getId(),ticket);
        return ticket;

    }
    public TicketModel getTicket(String id){
        return tickets.get(id);
    }
    public boolean addProductToTicket(String ticketId, Product product,int cantidad){
        TicketModel ticket = getTicket(ticketId);
        if (ticket == null){
            System.out.println("Ticket ID not found");
            return false;
        }
        ticket.addProduct(product,cantidad);
        return true;
    }
    public boolean removeProductFromTicket(String ticketId, Product product){
        TicketModel ticket = getTicket(ticketId);
        if (ticket == null){
            System.out.println("Ticket ID not found");
            return false;
        }
        ticket.removeProduct(product);
        return true;
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
    public void printTicket(String ticketId){
        TicketModel ticket = getTicket(ticketId);
    }
}
