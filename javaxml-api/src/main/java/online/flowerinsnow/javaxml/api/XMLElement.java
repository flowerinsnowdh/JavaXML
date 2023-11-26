package online.flowerinsnow.javaxml.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface XMLElement extends NodeWithChild {
    /**
     * <p>获取元素标签名</p>
     *
     * @return 节点名
     */
    @NotNull String getName();

    /**
     * <p>获取子文本节点，若没有返回null，若有多个返回第一个</p>
     *
     * @return 子文本节点中，若没有返回null，若有多个返回第一个
     */
    @Nullable XMLText getText();

    /**
     * <p>获取子文本节点中，若没有返回空，若有多个返回第一个</p>
     *
     * @return 子文本节点中，若没有返回空，若有多个返回第一个
     */
    @NotNull Optional<XMLText> getTextOptional();

    /**
     * <p>获取所有子文本节点中的文本</p>
     *
     * @return 所有子文本节点中的文本
     */
    @NotNull List<XMLText> getTextList();

    /**
     * <p>获取子文本节点中的文本，若没有返回null，若有多个返回第一个</p>
     *
     * @return 子文本节点中的文本，若没有返回null，若有多个返回第一个
     */
    @Nullable String getTextString();

    /**
     * <p>获取子文本节点中的文本，若没有返回空，若有多个返回第一个</p>
     *
     * @return 子文本节点中的文本，若没有返回空，若有多个返回第一个
     */
    @NotNull Optional<String> getTextStringOptional();

    /**
     * <p>获取所有子文本节点中的文本</p>
     *
     * @return 所有子文本节点中的文本
     */
    @NotNull List<String> getTextStringList();

    /**
     * <p>获取子文本节点中的文本，并转为布尔类型，若没有返回null，若有多个返回第一个</p>
     * <p>非true即为假（不区分大小写）</p>
     *
     * @return 子文本节点中的文本，并转为布尔类型
     */
    @Nullable Boolean getTextAsBoolean();

    /**
     * <p>获取子文本节点中的文本，并转为布尔类型，若没有返回null，若有多个返回第一个</p>
     * <p>非true即为假（不区分大小写）</p>
     *
     * @param default_ 默认值
     * @return 子文本节点中的文本，并转为布尔类型
     */
    boolean getTextAsBoolean(boolean default_);

    /**
     * <p>获取子文本节点中的文本，并转为字节类型，若没有返回null，若有多个返回第一个</p>
     *
     * @return 子文本节点中的文本，并转为字节类型
     * @throws NumberFormatException 当无法解析字节类型时抛出
     */
    @Nullable Byte getTextAsByte() throws NumberFormatException;

    /**
     * <p>获取子文本节点中的文本，并转为字节类型，若没有返回null，若解析失败返回null，若有多个返回第一个</p>
     *
     * @return 子文本节点中的文本，并转为字节类型，若解析失败返回null
     */
    @Nullable Byte getTextAsByteWithoutException();

    /**
     * <p>获取子文本节点中的文本，并转为字节类型，若没有返回默认值，若有多个返回第一个</p>
     *
     * @param default_ 默认值
     * @return 子文本节点中的文本，并转为字节类型，若没有返回默认值
     * @throws NumberFormatException 当无法解析字节类型时抛出
     */
    byte getTextAsByte(byte default_) throws NumberFormatException;

    /**
     * <p>获取子文本节点中的文本，并转为字节类型，若没有返回默认值，若解析失败返回默认值，若有多个返回第一个</p>
     *
     * @param default_ 默认值
     * @return 子文本节点中的文本，并转为字节类型，若没有返回默认值，若解析失败返回默认值
     */
    byte getTextAsByteWithoutException(byte default_);

    /**
     * <p>获取子文本节点中的文本，并转为短整数类型，若没有返回null，若有多个返回第一个</p>
     *
     * @return 子文本节点中的文本，并转为短整数类型
     * @throws NumberFormatException 当无法解析短整数类型时抛出
     */
    @Nullable Short getTextAsShort() throws NumberFormatException;

    /**
     * <p>获取子文本节点中的文本，并转为短整数类型，若没有返回null，若解析失败返回null，若有多个返回第一个</p>
     *
     * @return 子文本节点中的文本，并转为短整数类型，若解析失败返回null
     */
    @Nullable Short getTextAsShortWithoutException();

    /**
     * <p>获取子文本节点中的文本，并转为短整数类型，若没有返回默认值，若有多个返回第一个</p>
     *
     * @param default_ 默认值
     * @return 子文本节点中的文本，并转为短整数类型，若没有返回默认值
     * @throws NumberFormatException 当无法解析短整数类型时抛出
     */
    short getTextAsShort(short default_) throws NumberFormatException;

    /**
     * <p>获取子文本节点中的文本，并转为短整数类型，若没有返回默认值，若解析失败返回默认值，若有多个返回第一个</p>
     *
     * @param default_ 默认值
     * @return 子文本节点中的文本，并转为短整数类型，若没有返回默认值，若解析失败返回默认值
     */
    short getTextAsShortWithoutException(short default_);

    /**
     * <p>获取子文本节点中的文本，并转为整数类型，若没有返回null，若有多个返回第一个</p>
     *
     * @return 子文本节点中的文本，并转为整数类型
     * @throws NumberFormatException 当无法解析整数类型时抛出
     */
    @Nullable Integer getTextAsInt() throws NumberFormatException;

    /**
     * <p>获取子文本节点中的文本，并转为整数类型，若没有返回null，若解析失败返回null，若有多个返回第一个</p>
     *
     * @return 子文本节点中的文本，并转为整数类型，若解析失败返回null
     */
    @Nullable Integer getTextAsIntWithoutException();

    /**
     * <p>获取子文本节点中的文本，并转为整数类型，若没有返回默认值，若有多个返回第一个</p>
     *
     * @param default_ 默认值
     * @return 子文本节点中的文本，并转为整数类型，若没有返回默认值
     * @throws NumberFormatException 当无法解析整数类型时抛出
     */
    int getTextAsInt(int default_) throws NumberFormatException;

    /**
     * <p>获取子文本节点中的文本，并转为整数类型，若没有返回默认值，若解析失败返回默认值，若有多个返回第一个</p>
     *
     * @param default_ 默认值
     * @return 子文本节点中的文本，并转为整数类型，若没有返回默认值，若解析失败返回默认值
     */
    int getTextAsIntWithoutException(int default_);

    /**
     * <p>获取子文本节点中的文本，并转为长整数类型，若没有返回null，若有多个返回第一个</p>
     *
     * @return 子文本节点中的文本，并转为整数类型
     * @throws NumberFormatException 当无法解析整数类型时抛出
     */
    @Nullable Long getTextAsLong() throws NumberFormatException;

    /**
     * <p>获取子文本节点中的文本，并转为长整数类型，若没有返回null，若解析失败返回null，若有多个返回第一个</p>
     *
     * @return 子文本节点中的文本，并转为长整数类型，若解析失败返回null
     */
    @Nullable Long getTextAsLongWithoutException();

    /**
     * <p>获取子文本节点中的文本，并转为长整数类型，若没有返回默认值，若有多个返回第一个</p>
     *
     * @param default_ 默认值
     * @return 子文本节点中的文本，并转为长整数类型，若没有返回默认值
     * @throws NumberFormatException 当无法解析整数类型时抛出
     */
    long getTextAsLong(long default_) throws NumberFormatException;

    /**
     * <p>获取子文本节点中的文本，并转为长整数类型，若没有返回默认值，若解析失败返回默认值，若有多个返回第一个</p>
     *
     * @param default_ 默认值
     * @return 子文本节点中的文本，并转为长整数类型，若没有返回默认值，若解析失败返回默认值
     */
    long getTextAsLongWithoutException(long default_);

    /**
     * <p>获取子文本节点中的文本，并转为浮点类型，若没有返回null，若有多个返回第一个</p>
     *
     * @return 子文本节点中的文本，并转为整数类型
     * @throws NumberFormatException 当无法解析整数类型时抛出
     */
    @Nullable Float getTextAsFloat() throws NumberFormatException;

    /**
     * <p>获取子文本节点中的文本，并转为浮点类型，若没有返回null，若解析失败返回null，若有多个返回第一个</p>
     *
     * @return 子文本节点中的文本，并转为浮点类型，若解析失败返回null
     */
    @Nullable Float getTextAsFloatWithoutException();

    /**
     * <p>获取子文本节点中的文本，并转为浮点类型，若没有返回默认值，若有多个返回第一个</p>
     *
     * @param default_ 默认值
     * @return 子文本节点中的文本，并转为浮点类型，若没有返回默认值
     * @throws NumberFormatException 当无法解析整数类型时抛出
     */
    float getTextAsFloat(float default_) throws NumberFormatException;

    /**
     * <p>获取子文本节点中的文本，并转为浮点类型，若没有返回默认值，若解析失败返回默认值，若有多个返回第一个</p>
     *
     * @param default_ 默认值
     * @return 子文本节点中的文本，并转为浮点类型，若没有返回默认值，若解析失败返回默认值
     */
    float getTextAsFloatWithoutException(float default_);

    /**
     * <p>获取子文本节点中的文本，并转为双精度浮点类型，若没有返回null，若有多个返回第一个</p>
     *
     * @return 子文本节点中的文本，并转为整数类型
     * @throws NumberFormatException 当无法解析整数类型时抛出
     */
    @Nullable Double getTextAsDouble() throws NumberFormatException;

    /**
     * <p>获取子文本节点中的文本，并转为双精度浮点类型，若没有返回null，若解析失败返回null，若有多个返回第一个</p>
     *
     * @return 子文本节点中的文本，并转为双精度浮点类型，若解析失败返回null
     */
    @Nullable Double getTextAsDoubleWithoutException();

    /**
     * <p>获取子文本节点中的文本，并转为双精度浮点类型，若没有返回默认值，若有多个返回第一个</p>
     *
     * @param default_ 默认值
     * @return 子文本节点中的文本，并转为双精度浮点类型，若没有返回默认值
     * @throws NumberFormatException 当无法解析整数类型时抛出
     */
    double getTextAsDouble(double default_) throws NumberFormatException;

    /**
     * <p>获取子文本节点中的文本，并转为双精度浮点类型，若没有返回默认值，若解析失败返回默认值，若有多个返回第一个</p>
     *
     * @param default_ 默认值
     * @return 子文本节点中的文本，并转为双精度浮点类型，若没有返回默认值，若解析失败返回默认值
     */
    double getTextAsDoubleWithoutException(double default_);

    /**
     * <p>获取元素属性，若不存在返回null</p>
     *
     * @param name 属性名称
     * @return 元素属性，若不存在返回null
     */
    @Nullable String getAttribute(@NotNull String name);

    /**
     * <p>获取元素属性，若不存在返回空</p>
     *
     * @param name 属性名称
     * @return 元素属性，若不存在返回空
     */
    @NotNull Optional<String> getAttributeOptional(@NotNull String name);

    /**
     * <p>获取元素属性，若不存在抛出{@link NullPointerException}</p>
     *
     * @param name 属性名称
     * @return 元素属性，若不存在抛出{@link NullPointerException}
     */
    @NotNull String getAttributeNotNull(@NotNull String name);

    /**
     * <p>获取所有元素属性</p>
     *
     * @return 所有元素属性
     */
    @NotNull Map<String, String> getAttributes();

    /**
     * <p>以Beans的方式写入对象</p>
     *
     * @param object 对象
     */
    void writeObject(@NotNull Object object);
}
