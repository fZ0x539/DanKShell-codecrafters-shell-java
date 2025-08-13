package core;

import Commands.Type;
import Utility.ShellContext;

public abstract class ContextAwareCommandSuper extends CommandBase{

    private final ShellContext shellContext;

    public ContextAwareCommandSuper(String name, Type type, ShellContext shellContext) {
        super(name, type);
        this.shellContext = shellContext;
    }

    public ShellContext getShellContext() {
        return shellContext;
    }

}
