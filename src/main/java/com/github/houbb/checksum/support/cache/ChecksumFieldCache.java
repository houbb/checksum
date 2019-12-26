package com.github.houbb.checksum.support.cache;

import com.github.houbb.checksum.annotation.Checksum;
import com.github.houbb.heaven.support.cache.impl.AbstractCache;
import com.github.houbb.heaven.support.cache.impl.ClassFieldListCache;

import java.lang.reflect.Field;
import java.util.List;

/**
 * checksum 字段缓存
 * @author binbin.hou
 * @since 0.0.2
 * @see com.github.houbb.checksum.annotation.Checksum 验签字段
 */
public class ChecksumFieldCache extends AbstractCache<Class, Field> {

    @Override
    protected Field buildValue(Class aClass) {
        List<Field> fieldList = ClassFieldListCache.getInstance().get(aClass);

        for(Field field : fieldList) {
            if(field.isAnnotationPresent(Checksum.class)) {
                return field;
            }
        }

        return null;
    }

}
