# checksum

基于 java 注解生成加签验签 checksum。

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.houbb/checksum/badge.svg)](http://mvnrepository.com/artifact/com.github.houbb/checksum)
[![](https://img.shields.io/badge/license-Apache2-FF0080.svg)](https://github.com/houbb/checksum/blob/master/LICENSE.txt)
[![](https://img.shields.io/badge/license-Apache2-FF0080.svg)](https://github.com/houbb/secret/blob/master/LICENSE.txt)
[![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)](https://github.com/houbb/secret)

## 创作缘由

原来的代码中，checksum 的生成是用的工具类方法。

后来发现如下的问题：

1. 有些字段太大，不想参与验签，但是无法方便的调整。

2. 不同系统的 checksum 字段不同，只好把工具方法 copy 过去，改来改去。

感觉这样有很大的弊端，完全失去了灵活性。

## 特性

- 基于注解的 checksum 加签验签，灵活方便

- fluent 流式语法

- 支持用户策略自定义

- 高性能，和手动设置相差无几

- 轻量级框架，jar 包只有 19kb

## 更新记录

> [更新记录](doc/CHANGE_LOG.md)

# 快速开始

## 环境要求

jdk7+

maven 3.x+

## 引入

```xml
<plugin>
    <groupId>com.github.houbb</groupId>
    <artifactId>checksum</artifactId>
    <version>0.0.5</version>
</plugin>
```

## 定义待加签的示例对象

- User.java

```java
public class User {

    @CheckField
    private String name;

    private String password;

    @CheckField(required = false)
    private String address;

    @CheckValue
    private String checksum;

    //Getter & Setter
    //toString()
}
```

## 核心注解

`@CheckField` 表示参与加签的字段信息，默认都是参与加签的。指定 `required=false` 跳过加签。

`@CheckValue` 表示加签结果存放的字段，该字段类型需要为 String 类型。

后期将会添加一个 String 与不同类型的转换实现，拓展应用场景。

## 获取签名 

```java
User user = User.buildUser();

final String checksum = ChecksumHelper.checkValue(user);
```

该方法会把 User 对象中指定 `@CheckField` 的字段全部进行处理，

通过指定排序后进行拼接，然后结合指定加密策略构建最后的验签结果。

## 填充签名

```java
User user = User.buildUser();

ChecksumHelper.fill(user);
```

可以把对应的 checkValue 值默认填充到 `@CheckValue` 指定的字段上。

# 性能

## 背景

每次我们说到反射第一反应是方便，第二反应就是性能。

有时候往往因为关心性能，而选择手动一次次的复制，黏贴。

## 性能

详情见 [BenchmarkTest.java](https://github.com/houbb/checksum/blob/release_0.0.2/src/test/java/com/github/houbb/checksum/benchmark/BenchmarkTest.java)

![benchmark](https://github.com/houbb/checksum/blob/release_0.0.2/compare.png?raw=true)

本次进行 100w 次测试验证，耗时如下。

手动处理耗时：2505ms

注解处理耗时：2927ms

# Road-MAP

- [ ] 新增拦截器

