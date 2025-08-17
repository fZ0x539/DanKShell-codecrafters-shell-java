package Core;

import Command.CmdType;

public abstract class CommandSuper extends  CommandBase {

    public CommandSuper(String name, CmdType cmdType) {
        super(name, cmdType);
    }

    public abstract void runCommand(String[] inputArgs);

}
