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
            //Generamos el ID candidato (Lógica de fecha + random)
            newId = TicketModel.calculateID();
            //VERIFICACIÓN CONTRA EL MAPA (Solo el Controller puede hacer esto)
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

        for (TicketModel t : ticketsOfCashier) {
            tickets.remove(t.getId());
            userController.removeTicketFromAnyClient(t);
        }
    }

    public TicketModel newTicket(String ticketID, String cashierID, String userID) throws Exception {
        Cashier cashier = userController.getCashier(cashierID);
        if (cashier == null) {
            throw new Exception("No se encontro el ID del cajero.");
        }

        Client client = userController.getClient(userID);
        if (client == null) {
            throw new Exception("No se encontro el ID del cliente.");
        }

        String finalID = ticketID;
        if (finalID == null) {
            finalID = generateUniqueId();
        } else if (tickets.containsKey(finalID)) {
            throw new Exception("El ID del ticket ya existe: " + finalID);
        }

        TicketModel ticket = new TicketModel(finalID);

        tickets.put(ticketID, ticket);
        cashier.addTicket(ticket);
        client.addTicket(ticket);
        return ticket;
    }


    public boolean cashierHasTicket(String cashierId, TicketModel ticket) {//comprueba si un ticket pertenece al cajero
        List<TicketModel> cashierTickets = userController.getCashier(cashierId).getTickets();
        return cashierTickets.contains(ticket);
    }

    public TicketModel getTicket(String id) {
        return tickets.get(id);
    }

    public boolean addProductToTicket(String ticketId, Product product, int cantidad, ArrayList<String> personalizados) {
        TicketModel ticket = getTicket(ticketId);
        if (ticket == null || ticket.isClosed()) {
            return false;
        }

        ticket.addProduct(product, cantidad, personalizados);
        return true;
    }

    public boolean removeProductFromTicket(String ticketId, Product product) {
        TicketModel ticket = getTicket(ticketId);
        if (ticket == null || ticket.isClosed()) {
            return false;
        }

        ticket.removeProduct(product);
        return true;

    }

    public List<TicketModel> getTicketsSortedByCashierId() {
        List<TicketModel> sortedTickets = new ArrayList<>();

        List<Cashier> cashiers = userController.getCashiersSortedByID();

        // vamos en orden y sacamos los tickets
        for (Cashier c : cashiers) {
            //añadimos los tickets de este cajero a la lista final y como se hace por orden acaba ordenada
            sortedTickets.addAll(c.getTickets());
        }
        return sortedTickets;
    }

    public void listAllTickets() {
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

    public void swapIdInMapWhenClose(String oldTicketId) {
        TicketModel ticket = tickets.get(oldTicketId);

        if (ticket == null) {
            System.out.println("Error interno: No se encuentra el ticket para cerrar: " + oldTicketId);
            return;
        }

        //Lo borramos del mapa usando la clave vieja (antes de que cambie)
        tickets.remove(oldTicketId);

        //UPDATE: El modelo actualiza su estado y calcula su nuevo ID internamente
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
