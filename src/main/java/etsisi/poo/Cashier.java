package etsisi.poo;

public class Cashier extends Users{
    private String UW;
    public Cashier(String name, String email, String UW){
        super(name, email);
        this.UW = UW;
    }
    @Override
    public String getID(){
        return this.UW;
    }

}
