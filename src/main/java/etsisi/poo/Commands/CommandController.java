package etsisi.poo.Commands;

import java.util.HashMap;
import java.util.Map;

public class CommandController {
    private Map<String, ICommand> commands = new HashMap<>();

    public void registerCommand(ICommand command) {
        String key= command.getPrimerArgumento()+":"+command.getSegundoArgumento();//para que identifique la primera y segunda parte del comando
        commands.put(key, command);
    }

    public String executeCommand(String primerArgumento, String segundoArgumento, String[] args) {
        String key  = primerArgumento+":"+segundoArgumento;//construye una clave unica de texto que se refiere al comando ej ticket:print
        ICommand command = commands.get(key);//busca en el mapa si hay un comando que tenga esa clave
            if (command == null) {
                return "Comando no encontrado";//si no lo encuentra
            } return command.execute(args);// si lo encuentra es que el comando escrito por el usuario esta bien, y lo ejecuta
    }
}
