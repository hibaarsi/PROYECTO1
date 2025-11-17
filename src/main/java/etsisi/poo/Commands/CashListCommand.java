package etsisi.poo.Commands;

import etsisi.poo.Cashier;
import etsisi.poo.UserController;

import java.util.List;

public class CashListCommand implements ICommand {
    private UserController userController;

    public CashListCommand(UserController userController) {
        this.userController = userController;
    }
    public String getPrimerArgumento(){
        return "cash";
    }
    public String getSegundoArgumento(){
        return"list";
    }

    public String execute(String[]args){
        List<Cashier> cashiers=userController.getCashiersSortedByName();//lista de cajeros ordenada por nombre

        if(cashiers.isEmpty()){
            return "List of registered cashiers";
        }

        System.out.println("List of cashiers");

        for(Cashier c: cashiers){
            System.out.println("- "+c);
        }
        return "List of cashiers" +cashiers; // MIRAR

    }
}
