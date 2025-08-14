package core;

import Commands.CmdType;
import Utility.ShellContext;

public abstract class ContextAwareCommandSuper extends CommandBase{

    private final ShellContext shellContext;

    public ContextAwareCommandSuper(String name, CmdType cmdType, ShellContext shellContext) {
        super(name, cmdType);
        this.shellContext = shellContext;
    }

    public ShellContext getShellContext() {
        return shellContext;
    }

}
