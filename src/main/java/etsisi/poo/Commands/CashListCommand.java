package etsisi.poo.Commands;

import etsisi.poo.Cashier;
import etsisi.poo.UserController;

import java.util.List;

public class CashListCommand {
    private UserController userController;

    public CashListCommand(UserController userController) {
        this.userController = userController;
    }

    public void excute(String[]args){
        List<Cashier> cashiers=userController.getCashiersSortedByName();//lista de cajeros ordenada por nombre

        if(cashiers.isEmpty()){
            System.out.println("List of registered cashiers");
            return;
        }

        System.out.println("List of cashiers");

        for(Cashier c: cashiers){
            System.out.println("- "+c);
        }

    }
}
