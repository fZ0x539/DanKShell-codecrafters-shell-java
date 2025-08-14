package Commands.builtin;

import Commands.CmdType;
import Utility.ShellContext;
import core.ContextAwareCommandSuper;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
            Path candidateDirectory;
            if (path.startsWith("/")) {
                candidateDirectory = Paths.get(path).normalize();
            } else if (path.startsWith("~")) {
                candidateDirectory = Paths.get(System.getenv("HOME")).normalize();
            } else {
                candidateDirectory = getShellContext().getCurrentDirectory()
                        .resolve(path).normalize();
            }

            if (Files.isDirectory(candidateDirectory)) {
                getShellContext().setCurrentDirectory(candidateDirectory);
            } else {
                System.out.println("cd: " + path + ": No such file or directory");
            }
        } catch (InvalidPathException e) {
            System.out.println("cd: " + path + ": Invalid path");
        }
    }
}
