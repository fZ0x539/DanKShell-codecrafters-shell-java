package Commands;

public enum Type {
    BUILTIN(" is a shell builtin");

    private final String description;

    Type(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
