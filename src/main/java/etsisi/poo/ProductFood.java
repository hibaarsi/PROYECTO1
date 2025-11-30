package etsisi.poo;

import java.time.*;

public class ProductFood extends Product implements EventProduct {

    private final LocalDateTime eventDate; //fecha del evento
    private final int maxPeople;
    private static final Duration MIN_PLANNING = Duration.ofDays(3); //el tiempo minimo de planificación son 3 dias
    private int actualPeople = 0;

    public ProductFood(int id, String name, double price, LocalDateTime eventDate, int maxPeople) {
        super(id, name, price); // sin categoría
        this.eventDate = eventDate;
        this.maxPeople = maxPeople;

        if (maxPeople < 1 || maxPeople > 100 || !hasEnoughPlanning()) {
            System.out.println("Error adding product\n");
        }
    }

    public void setActualPeople(int n) {
        this.actualPeople = n;
    }

    public int getActualPeople() {
        return actualPeople;
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
        if (actualPeople > 0) {
            return String.format(java.util.Locale.US, "{class:Food, id:%d, name:'%s', price:%.1f, date of Event:%s, max people allowed:%d, actual people in event:%d}",
                    id, name.replace("\"", ""), price, eventDate.toLocalDate(), maxPeople, getActualPeople());
        } else
            return String.format(java.util.Locale.US, "{class:Food, id:%d, name:'%s', price:0.0, date of Event:%s, max people allowed:%d}",
                    id, name.replace("\"", ""), eventDate.toLocalDate(), maxPeople);

    }
}