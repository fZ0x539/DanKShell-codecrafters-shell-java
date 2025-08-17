package Command.Builtin;

import Command.CmdType;
import Utility.ShellContext;
import Core.ContextAwareCommandSuper;

public class Pwd extends ContextAwareCommandSuper {

    public Pwd(ShellContext shellContext){
        super("pwd", CmdType.BUILTIN, shellContext);
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
