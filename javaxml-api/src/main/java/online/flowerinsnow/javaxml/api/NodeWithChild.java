package online.flowerinsnow.javaxml.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface NodeWithChild extends XMLNode {
    /**
     * <p>获取指定名称的子元素，若有多个则获取第一个</p>
     *
     * @param name 元素名
     * @return 指定名称的子元素，若不存在返回null，若有多个返回第一个
     */
    @Nullable XMLElement getElement(@NotNull String name);

    /**
     * <p>获取指定名称的子元素，若有多个则获取第一个</p>
     *
     * @param name 元素名
     * @return 指定名称的子元素，若不存在返回空，若有多个返回第一个
     */
    @NotNull Optional<XMLElement> getElementOptional(@NotNull String name);

    /**
     * <p>获取指定名称的子元素，若有多个则获取第一个</p>
     *
     * @param name 元素名
     * @return 指定名称的子元素，若不存抛出{@link NullPointerException}，若有多个返回第一个
     */
    @NotNull XMLElement getElementNotNull(@NotNull String name);

    /**
     * <p>获取所有指定名称的子元素</p>
     *
     * @param name 元素名
     * @return 指定名称的子元素
     */
    @NotNull List<XMLElement> getElementList(@NotNull String name);

    /**
     * <p>获取所有子元素</p>
     *
     * @return 所有子元素
     */
    @NotNull Map<String, List<XMLElement>> getElements();

    /**
     * <p>获取所有子节点</p>
     *
     * @return 所有子节点
     */
    @NotNull List<XMLNode> getXMLNodes();
}
