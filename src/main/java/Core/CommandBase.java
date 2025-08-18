package Core;

import Command.CmdType;
import Utility.Redirection.RedirectionContext;
import Utility.Redirection.RedirectionType;

import java.io.IOException;
import java.nio.file.*;

public abstract class CommandBase {
    private final String name;
    private final CmdType cmdType;

    public CommandBase(String name, CmdType cmdType) {
        this.name = name.toLowerCase();
        this.cmdType = cmdType;
    }

    public String getType() {
        return cmdType.getDescription();
    }


    public String getName() {
        return name;
    }

    public String tooManyArgs() {
        return name + ": too many arguments";
    }

    public abstract void runCommand(String[] inputArgs);

    //!   RedirectionContext contains:
//    private String[] partsBeforeOutput;
//    private String output;
//    private RedirectionType redirectionType;
//
    protected void redirectBulitinOutput(RedirectionContext resultContext) {

        try {
            Path outputPath = Paths.get(resultContext.getOutput());
            //Create file if it doesn't exist
            Files.createDirectories(outputPath.getParent());
            if (Files.notExists(outputPath))
                Files.createFile(outputPath);

            String content = String.join("", resultContext.getPartsBeforeOutput());

            switch (resultContext.getRedirectionType()) {
                case STDOUT -> Files.write(outputPath, (content + System.lineSeparator()).getBytes(), StandardOpenOption.WRITE);
                case STDERR -> System.out.println(content);
                case STDOUT_APPEND -> Files.writeString(outputPath, content + System.lineSeparator(), StandardOpenOption.APPEND);
                case STDERR_APPEND -> System.out.println(content);
            }
        } catch (InvalidPathException | IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
