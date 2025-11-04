package etsisi.poo;

public class Client extends Users {
    private String DNI;
    private Cashier cashier;
    public Client(String name, String email, String DNI, Cashier cashier){
        super(name, email);
        this.DNI = DNI;
        this.cashier = cashier;
    }
    @Override
    public String getID(){
        return this.DNI;
    }

}
