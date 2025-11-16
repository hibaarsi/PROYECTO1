package etsisi.poo.Commands;

public class TicketListCommand implements ICommand{
    private final TicketListCommand ticketListCommand;
    TicketListCommand(TicketListCommand d){
        this.ticketListCommand=d;
    }
    public String execute (String[] args){

        if (args.length>2){
            System.out.println("Mal");
        }else{

        }
        return null;
    }
    public String getPrimerArgumento(){
        return  "ticket";
    }
    public String getSegundoArgumento(){
        return "print";
    }
//cuando se ponga lo de implements hay que sobreescribir los metodos getName getSurname y execute,
    //para que asi esta clase se encargue de ejecutar su comando ticket list

}
