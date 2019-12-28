package com.github.houbb.checksum.core;

import com.github.houbb.checksum.annotation.Checksum;
import com.github.houbb.checksum.api.checksum.IChecksum;
import com.github.houbb.checksum.api.checksum.IChecksumContext;
import com.github.houbb.checksum.api.secret.ISecret;
import com.github.houbb.checksum.support.checksum.DefaultChecksum;
import com.github.houbb.checksum.support.checksum.DefaultChecksumContext;
import com.github.houbb.checksum.support.secret.Secrets;
import com.github.houbb.converter.api.sorter.IMyFieldSort;
import com.github.houbb.converter.core.sorter.myfield.NameAscMyFieldSort;
import com.github.houbb.converter.core.sorter.myfield.NoMyFieldSort;
import com.github.houbb.heaven.annotation.NotThreadSafe;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.common.ArgUtil;

/**
 * 验签引导类
 * @author binbin.hou
 * @since 0.0.1
 */
@NotThreadSafe
public final class ChecksumBs {

    /**
     * 待处理的对象
     * @since 0.0.1
     */
    private Object target;

    /**
     * 加签的实现
     * @since 0.0.1
     */
    private ISecret secret = Secrets.defaultMd5Secret();

    /**
     * 排序的实现
     * 默认不进行任何排序。
     * @since 0.0.1
     */
    private IMyFieldSort sort = Instances.singleton(NoMyFieldSort.class);

    /**
     * 加签的处理类
     * 1. 暂时不开放这个类
     * @since 0.0.1
     */
    private IChecksum checkSum = Instances.singleton(DefaultChecksum.class);

    /**
     * 私有化构造器
     * @since 0.0.1
     */
    private ChecksumBs(){}

    /**
     * 创建引导类示例
     * @param target 待处理的对象
     * @return 引导类新实例
     * @since 0.0.1
     */
    public static ChecksumBs newInstance(final Object target) {
        ArgUtil.notNull(target, "target");

        ChecksumBs checkSumBs = new ChecksumBs();
        checkSumBs.target = target;
        return checkSumBs;
    }

    /**
     * 指定加签实现
     * @param secret 加签策略
     * @return this
     * @since 0.0.1
     */
    private ChecksumBs secret(ISecret secret) {
        ArgUtil.notNull(secret, "secret");

        this.secret = secret;
        return this;
    }

    /**
     * 获取加签结果
     * 1. 不会将这个值放在对象中
     * @return 加签结果
     * @since 0.0.1
     */
    public String checksum() {
        IChecksumContext context = buildContext();

        return this.checkSum.checksum(context).checksum();
    }

    /**
     * 填充 checkSum 字段信息
     * @see Checksum 标识加签的字段注解
     * @since 0.0.1
     */
    public void fill() {
        IChecksumContext context = buildContext();

        this.checkSum.fill(context);
    }

    /**
     * 构建验签上下文
     * @return 上下文
     * @since 0.0.2
     */
    private IChecksumContext buildContext() {
        return DefaultChecksumContext
                .newInstance()
                .target(target)
                .secret(secret)
                .sort(sort);
    }

}
