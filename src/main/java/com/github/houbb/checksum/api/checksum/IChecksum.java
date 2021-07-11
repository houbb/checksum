package com.github.houbb.checksum.api.checksum;

/**
 * 1. 为了简化结果，所有的签名都约定为字符串。
 * @author binbin.hou
 * @since 0.0.1
 */
public interface IChecksum {

    /**
     * 根据上下文生产加签名结果
     * @param context 上下文
     * @return 结果
     */
    IChecksumResult checkValue(final IChecksumContext context);

    /**
     * 根据上下文生产加签名结果并将结果填充
     * @param context 上下文
     */
    void fillCheckValue(final IChecksumContext context);

}
