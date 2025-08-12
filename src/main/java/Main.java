import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Uncomment this block to pass the first stage

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("$ ");
            String command = scanner.next();
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(command) && " 0".equalsIgnoreCase(input))
                break;
            if ("echo".equalsIgnoreCase(command))
                System.out.println(input.trim());
            else
                System.out.println(input + ": command not found");

        }
    }
}
