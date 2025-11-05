package etsisi.poo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class UserController {
    private Map<String,Client> clientMap;     // La clave es el DNI
    private Map<String,Cashier> cashierMap;   // La clave es el código UW
    private TicketController ticketController;

    public UserController(TicketController ticketController){
        this.clientMap = new HashMap<>();
        this.cashierMap = new HashMap<>();
        this.ticketController = ticketController;

    }
    public Client createClient(String name, String email, String DNI, Cashier cashier){
        if (clientMap.containsKey(DNI)){
            System.out.println("El cliente ya existe.");
            return null;
        }
        if (!cashierMap.containsKey(cashier.getID())){
            System.out.println("El cajero no existe.");
            return null;
        }
        return new Client(name, email, DNI, cashier);
    }
    public void addClient(Client client){
        if (client == null){
            System.out.println("El cliente no puede ser nulo.");
        }else{
            clientMap.put(client.getID(),client);
        }
    }

    public Cashier createCashier(String name, String email, String UW){
        if (UW == null){
            UW = generateCashierID();
            return new Cashier(name, email, UW);
        }else{
            if (properFormatUW(UW)){
                return new Cashier(name, email, UW);
            }else {
                return null;
            }
        }

    }
    public void addCashier(Cashier cashier){
        if (cashier == null){
            System.out.println("El cajero no puede ser nulo.");
        }else{
            cashierMap.put(cashier.getID(),cashier);
        }
    }
    private String generateCashierID(){
        StringBuilder sb = new StringBuilder("UW");
        int numericPart = ThreadLocalRandom.current().nextInt(1000000, 10000000);
        sb.append(numericPart);
        return sb.toString();
    }
    private boolean properFormatUW(String UW){
        if (!UW.startsWith("UW")){
            return false;
        }
        if (UW.length() != 9){
            return false;
        }else {
            String numericPart = UW.substring(2);
            for (Character c : numericPart.toCharArray()){
                if (!Character.isDigit(c)){
                    return false;
                }
            }
        }
        return true;

    }
}
