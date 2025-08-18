import Command.Builtin.*;
import Core.CommandBase;
import Utility.Lexer.InputLexer;
import Utility.ShellContext;
import org.jline.reader.Completer;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static View.ConsoleUI.printInfo;


public class Main {
    public static void main(String[] args) throws Exception {
//        Scanner scanner = new Scanner(System.in);
        System.setProperty("org.jline.terminal.jansi.verbose", "false");
        System.setProperty("org.jline.utils.Log.level", "OFF");
        java.util.logging.Logger.getLogger("org.jline").setLevel(java.util.logging.Level.OFF);

        ShellContext shellContext = new ShellContext();
        Map<String, CommandBase> commandMap = new HashMap<>();
        commandMap.put("exit", new Exit());
        commandMap.put("pwd", new Pwd(shellContext));
        commandMap.put("echo", new Echo());
        commandMap.put("cd", new Cd(shellContext));
        commandMap.put("type", new Type(commandMap, shellContext));

        Completer cmdCompleter = new StringsCompleter(commandMap.keySet());



        Terminal terminal = TerminalBuilder
                .builder()
                .system(true)
                .type("ansi")
                .build();

        LineReader reader = LineReaderBuilder
                .builder()
                .terminal(terminal)
                .completer(cmdCompleter)
                .option(LineReader.Option.DISABLE_EVENT_EXPANSION, true) //Disable JLines lexer in favor of my own.
                .build();
        InputLexer inputLexer = new InputLexer();

        while (true) {

            String inputLine;

            try {
                inputLine = reader.readLine("$ ");
                if (inputLine.isEmpty()) continue;

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
            } catch (NullPointerException e) {
                continue;
            } catch (RuntimeException e) {
                System.err.println(e.getMessage());
            }

        }

    }
}
