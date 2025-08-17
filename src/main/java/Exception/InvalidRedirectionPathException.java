package Exception;

public class InvalidRedirectionPathException extends RuntimeException {
    public InvalidRedirectionPathException() {
        super("Invalid redirection path.");
    }
}
