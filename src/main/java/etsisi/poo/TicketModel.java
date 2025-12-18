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

    public TicketModel(String id) {
        this.id = id;
        this.elementos = new ArrayList<>();
        this.products = new ArrayList<>();
        this.ticketStatus = TicketStatus.EMPTY;
        this.openDate = LocalDateTime.now();
    }

    public List<ElementoTicket> getElementos() {//para leer desde fuera las lineas del ticket
        return elementos;
    }

    public static String calculateID() {
        String baseId = LocalDateTime.now().format(DATE_FORMATTER);
        Random random = new Random();
        return baseId + "-" + String.format("%05d", random.nextInt(100000));
    }

    public void addProduct(Product product, int cantidad, ArrayList<String> personalizados) {
        if (isClosed()) {
            System.out.println("You cant add more products, its closed");
            return;
        }

        if (product instanceof ProductPersonalized pp && personalizados != null && !personalizados.isEmpty()) {
            ProductPersonalized productWithPersonalization = new ProductPersonalized(
                    pp.getId(),
                    pp.getName(),
                    pp.getCategory(),
                    pp.getBasePrice(), //precio base sin personalizaciones
                    pp.getMaxPersonal()
            );
            productWithPersonalization.setPersonalizations(personalizados);
            elementos.add(new ElementoTicket(productWithPersonalization, cantidad, personalizados));
            products.add(productWithPersonalization);

        } else if (product instanceof EventProducts) {
            for (ElementoTicket e : elementos) {
                if (e.getProduct() instanceof EventProducts) {
                    System.out.println("No se pueden añadir productos de tipo comida o reunion");
                    return;
                }
            }

            ((EventProducts) product).setActualPeople(cantidad);

            double finalPrice = product.getPrice() * cantidad;
            product.setPrice(finalPrice);

            elementos.add(new ElementoTicket(product, 1, personalizados)); //se añade 1 vez solo
            products.add(product);

        } else {
            ElementoTicket elemento = new ElementoTicket(product, cantidad, personalizados);
            elementos.add(elemento);
            products.add(product);
        }

        if (ticketStatus == TicketStatus.EMPTY) {
            ticketStatus = TicketStatus.OPEN;
        }
    }

    public void removeProduct(Product product) {
        if (isClosed()) {
            System.out.println("You cant add more products, its closed");
            return;
        }

        // con iteradores primero lo eliminados de la lista de elementos todas las instancias y de los productos
        Iterator<ElementoTicket> elementoTicket = elementos.iterator();
        while (elementoTicket.hasNext()) {
            ElementoTicket e = elementoTicket.next();
            if (e.getProduct().getId() == product.getId()) {
                elementoTicket.remove();
            }
        }

        Iterator<Product> producto = products.iterator();
        while (producto.hasNext()) {
            Product p = producto.next();
            if (p.getId() == product.getId()) {
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
            id += cierre;
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