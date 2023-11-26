package online.flowerinsnow.javaxml.api;

import org.jetbrains.annotations.NotNull;

public interface WrappedXMLNode<T> extends XMLNode {
    @NotNull T getWrapped();
}
