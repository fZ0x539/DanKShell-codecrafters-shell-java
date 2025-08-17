package Core;

import Command.CmdType;

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

    public String tooManyArgs(){
        return name+": too many arguments";
    }

    public abstract void runCommand(String[] inputArgs);
}
