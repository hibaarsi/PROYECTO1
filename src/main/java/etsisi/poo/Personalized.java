package etsisi.poo;

import java.util.List;

public interface Personalized {

    int getMaxPersonal();

    default double getPriceWithTexts(double basePrice, int quantity, List<String> texts) {
        int nTexts;
        if (texts == null) {
            nTexts = 0;
        } else {
            nTexts = texts.size();
        }

        if (nTexts > getMaxPersonal()) {
            System.out.println("Too many customization texts.Use max: " +getMaxPersonal());

        }
        double multiplier = 1 + (0.10 * nTexts);
        return basePrice * multiplier * quantity;
    }
}
