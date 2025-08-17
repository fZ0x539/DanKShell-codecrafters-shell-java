package Command.Builtin;

import Core.CommandSuper;
import Command.CmdType;

import java.util.Arrays;

public class Echo extends CommandSuper {

    public Echo() {
        super("echo", CmdType.BUILTIN);
    }

    @Override
    public void runCommand(String[] inputArgs) {
        System.out.println(String.join(" ", Arrays.copyOfRange(inputArgs, 0, inputArgs.length)));
    }
}
