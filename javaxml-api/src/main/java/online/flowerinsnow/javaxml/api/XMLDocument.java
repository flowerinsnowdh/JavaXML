package online.flowerinsnow.javaxml.api;

import org.jetbrains.annotations.NotNull;

public interface XMLDocument extends NodeWithChild {
    /**
     * 获取根元素
     *
     * @return 根元素
     */
    @NotNull XMLElement getRoot();
}
