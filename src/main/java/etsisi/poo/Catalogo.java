package etsisi.poo;

import java.util.HashMap;

public class Catalogo {
    private static final int max_ELEMENTS = 200;
    private HashMap<Integer, Product> items;
    ;

    public Catalogo() {
        this.items = new HashMap<>();
    }

    public void addProduct(Product product) { // revisar
        if (items.size() >= max_ELEMENTS)
            throw new IllegalArgumentException("Items limit reached");
        if (items.containsKey(product.getId())) {
            throw new IllegalArgumentException("Product with id " + product.getId() + " already exists");
        }
        items.put(product.getId(), product);
        System.out.println("prod add: ok");
    }

    public void listProducts() {
        if (items.isEmpty()) {
            System.out.println("No products in catalog");
        }
        System.out.println("Catalog:");
        for (Product product : items.values()) {
            System.out.println(product);
        }
    }

    public Product getProduct(int id) {
        if (!items.containsKey(id)) {
            throw new IllegalArgumentException("Product with id " + id + " does not exist");
        }
        return items.get(id);
    }

    public void removeProduct(int id) {
        if (!items.containsKey(id)) {
            throw new IllegalArgumentException("Product with id " + id + " does not exist");
        }
        items.remove(id);
    }

    public void updateProduct(int id, String field, String value) {
        //revisar
        if (!items.containsKey(id)) {
            throw new IllegalArgumentException("Product with id " + id + " does not exist");
        }
        Product product = items.get(id);

        switch (field.toUpperCase()) {
            case "NAME":
                product.setNombre(value);
                break;

            case "CATEGORY":
                try {
                    Category category = Category.valueOf(value.toUpperCase());
                    product.setCategory(category);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid category: " + value);
                }
                break;

            case "PRICE":
                try {
                    double price = Double.parseDouble(value);
                    product.setPrecio(price);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Price must be a positive number: " + value);
                }
                break;

            default:
                throw new IllegalArgumentException("Unknown field: " + field);
        }

        System.out.println(product);
        System.out.println("prod update: ok");
    }

    public int size() {
        return items.size();
    }

    public boolean existProduct(int id) {
        return items.containsKey(id);
    }


}
