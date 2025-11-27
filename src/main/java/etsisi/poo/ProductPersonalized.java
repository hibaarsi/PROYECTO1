package etsisi.poo;

public class ProductPersonalized extends RegularProduct implements Personalized {

    private final int maxPersonal;

    public ProductPersonalized(int id, String name, Category category, double price, int maxPersonal) {
        super(id, name, category, price);
        if (maxPersonal < 1) {
            throw new IllegalArgumentException("maxPersonal must be >= 1");
        }
        this.maxPersonal = maxPersonal;
    }

    @Override
    public int getMaxPersonal() {
        return maxPersonal;
    }

    @Override
    public String toString() {
        return String.format(java.util.Locale.US,"{class:ProductPersonalized, id:%d, name:'%s', category:%s, price:%.1f, maxPersonal:%d}",
                id, name.replace("\"", ""), getCategory(), price, maxPersonal);
    }
}
