package core;

import Commands.Type;
import Utility.ShellContext;

public abstract class CommandSuper extends  CommandBase {

    public CommandSuper(String name, Type type) {
        super(name, type);
    }

    public abstract void runCommand(String[] inputArgs);

}
