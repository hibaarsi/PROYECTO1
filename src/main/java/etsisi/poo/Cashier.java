package etsisi.poo;

import java.util.ArrayList;
import java.util.List;

public class Cashier extends Users{
    private String UW;
    private List<Ticket> tickets;
    public Cashier(String name, String email, String UW){
        super(name, email);
        this.UW = UW;
        this.tickets = new ArrayList<>();
    }
    public void addTicket(Ticket ticket){
        this.tickets.add(ticket);
    }
    public void removeTicket(Ticket ticket){
        this.tickets.remove(ticket);
    }

    public List<Ticket> getTickets(){
        return this.tickets;
    }

    @Override
    public String getID(){
        return this.UW;
    }
    @Override
    public String toString(){
        return String.format("UW: %s, nombre: %s, email: %s"
                            ,this.UW,getName(),getEmail());
    }
}
