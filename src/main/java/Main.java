import Commands.CommandSuper;
import Commands.builtin.Echo;
import Commands.builtin.Exit;
import Commands.builtin.Pwd;
import Commands.builtin.TypeCmd;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Map<String, CommandSuper> commandMap = new HashMap<>();
        commandMap.put("exit", new Exit());
        commandMap.put("pwd", new Pwd());
        commandMap.put("echo", new Echo());
        commandMap.put("type", new TypeCmd(commandMap));

        while (true) {
            System.out.print("$ ");
            String inputLine = scanner.nextLine().trim();
            if (inputLine.isEmpty()) continue;

            String[] parts = inputLine.split(" ");
            String commandName = parts[0];
            String[] arguments = Arrays.copyOfRange(parts, 1, parts.length);

            CommandSuper command = commandMap.get(commandName.toLowerCase());

            if (command != null) {
                command.runCommand(arguments);
            } else {
                if (!ProcessRunner.findAndRunExecutable(parts)) {
                    System.out.println(commandName + ": command not found");
                }
            }
        }

    }
}
