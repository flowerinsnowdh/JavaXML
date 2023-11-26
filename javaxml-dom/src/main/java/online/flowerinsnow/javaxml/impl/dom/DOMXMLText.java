package online.flowerinsnow.javaxml.impl.dom;

import online.flowerinsnow.javaxml.api.WrappedXMLNode;
import online.flowerinsnow.javaxml.api.XMLText;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.Objects;

public class DOMXMLText implements XMLText, WrappedXMLNode<Text> {
    @NotNull private final Text wrapped;

    public DOMXMLText(@NotNull Text wrapped) {
        this.wrapped = Objects.requireNonNull(wrapped);
    }

    @Override
    public @NotNull String getText() {
        return wrapped.getNodeValue();
    }

    @Override
    public @NotNull Text getWrapped() {
        return this.wrapped;
    }
}
