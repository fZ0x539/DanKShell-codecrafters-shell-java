package Commands.builtin;

import Commands.CommandSuper;
import Commands.Type;

import java.nio.file.Paths;

public class Pwd extends CommandSuper {

    public Pwd(){
        super("pwd", Type.BUILTIN);
    }

    @Override
    public void runCommand(String[] inputArgs) {
        if(inputArgs.length > 0)
            System.out.println(tooManyArgs());
        else {
            String workingDir = Paths.get("").toAbsolutePath().toString();
            System.out.println(workingDir);
        }
    }
}
