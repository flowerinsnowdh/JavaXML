package online.flowerinsnow.javaxml.api.exception;

public class DefaultWrongTypeException extends RuntimeException {
    public DefaultWrongTypeException() {
        super();
    }

    public DefaultWrongTypeException(String message) {
        super(message);
    }

    public DefaultWrongTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DefaultWrongTypeException(Throwable cause) {
        super(cause);
    }
}
