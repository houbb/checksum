package com.github.houbb.checksum.exception;

/**
 * 加密验签运行时异常
 * @author binbin.hou
 * @since 0.0.3
 */
public class ChecksumRuntimeException extends RuntimeException {

    public ChecksumRuntimeException() {
    }

    public ChecksumRuntimeException(String message) {
        super(message);
    }

    public ChecksumRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChecksumRuntimeException(Throwable cause) {
        super(cause);
    }

    public ChecksumRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
