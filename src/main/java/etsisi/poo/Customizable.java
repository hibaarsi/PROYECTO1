package etsisi.poo;

import java.util.List;

public interface Customizable {
    int getMaxTexts();

    default double getPriceWithTexts(double basePrice, int quantity, List<String> texts) {
        int nTexts = (texts == null) ? 0 : texts.size();
        if (nTexts > getMaxTexts()) {
            throw new IllegalArgumentException("Too many customization texts");
        }
        double multiplier = 1 + 0.10 * nTexts;
        return basePrice * multiplier * quantity;
    }
}
