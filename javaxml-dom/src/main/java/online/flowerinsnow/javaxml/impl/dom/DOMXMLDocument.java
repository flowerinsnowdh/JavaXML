package online.flowerinsnow.javaxml.impl.dom;

import online.flowerinsnow.javaxml.api.WrappedXMLNode;
import online.flowerinsnow.javaxml.api.XMLDocument;
import online.flowerinsnow.javaxml.api.XMLElement;
import online.flowerinsnow.javaxml.api.XMLNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Document;

import java.util.*;

public class DOMXMLDocument implements XMLDocument, WrappedXMLNode<Document> {
    @NotNull private final Document wrapped;
    @SuppressWarnings("NotNullFieldNotInitialized")
    @NotNull private DOMXMLElement root;

    public DOMXMLDocument(@NotNull Document wrapped) {
        this.wrapped = Objects.requireNonNull(wrapped);

        init();
    }

    private void init() {
        root = new DOMXMLElement(wrapped.getDocumentElement());
    }

    @Override
    public @Nullable XMLElement getElement(@NotNull String name) {
        Objects.requireNonNull(name);
        if (root.getName().equals(name)) {
            return root;
        }
        return null;
    }

    @Override
    public @NotNull Optional<XMLElement> getElementOptional(@NotNull String name) {
        Objects.requireNonNull(name);
        return Optional.ofNullable(getElement(name));
    }

    @Override
    public @NotNull XMLElement getElementNotNull(@NotNull String name) {
        return Objects.requireNonNull(getElement(name));
    }

    @Override
    public @NotNull List<XMLElement> getElementList(@NotNull String name) {
        return Collections.singletonList(getRoot());
    }

    @Override
    public @NotNull Map<String, List<XMLElement>> getElements() {
        XMLElement root = getRoot();
        return Collections.singletonMap(root.getName(), Collections.singletonList(root));
    }

    @Override
    public @NotNull List<XMLNode> getXMLNodes() {
        return Collections.singletonList(root);
    }

    @Override
    public @NotNull XMLElement getRoot() {
        return this.root;
    }

    @Override
    public @NotNull Document getWrapped() {
        return this.wrapped;
    }
}
