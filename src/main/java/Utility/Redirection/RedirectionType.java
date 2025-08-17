package Utility.Redirection;

public enum RedirectionType {
    STDOUT(">", "1>"),
    STDERR("2>");

    private final String[] operators;

    RedirectionType(String... operators) {
        this.operators = operators;
    }

    public static RedirectionType fromOperator(String op) {
        for (RedirectionType type : values()) {
            for (String operator : type.operators) {
                if (operator.equals(op)) {
                    return type;
                }
            }
        }
        return null;
    }
}
