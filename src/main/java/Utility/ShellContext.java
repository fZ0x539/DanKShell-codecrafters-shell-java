package Utility;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ShellContext {
    private Path currentDirectory;

    public ShellContext() {
        this.currentDirectory = Paths.get(System.getProperty("user.dir"));
    }

    public Path getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(Path currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    public String resolveExecutablePath(String execName) {
        String pathEnv = System.getenv("PATH");
        String[] dirArray = pathEnv.split(":");
        for (String directoryPath : dirArray) {
            Path candidatePath = Paths.get(directoryPath, execName);

            if (Files.exists(candidatePath) && Files.isExecutable(candidatePath)) {
                return candidatePath.toString();
            }
        }
        return null;
    }


}
