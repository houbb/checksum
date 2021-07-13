package com.github.houbb.checksum.support.cache;

import com.github.houbb.checksum.annotation.CheckField;
import com.github.houbb.heaven.reflect.api.IField;
import com.github.houbb.heaven.support.cache.impl.AbstractCache;
import com.github.houbb.heaven.support.cache.impl.DefaultFieldListCache;
import com.github.houbb.heaven.support.condition.ICondition;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.util.CollectionUtil;

import java.lang.reflect.Field;
import java.util.List;

/**
 * CheckField 字段列表缓存
 * @author binbin.hou
 * @since 0.0.2
 * @see com.github.houbb.checksum.annotation.CheckField 参加验签字段
 */
public class CheckFieldListCache extends AbstractCache<Class, List<IField>>
    implements ICheckFieldListCache {

    @Override
    protected List<IField> buildValue(Class clazz) {
        // 执行一次过滤，只有 @CheckField 对应的字段才会被传入
        // 默认引入字段
        // 如果有 @CheckField && required=false，则忽略。
        List<IField> allFieldList = Instances.singleton(DefaultFieldListCache.class)
                .get(clazz);

        return CollectionUtil.conditionList(allFieldList, new ICondition<IField>() {
            @Override
            public boolean condition(IField iField) {
                final Field field = iField.field();
                // 如果指定，则看是否需要
                if(field.isAnnotationPresent(CheckField.class)) {
                    CheckField checkField = field.getAnnotation(CheckField.class);
                    return checkField.required();
                }

                // 默认引入
                return true;
            }
        });
    }

}
