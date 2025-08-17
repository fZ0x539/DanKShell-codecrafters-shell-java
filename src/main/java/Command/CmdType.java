package Command;

public enum CmdType {
    BUILTIN(" is a shell builtin");

    private final String description;

    CmdType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
