package etsisi.poo;

import java.time.*;

public class ProductReunion extends Product implements EventProduct {
    private final LocalDateTime eventDate; //cuando es la reunion
    private final int maxPeople;
    private static final Duration MIN_PLANNING = Duration.ofHours(12); //tiempo min de antelacion para programarla

    public ProductReunion(int id, String name, double price, LocalDateTime eventDate, int maxPeople) {
        super(id, name, price);
        if (maxPeople < 1 || maxPeople > 100) //personas
            throw new IllegalArgumentException("maxPeople must be between 1 and 100");
        this.eventDate = eventDate;
        this.maxPeople = maxPeople;
        if (!hasEnoughPlanning()) { //comprara el tiempo que falta entre LocalDateTime.now() y eventdate
            //System.out.println("Warning: MeetingProduct does not meet 12-hour planning rule");
            throw new IllegalArgumentException("Error adding product");

        }
    }

    @Override
    public LocalDateTime getEventDate() {
        return eventDate;
    }

    @Override
    public int getMaxPeople() {
        return maxPeople;
    }

    @Override
    public Duration getMinPlanning() {
        return MIN_PLANNING;
    }

    @Override
    public String toString() {
        return String.format("{class:Meeting, id:%d, name:'%s', price:%.1f, date of Event:%s, max people allowed:%d}",
                id, name.replace("\"", ""), price, eventDate.toLocalDate(), maxPeople);
    }
}

