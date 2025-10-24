package etsisi.poo;

public class Usuarios {

    private String name;
    private String email;

    public Usuarios(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name; }
    public String getEmail() {
        return email; }

    public String toString() {
        return "{name:" + name + "', email:'" + email + "'}";
    }
}
