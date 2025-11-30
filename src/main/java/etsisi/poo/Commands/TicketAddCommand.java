package etsisi.poo.Commands;

import etsisi.poo.*;
import java.util.ArrayList;
import java.util.List;

public class TicketAddCommand implements ICommand {
    private final TicketController ticketController;
    private final UserController userController;
    private final Catalog catalog;

    public TicketAddCommand(TicketController ticketController, UserController userController, Catalog catalog) {
        this.ticketController = ticketController;
        this.userController = userController;
        this.catalog = catalog;
    }

    public String getPrimerArgumento() {
        return "ticket";
    }

    public String getSegundoArgumento() {
        return "add";
    }

    public String execute(String[] args) {
        if (args.length < 6) {
            return "Usage: ticket add <ticketId> <cashierId> <productId> <quantity> [--p personalization1 --p personalization2 ...]";
        }
            try {
                String ticketId = args[2];
                String cashierId = args[3];
                int productId = Integer.parseInt(args[4]);
                int quantity = Integer.parseInt(args[5]);

                ArrayList<String> personalizations = new ArrayList<>();
                for (int i = 6; i < args.length; i++) {
                    if (args[i].startsWith("--p")) {
                        String personalization = args[i].substring(3); // despues de --p empieza la personalizacion
                        if (!personalization.isEmpty()) {
                            personalizations.add(personalization);
                        }
                    }
                }

                Product product = catalog.getProduct(productId);
                if (product == null) {
                    return "Product not found";
                }

                TicketModel ticket = ticketController.getTicket(ticketId);
                if (ticket == null) return "Ticket not found";
                Cashier cashier = userController.getCashier(cashierId);
                if (cashier == null) return "Cashier not found";

                if (product instanceof ProductMeeting || product instanceof ProductFood) {
                    for (ElementoTicket elementoTicket : ticket.getElementos()) {
                        if (elementoTicket.getProduct().getId() == productId)
                            return "Product already in ticket";

                    }
                }
                ticket.addProduct(product, quantity, personalizations);
                ticketController.printTicketInfo(ticket);
            } catch (NumberFormatException e) {
                return "Invalid number format for product ID or quantity";
            } catch (Exception e) {
                return "Error: " + e.getMessage();
            }
            return "ticket add: ok\n";
        }

    }