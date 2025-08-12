package Commands;

public class Exit extends CommandSuper {

    public Exit() {
        super("exit", Type.BUILTIN);
    }

    @Override
    public void runCommand(String[] inputArgs) {
        String input = inputArgs[0];
        if (input.trim().equalsIgnoreCase("0"))
            System.exit(0);
        else
            System.out.println("Invalid exit code.");
    }
}
