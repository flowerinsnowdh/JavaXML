package online.flowerinsnow.javaxml.api.exception;

public class DefaultMissingException extends RuntimeException {
    public DefaultMissingException() {
        super();
    }

    public DefaultMissingException(String message) {
        super(message);
    }

    public DefaultMissingException(String message, Throwable cause) {
        super(message, cause);
    }

    public DefaultMissingException(Throwable cause) {
        super(cause);
    }
}
