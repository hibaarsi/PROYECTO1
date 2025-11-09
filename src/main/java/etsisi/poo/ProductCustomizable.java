package etsisi.poo;

public class ProductCustomizable extends Product implements Customizable {
    private final int maxTexts;

    public ProductCustomizable(int id, String name, Category category, double price, int maxTexts) {
        super(id, name, category, price);
        if (maxTexts < 0)
            throw new IllegalArgumentException("It has to be a positive number");
        this.maxTexts = maxTexts;
    }

    @Override
    public int getMaxTexts() { return maxTexts; }
}
