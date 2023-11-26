package online.flowerinsnow.javaxml.impl.dom.exception;

/**
 * <p>占位符异常，当异常不应该被抛出时抛出，出现该异常可能是代码的逻辑问题</p>
 */
public class UnexpectedException extends RuntimeException {
    public UnexpectedException() {
        super();
    }

    public UnexpectedException(String message) {
        super(message);
    }

    public UnexpectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedException(Throwable cause) {
        super(cause);
    }
}
