package etsisi.poo;

import java.time.*;

public class ProductFood extends Product implements EventProduct {

    private final LocalDateTime eventDate; //fecha del evento
    private final int maxPeople;
    private static final Duration MIN_PLANNING = Duration.ofDays(3); //el tiempo minimo de planificación son 3 dias

    public ProductFood(int id, String name, double price, LocalDateTime eventDate, int maxPeople) {
        super(id, name, null, price); // sin categoría
        if (maxPeople < 1 || maxPeople > 100)
            throw new IllegalArgumentException("The capacity of people is 100");
        this.eventDate = eventDate;
        this.maxPeople = maxPeople;
        if (!hasEnoughPlanning()) {
            System.out.println("Warning: FoodProduct does not meet 3-day planning rule");
        }
    }

    @Override
    public LocalDateTime getEventDate() { return eventDate; }

    @Override
    public int getMaxPeople() { return maxPeople; }

    @Override
    public Duration getMinPlanning() { return MIN_PLANNING; }
}