package Utility.Lexer;

import java.util.ArrayList;
import java.util.List;

import static view.ConsoleUI.printError;


/**
 * ? Input                    : Output
 * echo 'hello      world'  : hello      world
 * echo hello    world      : hello world
 * echo 'hello''world'      : helloworld
 * echo hello''world        : helloworld
 */
public class InputLexer {
    public List<String> tokenizeInput(String rawInputLine) {
        StringBuilder currentToken = new StringBuilder();
        List<String> tokenList = new ArrayList<>();
        boolean insideQuotes = false;

        for (char c : rawInputLine.toCharArray()) {
            switch (c) {
                case '\'' -> insideQuotes = !insideQuotes;

                case ' ', '\t' -> {
                    if (insideQuotes) {
                        currentToken.append(c);
                    } else {
                        finishCurrentToken(currentToken, tokenList);
                    }
                }

                default -> currentToken.append(c);
            }
        }

        // Add final token if exists
        finishCurrentToken(currentToken, tokenList);

        // Check for unclosed quotes
        if (insideQuotes) {
            printError("Unclosed quote");
            return null;
        }

        return tokenList;
    }

    private void finishCurrentToken(StringBuilder currentToken, List<String> tokenList) {
        if (!currentToken.isEmpty()) {
            tokenList.add(currentToken.toString());
            currentToken.setLength(0);
        }
    }
}
