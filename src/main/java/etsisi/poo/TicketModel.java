package etsisi.poo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TicketModel {
    private String id;
    private TicketStatus ticketStatus;
    private ArrayList<Product> products;
    private LocalDateTime openDate;
    private LocalDateTime endDate;
    private Cashier cashier;
    private Client client;
    private final List<ElementoTicket> elementos;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm");
    private static ArrayList<String> listaIds = new ArrayList<>();

    public TicketModel(String id) {
        if (listaIds.contains(id)) {
            System.out.println("El id ya existe");
        }
        this.id = id;
        this.elementos = new ArrayList<>();
        this.products = new ArrayList<>();
        this.ticketStatus = TicketStatus.VACIO;
        this.openDate = LocalDateTime.now();

    }

    public TicketModel() {
        this.id = generateId();
        this.products = new ArrayList<>();
        this.ticketStatus = TicketStatus.VACIO;
        this.openDate = LocalDateTime.now();
        this.elementos = new ArrayList<>();
    }
    public Cashier getCashier(){
        return cashier;
    }
    public Client getClient(){
        return client;
    }

    private String generateId() {
        String baseId = LocalDate.now().format(DATE_FORMATTER);
        Random random = new Random();
        String newId;
        do {
            newId = baseId + "-" + String.format("%05d", random.nextInt(100000));
        } while (listaIds.contains(newId));
        listaIds.add(newId);
        return newId;
    }

    public void addProduct(Product product,int cantidad) {// mejorar dependiendo de qur producto meto
        if (isClosed()) {
            System.out.println("No se pueden añadir productos, esta cerrado");
        }
        if (product instanceof ProductFood || product instanceof ProductReunion) {
            for (Product p : products) {
                if (p instanceof ProductFood || p instanceof ProductReunion) {
                    System.out.println("No se pueden añadir productos de tipo comida o reunion");
                    return;
                }
            }
        }
        ElementoTicket elemento = new ElementoTicket(product,cantidad);
        elementos.add(elemento);
        products.add(product);
        if (ticketStatus == TicketStatus.VACIO)
            ticketStatus = TicketStatus.ACTIVO;
    }

    public void removeProduct(Product product) {// mejorar dependiendo d productos
        if (isClosed()) {
            System.out.println("No se pueden eliminar productos, esta cerrado");
        }
        products.remove(product);
        if (products.isEmpty())
            ticketStatus = TicketStatus.VACIO;

    }

    public void close() {
        if (!isClosed()) {
            ticketStatus = TicketStatus.CERRADO;
            endDate = LocalDateTime.now();
            String cierre = "-" + endDate.format(DATE_FORMATTER);
            listaIds.remove(id);
            id += cierre;
            listaIds.add(id);
        }

    }

    public boolean isClosed() {
        return ticketStatus == TicketStatus.CERRADO;
    }

    public String getId() {
        return id;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }


}
