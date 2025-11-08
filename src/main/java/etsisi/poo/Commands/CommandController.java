package etsisi.poo.Commands;

import java.util.HashMap;
import java.util.Map;

public class CommandController {
    private Map<String, ICommand> commands = new HashMap<>();

    public void registerCommand(String commandName, ICommand command) {
        commands.put(commandName, command);
    }

    public String executeCommand(String commandName, String[] args) {
        String result = "";
        ICommand command = commands.get(commandName);
        if (commands.containsKey(commandName)) {
            if (command != null) {
                result = command.execute(args);
            } else System.out.println("Comando no encontrado");

        }
        return result;
    }
}
