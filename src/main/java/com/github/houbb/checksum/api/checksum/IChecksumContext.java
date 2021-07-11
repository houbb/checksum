package com.github.houbb.checksum.api.checksum;

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
     * @since 0.0.4
     * @return hash
     * hash 策略
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

}
