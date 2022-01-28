package com.miex.exception;

import com.miex.enums.RequestEnum;

/**
 * @author liutz
 * @date 2022/1/26
 */
public class RequestException extends RuntimeException{
	private Integer code;

	public RequestException(RequestEnum eu) {
		this(eu.getCode(), eu.getMsg());
	}

	public RequestException(RequestEnum eu,String msg) {
		this(eu.getCode(), msg);
	}

	private RequestException(Integer code,String msg) {
		super(msg);
		this.code = code;
	}

	public RequestException(RequestEnum eu,String msg,Throwable throwable) {
		this(eu.getCode(), msg, throwable);
	}

	private RequestException(Integer code,String msg,Throwable throwable) {
		super(msg,throwable);
		this.code = code;
	}
}
