package Utility.Lexer;

import java.util.ArrayList;
import java.util.List;

public class InputLexer {
    public List<String> tokenizeInput(String rawInputLine){
        if(rawInputLine == null || rawInputLine.isEmpty())
            return new ArrayList<>();

        TokenizerState tokenizerState = new TokenizerState();
        for(int i = 0; i < rawInputLine.length(); i++){
            char c = rawInputLine.charAt(i);
            processCharacter(c, tokenizerState);
        }

        //Finalize the last token
        tokenizerState.finishCurrentToken();
//        System.out.println("Current Token: " + tokenizerState.currentToken);

        // Validate quote closure
        if(!tokenizerState.isQuotesClosed()){
            System.err.println("Unclosed quote");
            return null;
        }

        return tokenizerState.getTokens();
    }

    private void processCharacter(char c, TokenizerState state){
        switch(c){
            case '\'' -> handleSingleQuote(state);
            case '\"' -> handleDoubleQuote(state);
            case '\\' -> handleEscape(state);
            case ' ', '\t' -> handleWhitespace(c, state);
            default -> handleRegularCharacter(c, state);
        }
    }

    private void handleSingleQuote(TokenizerState state){
        if(state.isEscaped()){
            //* Escaped single quote - treat as literal
            if(state.isInsideDoubleQuotes()){
                state.appendToCurrentToken('\\');
                state.appendToCurrentToken('\'');
                state.clearEscapeFlag();
            } else {
                state.appendToCurrentToken('\'');
                state.clearEscapeFlag();
            }
        } else if(state.isInsideDoubleQuotes()){
            //* Single quote inside double quotes - treat as literal
            state.appendToCurrentToken('\'');
        } else {
            //* Toggle single quote state
            state.toggleSingleQuotes();
        }
    }

    private void handleDoubleQuote(TokenizerState state){
        if(state.isEscaped()){
            //* Escaped double quote - treat as literal
            state.appendToCurrentToken('\"');
            state.clearEscapeFlag();
        } else if(state.isInsideSingleQuotes()){
            //* Double quote inside single quotes - treat as literal
            state.appendToCurrentToken('\"');
        } else {
            //* Toggle double quote state
            state.toggleDoubleQuotes();
        }
    }

    private void handleEscape(TokenizerState state){
        if(state.isEscaped()){
            // Escaped backslash - add literal backslash
            state.appendToCurrentToken('\\');
            state.clearEscapeFlag();
        } else if(state.isInsideSingleQuotes()){
            // Inside single quotes, backslash is literal (no escaping in single quotes)
            state.appendToCurrentToken('\\');
        } else {
            // Set escape flag for next character
            state.setEscaped();
        }
    }

    private void handleWhitespace(char c, TokenizerState state){
        if(state.isInsideQuotes() || state.isEscaped()){
            handleRegularCharacter(c, state);
        } else {
            state.finishCurrentToken();
        }
    }

    private void handleRegularCharacter(char c, TokenizerState state){
            if(state.insideDoubleQuotes && state.isEscaped()){
                state.appendToCurrentToken('\\');
                state.appendToCurrentToken(c);
                state.clearEscapeFlag();
            } else {
                state.appendToCurrentToken(c);
                state.clearEscapeFlag();
            }
    }

    public static class TokenizerState{
        private final StringBuilder currentToken = new StringBuilder();
        private final List<String> tokens = new ArrayList<>();
        private boolean insideSingleQuotes = false;
        private boolean insideDoubleQuotes = false;
        private boolean escaped = false;

        public boolean isInsideSingleQuotes() {
            return insideSingleQuotes;
        }

        public boolean isInsideDoubleQuotes() {
            return insideDoubleQuotes;
        }

        public boolean isInsideQuotes(){
            return insideSingleQuotes || insideDoubleQuotes;
        }

        public boolean isQuotesClosed(){
            return !insideSingleQuotes && !insideDoubleQuotes;
        }

        public boolean isEscaped() {
            return escaped;
        }

        public void toggleSingleQuotes(){
            insideSingleQuotes = !insideSingleQuotes;
        }

        public void toggleDoubleQuotes(){
            insideDoubleQuotes = !insideDoubleQuotes;
        }

        public void setEscaped(){
            escaped = true;
        }

        public void clearEscapeFlag(){
            escaped = false;
        }

        public void appendToCurrentToken(char c){
            currentToken.append(c);
        }

        public void finishCurrentToken(){
            if(!currentToken.isEmpty()){
                tokens.add(currentToken.toString());
                currentToken.setLength(0);
            }
        }

        public List<String> getTokens() {
            return tokens;
        }
    }
}

