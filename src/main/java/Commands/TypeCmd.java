package Commands;

import java.util.Map;
import java.util.Scanner;

public class TypeCmd extends CommandSuper{

    private final Map<String, CommandSuper> commandMap;

    public TypeCmd(Map<String, CommandSuper> commandMap){
        super("type", Type.BUILTIN);
        this.commandMap = commandMap;
    }

    @Override
    public void runCommand(Scanner scanner) {
        String commandName = scanner.next();
        CommandSuper command = commandMap.get(commandName.toLowerCase());
        if (command != null){
            System.out.println(command.getName() + command.getType());
        }
        else {
            System.out.println(commandName + ": not found");
            scanner.nextLine();
        }
    }
}
