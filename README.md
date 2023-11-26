# 未测试
项目没来得及测试，可能有BUG或完全无法使用

# JavaXML
Java解析XML工具

# XML节点
XML万物皆节点

XML有多种节点，有文档节点、元素节点、文本节点、注释节点等

# 解析方式
```java
XMLDocument document = JavaDOMXML.parse(file);
```

# Beans方式
## 注解
### 声明为变量
```java
@ConfigValue("field-name")
```

### 当未读到值时的做法
```java
@OnMiss(MissDo.IGNORE)
```

### 当类型错误时的做法
```java
@OnWrongType(WrongDo.IGNORE)
```

### 默认值
```java
@DefaultValue("127")
```