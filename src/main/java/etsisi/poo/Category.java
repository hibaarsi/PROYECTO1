package etsisi.poo;

public enum Category {
    MERCH(0.00), STATIONERY(0.05), CLOTHES(0.07), BOOK(0.10), ELECTRONICS(0.03);
    private double discount;

    Category(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

}
