package exception;

public class NoCafeteriasFoundException extends GenericException {
    public NoCafeteriasFoundException(String msg) {
        super("cafeteria not found" + msg);
    }

    public NoCafeteriasFoundException(String msg, Throwable cause) {
        super("cafeteria not found" + msg, cause);
    }
}
