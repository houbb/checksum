package com.github.houbb.checksum.exception;

/**
 * 加密验签运行时异常
 * @author binbin.hou
 * @since 0.0.3
 */
public class ChecksumException extends RuntimeException {

    public ChecksumException() {
    }

    public ChecksumException(String message) {
        super(message);
    }

    public ChecksumException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChecksumException(Throwable cause) {
        super(cause);
    }

    public ChecksumException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
