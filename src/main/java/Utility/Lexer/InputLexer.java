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
        boolean insideDoubleQuotes = false;
        boolean isEscaped = false;

        for (char c : rawInputLine.toCharArray()) {
            switch (c) {
                case '\"' -> {
                    if (!insideQuotes && !isEscaped)
                        insideDoubleQuotes = !insideDoubleQuotes;
                    else{
                        currentToken.append(c);
                        if(isEscaped)
                            isEscaped = !isEscaped;
                    }

                }

                case '\'' -> {
                    if(!insideDoubleQuotes && !isEscaped)
                        insideQuotes = !insideQuotes;
                    else{
                        currentToken.append(c);
                        if(isEscaped)
                            isEscaped = !isEscaped;
                    }
                }

                case '\\' -> {
                    if (insideQuotes || insideDoubleQuotes) { //
                        currentToken.append(c);
                    }
                    else
                        isEscaped = !isEscaped;
                }

                //Break tokens up on space/tab, or keep the indentation if inside a single quote
                case ' ', '\t' -> {
                    if (insideQuotes || insideDoubleQuotes || isEscaped) {
                        currentToken.append(c);
                        if(isEscaped)
                            isEscaped = !isEscaped;
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
        if (insideQuotes || insideDoubleQuotes) {
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
