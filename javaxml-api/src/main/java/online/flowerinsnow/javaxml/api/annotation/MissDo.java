package online.flowerinsnow.javaxml.api.annotation;

public enum MissDo {
    /**
     * <p>使用默认值</p>
     * <p>必须使用{@link DefaultValue}指定默认值，否则将抛出{@link online.flowerinsnow.javaxml.api.exception.DefaultMissingException}</p>
     */
    DEFAULT,
    /**
     * <p>忽略</p>
     * <p>忽略，不进行赋值操作</p>
     */
    IGNORE,
    /**
     * <p>抛出{@link online.flowerinsnow.javaxml.api.exception.NodeMissingException}</p>
     */
    THROWING
}
