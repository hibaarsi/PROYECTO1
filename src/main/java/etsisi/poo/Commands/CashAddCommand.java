package etsisi.poo.Commands;

import etsisi.poo.Cashier;
import etsisi.poo.UserController;

public class CashAddCommand {
    private UserController userController;
    public CashAddCommand(UserController userController){
        this.userController=userController;
    }

    public void Excute(String[]args){
        if(args.length<3){
            System.out.println("cashAdd <name> <email> <UW>");
            return;
        }

        String name =args[0];
        String email=args[1];
        String UW= args[2];

        Cashier cashier= userController.createCashier(name,email,UW);

        if(cashier==null){
            System.out.println("Cashier could not be created");
            return;
        }

        userController.addCashier(cashier);
        System.out.println("Cashier added");
    }
}
