package com.miex.exception;

/**
 * @author liutz
 * @date 2021/11/5
 */
public class ESException extends RuntimeException{
    public ESException(String message) {
        super(message);
    }

    public ESException(String message,Throwable cause) {
        super(message,cause);
    }
}
