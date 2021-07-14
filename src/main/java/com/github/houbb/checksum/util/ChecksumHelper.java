package com.github.houbb.checksum.util;

import com.github.houbb.checksum.bs.ChecksumBs;

/**
 * 驗簽工具類
 * @since 0.0.4
 */
public final class ChecksumHelper {

    private ChecksumHelper(){}

    /**
     * 获取签名
     * @param target 目标
     * @param salt 盐值
     * @return 结果
     * @since 0.0.4
     */
    public static String checkValue(final Object target, byte[] salt) {
        return ChecksumBs.newInstance()
                .target(target)
                .salt(salt)
                .checkValue();
    }

    /**
     * 获取签名
     * @param target 目标
     * @return 结果
     * @since 0.0.4
     */
    public static String checkValue(final Object target) {
        return checkValue(target, null);
    }

    /**
     * 填充签名
     * @param target 目标
     * @param salt 盐值
     * @since 0.0.4
     */
    public static void fill(final Object target, byte[] salt) {
        ChecksumBs.newInstance()
                .target(target)
                .salt(salt)
                .fill();
    }

    /**
     * 填充签名
     * @param target 目标
     * @since 0.0.4
     */
    public static void fill(final Object target) {
        fill(target, null);
    }

    /**
     * 是否合法
     * @param target 目标
     * @param salt 秘钥
     * @return 结果
     * @since 0.0.6
     */
    public static boolean isValid(final Object target, byte[] salt) {
        return ChecksumBs.newInstance()
                .target(target)
                .salt(salt)
                .isValid();
    }

    /**
     * 是否合法
     * @param target 目标
     * @return 结果
     * @since 0.0.6
     */
    public static boolean isValid(final Object target) {
        return isValid(target, null);
    }

}
