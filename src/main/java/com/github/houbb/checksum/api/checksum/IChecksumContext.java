package com.github.houbb.checksum.api.checksum;

import com.github.houbb.checksum.api.secret.ISecret;
import com.github.houbb.converter.api.sorter.IMyFieldSort;

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
    IMyFieldSort sort();

    /**
     * 加密实现
     * @return 加密实现
     * @since 0.0.1
     */
    ISecret secret();

}
