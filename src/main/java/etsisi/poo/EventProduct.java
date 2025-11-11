package etsisi.poo;

import java.time.Duration;
import java.time.LocalDateTime;

public interface EventProduct {
    LocalDateTime getEventDate();

    int getMaxPeople();

    Duration getMinPlanning(); //tiempo mínimo de planificación

    default boolean hasEnoughPlanning() { //para ver si hay suficiente tiempo de planificación
        return Duration.between(LocalDateTime.now(), getEventDate()).compareTo(getMinPlanning()) >= 0;
    }
}