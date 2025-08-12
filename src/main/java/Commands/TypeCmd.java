package Commands;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class TypeCmd extends CommandSuper {

    private final Map<String, CommandSuper> commandMap;

    public TypeCmd(Map<String, CommandSuper> commandMap) {
        super("type", Type.BUILTIN);
        this.commandMap = commandMap;
    }

    @Override
    public void runCommand(String[] inputArgs) {
        String commandName = inputArgs[0];
        CommandSuper command = commandMap.get(commandName.toLowerCase());

        //If command isn't registered internally then search PATHs for exec
        if (command == null) {
            searchPathsForExec(commandName);
        }
        // If command is registered, print out the name and type
        else if (commandMap.containsKey(commandName.toLowerCase())) {
            System.out.println(command.getName() + command.getType());
        }
        //Otherwise not found
        else {
            System.out.println(commandName + ": not found");
        }
    }

    public void searchPathsForExec(String commandName) {
        String pathEnv = System.getenv("PATH");
        String[] dirArray = pathEnv.split(":");
        boolean found = false;
        for (String directoryPath : dirArray) {
            Path candidatePath = Paths.get(directoryPath, commandName);

            if (Files.exists(candidatePath) && Files.isExecutable(candidatePath)) {
                System.out.println(commandName + " is " + candidatePath);
                found = true;
            }
        }
        if (!found)
            System.out.println(commandName + ": not found");
    }
}
