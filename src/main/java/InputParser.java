import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputParser {

//    public List<String> tokenizeInput(String rawInputLine) {
//
//        String[] splitInputArray = rawInputLine.split(" ");
//        System.out.println(Arrays.toString(splitInputArray));
//
//        return Arrays.stream(splitInputArray).toList();
//    }

    /**
     * ? Input                    : Output
     * echo 'hello      world'  : hello      world
     * echo hello    world      : hello world
     * echo 'hello''world'      : helloworld
     * echo hello''world        : helloworld
     */
    public List<String> tokenizeInput(String rawInputLine) {

        StringBuilder currentToken = new StringBuilder();
        List<String> tokenList = new ArrayList<>();
        boolean insideQuotes = false;

        for (int i = 0; i < rawInputLine.length(); i++) {
            char c = rawInputLine.charAt(i);

            if (c == '\'') {
                if (insideQuotes) {
                    insideQuotes = false;
                } else {
                    insideQuotes = true;
                }

            } else if (c == ' ' || c == '\t') {
                //inside quotes
                if (insideQuotes) {
                    //Print error & return null if it doesn't match pairs.
                    if (i == rawInputLine.length() - 1) {
                        printError();
                        return null;
                    }
                    currentToken.append(c); // Add space if inside a quotation
                }
                //outside quotes
                else {
                    if (!currentToken.isEmpty()) {
                        //If c is a space outside quotes and the token isn't empty, add it to the list and reset the sb.
                        tokenList.add(currentToken.toString());
                        currentToken = new StringBuilder();
                    }
                }
            } else {
                currentToken.append(c);
            }
        }
        if (!currentToken.isEmpty()) {
            tokenList.add(currentToken.toString());
        }

        return tokenList;
    }

    private void printError() {
        System.err.println("Unclosed quote.");
    }
}
