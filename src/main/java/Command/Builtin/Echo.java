package Command.Builtin;

import Core.CommandSuper;
import Command.CmdType;
import Utility.Redirection.RedirectionContext;

import java.util.Arrays;

import static Utility.Redirection.RedirectionHandler.redirectOutput;

public class Echo extends CommandSuper {

    public Echo() {
        super("echo", CmdType.BUILTIN);
    }

    @Override
    public void runCommand(String[] inputArgs) {

        RedirectionContext redirectionResult = redirectOutput(inputArgs);

        if(redirectionResult != null){
//            System.out.println("Echoing: "
//                    + Arrays.toString(redirectionResult.getPartsBeforeOutput())
//                    + " " + redirectionResult.getRedirectionType().toString()
//                    + " " + redirectionResult.getOutput() );
            redirectBulitinOutput(redirectionResult);
        } else {
            System.out.println(String.join(" ", Arrays.copyOfRange(inputArgs, 0, inputArgs.length)));
        }
    }
}
