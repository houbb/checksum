package com.github.houbb.checksum.api.checksum;

import com.github.houbb.checksum.support.cache.ICheckFieldListCache;
import com.github.houbb.checksum.support.cache.ICheckValueCache;
import com.github.houbb.hash.api.IHash;
import com.github.houbb.sort.api.ISort;

/**
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IChecksumContext {

    /**
     * 目标对象
     * @return 目标对象
     * @since 0.0.1
     */
    Object target();

    /**
     * 排序实现
     * @return 排序实现
     * @since 0.0.1
     */
    ISort sort();

    /**
     * hash 策略
     *
     * @since 0.0.4
     * @return hash
     */
    IHash hash();

    /**
     * 加密次数
     * @return 次数
     * @since 0.0.4
     */
    int times();

    /**
     * 盐值
     * @return 盐值
     * @since 0.0.4
     */
    byte[] salt();

    /**
     * 编码
     * @return 编码
     * @since 0.0.4
     */
    String charset();

    /**
     * 签名字段缓存
     * @since 0.0.5
     * @return 签名缓存
     */
    ICheckValueCache checkValueCache();

    /**
     * 待加签的字段列表缓存
     * @since 0.0.5
     * @return 加签的字段缓存
     */
    ICheckFieldListCache checkFieldListCache();

}
