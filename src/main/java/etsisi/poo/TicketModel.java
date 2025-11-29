package etsisi.poo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TicketModel {
    private String id;
    private TicketStatus ticketStatus;
    private ArrayList<Product> products;
    private LocalDateTime openDate;
    private LocalDateTime endDate;

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
        this.ticketStatus = TicketStatus.EMPTY;
        this.openDate = LocalDateTime.now();

    }

    public TicketModel() {
        this.id = generateId();
        this.products = new ArrayList<>();
        this.ticketStatus = TicketStatus.EMPTY;
        this.openDate = LocalDateTime.now();
        this.elementos = new ArrayList<>();
    }

    public List<ElementoTicket> getElementos() {//para leer desde fuera las lineas del ticket
        return elementos;
    }

    public double getTotal() { //calculo del total bruto del ticket
        double total = 0.0;
        for (ElementoTicket e : elementos) {
            Product p = e.getProduct();   // asumo que ElementoTicket tiene getProduct()
            int cantidad = e.getQuantity(); // y getCantidad()
            total += p.getPrice() * cantidad;
        }
        return total;
    }

    private String generateId() {
        String baseId = LocalDateTime.now().format(DATE_FORMATTER);
        Random random = new Random();
        String newId;
        do {
            newId = baseId + "-" + String.format("%05d", random.nextInt(100000));
        } while (listaIds.contains(newId));
        listaIds.add(newId);
        return newId;
    }

    public void addProduct(Product product, int cantidad, ArrayList<String> personalizados) {// mejorar dependiendo de qur producto meto
        if (isClosed()) {
            System.out.println("You cant add more products, its closed");
            return;
        }

        if (product instanceof ProductFood || product instanceof ProductMeeting) {

            for (ElementoTicket e : elementos) {
                if (e.getProduct() instanceof ProductFood || e.getProduct() instanceof ProductMeeting) {
                    System.out.println("No se pueden añadir productos de tipo comida o reunion"); // mejor quitarlo
                    return;
                }
            }

            if (product instanceof ProductFood) //guardar las personas dentro del evento
                ((ProductFood) product).setActualPeople(cantidad);

            if (product instanceof ProductMeeting)
                ((ProductMeeting) product).setActualPeople(cantidad);

            double finalPrice = product.getPrice() * cantidad;
            product.setPrice(finalPrice);

            elementos.add(new ElementoTicket(product, 1, personalizados)); //se añade 1 vez solo
            products.add(product);

            if (ticketStatus == TicketStatus.EMPTY)
                ticketStatus = TicketStatus.ACTIVE;

            return;
        }

        ElementoTicket elemento = new ElementoTicket(product, cantidad, personalizados);
        elementos.add(elemento);
        products.add(product);
        if (ticketStatus == TicketStatus.EMPTY)
            ticketStatus = TicketStatus.ACTIVE;
    }

    public void removeProduct(Product product) {// mejorar dependiendo d productos
        if (isClosed()) {
            System.out.println("You cant add morw products, its closed");
            return;
        }
        // con iteradores primero lo eliminados de ls lista de elementos todas las instancias y de los productos
        Iterator<ElementoTicket> elementoTicket = elementos.iterator();
        while (elementoTicket.hasNext()) {
            ElementoTicket e = elementoTicket.next();
            if (e.getProduct().getId()== product.getId()) {
                elementoTicket.remove();
            }
        }


        Iterator<Product> producto = products.iterator();
        while (producto.hasNext()) {
            Product p = producto.next();
            if (p.getId()== product.getId()) {
                producto.remove();
            }
        }

        products.remove(product);
        if (products.isEmpty())
            ticketStatus = TicketStatus.EMPTY;

    }

    public void close() {
        if (!isClosed()) {
            ticketStatus = TicketStatus.CLOSED;
            endDate = LocalDateTime.now();
            String cierre = "-" + endDate.format(DATE_FORMATTER);
            listaIds.remove(id);
            id += cierre;
            listaIds.add(id);
        }

    }

    public boolean isClosed() {
        return ticketStatus == TicketStatus.CLOSED;
    }

    public String getId() {
        return id;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }


}
