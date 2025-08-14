package Commands.builtin;

import core.CommandSuper;
import Commands.CmdType;

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
