package online.flowerinsnow.javaxml.api.exception;

public class NodeWrongTypeException extends RuntimeException {
    public NodeWrongTypeException() {
        super();
    }

    public NodeWrongTypeException(String message) {
        super(message);
    }

    public NodeWrongTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NodeWrongTypeException(Throwable cause) {
        super(cause);
    }
}
