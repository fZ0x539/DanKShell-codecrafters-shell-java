package Commands.builtin;

import Commands.Type;
import Utility.ShellContext;
import core.ContextAwareCommandSuper;

import java.nio.file.Paths;

public class Pwd extends ContextAwareCommandSuper {

    public Pwd(ShellContext shellContext){
        super("pwd", Type.BUILTIN, shellContext);
    }

    @Override
    public void runCommand(String[] inputArgs) {
        if(inputArgs.length > 0)
            System.out.println(tooManyArgs());
        else {
            String workingDir = getShellContext().getCurrentDirectory().toString();
            System.out.println(workingDir);
        }
    }
}
