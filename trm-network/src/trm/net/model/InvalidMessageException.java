package trm.net.model;

/**
 * @author TRM
 * @version 0.99
 */
public class InvalidMessageException extends RuntimeException {

    public InvalidMessageException() {}
    
    public InvalidMessageException(String message) {
        super(message);
    }

    public InvalidMessageException(Throwable cause) {
        super(cause);
    }

    public InvalidMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
