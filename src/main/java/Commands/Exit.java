package Commands;

import java.util.Scanner;

public class Exit extends CommandSuper {

    public Exit() {
        super("exit", Type.BUILTIN);
    }

    @Override
    public void runCommand(Scanner scanner) {
        String input = scanner.nextLine();
        if (input.trim().equalsIgnoreCase("0"))
            System.exit(0);
    }
}
