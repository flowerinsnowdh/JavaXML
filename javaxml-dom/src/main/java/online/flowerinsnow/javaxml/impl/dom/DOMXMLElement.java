package online.flowerinsnow.javaxml.impl.dom;

import online.flowerinsnow.javaxml.api.*;
import online.flowerinsnow.javaxml.api.annotation.*;
import online.flowerinsnow.javaxml.api.exception.DefaultWrongTypeException;
import online.flowerinsnow.javaxml.api.exception.NodeMissingException;
import online.flowerinsnow.javaxml.api.exception.NodeWrongTypeException;
import online.flowerinsnow.javaxml.impl.dom.exception.UnexpectedException;
import online.flowerinsnow.javaxml.impl.dom.util.WriteObjectNumberUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class DOMXMLElement implements XMLElement, WrappedXMLNode<Element> {
    @NotNull private final Element wrapped;
    @NotNull private final List<XMLNode> childNodes = new ArrayList<>();
    @NotNull private final List<DOMXMLText> childTexts = new ArrayList<>();
    @NotNull private final Map<String, List<DOMXMLElement>> childElements = new HashMap<>();
    @NotNull private final Map<String, String> attributes = new HashMap<>();

    public DOMXMLElement(@NotNull Element wrapped) {
        this.wrapped = Objects.requireNonNull(wrapped);

        init();
    }

    private void init() {
        NodeList nodeList = wrapped.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Text) {
                DOMXMLText textNode = new DOMXMLText((Text) node);
                childNodes.add(textNode);
                childTexts.add(textNode);
            } else if (node instanceof Comment) {
                childNodes.add(new DOMXMLComment((Comment) node));
            } else if (node instanceof Element) {
                DOMXMLElement element = new DOMXMLElement((Element) node);
                String name = element.getName();
                childNodes.add(element);
                List<DOMXMLElement> list = childElements.computeIfAbsent(name, k -> new ArrayList<>());
                list.add(element);
            }
        }

        NamedNodeMap namedNodeMap = wrapped.getAttributes();
        for (int i = 0; i < namedNodeMap.getLength(); i++) {
            Node node = namedNodeMap.item(i);
            attributes.put(node.getNodeName(), node.getNodeValue());
        }
    }

    @Override
    public @NotNull String getName() {
        return wrapped.getTagName();
    }

    @Override
    public @Nullable DOMXMLElement getElement(@NotNull String name) {
        List<DOMXMLElement> elements = childElements.get(name);
        if (elements != null && !elements.isEmpty()) {
            return elements.get(0);
        }
        return null;
    }

    @Override
    public @NotNull Optional<XMLElement> getElementOptional(@NotNull String name) {
        return Optional.ofNullable(getElement(name));
    }

    @Override
    public @NotNull XMLElement getElementNotNull(@NotNull String name) {
        return Objects.requireNonNull(getElement(name));
    }

    @Override
    public @NotNull List<XMLElement> getElementList(@NotNull String name) {
        return Collections.unmodifiableList(childElements.getOrDefault(name, Collections.emptyList()));
    }

    @Override
    public @NotNull Map<String, List<XMLElement>> getElements() {
        return Collections.unmodifiableMap(childElements.entrySet().stream()
                .collect(
                        HashMap::new,
                        (map, entry) -> map.put(entry.getKey(), Collections.unmodifiableList(entry.getValue())),
                        HashMap::putAll
                ));
    }

    @Override
    public @NotNull List<XMLNode> getXMLNodes() {
        return Collections.unmodifiableList(childNodes);
    }

    @Override
    public @Nullable String getAttribute(@NotNull String name) {
        return attributes.get(name);
    }

    @Override
    public @NotNull Optional<String> getAttributeOptional(@NotNull String name) {
        return Optional.ofNullable(getAttribute(name));
    }

    @Override
    public @NotNull String getAttributeNotNull(@NotNull String name) {
        return Objects.requireNonNull(getAttribute(name));
    }

    @Override
    public @NotNull Map<String, String> getAttributes() {
        return Collections.unmodifiableMap(attributes);
    }

    @Override
    public @Nullable DOMXMLText getText() {
        return childTexts.stream().findFirst().orElse(null);
    }

    @Override
    public @NotNull Optional<XMLText> getTextOptional() {
        return Optional.ofNullable(getText());
    }

    @Override
    public @NotNull List<XMLText> getTextList() {
        return Collections.unmodifiableList(childTexts);
    }

    @Override
    public @Nullable String getTextString() {
        return getTextOptional().map(NodeWithText::getText).orElse(null);
    }

    @Override
    public @NotNull Optional<String> getTextStringOptional() {
        return Optional.ofNullable(getTextString());
    }

    @Override
    public @NotNull List<String> getTextStringList() {
        return Collections.unmodifiableList(childTexts.stream().map(NodeWithText::getText).collect(Collectors.toList()));
    }

    @Override
    public @Nullable Boolean getTextAsBoolean() {
        return Boolean.parseBoolean(getTextString());
    }

    @Override
    public boolean getTextAsBoolean(boolean default_) {
        String textString = getTextString();
        return textString != null ? Boolean.parseBoolean(textString) : default_;
    }

    @Override
    public @Nullable Byte getTextAsByte() throws NumberFormatException {
        String textString = getTextString();
        if (textString == null) {
            return null;
        }
        return Byte.parseByte(getTextString());
    }

    @Override
    public @Nullable Byte getTextAsByteWithoutException() {
        try {
            //noinspection DataFlowIssue
            return Byte.parseByte(getTextString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public byte getTextAsByte(byte default_) throws NumberFormatException {
        String textString = getTextString();
        if (textString == null) {
            return default_;
        }
        return Byte.parseByte(getTextString());
    }

    @Override
    public byte getTextAsByteWithoutException(byte default_) {
        try {
            //noinspection DataFlowIssue
            return Byte.parseByte(getTextString());
        } catch (NumberFormatException e) {
            return default_;
        }
    }

    @Override
    public @Nullable Short getTextAsShort() throws NumberFormatException {
        String textString = getTextString();
        if (textString == null) {
            return null;
        }
        return Short.parseShort(getTextString());
    }

    @Override
    public @Nullable Short getTextAsShortWithoutException() {
        try {
            //noinspection DataFlowIssue
            return Short.parseShort(getTextString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public short getTextAsShort(short default_) throws NumberFormatException {
        String textString = getTextString();
        if (textString == null) {
            return default_;
        }
        return Short.parseShort(getTextString());
    }

    @Override
    public short getTextAsShortWithoutException(short default_) {
        try {
            //noinspection DataFlowIssue
            return Short.parseShort(getTextString());
        } catch (NumberFormatException e) {
            return default_;
        }
    }

    @Override
    public @Nullable Integer getTextAsInt() throws NumberFormatException {
        String textString = getTextString();
        if (textString == null) {
            return null;
        }
        return Integer.parseInt(getTextString());
    }

    @Override
    public @Nullable Integer getTextAsIntWithoutException() {
        try {
            //noinspection DataFlowIssue
            return Integer.parseInt(getTextString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public int getTextAsInt(int default_) throws NumberFormatException {
        String textString = getTextString();
        if (textString == null) {
            return default_;
        }
        return Integer.parseInt(getTextString());
    }

    @Override
    public int getTextAsIntWithoutException(int default_) {
        try {
            //noinspection DataFlowIssue
            return Integer.parseInt(getTextString());
        } catch (NumberFormatException e) {
            return default_;
        }
    }

    @Override
    public @Nullable Long getTextAsLong() throws NumberFormatException {
        String textString = getTextString();
        if (textString == null) {
            return null;
        }
        return Long.parseLong(getTextString());
    }

    @Override
    public @Nullable Long getTextAsLongWithoutException() {
        try {
            //noinspection DataFlowIssue
            return Long.parseLong(getTextString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public long getTextAsLong(long default_) throws NumberFormatException {
        String textString = getTextString();
        if (textString == null) {
            return default_;
        }
        return Long.parseLong(getTextString());
    }

    @Override
    public long getTextAsLongWithoutException(long default_) {
        try {
            //noinspection DataFlowIssue
            return Long.parseLong(getTextString());
        } catch (NumberFormatException e) {
            return default_;
        }
    }

    @Override
    public @Nullable Float getTextAsFloat() throws NumberFormatException {
        String textString = getTextString();
        if (textString == null) {
            return null;
        }
        return Float.parseFloat(getTextString());
    }

    @Override
    public @Nullable Float getTextAsFloatWithoutException() {
        try {
            //noinspection DataFlowIssue
            return Float.parseFloat(getTextString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public float getTextAsFloat(float default_) throws NumberFormatException {
        String textString = getTextString();
        if (textString == null) {
            return default_;
        }
        return Float.parseFloat(getTextString());
    }

    @Override
    public float getTextAsFloatWithoutException(float default_) {
        try {
            //noinspection DataFlowIssue
            return Float.parseFloat(getTextString());
        } catch (NumberFormatException e) {
            return default_;
        }
    }

    @Override
    public @Nullable Double getTextAsDouble() throws NumberFormatException {
        String textString = getTextString();
        if (textString == null) {
            return null;
        }
        return Double.parseDouble(getTextString());
    }

    @Override
    public @Nullable Double getTextAsDoubleWithoutException() {
        try {
            //noinspection DataFlowIssue
            return Double.parseDouble(getTextString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public double getTextAsDouble(double default_) throws NumberFormatException {
        String textString = getTextString();
        if (textString == null) {
            return default_;
        }
        return Double.parseDouble(getTextString());
    }

    @Override
    public double getTextAsDoubleWithoutException(double default_) {
        try {
            //noinspection DataFlowIssue
            return Double.parseDouble(getTextString());
        } catch (NumberFormatException e) {
            return default_;
        }
    }

    @Override
    public @NotNull Element getWrapped() {
        return this.wrapped;
    }

    @Override
    public void writeObject(@NotNull Object object) {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (!field.isAnnotationPresent(ConfigValue.class)) {
                continue;
            }
            ConfigValue configValue = field.getAnnotation(ConfigValue.class);
            String var0 = configValue.value();
            if (var0.isEmpty()) {
                var0 = field.getName();
            }
            final String fieldName = var0;
            field.setAccessible(true);

            MissDo missDo = MissDo.IGNORE;
            if (field.isAnnotationPresent(OnMiss.class)) {
                missDo = field.getAnnotation(OnMiss.class).value();
            }
            WrongTypeDo wrongTypeDo = WrongTypeDo.IGNORE;
            if (field.isAnnotationPresent(OnWrongType.class)) {
                wrongTypeDo = field.getAnnotation(OnWrongType.class).value();
            }
            String defaultValue = null;
            if (field.isAnnotationPresent(DefaultValue.class)) {
                defaultValue = field.getAnnotation(DefaultValue.class).value();
            }

            Class<?> type = field.getType();
            if (type.equals(Boolean.class) || type.equals(Boolean.TYPE)) {
                WriteObjectNumberUtils.getConfigValue(
                        getElement(fieldName), missDo, wrongTypeDo, defaultValue,
                        Boolean::parseBoolean,
                        valueParsed -> { // 成功解析
                            try {
                                field.set(object, valueParsed);
                            } catch (IllegalAccessException e) {
                                throw new UnexpectedException(e);
                            }
                        },
                        null, // 值不正确且WrongType是THROWING，但不可能
                        () -> { // 值是空且OnMiss是THROWING
                            throw new NodeMissingException("'" + fieldName + "' must not be null.");
                        },
                        default_ -> { // 值不正确且WrongType是DEFAULT或值是空且OnMiss是DEFAULT
                            try {
                                field.set(object, default_);
                            } catch (IllegalAccessException e) {
                                throw new UnexpectedException(e);
                            }
                        },
                        null // 获取默认值异常时抛出，但不可能
                );
            } else if (type.equals(Byte.class) || type.equals(Byte.TYPE)) {
                WriteObjectNumberUtils.getConfigValue(
                        getElement(fieldName), missDo, wrongTypeDo, defaultValue,
                        Byte::parseByte,
                        valueParsed -> { // 成功解析
                            try {
                                field.set(object, valueParsed);
                            } catch (IllegalAccessException e) {
                                throw new UnexpectedException(e);
                            }
                        },
                        ex -> { // 值不正确且WrongType是THROWING
                            throw new NodeWrongTypeException("'" + fieldName + "' must be byte type.", ex);
                        },
                        () -> { // 值是空且OnMiss是THROWING
                            throw new NodeMissingException("'" + fieldName + "' must not be null.");
                        },
                        default_ -> { // 值不正确且WrongType是DEFAULT或值是空且OnMiss是DEFAULT
                            try {
                                field.set(object, default_);
                            } catch (IllegalAccessException e) {
                                throw new UnexpectedException(e);
                            }
                        },
                        ex -> { // 获取默认值异常时抛出
                            throw new DefaultWrongTypeException("'" + fieldName + "' has illegal default value.");
                        }
                );
            } else if (type.equals(Short.class) || type.equals(Short.TYPE)) {
                WriteObjectNumberUtils.getConfigValue(
                        getElement(fieldName), missDo, wrongTypeDo, defaultValue,
                        Short::parseShort,
                        valueParsed -> { // 成功解析
                            try {
                                field.set(object, valueParsed);
                            } catch (IllegalAccessException e) {
                                throw new UnexpectedException(e);
                            }
                        },
                        ex -> { // 值不正确且WrongType是THROWING
                            throw new NodeWrongTypeException("'" + fieldName + "' must be short type.", ex);
                        },
                        () -> { // 值是空且OnMiss是THROWING
                            throw new NodeMissingException("'" + fieldName + "' must not be null.");
                        },
                        default_ -> { // 值不正确且WrongType是DEFAULT或值是空且OnMiss是DEFAULT
                            try {
                                field.set(object, default_);
                            } catch (IllegalAccessException e) {
                                throw new UnexpectedException(e);
                            }
                        },
                        ex -> { // 获取默认值异常时抛出
                            throw new DefaultWrongTypeException("'" + fieldName + "' has illegal default value.");
                        }
                );
            } else if (type.equals(Integer.class) || type.equals(Integer.TYPE)) {
                WriteObjectNumberUtils.getConfigValue(
                        getElement(fieldName), missDo, wrongTypeDo, defaultValue,
                        Integer::parseInt,
                        valueParsed -> { // 成功解析
                            try {
                                field.set(object, valueParsed);
                            } catch (IllegalAccessException e) {
                                throw new UnexpectedException(e);
                            }
                        },
                        ex -> { // 值不正确且WrongType是THROWING
                            throw new NodeWrongTypeException("'" + fieldName + "' must be int type.", ex);
                        },
                        () -> { // 值是空且OnMiss是THROWING
                            throw new NodeMissingException("'" + fieldName + "' must not be null.");
                        },
                        default_ -> { // 值不正确且WrongType是DEFAULT或值是空且OnMiss是DEFAULT
                            try {
                                field.set(object, default_);
                            } catch (IllegalAccessException e) {
                                throw new UnexpectedException(e);
                            }
                        },
                        ex -> { // 获取默认值异常时抛出
                            throw new DefaultWrongTypeException("'" + fieldName + "' has illegal default value.");
                        }
                );
            } else if (type.equals(Long.class) || type.equals(Long.TYPE)) {
                WriteObjectNumberUtils.getConfigValue(
                        getElement(fieldName), missDo, wrongTypeDo, defaultValue,
                        Long::parseLong,
                        valueParsed -> { // 成功解析
                            try {
                                field.set(object, valueParsed);
                            } catch (IllegalAccessException e) {
                                throw new UnexpectedException(e);
                            }
                        },
                        ex -> { // 值不正确且WrongType是THROWING
                            throw new NodeWrongTypeException("'" + fieldName + "' must be long type.", ex);
                        },
                        () -> { // 值是空且OnMiss是THROWING
                            throw new NodeMissingException("'" + fieldName + "' must not be null.");
                        },
                        default_ -> { // 值不正确且WrongType是DEFAULT或值是空且OnMiss是DEFAULT
                            try {
                                field.set(object, default_);
                            } catch (IllegalAccessException e) {
                                throw new UnexpectedException(e);
                            }
                        },
                        ex -> { // 获取默认值异常时抛出
                            throw new DefaultWrongTypeException("'" + fieldName + "' has illegal default value.");
                        }
                );
            } else if (type.equals(Float.class) || type.equals(Float.TYPE)) {
                WriteObjectNumberUtils.getConfigValue(
                        getElement(fieldName), missDo, wrongTypeDo, defaultValue,
                        Float::parseFloat,
                        valueParsed -> { // 成功解析
                            try {
                                field.set(object, valueParsed);
                            } catch (IllegalAccessException e) {
                                throw new UnexpectedException(e);
                            }
                        },
                        ex -> { // 值不正确且WrongType是THROWING
                            throw new NodeWrongTypeException("'" + fieldName + "' must be float type.", ex);
                        },
                        () -> { // 值是空且OnMiss是THROWING
                            throw new NodeMissingException("'" + fieldName + "' must not be null.");
                        },
                        default_ -> { // 值不正确且WrongType是DEFAULT或值是空且OnMiss是DEFAULT
                            try {
                                field.set(object, default_);
                            } catch (IllegalAccessException e) {
                                throw new UnexpectedException(e);
                            }
                        },
                        ex -> { // 获取默认值异常时抛出
                            throw new DefaultWrongTypeException("'" + fieldName + "' has illegal default value.");
                        }
                );
            } else if (type.equals(Double.class) || type.equals(Double.TYPE)) {
                WriteObjectNumberUtils.getConfigValue(
                        getElement(fieldName), missDo, wrongTypeDo, defaultValue,
                        Double::parseDouble,
                        valueParsed -> { // 成功解析
                            try {
                                field.set(object, valueParsed);
                            } catch (IllegalAccessException e) {
                                throw new UnexpectedException(e);
                            }
                        },
                        ex -> { // 值不正确且WrongType是THROWING
                            throw new NodeWrongTypeException("'" + fieldName + "' must be double type.", ex);
                        },
                        () -> { // 值是空且OnMiss是THROWING
                            throw new NodeMissingException("'" + fieldName + "' must not be null.");
                        },
                        default_ -> { // 值不正确且WrongType是DEFAULT或值是空且OnMiss是DEFAULT
                            try {
                                field.set(object, default_);
                            } catch (IllegalAccessException e) {
                                throw new UnexpectedException(e);
                            }
                        },
                        ex -> { // 获取默认值异常时抛出
                            throw new DefaultWrongTypeException("'" + fieldName + "' has illegal default value.");
                        }
                );
            } else if (type.equals(String.class)) {
                WriteObjectNumberUtils.getConfigValue(
                        getElement(fieldName), missDo, wrongTypeDo, defaultValue,
                        text -> text,
                        valueParsed -> { // 成功解析
                            try {
                                field.set(object, valueParsed);
                            } catch (IllegalAccessException e) {
                                throw new UnexpectedException(e);
                            }
                        },
                        null, // 值不正确且WrongType是THROWING，但不可能
                        null, // 值是空且OnMiss是THROWING，但不可能
                        default_ -> { // 值不正确且WrongType是DEFAULT或值是空且OnMiss是DEFAULT
                            try {
                                field.set(object, default_);
                            } catch (IllegalAccessException e) {
                                throw new UnexpectedException(e);
                            }
                        },
                        null // 获取默认值异常时抛出，但不可能
                );
            } else {
                DOMXMLElement childElement = getElement(fieldName);
                if (childElement == null) {
                    try {
                        childElement = new DOMXMLElement(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument().createElement(fieldName));
                    } catch (ParserConfigurationException e) {
                        throw new UnexpectedException(e);
                    }
                }
                try {
                    Object childObject = field.getType().getDeclaredConstructor().newInstance();
                    childElement.writeObject(childObject);
                    field.set(object, childObject);
                } catch (IllegalAccessException | NoSuchMethodException | InstantiationException |
                         InvocationTargetException e) {
                    throw new UnexpectedException(e);
                }
            }
        }
    }
}
