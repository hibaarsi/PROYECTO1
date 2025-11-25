package etsisi.poo.Commands;

import etsisi.poo.Cashier;
import etsisi.poo.UserController;

public class CashAddCommand implements ICommand{
    private UserController userController;
    public CashAddCommand(UserController userController){
        this.userController=userController;
    }
    public String getPrimerArgumento(){
        return "cash";
    }
    public String getSegundoArgumento(){
        return"add";
    }

    public String execute(String[]args){
        if(args.length<5){
            return "cashAdd <UW> <name> <email> ";
        }

        String UW= args[2];
        String name =args[3];
        String email=args[4];



        Cashier cashier= userController.createCashier(name,email,UW);

        if(cashier==null){
            return "Cashier could not be created";

        }

        userController.addCashier(cashier);
        System.out.println("Cashier added");
        return "Cashier" +cashier.getName() + "added with " +cashier.getID();
    }
}
