package etsisi.poo;

import java.time.*;

public class ProductFood extends Product implements EventProduct {

    private final LocalDateTime eventDate; //fecha del evento
    private final int maxPeople;
    private static final Duration MIN_PLANNING = Duration.ofDays(3); //el tiempo minimo de planificación son 3 dias

    public ProductFood(int id, String name, double price, LocalDateTime eventDate, int maxPeople) {
        super(id, name, price); // sin categoría
        if (maxPeople < 1 || maxPeople > 100)
            System.out.println("maxPeople must be between 1 and 100");
        this.eventDate = eventDate;
        this.maxPeople = maxPeople;
        if (!hasEnoughPlanning()) {
            System.out.println("Warning: FoodProduct does not meet 3-day planning rule");
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
        return String.format("{class:ProductFood, id:%d, name:'%s', price:%.1f, eventDate:%s, maxPeople:%d}",
                id, name.replace("\"", ""), price, eventDate, maxPeople);
    }
}