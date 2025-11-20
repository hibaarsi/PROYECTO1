package etsisi.poo;

import java.util.*;

public class TicketController {

    private Map<String,List<TicketModel>>ticketsByCashier;//el mapa del id cajero y lista de tickets de ese cajero

    private Map<String,TicketModel> tickets; //este es el mapa global

    public TicketController(){
        this.tickets = new HashMap<>();
        this.ticketsByCashier= new HashMap<>();
    }

    /*public void removeTicketsFromCashier(Cashier cashier){
        List<String> remove= new ArrayList<>();
        for (TicketModel ticket: tickets.values()){
            if (ticket.getCashier() == cashier){
                remove.add(ticket.getId());
            }
        }
        for (String id: remove){
            tickets.remove(id);
        }
    }*/
    //este salia en rojo get.Cashier();
    public void removeTicketsFromCashier(Cashier cashier){
        if (cashier==null) return;
        String cashierId =cashier.getID();
        List<TicketModel> lista=ticketsByCashier.remove(cashierId);//quitar la lista de tickets del cajero del mapa que esta en CASHIER
        if (lista==null)return;//el cajero no tenia tickets ahi
        for (TicketModel t:lista){//borrar los tickets del mapa global
            tickets.remove(t.getId());
        }
        cashier.getTickets().clear();//vaciar la lista interna del cajero
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
    public void associateTicketToCashier(Cashier cashier,TicketModel ticket){
        if (cashier==null||ticket==null)return;
        String cashierId = cashier.getID();
        List<TicketModel> lista = ticketsByCashier.get(cashierId);//coje la lista de tickets del cajero
        if (lista==null){//si el cajero aun no tiene lista asociada, se crea una
            lista=new ArrayList<>();
            ticketsByCashier.put(cashierId,lista);
        }
        lista.add(ticket);//añadimos el ticket a la lista de ids y cajeros
        cashier.addTicket(ticket);//añadirmos el ticket a la lista del cajero interna la que se crea en cashier
        //esta lista es para si borras el cajero que se borren todos sus tickets
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
        if (ticketsByCashier.isEmpty()){
            System.out.println("No tickets");
            return;
        }
        List<String> cashierIds =new ArrayList<>(ticketsByCashier.keySet());//cojes los ids
        cashierIds.sort(String::compareTo);//se ordenan alfabeticamente los cajeros
        for (String cashierId : cashierIds) {//recorrer los cajeros ya ordenados
            List<TicketModel> lista = ticketsByCashier.get(cashierId);// Obtener su lista de tickets
            // Si el cajero no tiene tickets o está vacío, lo saltamos para que no se pare la impresion
            if (lista == null || lista.isEmpty()) {
                continue;
            }
            lista.sort(Comparator.comparing(TicketModel::getId));//se ordenar los tickets del cajero por su ID de ticket
            for (TicketModel t : lista) {//se muestra la info de cada ticket
                System.out.println(
                        "cashier=" + cashierId +
                                ", ticketId=" + t.getId() +
                                ", status=" + t.getTicketStatus()
                );
            }
        }


    }
    public void printTicket(String ticketId){
        TicketModel ticket = getTicket(ticketId);
    }
}
