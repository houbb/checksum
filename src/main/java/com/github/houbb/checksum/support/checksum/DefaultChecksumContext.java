package com.github.houbb.checksum.support.checksum;

import com.github.houbb.checksum.api.checksum.IChecksumContext;
import com.github.houbb.checksum.api.secret.ISecret;
import com.github.houbb.checksum.api.sort.ISort;
import com.github.houbb.heaven.annotation.NotThreadSafe;

/**
 * 默认的验签上下文
 * @author binbin.hou
 * @since 0.0.1
 */
@NotThreadSafe
public class DefaultChecksumContext implements IChecksumContext {

    /**
     * 目标对象
     */
    private Object target;

    /**
     * 加密策略
     */
    private ISecret secret;

    /**
     * 排序策略
     */
    private ISort sort;

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

    public DefaultChecksumContext secret(ISecret secret) {
        this.secret = secret;
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

    @Override
    public ISecret secret() {
        return secret;
    }

    @Override
    public String toString() {
        return "DefaultChecksumContext{" +
                "target=" + target +
                ", secret=" + secret +
                ", sort=" + sort +
                '}';
    }

}
