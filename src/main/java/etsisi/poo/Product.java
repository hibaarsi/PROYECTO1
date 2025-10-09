package etsisi.poo;

public class Product {
    public static final int MAX_NAME_LENGTH = 100;
    private final int id; // es unico
    private String name;
    private Category category;
    private double price;

    public Product(int id, String name, Category category, double price) {

        if (id <= 0) throw new IllegalArgumentException("It needs to be a positive number");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("It can't be empty");
        if (name.length() > MAX_NAME_LENGTH) throw new IllegalArgumentException("No more than 100 characters");
        if (price <= 0) throw new IllegalArgumentException("Be greater than 0");
        this.id = id;
        this.name = name.trim();
        this.category = category;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("It can't be empty");

        if (name.length() > MAX_NAME_LENGTH) throw new IllegalArgumentException("No more than 100 characters");

        this.name = name.trim();
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Be greater than 0");
        }
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product producto = (Product) o;
        return id == producto.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        String cleanName = name.replace("\"", ""); //esto es para quitar las comillas dobles
        return String.format("{class:Product, id:%d, name:'%s', category:%s, price:%.1f}",
                id, cleanName, category, price);
    }

}
