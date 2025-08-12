package Commands;

import java.util.Scanner;

public class Echo extends CommandSuper {

    public Echo() {
        super("echo", Type.BUILTIN);
    }

    @Override
    public void runCommand(Scanner scanner) {
        if (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine().trim());
        }
    }
}
