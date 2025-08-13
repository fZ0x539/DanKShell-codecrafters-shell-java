package Commands.builtin;

import Commands.Type;
import Utility.ShellContext;
import core.ContextAwareCommandSuper;

public class Cd extends ContextAwareCommandSuper {

    public Cd(String name, Type type, ShellContext shellContext) {
        super("cd", Type.BUILTIN, shellContext);
    }

    @Override
    public void runCommand(String[] inputArgs) {
        if(inputArgs.length < 1){

        } else {

        }
    }
}
