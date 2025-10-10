package etsisi.poo;


import java.util.HashMap;

public class Catalog {
    private static final int MAX_ELEMENTS = 200;
    private HashMap<Integer, Product> items;


    public Catalog() {
        this.items = new HashMap<>();
    }

    public void addProduct(Product product) {
        if (size() >= MAX_ELEMENTS) {
            System.out.println("Items limit reached");
        } else if (existProduct(product.getId())) {
            System.out.println("Product with id " + product.getId() + " already exists");
        } else {
            items.put(product.getId(), product);
            String productString = product.toString();
            System.out.println(productString);
            System.out.println("prod add: ok\n");
        }
    }

    public void listProducts() {
        if (items.isEmpty()) {
            System.out.println("No products in catalog");
            return;
        }
        System.out.println("Catalog:");
        for (Product product : items.values()) {
            System.out.println(product);
        }
        System.out.println("prod list: ok\n");
    }

    public Product getProduct(int id) {
        if (!items.containsKey(id)) {
            System.out.println("Product with id " + id + " does not exist");
        }
        return items.get(id);
    }

    public void removeProduct(int id) {
        if (!existProduct(id)) {
            System.out.println("Product with id " + id + " does not exist");
        } else {
            Product removed = items.get(id);
            items.remove(id);
            System.out.println(removed.toString());
            System.out.println("prod remove:ok\n");
        }
    }

    public void updateProduct(int id, String field, String value) {
        if (!existProduct(id)) {
            System.out.println("Product with id " + id + " does not exist");
        } else {
            Product product = items.get(id);

            switch (field.toUpperCase()) {
                case "NAME":
                    product.setName(value);
                    System.out.println(product.toString());
                    System.out.println("prod update: ok\n");
                    break;

                case "CATEGORY":
                    try {
                        Category category = Category.valueOf(value.toUpperCase());
                        product.setCategory(category);
                        System.out.println(product.toString());
                        System.out.println("prod update: ok\n");
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("Invalid category: " + value);
                    }
                    break;

                case "PRICE":
                    double price = Double.parseDouble(value);
                    if (price <= 0) {
                        System.out.println("Price must be a positive number: " + value);
                    } else {
                        product.setPrice(price);
                        System.out.println(product.toString());
                        System.out.println("prod update: ok\n");
                    }

                    break;

                default:
                    System.out.println("Unknown field: " + field);
            }

        }

    }

    public int size() {
        return items.size();
    }

    public boolean existProduct(int id) {
        return items.containsKey(id);
    }


}
