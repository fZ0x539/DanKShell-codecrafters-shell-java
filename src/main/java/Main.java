import core.CommandBase;
import core.CommandSuper;
import Commands.builtin.Echo;
import Commands.builtin.Exit;
import Commands.builtin.Pwd;
import Commands.builtin.TypeCmd;
import Utility.ShellContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        ShellContext shellContext = new ShellContext();
        Map<String, CommandBase> commandMap = new HashMap<>();
        commandMap.put("exit", new Exit());
        commandMap.put("pwd", new Pwd(shellContext));
        commandMap.put("echo", new Echo());
        commandMap.put("type", new TypeCmd(commandMap, shellContext));

        while (true) {
            System.out.print("$ ");
            String inputLine = scanner.nextLine().trim();
            if (inputLine.isEmpty()) continue;

            String[] parts = inputLine.split(" ");
            String commandName = parts[0];
            String[] arguments = Arrays.copyOfRange(parts, 1, parts.length);

            CommandBase command = commandMap.get(commandName.toLowerCase());

            if (command != null) {
                command.runCommand(arguments);
            } else {
                if (!ProcessRunner.findAndRunExecutable(parts, shellContext)) {
                    System.out.println(commandName + ": command not found");
                }
            }
        }

    }
}
