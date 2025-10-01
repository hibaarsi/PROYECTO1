package etsisi.poo;

public enum Category {
    MERCH(0.00),PAPELERIA(0.05), ROPA(0.07), LIBRO(0.10), ELECTRONICA(0.03);
    private double descuento;
    Category(double descuento) {
        this.descuento= descuento;
    }
    public double getDiscount() {
        return descuento;
    }
    public void setDiscount(double discount) {
        this.descuento = discount;
    }

}
