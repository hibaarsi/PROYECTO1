package etsisi.poo;


import java.util.HashMap;

public class Catalog {
    private HashMap<Integer, Product> items;
    private static final int MAX_ELEMENTS = 200;

    //Mensajes estáticos
    private static final String MSG_MAX_ELEMENTS = "Items limit reached";
    private static final String MSG_ADD_OK = "prod add: ok\n";
    private static final String MSG_ADD_DUPLICATE = "Product with id %d already exists";
    private static final String MSG_NO_PRODUCTS = "No products in catalog";
    private static final String MSG_CATALOG_HEADER = "Catalog:";
    private static final String MSG_LIST_OK = "prod list: ok\n";
    private static final String MSG_NOT_FOUND = "Product with id %d does not exist";
    private static final String MSG_REMOVE_OK = "prod remove: ok\n";
    private static final String MSG_UPDATE_OK = "prod update: ok\n";
    private static final String MSG_INVALID_CATEGORY = "Invalid category: %s";
    private static final String MSG_INVALID_PRICE = "Price must be a positive number: %s";
    private static final String MSG_UNKNOWN_FIELD = "Unknown field: %s";

    public Catalog() {
        this.items = new HashMap<>();
    }

    public void addProduct(Product product) {
        if (size() >= MAX_ELEMENTS) {
            System.out.println(MSG_MAX_ELEMENTS);
        } else if (existProduct(product.getId())) {
            System.out.println(String.format(MSG_ADD_DUPLICATE, product.getId()));
        } else {
            items.put(product.getId(), product);
            System.out.println(product);
            System.out.println(MSG_ADD_OK);
        }
    }

    public void listProducts() {
        if (items.isEmpty()) {
            System.out.println(MSG_NO_PRODUCTS);
            return;
        }
        System.out.println(MSG_CATALOG_HEADER);
        for (Product product : items.values()) {
            System.out.println(product);
        }
        System.out.println(MSG_LIST_OK);
    }

    public Product getProduct(int id) {
        if (!items.containsKey(id)) {
            System.out.println(String.format(MSG_NOT_FOUND, id));
        }
        return items.get(id);
    }

    public void removeProduct(int id) {
        if (!existProduct(id)) {
            System.out.println(String.format(MSG_NOT_FOUND, id));
        } else {
            Product removed = items.get(id);
            items.remove(id);
            System.out.println(removed);
            System.out.println(MSG_REMOVE_OK);
        }
    }

    public void updateProduct(int id, String field, String value) {
        if (!existProduct(id)) {
            System.out.println(String.format(MSG_NOT_FOUND, id));
        } else {
            Product product = items.get(id);

            switch (field.toUpperCase()) {
                case "NAME":
                    product.setName(value);
                    System.out.println(product);
                    System.out.println(MSG_UPDATE_OK);
                    break;

                case "CATEGORY":
                    try {
                        Category category = Category.valueOf(value.toUpperCase());
                        product.setCategory(category);
                        System.out.println(product);
                        System.out.println(MSG_UPDATE_OK);
                    } catch (IllegalArgumentException e) {
                        System.out.println(String.format(MSG_INVALID_CATEGORY, value));
                    }
                    break;

                case "PRICE":
                    double price = Double.parseDouble(value);
                    if (price <= 0) {
                        System.out.println(String.format(MSG_INVALID_PRICE, value));
                    } else {
                        product.setPrice(price);
                        System.out.println(product);
                        System.out.println(MSG_UPDATE_OK);
                    }
                    break;

                default:
                    System.out.println(String.format(MSG_UNKNOWN_FIELD, field));
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

