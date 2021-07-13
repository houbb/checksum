package com.github.houbb.checksum.support.checksum;

import com.github.houbb.checksum.api.checksum.IChecksum;
import com.github.houbb.checksum.api.checksum.IChecksumContext;
import com.github.houbb.checksum.api.checksum.IChecksumResult;
import com.github.houbb.checksum.constant.ChecksumConst;
import com.github.houbb.checksum.support.cache.ICheckFieldListCache;
import com.github.houbb.checksum.support.cache.ICheckValueCache;
import com.github.houbb.hash.api.IHash;
import com.github.houbb.hash.bs.HashBs;
import com.github.houbb.hash.core.handler.HashResultHandlers;
import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.reflect.api.IField;
import com.github.houbb.heaven.util.lang.ObjectUtil;
import com.github.houbb.heaven.util.lang.StringUtil;
import com.github.houbb.heaven.util.lang.reflect.ReflectFieldUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.github.houbb.sort.api.ISort;

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
    public IChecksumResult checkValue(IChecksumContext context) {
        final Object target = context.target();
        final ISort sort = context.sort();
        final int times = context.times();
        final byte[] salt = context.salt();
        final String charset = context.charset();
        final IHash hash = context.hash();
        final ICheckFieldListCache checkFieldListCache = context.checkFieldListCache();

        // 1. 执行一次过滤，只有 @CheckField 对应的字段才会被传入
        List<IField> checkFieldList = this.sortFieldList(checkFieldListCache,
                target, sort);

        // 2. 执行值初始化
        this.initFieldValue(target, checkFieldList);

        // 3. 获取 Field 的拼接字符串
        String fieldListString = this.getFieldListString(checkFieldList);

        // 4. 执行加签
        byte[] sourceBytes = StringUtil.getBytes(fieldListString, charset);
        String hex = HashBs.newInstance().hash(hash)
                .salt(salt)
                .times(times)
                .source(sourceBytes)
                .execute(HashResultHandlers.hex());

        // 构建结果
        return DefaultChecksumResult
                .newInstance()
                .checksum(hex);
    }

    @Override
    public void fillCheckValue(IChecksumContext context) {
        Object target = context.target();
        final Class<?> clazz = target.getClass();

        // 验签结果
        final IChecksumResult checkSumResult = this.checkValue(context);
        final String checksum = checkSumResult.checksum();
        final ICheckValueCache checkValueCache = context.checkValueCache();

        //这里也可以添加缓存。
        Field checksumField = checkValueCache.get(clazz);

        // reflect asm 设置属性
        if (ObjectUtil.isNotNull(checksumField)) {
            ReflectFieldUtil.setValue(checksumField, target, checksum);
        }
    }

    /**
     * 排序对应的字段
     *
     * @param checkFieldListCache 待加签字段的缓存
     * @param target 待加签的对象
     * @param sort 排序
     * @since 0.0.4
     * @return 排序後的字段列表
     */
    protected List<IField> sortFieldList(final ICheckFieldListCache checkFieldListCache,
                                         final Object target, final ISort sort) {
        final Class<?> clazz = target.getClass();

        // 执行一次过滤
        List<IField> checkFieldList = checkFieldListCache.get(clazz);

        // 执行排序
        if (CollectionUtil.isNotEmpty(checkFieldList)) {
            sort.sort(checkFieldList);
        }

        return checkFieldList;
    }

    /**
     * 初始化字段值
     *
     * @param target    目标对象
     * @param fieldList 字段列表
     * @since 0.0.2
     */
    protected void initFieldValue(final Object target,
                                      final List<IField> fieldList) {
        if (CollectionUtil.isNotEmpty(fieldList)) {
            for (IField field : fieldList) {
                final Object value = ReflectFieldUtil.getValue(field.field(), target);
                field.value(value);
            }
        }
    }

    /**
     * 获取字段列表字符串
     * @param fieldList 列表
     * @return 字符串
     */
    protected String getFieldListString(List<IField> fieldList) {
        if (CollectionUtil.isEmpty(fieldList)) {
            return ChecksumConst.DEFAULT_CHECK_SUM;
        }

        // 后期可以拓展：每个字段都有一个 convertToStr()
        //null 字段使用 "" 字符串
        StringBuilder stringBuilder = new StringBuilder();
        for(IField iField : fieldList) {
            final String fieldString = StringUtil.objectToString(iField.value(), StringUtil.EMPTY);
            stringBuilder.append(fieldString);
        }

        return stringBuilder.toString();
    }

}
