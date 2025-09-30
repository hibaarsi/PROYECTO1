package etsisi.poo;

public class Product {
    public static final int MAX_NAME_LENGTH = 100;
    private final int id; // es unico
    private String nombre;
    private Category category;
    private double precio;

    public Product(int id, String nombre, Category category, double precio) {

        if (id <= 0)  throw new IllegalArgumentException("Tiene que ser un numero positivo");
        if (nombre == null || nombre.trim().isEmpty()) throw new IllegalArgumentException("No puede estar vacio");
        if (nombre.length() > MAX_NAME_LENGTH) throw new IllegalArgumentException("No puede exceder 100 caracteres");
        if (precio <= 0) throw new IllegalArgumentException("Tiene que ser mayor a 0");
        this.id = id;
        this.nombre = nombre.trim();
        this.category = category;
        this.precio = precio;
    }
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public Category getCategory() {
        return category;
    }
    
    public double getPrecio() {
        return precio;
    }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) throw new IllegalArgumentException("No puede estar vacio");

        if (nombre.length() > MAX_NAME_LENGTH) throw new IllegalArgumentException("No puede exceder 100 caracteres");

        this.nombre = nombre.trim();
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    
    public void setPrecio(double precio) {
        if (precio <= 0) {
            throw new IllegalArgumentException("Tiene que ser mayor a 0");
        }
        this.precio = precio;
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
        return String.format("Producto{id=%d, nombre='%s', category=%s, precio=%.2f}", 
                            id, nombre, category, precio);
    }

}
