package com.github.houbb.checksum.support.checksum;

import com.github.houbb.checksum.api.checksum.IChecksumResult;
import com.github.houbb.heaven.annotation.NotThreadSafe;

/**
 * 默认的加签结果信息
 * @author binbin.hou
 * @since 0.0.1
 */
@NotThreadSafe
public class DefaultChecksumResult implements IChecksumResult {

    /**
     * 加签结果
     */
    private String checksum;

    public static DefaultChecksumResult newInstance() {
        return new DefaultChecksumResult();
    }

    @Override
    public String checksum() {
        return checksum;
    }

    public DefaultChecksumResult checksum(String checksum) {
        this.checksum = checksum;
        return this;
    }

    @Override
    public String toString() {
        return "DefaultChecksumResult{" +
                "checksum='" + checksum + '\'' +
                '}';
    }

}
