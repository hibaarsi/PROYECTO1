package etsisi.poo.Commands;

public interface ICommand {
    String execute(String[] args);
    String getPrimerArgumento();//la primera parte del comando
    String getSegundoArgumento();// la segunda parte del comando
}
