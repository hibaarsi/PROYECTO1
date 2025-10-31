package etsisi.poo;

public abstract class Users {

    protected String name;
    protected String email;

    public Users(String name, String email) {
        this.name = name;
        this.email = email;
    }//

    public String getName() {
        return name; }
    public String getEmail() {
        return email; }

    public String toString() {
        return "{name:" + name + "', email:'" + email + "'}";
    }
}
