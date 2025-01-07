package exception;

public class NoUserFoundException extends GenericException {
    public NoUserFoundException() {
        super("user with this credentials not found");
    }
}
