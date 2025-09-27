package etsisi.poo;

public enum Category {
    MERCH(0.00),PAPELERIA(0.05), ROPA(0.07), LIBRO(0.10), ELECTRONICA(0.03);
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
