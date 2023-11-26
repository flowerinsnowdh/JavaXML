package online.flowerinsnow.javaxml.api.exception;

import online.flowerinsnow.javaxml.api.annotation.MissDo;

/**
 * <p>当节点不存在且{@link online.flowerinsnow.javaxml.api.annotation.OnMiss} 设为{@link MissDo#THROWING} 时抛出</p>
 */
public class NodeMissingException extends RuntimeException {
    public NodeMissingException() {
        super();
    }

    public NodeMissingException(String message) {
        super(message);
    }

    public NodeMissingException(String message, Throwable cause) {
        super(message, cause);
    }

    public NodeMissingException(Throwable cause) {
        super(cause);
    }
}
