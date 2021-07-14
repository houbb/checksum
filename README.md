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
    <version>0.0.6</version>
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

所有的工具类方法见 `ChecksumHelper`，且下面的几个方法都支持指定秘钥。

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

## 验证签名

```java
User user = User.buildUser();

boolean isValid = ChecksumHelper.isValid(user);
```

会对当前的 user 对象进行加签运算，并且将加签的结果和 user 本身的签名进行对比。

# 引导类

## ChecksumBs 引导类

为了满足更加灵活的场景，我们引入了基于 fluent-api 的 ChecksumBs 引导类。

上面的配置默认等价于：

```java
final String checksum = ChecksumBs
        .newInstance()
        .target(user)
        .charset("UTF-8")
        .checkSum(new DefaultChecksum())
        .sort(Sorts.quick())
        .hash(Hashes.md5())
        .times(1)
        .salt(null)
        .checkFieldListCache(new CheckFieldListCache())
        .checkValueCache(new CheckValueCache())
        .checkValue();
```

## 配置说明

上面所有的配置都是可以灵活替换的，所有的实现都支持用户自定义。

| 属性 | 说明 |
|:--|:--|
| target | 待加签对象 |
| charset | 编码 |
| checkSum | 具体加签实现 |
| sort | 字段排序策略 |
| hash | 字符串加密 HASH 策略 |
| salt | 加密对应的盐值 |
| times | 加密的次数 |
| checkFieldListCache | 待加签字段的缓存实现 |
| checkValueCache | 签名字段的缓存实现 |

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

