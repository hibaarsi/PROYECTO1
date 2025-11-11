package etsisi.poo;

import java.util.ArrayList;
import java.util.List;

public class Client extends Users {
    private String DNI;
    private Cashier cashier;
    private List<Ticket> tickets;

    public Client(String name, String email, String DNI, Cashier cashier) {
        super(name, email);
        this.DNI = DNI;
        this.cashier = cashier;
        this.tickets = new ArrayList<>();
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public void removeTicket(Ticket ticket) {
        this.tickets.remove(ticket);
    }

    public Cashier getCashier() {
        return this.cashier;
    }

    @Override
    public String getID() {
        return this.DNI;
    }

    @Override
    public String toString() {
        return String.format("DNI: %s, nombre: %s, email: %s, cajero asociado: %s"
                , this.DNI, getName(), getEmail(), this.cashier.getID());
    }

}
