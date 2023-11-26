package online.flowerinsnow.javaxml.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OnMiss {
    /**
     * <p>当节点不存在时执行的操作</p>
     *
     * @return 当节点不存在时执行的操作
     */
    MissDo value() default MissDo.IGNORE;
}
