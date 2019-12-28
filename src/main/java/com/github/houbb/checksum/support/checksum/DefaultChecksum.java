package com.github.houbb.checksum.support.checksum;

import com.github.houbb.checksum.api.checksum.IChecksum;
import com.github.houbb.checksum.api.checksum.IChecksumContext;
import com.github.houbb.checksum.api.checksum.IChecksumResult;
import com.github.houbb.checksum.api.secret.ISecret;
import com.github.houbb.checksum.api.secret.ISecretContext;
import com.github.houbb.checksum.support.cache.CheckFieldListCache;
import com.github.houbb.checksum.support.cache.ChecksumFieldCache;
import com.github.houbb.checksum.support.secret.DefaultSecretContext;
import com.github.houbb.converter.api.sorter.IMyFieldSort;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.reflect.api.IField;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.reflect.ReflectFieldUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 验签的默认实现
 *
 * @author binbin.hou
 * @since 0.0.1
 */
@ThreadSafe
public class DefaultChecksum implements IChecksum {

    @Override
    public IChecksumResult checksum(IChecksumContext context) {
        final Object target = context.target();
        final ISecret secret = context.secret();
        final IMyFieldSort sort = context.sort();
        final Class clazz = target.getClass();

        // 执行一次过滤，只有 @CheckField 对应的字段才会被传入
        List<IField> checkFieldList = Instances.singleton(CheckFieldListCache.class)
                .get(clazz);

        // 执行排序
        if (CollectionUtil.isNotEmpty(checkFieldList)) {
            checkFieldList = sort.sort(checkFieldList);
        }

        // 执行值初始化
        initFieldValue(target, checkFieldList);

        // 执行加签
        ISecretContext secretContext = DefaultSecretContext
                .newInstance()
                .target(target)
                .fields(checkFieldList);
        final String checksum = secret.secret(secretContext);

        // 构建结果
        return DefaultChecksumResult
                .newInstance()
                .checksum(checksum);
    }

    @Override
    public void fill(IChecksumContext context) {
        Object target = context.target();
        final Class clazz = target.getClass();

        // 验签结果
        final IChecksumResult checkSumResult = this.checksum(context);
        final String checksum = checkSumResult.checksum();

        //这里也可以添加缓存。
        Field checksumField = Instances.singleton(ChecksumFieldCache.class).get(clazz);

        // reflect asm 设置属性
        if (ObjectUtil.isNotNull(checksumField)) {
            ReflectFieldUtil.setValue(checksumField, target, checksum);
        }
    }

    /**
     * 初始化字段值
     *
     * @param target    目标对象
     * @param fieldList 字段列表
     * @since 0.0.2
     */
    private void initFieldValue(final Object target,
                                      final List<IField> fieldList) {
        if (CollectionUtil.isNotEmpty(fieldList)) {
            for (IField field : fieldList) {
                final Object value = ReflectFieldUtil.getValue(field.field(), target);
                field.value(value);
            }
        }
    }

}
