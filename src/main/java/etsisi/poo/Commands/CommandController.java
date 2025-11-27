package etsisi.poo.Commands;

import java.util.HashMap;
import java.util.Map;

public class CommandController {
    private Map<String, ICommand> commands = new HashMap<>();

    public void registerCommand(ICommand command) {
        //para que identifique la primera y segunda parte del comando
        String key = command.getPrimerArgumento() + ":" + command.getSegundoArgumento();//para que identifique la primera y segunda parte del comando
        commands.put(key, command);
    }

    public boolean executeCommand(String primerArgumento, String segundoArgumento, String[] args) {
        String key = primerArgumento + ":" + segundoArgumento;//construye una clave unica de texto que se refiere al comando ej ticket:print
        ICommand command = commands.get(key);//busca en el mapa si hay un comando que tenga esa clave
        if (command == null) {
            System.out.println("Comando no encontrado");
            return true;//si no lo encuentra
        }
        String comandoexecute = command.execute(args);
        // si lo encuentra es que el comando escrito por el usuario esta bien, y lo ejecuta
        if (comandoexecute != null) {
            System.out.println(comandoexecute);

        }
      boolean exit=  primerArgumento.equals("exit");

        return !exit;

    }
}
