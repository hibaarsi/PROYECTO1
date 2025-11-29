package etsisi.poo;

import java.util.*;

public class TicketController {

    private Map<String, List<TicketModel>> ticketsByCashier;//el mapa del id cajero y lista de tickets de ese cajero

    private Map<String, TicketModel> tickets; //este es el mapa global

    public TicketController() {
        this.tickets = new HashMap<>();
        this.ticketsByCashier = new HashMap<>();
    }

    /*public void removeTicketsFromCashier(Cashier cashier){
        List<String> remove= new ArrayList<>();
        for (TicketModel ticket: tickets.values()){
            if (ticket.getCashier() == cashier){
                remove.add(ticket.getId());
            }
        }
        for (String id: remove){
            tickets.remove(id);
        }
    }*/
    //este salia en rojo get.Cashier();


    public void removeTicketsFromCashier(Cashier cashier) {
        if (cashier == null) return;
        String cashierId = cashier.getID();
        List<TicketModel> lista = ticketsByCashier.remove(cashierId);//quitar la lista de tickets del cajero del mapa que esta en CASHIER
        if (lista == null) return;//el cajero no tenia tickets ahi
        for (TicketModel t : lista) {//borrar los tickets del mapa global
            tickets.remove(t.getId());
        }
        cashier.getTickets().clear();//vaciar la lista interna del cajero
    }


    /* public void newTicket(String ticketID, String cashierID, String userID){
         if (userController.getCashier(cashierID) == null){
             System.out.println("Cashier ID not found");
             return;
         }
         if (userController.getClient(userID) == null){
             System.out.println("User ID not found");
         }
         if (ticketID == null){
             tickets.put(ticketID,new TicketModel());
             userController.getCashier(cashierID).addTicket(tickets.get(ticketID));
             userController.getClient(userID).addTicket(tickets.get(ticketID));
         }else{
             tickets.put(ticketID,new TicketModel(ticketID));
             userController.getCashier(cashierID).addTicket(tickets.get(ticketID));
             userController.getClient(userID).addTicket(tickets.get(ticketID));
         }

    } */
    public TicketModel newTicket(String id) {
        TicketModel ticket;
        if (id == null) ticket = new TicketModel();
        else ticket = new TicketModel(id);
        tickets.put(ticket.getId(), ticket);
        return ticket;

    }


    //el metodo registra un ticket nuevo en la estructura del mapa tickets by cashier
    //y añade el ticket a la lista interna del mapa Cashier
    public void associateTicketToCashier(Cashier cashier, TicketModel ticket) {//hay que llamar a este cuando se cree el ticket
        if (cashier == null || ticket == null) return;
        String cashierId = cashier.getID();
        List<TicketModel> lista = ticketsByCashier.get(cashierId);//coje la lista de tickets del cajero
        if (lista == null) {//si el cajero aun no tiene lista asociada, se crea una
            lista = new ArrayList<>();
            ticketsByCashier.put(cashierId, lista);
        }
        lista.add(ticket);//añadimos el ticket a la lista de ids y cajeros
        cashier.addTicket(ticket);//añadirmos el ticket a la lista del cajero interna la que se crea en cashier
        //esta lista es para si borras el cajero que se borren todos sus tickets
    }

    public boolean cashierHasTicket(String cashierId, TicketModel ticket) {//comprueba si un ticket pertenece al cajero
        List<TicketModel> lista = ticketsByCashier.get(cashierId);
        if (lista == null) return false;
        return lista.contains(ticket);
    }

    public TicketModel getTicket(String id) {
        return tickets.get(id);
    }

    public boolean addProductToTicket(String ticketId, Product product, int cantidad,ArrayList<String> personalizados) {
        TicketModel ticket = getTicket(ticketId);
        if (ticket == null) {
            System.out.println("Ticket ID not found");
            return false;
        }
        ticket.addProduct(product, cantidad,personalizados);
        return true;
    }

    public boolean removeProductFromTicket(String ticketId, Product product) {
        TicketModel ticket = getTicket(ticketId);
        if (ticket == null) {
            System.out.println("Ticket ID not found");
            return false;
        }
        ticket.removeProduct(product);
        return true;
    }

