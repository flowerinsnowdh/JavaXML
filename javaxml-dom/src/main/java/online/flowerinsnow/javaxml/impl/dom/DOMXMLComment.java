package online.flowerinsnow.javaxml.impl.dom;

import online.flowerinsnow.javaxml.api.WrappedXMLNode;
import online.flowerinsnow.javaxml.api.XMLComment;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Comment;

import java.util.Objects;

public class DOMXMLComment implements XMLComment, WrappedXMLNode<Comment> {
    @NotNull private final Comment wrapped;

    public DOMXMLComment(@NotNull Comment wrapped) {
        this.wrapped = Objects.requireNonNull(wrapped);
    }

    @Override
    public @NotNull String getText() {
        return wrapped.getNodeValue();
    }

    @Override
    public @NotNull Comment getWrapped() {
        return this.wrapped;
    }
}
