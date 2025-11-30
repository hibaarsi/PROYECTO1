package etsisi.poo;

import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class UserController {
    private Map<String, Client> clientMap;     // La clave es el DNI
    private Map<String, Cashier> cashierMap;   // La clave es el código UW
    private TicketController ticketController;

    public UserController(TicketController ticketController) {
        this.clientMap = new HashMap<>();
        this.cashierMap = new HashMap<>();
        this.ticketController = ticketController;
    }

    public Client getClient(String clientID) {
        return clientMap.get(clientID);
        // Si no lo encuentra devuelve null
    }

    public Cashier getCashier(String cashierID) {
        return cashierMap.get(cashierID);
        // Si no lo encuentra devuelve null
    }

    public Client createClient(String name, String email, String DNI, Cashier cashier) {
        if (clientMap.containsKey(DNI)) {
            return null;
        }
        if (!cashierMap.containsKey(cashier.getID())) {
            return null;
        }

        return new Client(name, email, DNI, cashier);
    }

    public void addClient(Client client) {
        if (client != null) {
            clientMap.put(client.getID(), client);
        }
    }

    public void removeClient(String DNI) {
        clientMap.remove(DNI);
    }

    public void listClients() {
        List<Client> sortedClients = getClientsSortedByName();
        for (Client c : sortedClients) {
            System.out.println(c);
        }
    }

    public List<Client> getClientsSortedByName() {
        List<Client> clientList = new ArrayList<>(this.clientMap.values());
        //Esto compara los clientes usando el .getName()
        clientList.sort(Comparator.comparing(Client::getName));
        return clientList;
    }

    public Cashier createCashier(String name, String email, String UW) {
        if (UW == null) {
            UW = generateCashierID();
            return new Cashier(name, email, UW);
        }
        if (!properFormatUW(UW)) {
            return null;
        }
        return new Cashier(name, email, UW);
    }

    public void addCashier(Cashier cashier) {
        if (cashier != null) {
            cashierMap.put(cashier.getID(), cashier);
        }
    }

    public void removeCashier(String UW) {
        Cashier c = cashierMap.get(UW);

        if (c == null) return;
        // Aqui hay que borrar también lso tickets del cajero.
        // Lo pongo cuando esté hecho en TicketController
        ticketController.removeTicketsFromCashier(c);
        cashierMap.remove(UW);
    }


    public void listCashier() {
        List<Cashier> sortedCashier = getCashiersSortedByName();
        for (Cashier c : sortedCashier) {
            System.out.println(" " + c);
        }
    }

    public List<Cashier> getCashiersSortedByName() {
        List<Cashier> cashierList = new ArrayList<>(this.cashierMap.values());
        cashierList.sort(Comparator.comparing(Cashier::getName));
        return cashierList;
    }

    private String generateCashierID() {
        StringBuilder sb = new StringBuilder("UW");
        int numericPart = ThreadLocalRandom.current().nextInt(1000000, 10000000);
        sb.append(numericPart);
        return sb.toString();
    }

    private boolean properFormatUW(String UW) {
        if (!UW.startsWith("UW")) {
            return false;
        }
        if (UW.length() != 9) {
            return false;
        } else {
            String numericPart = UW.substring(2);
            for (Character c : numericPart.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
        }
        return true;
    }
}
