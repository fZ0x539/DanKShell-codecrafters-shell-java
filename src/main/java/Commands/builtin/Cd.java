package Commands.builtin;

import Commands.CmdType;
import Utility.ShellContext;
import core.ContextAwareCommandSuper;

import java.nio.file.InvalidPathException;

public class Cd extends ContextAwareCommandSuper {

    public Cd(ShellContext shellContext) {
        super("cd", CmdType.BUILTIN, shellContext);
    }

    @Override
    public void runCommand(String[] inputArgs) {
        if (inputArgs.length != 1) {
            System.out.println("cd: wrong number of arguments");
            return;
        }
        String path = inputArgs[0].trim();

        try {
            getShellContext().setCurrentDirectory(path);
        } catch (InvalidPathException e) {
            System.out.println("cd: " + path + ": Invalid path");
        }
    }
}
