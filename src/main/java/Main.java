import Commands.CommandSuper;
import Commands.Echo;
import Commands.Exit;
import Commands.TypeCmd;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Map<String, CommandSuper> commandMap = new HashMap<>();
        commandMap.put("exit", new Exit());
        commandMap.put("echo", new Echo());
        commandMap.put("type", new TypeCmd(commandMap));

        while (true) {
            System.out.print("$ ");
            String commandName = scanner.next();
            CommandSuper command = commandMap.get(commandName.toLowerCase());

            if (command != null){
                command.runCommand(scanner);
            }
            else {
                System.out.println(commandName + ": command not found");
                scanner.nextLine();
            }
        }
    }
}
