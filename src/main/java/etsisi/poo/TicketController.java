package etsisi.poo;

import java.util.*;

public class TicketController {

    private Map<String, TicketModel> tickets; //este es el mapa global
    private UserController userController;
    public TicketController(UserController userController) {
        this.tickets = new HashMap<>();
        this.userController = userController;
    }
    private String generateUniqueId() {
        String newId = "";
        boolean unique = false;

        while (!unique) {
            // 1. Generamos el ID candidato (Lógica de fecha + random)
            // Puedes tener un método estático en TicketModel para esto si quieres reutilizar lógica
            newId = TicketModel.calculateID();
            // 2. VERIFICACIÓN CONTRA EL MAPA (Solo el Controller puede hacer esto)
            if (!tickets.containsKey(newId)) {
                unique = true;
            }
        }
        return newId;
    }

    // el que estaba antes
    /*public void removeTicketsFromCashier(Cashier cashier) {
        if (cashier == null) return;
        String cashierId = cashier.getID();
        List<TicketModel> lista = ticketsByCashier.remove(cashierId);//quitar la lista de tickets del cajero del mapa que esta en CASHIER
        if (lista == null) return;//el cajero no tenia tickets ahi
        for (TicketModel t : lista) {//borrar los tickets del mapa global
            tickets.remove(t.getId());
        }
        cashier.getTickets().clear();//vaciar la lista interna del cajero
    }*/
    // Añadido ahora
    public void removeTicketsFromCashier(Cashier cashier) {
        if (cashier == null) return;
        List<TicketModel> ticketsOfCashier = cashier.getTickets();
        for (TicketModel t : ticketsOfCashier){
            tickets.remove(t.getId());
            userController.removeTicketFromAnyClient(t);
        }
    }


    public TicketModel newTicket(String ticketID, String cashierID, String userID) throws Exception{
        Cashier cashier = userController.getCashier(cashierID);
        if (cashier == null){
            throw new Exception("No se encontro el ID del cajero.");
        }
        Client client = userController.getClient(userID);
        if (client == null){
            throw new Exception("No se encontro el ID del cliente.");
        }
        String finalID = ticketID;
        if (finalID == null) {
            finalID = generateUniqueId();
        } else if (tickets.containsKey(finalID)) {
            throw new Exception("El ID del ticket ya existe: " + finalID);
        }

        TicketModel ticket = new TicketModel(finalID);

        tickets.put(ticketID,ticket);
        cashier.addTicket(ticket);
        client.addTicket(ticket);
        return ticket;
    }
    /*
    public TicketModel newTicket(String id) {
        TicketModel ticket;
        if (id == null) ticket = new TicketModel();
        else ticket = new TicketModel(id);
        tickets.put(ticket.getId(), ticket);
        return ticket;

    }*/


    //el metodo registra un ticket nuevo en la estructura del mapa tickets by cashier
    //y añade el ticket a la lista interna del mapa Cashier
    /*public void associateTicketToCashier(Cashier cashier, TicketModel ticket) {//hay que llamar a este cuando se cree el ticket
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
    */
    /*public boolean cashierHasTicket(String cashierId, TicketModel ticket) {//comprueba si un ticket pertenece al cajero
        List<TicketModel> lista = ticketsByCashier.get(cashierId);
        if (lista == null) return false;
        return lista.contains(ticket);
    }*/

    public boolean cashierHasTicket(String cashierId, TicketModel ticket) {//comprueba si un ticket pertenece al cajero
        List<TicketModel> cashierTickets = userController.getCashier(cashierId).getTickets();
        return cashierTickets.contains(ticket);
    }

    public TicketModel getTicket(String id) {
        return tickets.get(id);
    }

    public boolean addProductToTicket(String ticketId, Product product, int cantidad, ArrayList<String> personalizados) {
        TicketModel ticket = getTicket(ticketId);
        if (ticket == null) {
            System.out.println("Ticket ID not found");
            return false;
        }
        ticket.addProduct(product, cantidad, personalizados);
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

    /*public void listTickets() {
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
        allTickets.sort((a, b) -> {
            int estadoOrden = a.getTicketStatus().compareTo(b.getTicketStatus());
            if (estadoOrden != 0) {
                return estadoOrden;
            }
            return a.getId().compareTo(b.getId());
        });

        for (TicketModel t : allTickets) {
            System.out.println("  " + t.getId() + " - " + t.getTicketStatus()); //Mostrar uno por línea con el formato
        }

    }*/
    public List<TicketModel> getTicketsSortedByCashierId(){
        List<TicketModel> sortedTickets = new ArrayList<>();

        List<Cashier> cashiers = userController.getCashiersSortedByID();

        // vamos en orden y sacamos los tickets
        for (Cashier c : cashiers) {
            //añadimos los tickets de este cajero a la lista final y como se hace por orden acaba ordenada
            sortedTickets.addAll(c.getTickets());
        }
        return sortedTickets;
    }
    public void listAllTickets(){
        System.out.println("Ticket List:");
        List<TicketModel> tickets = getTicketsSortedByCashierId();
        for (TicketModel t : tickets) {
            System.out.println("  " + t.getId() + " - " + t.getTicketStatus());
        }
    }

    public void printTicketInfo(TicketModel ticket) {
        if (ticket == null) {
            System.out.println("Ticket ID not found");
            return;
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
                    System.out.printf("  %s **discount -%.3f%n", p, perUnitDiscount);
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

        System.out.printf("  Total price: %.3f%n", totalPrice);
        System.out.printf("  Total discount: %.3f%n", totalDiscount);
        System.out.printf("  Final Price: %.3f%n", finalPrice);

    }

    public void swapIdInMapWhenClose(String oldTicketId){
        TicketModel ticket = tickets.get(oldTicketId);

        if (ticket == null) {
            System.out.println("Error interno: No se encuentra el ticket para cerrar: " + oldTicketId);
            return;
        }

        //Lo borramos del mapa usando la clave vieja (antes de que cambie)
        tickets.remove(oldTicketId);

        // 3. UPDATE: El modelo actualiza su estado y calcula su nuevo ID internamente
        ticket.close();

        // Lo volvemos a insertar en el mapa con la nueva clave
        // Como 'ticket' es una referencia al objeto, ticket.getId() ya devuelve el nuevo valor
        tickets.put(ticket.getId(), ticket);
    }
    public void printTicket(String ticketId) {
        TicketModel ticket = getTicket(ticketId);
        if (ticket == null) {
            System.out.println("Ticket ID not found");
            return;
        }
        if (!ticket.isClosed()) {// primero se cierra el ticket si no esta cerrado
            //ticket.close();
            swapIdInMapWhenClose(ticketId);
        }
        printTicketInfo(ticket);
    }

}
