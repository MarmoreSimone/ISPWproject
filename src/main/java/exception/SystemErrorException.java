package exception;

public class SystemErrorException extends GenericException {

    public SystemErrorException() {
        super("system error");
    }

    public SystemErrorException(Throwable cause) {
        super("system error", cause);
    }
}
