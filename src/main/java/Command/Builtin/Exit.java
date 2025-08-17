package Command.Builtin;

import Core.CommandSuper;
import Command.CmdType;

public class Exit extends CommandSuper {

    public Exit() {
        super("exit", CmdType.BUILTIN);
    }

    @Override
    public void runCommand(String[] inputArgs) {
        String input = inputArgs[0];
        if (inputArgs.length > 1) {
            System.out.println(tooManyArgs());
        } else {
            if (input.trim().equalsIgnoreCase("0"))
                System.exit(0);
            else
                System.out.println("Invalid exit code.");
        }
    }
}
