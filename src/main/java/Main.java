import Commands.builtin.*;
import Utility.Lexer.InputLexer;
import core.CommandBase;
import Utility.ShellContext;

import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        InputLexer inputLexer = new InputLexer();

        ShellContext shellContext = new ShellContext();
        Map<String, CommandBase> commandMap = new HashMap<>();
        commandMap.put("exit", new Exit());
        commandMap.put("pwd", new Pwd(shellContext));
        commandMap.put("echo", new Echo());
        commandMap.put("cd", new Cd(shellContext));
        commandMap.put("type", new Type(commandMap, shellContext));

        while (true) {
            System.out.print("$ ");
            String inputLine = scanner.nextLine().trim();
            if (inputLine.isEmpty()) continue;

            try{
                List<String> tokens = inputLexer.tokenizeInput(inputLine);
                String commandName = tokens.getFirst().toLowerCase();
                String[] arguments = tokens.subList(1, tokens.size()).toArray(new String[0]);

                CommandBase command = commandMap.get(commandName.toLowerCase());

                if (command != null) {
                    command.runCommand(arguments);
                } else {
                    if (!ProcessRunner.findAndRunExecutable(tokens.toArray(new String[0]), shellContext)) {
                        System.out.println(commandName + ": command not found");
                    }
                }
            } catch (NullPointerException e){
                continue;
            }

        }

    }
}