    public void listTickets() {
        if (ticketsByCashier.isEmpty()) {
            return;
        }
        System.out.println("Ticket List:");
        List<TicketModel> allTickets = new ArrayList<>();//cojer los tickets de los cajeros
        for (List<TicketModel> lista : ticketsByCashier.values()) {// recorrer la lista y añadirla al hashmap si existen
            if (lista != null) {
                allTickets.addAll(lista);
            }
        }
        //ordenar de tal manera que primero salga empty y luego los closed
        allTickets.sort((a,b)->{
            int estadoOrden=a.getTicketStatus().compareTo(b.getTicketStatus());
            if (estadoOrden!=0){
                return estadoOrden;
            }
            return a.getId().compareTo(b.getId());
        });

        for (TicketModel t : allTickets) {
            System.out.println("  " + t.getId() + " - " + t.getTicketStatus()); //Mostrar uno por línea con el formato
        }

    }

    public void printTicket(String ticketId) {
        TicketModel ticket = getTicket(ticketId);
        if (ticket == null) {
            System.out.println("Ticket ID not found");
            return;
        }
        if (!ticket.isClosed()) {// primero se cierra el ticket si no esta cerrado
            ticket.close();
        }
        List<ElementoTicket> elementos = ticket.getElementos();
        if (elementos.isEmpty()) { //segundo se van coger cada linea del ticket
            System.out.println("Its empty");
            return;
        }
        Map<Category, Integer> unidadesPorCategoria = new HashMap<>();//para contar cuantas unidades hay por categoria
        //en vez de usar contadores que es ineficiente usamos un hashmap
        for (ElementoTicket e : elementos) {
            Product p = e.getProduct();
            int cantidad = e.getQuantity();

            if (p instanceof RegularProduct) {//si extiende regular product es que es un producto con category y solo se coge esos
                Category category = ((RegularProduct) p).getCategory();//pasa de product a regular product porq el get categori solo esta en regular product
                int actual = unidadesPorCategoria.getOrDefault(category, 0);//busca la clave categoria que es la categoria de cada prod
                unidadesPorCategoria.put(category, actual + cantidad);//actualiza el mapa sumando la cantidad actual+nuevas uds


            }
        }
        double totalPrice = 0.0;
        double totalDiscount = 0.0;

        System.out.println("Ticket : " + ticket.getId());
        elementos.sort((e1, e2) -> e1.getProduct().getName().compareToIgnoreCase(e2.getProduct().getName()));
        //ordenar las lineas por nombre

        //recorrer de nuevo para imprimir cada linea y calcular totales
        for (ElementoTicket e : elementos) {
            Product p = e.getProduct();
            int cantidad = e.getQuantity();
            double unitPrice = p.getPrice();

            double perUnitDiscount = 0.0;
            boolean tieneDescuento = false;

            if (p instanceof RegularProduct) {//si hereda de regular product
                Category cat = ((RegularProduct) p).getCategory(); //para que pueda usar el get cayegory
                int udsCategoria = unidadesPorCategoria.getOrDefault(cat, 0);

                // Si hay 2 o más uds en esa categoría en el ticet se aplica el desciemto
                if (udsCategoria >= 2) {
                    perUnitDiscount = unitPrice * cat.getDiscount();
                    tieneDescuento = true;
                }
            }

            // se imprime una linea por unidad
            for (int i = 0; i < cantidad; i++) {
                if (tieneDescuento) {
                    System.out.printf("  %s **discount -%.2f%n", p, perUnitDiscount);
                } else {
                    System.out.println("  " + p);
                }
            }

            // Actualizamos totales
            double linePrice = unitPrice * cantidad;
            double lineDiscount = perUnitDiscount * cantidad;

            totalPrice += linePrice;
            totalDiscount += lineDiscount;
        }

        double finalPrice = totalPrice - totalDiscount;

        System.out.printf("  Total price: %.1f%n", totalPrice);
        System.out.printf("  Total discount: %.1f%n", totalDiscount);
        System.out.printf("  Final Price: %.1f%n", finalPrice);

    }
}
