package online.flowerinsnow.javaxml.api;

import org.jetbrains.annotations.NotNull;

public interface NodeWithText extends XMLNode {
    /**
     * <p>获取节点中的文本</p>
     *
     * @return 节点中的文本
     */
    @NotNull String getText();
}
