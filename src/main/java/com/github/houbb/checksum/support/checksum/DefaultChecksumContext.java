package com.github.houbb.checksum.support.checksum;

import com.github.houbb.checksum.api.checksum.IChecksumContext;
import com.github.houbb.hash.api.IHash;
import com.github.houbb.heaven.annotation.NotThreadSafe;
import com.github.houbb.sort.api.ISort;

/**
 * 默认的验签上下文
 * @author binbin.hou
 * @since 0.0.1
 */
@NotThreadSafe
public class DefaultChecksumContext implements IChecksumContext {

    /**
     * 待处理的对象
     * @since 0.0.1
     */
    private Object target = null;

    /**
     * 排序算法
     * @since 0.0.4
     */
    private ISort sort;

    /**
     * hash 策略
     * @since 0.0.4
     */
    private IHash hash;

    /**
     * 迭代次数
     * @since 0.0.4
     */
    private int times;

    /**
     * 加密的盐值
     * @since 0.0.4
     */
    private byte[] salt;

    /**
     * 编码
     * @since 0.0.4
     */
    private String charset;

    /**
     * 创建实例
     * @return 实例
     */
    public static DefaultChecksumContext newInstance() {
        return new DefaultChecksumContext();
    }

    public DefaultChecksumContext target(Object target) {
        this.target = target;
        return this;
    }

    @Override
    public Object target() {
        return target;
    }

    @Override
    public ISort sort() {
        return sort;
    }

    public DefaultChecksumContext sort(ISort sort) {
        this.sort = sort;
        return this;
    }

    public IHash hash() {
        return hash;
    }

    public DefaultChecksumContext hash(IHash hash) {
        this.hash = hash;
        return this;
    }

    public int times() {
        return times;
    }

    public DefaultChecksumContext times(int times) {
        this.times = times;
        return this;
    }

    public byte[] salt() {
        return salt;
    }

    public DefaultChecksumContext salt(byte[] salt) {
        this.salt = salt;
        return this;
    }

    @Override
    public String charset() {
        return charset;
    }

    public DefaultChecksumContext charset(String charset) {
        this.charset = charset;
        return this;
    }
}
