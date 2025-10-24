package etsisi.poo;

public class Product {
    //Constantes públicas
    public static final int MAX_NAME_LENGTH = 100;

    //Mensajes de validación
    private static final String NEEDS_TO_BE_POSITIVE = "It needs to be a positive number";
    private static final String NOT_EMPTY = "It can't be empty";
    private static final String SIZE_LIMIT_MESSAGE = "No more than 100 characters";
    private static final String PRICE_RESTRICTION = "Be greater than 0";

    //Mensajes de formato
    private static final String PRODUCT_FORMAT = "{class:Product, id:%d, name:'%s', category:%s, price:%.1f}";

    //Atributos
    private final int id; // es único
    private String name;
    private Category category;
    private double price;

    // 🔹 Constructor
    public Product(int id, String name, Category category, double price) {
        if (id < 0) throw new IllegalArgumentException(NEEDS_TO_BE_POSITIVE);
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException(NOT_EMPTY);
        if (name.length() > MAX_NAME_LENGTH) throw new IllegalArgumentException(SIZE_LIMIT_MESSAGE);
        if (price <= 0) throw new IllegalArgumentException(PRICE_RESTRICTION);

        this.id = id;
        this.name = name.trim();
        this.category = category;
        this.price = price;
    }

    // 🔹 Getters
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

    // 🔹 Setters con validaciones
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException(NOT_EMPTY);
        if (name.length() > MAX_NAME_LENGTH) throw new IllegalArgumentException(SIZE_LIMIT_MESSAGE);
        this.name = name.trim();
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPrice(double price) {
        if (price <= 0) throw new IllegalArgumentException(PRICE_RESTRICTION);
        this.price = price;
    }

    // 🔹 equals y hashCode
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

    // 🔹 toString limpio y con formato definido
    @Override
    public String toString() {
        String cleanName = name.replace("\"", ""); // elimina comillas dobles
        return String.format(PRODUCT_FORMAT, id, cleanName, category, price);
    }
}

