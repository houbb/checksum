package com.github.houbb.checksum.support.cache;

import java.lang.reflect.Field;

/**
 * 签名字段的缓存
 *
 * @author binbin.hou
 * @since 0.0.5
 */
public interface ICheckValueCache {

    /**
     * 获取对应的签名字段列表
     * @param clazz 类
     * @return 结果
     * @since 0.0.5
     */
    Field get(Class<?> clazz);

}
