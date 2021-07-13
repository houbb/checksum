package com.github.houbb.checksum.support.cache;

import com.github.houbb.heaven.reflect.api.IField;

import java.util.List;

/**
 * 参与校验的字段缓存
 *
 * @author binbin.hou
 * @since 0.0.5
 */
public interface ICheckFieldListCache {

    /**
     * 获取待验签字段列表
     * @param clazz 类
     * @return 结果
     * @since 0.0.5
     */
    List<IField> get(Class<?> clazz);

}
