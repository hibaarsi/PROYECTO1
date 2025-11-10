package etsisi.poo.Commands;

public interface ICommand {
    String execute(String[] args);
    String getName();//la primera parte del comando
    String getSubName();// la segunda parte del comando
}
