package Commands;

public abstract class CommandSuper {
    private final String name;
    private final Type type;

    public CommandSuper(String name, Type type) {
        this.name = name.toLowerCase();
        this.type = type;
    }


    public String getType() {
        return type.getDescription();
    }


    public String getName() {
        return name;
    }

    public String tooManyArgs(){
        return name+": too many arguments";
    }

    public abstract void runCommand(String[] inputArgs);

}
