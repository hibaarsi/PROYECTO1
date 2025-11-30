package etsisi.poo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Client extends Users {
    private String DNI;
    private Cashier cashier;
    private List<TicketModel> tickets;

    public Client(String name, String email, String DNI, Cashier cashier) {
        super(name, email);
        this.DNI = DNI;
        this.cashier = cashier;
        this.tickets = new ArrayList<>();
    }

    public void addTicket(TicketModel ticket) {
        this.tickets.add(ticket);
    }

    public void removeTicket(TicketModel ticket) {
        this.tickets.remove(ticket);
    }

    public List<TicketModel> getTickets(){return this.tickets;}

    public Cashier getCashier() {
        return this.cashier;
    }

    @Override
    public String getID() {
        return this.DNI;
    }

    @Override
    public String toString() {
        return String.format(
                "Client{identifier='%s', name='%s', email='%s', cash=%s}",
                this.DNI, getName(), getEmail(), this.cashier.getID());
    }
}
