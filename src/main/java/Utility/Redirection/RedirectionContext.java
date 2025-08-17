package Utility.Redirection;


public class RedirectionContext {
    private String[] partsBeforeOutput;
    private String output;
    private RedirectionType redirectionType;

    public RedirectionContext(String[] partsBeforeOutput, String output, RedirectionType redirectionType) {
        this.partsBeforeOutput = partsBeforeOutput;
        this.output = output;
        this.redirectionType = redirectionType;
    }

    public RedirectionContext() {
    }

    public String[] getPartsBeforeOutput() {
        return partsBeforeOutput;
    }

    public void setPartsBeforeOutput(String[] partsBeforeOutput) {
        this.partsBeforeOutput = partsBeforeOutput;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public RedirectionType getRedirectionType() {
        return redirectionType;
    }

    public void setRedirectionType(RedirectionType redirectionType) {
        this.redirectionType = redirectionType;
    }
}
