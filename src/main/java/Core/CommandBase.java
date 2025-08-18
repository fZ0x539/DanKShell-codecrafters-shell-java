package Core;

import Command.CmdType;
import Utility.Redirection.RedirectionContext;
import Utility.Redirection.RedirectionType;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Objects;

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

            //? potential future use case, leaving it here for now
//            StandardOpenOption operationType = Objects
//                    .equals(resultContext.getRedirectionType().toString(), ">>")
//                    ? StandardOpenOption.APPEND : StandardOpenOption.WRITE;

            if(resultContext.getRedirectionType() == RedirectionType.STDOUT)
                Files.write(outputPath, content.getBytes(), StandardOpenOption.WRITE);
            else
                System.out.println(content);
        }
        catch (InvalidPathException | IOException e){
            System.err.println(e.getMessage());
        }
    }
}
