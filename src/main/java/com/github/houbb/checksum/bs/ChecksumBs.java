package com.github.houbb.checksum.bs;

import com.github.houbb.checksum.annotation.CheckValue;
import com.github.houbb.checksum.api.checksum.IChecksum;
import com.github.houbb.checksum.api.checksum.IChecksumContext;
import com.github.houbb.checksum.support.checksum.DefaultChecksum;
import com.github.houbb.checksum.support.checksum.DefaultChecksumContext;
import com.github.houbb.hash.api.IHash;
import com.github.houbb.hash.core.hash.Hashes;
import com.github.houbb.heaven.constant.CharsetConst;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.sort.api.ISort;
import com.github.houbb.sort.core.api.Sorts;
import sun.nio.cs.StandardCharsets;

/**
 * 验签引导类
 * @author binbin.hou
 * @since 0.0.1
 */
public final class ChecksumBs {

    /**
     * 加签的处理类
     * 1. 暂时不开放这个类
     * @since 0.0.1
     */
    private IChecksum checkSum = Instances.singleton(DefaultChecksum.class);

    /**
     * 待处理的对象
     * @since 0.0.1
     */
    private Object target = null;

    /**
     * 排序算法
     * @since 0.0.4
     */
    private ISort sort = Sorts.quick();

    /**
     * hash 策略
     * @since 0.0.4
     */
    private IHash hash = Hashes.md5();

    /**
     * 迭代次数
     * @since 0.0.4
     */
    private int times = 1;

    /**
     * 加密的盐值
     * @since 0.0.4
     */
    private byte[] salt = null;

    /**
     * 编码
     * @since 0.0.4
     */
    private String charset = CharsetConst.UTF8;

    /**
     * 私有化构造器
     * @since 0.0.1
     */
    private ChecksumBs(){}

    /**
     * 创建引导类示例
     * @return 引导类新实例
     * @since 0.0.1
     */
    public static ChecksumBs newInstance() {
        return new ChecksumBs();
    }

    public ChecksumBs target(Object target) {
        ArgUtil.notNull(target, "target");

        this.target = target;
        return this;
    }

    public ChecksumBs checkSum(IChecksum checkSum) {
        ArgUtil.notNull(checkSum, "checkSum");

        this.checkSum = checkSum;
        return this;
    }

    public ChecksumBs sort(ISort sort) {
        this.sort = sort;
        return this;
    }

    public ChecksumBs hash(IHash hash) {
        this.hash = hash;
        return this;
    }

    public ChecksumBs times(int times) {
        this.times = times;
        return this;
    }

    public ChecksumBs salt(byte[] salt) {
        this.salt = salt;
        return this;
    }

    public ChecksumBs charset(String charset) {
        this.charset = charset;
        return this;
    }

    /**
     * 获取加签结果
     * 1. 不会将这个值放在对象中
     * @return 加签结果
     * @since 0.0.1
     */
    public String checkValue() {
        //fast-failed
        if(target == null) {
            return null;
        }

        IChecksumContext context = buildContext();

        return this.checkSum.checkValue(context).checksum();
    }

    /**
     * 填充 CheckValue 字段信息
     * @see CheckValue 标识加签的字段注解
     * @since 0.0.1
     */
    public void fill() {
        if(target == null) {
            return;
        }

        IChecksumContext context = buildContext();

        this.checkSum.fillCheckValue(context);
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
                .times(times)
                .hash(hash)
                .sort(sort)
                .salt(salt)
                .charset(charset);
    }

}
