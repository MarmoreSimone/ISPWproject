package exception;

public class WrongFormatException extends GenericException {
    public WrongFormatException(String msg) {
        super("wrong format" + msg);
    }

    public WrongFormatException(String msg, Throwable cause) {
        super("wrong format" + msg, cause);
    }
}
