package Commands.builtin;

import Commands.CmdType;
import Utility.ShellContext;
import core.CommandBase;
import core.ContextAwareCommandSuper;

import java.util.Map;

public class Type extends ContextAwareCommandSuper {

    private final Map<String, CommandBase> commandMap;

    public Type(Map<String, CommandBase> commandMap, ShellContext shellContext) {
        super("type", CmdType.BUILTIN, shellContext);
        this.commandMap = commandMap;
    }

    @Override
    public void runCommand(String[] inputArgs) {
//        String commandName = inputArgs[0];
        for (String commandName : inputArgs) {
            CommandBase command = commandMap.get(commandName.toLowerCase());

            //If command isn't registered internally then search PATHs for exec
            if (command == null) {
                String execPath = getShellContext().resolveExecutablePath(commandName);
                if (execPath != null)
                    System.out.println(commandName + " is " + execPath);
                else
                    System.out.println(commandName + ": not found");
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
    }
}
