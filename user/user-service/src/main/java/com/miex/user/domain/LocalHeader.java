package com.miex.user.domain;

import com.miex.user.config.AllRequestHandlerInterceptor;
import com.miex.user.domain.entity.User;
import lombok.Data;

/**
 * @author liutz
 * @date 2022/1/25
 */
@Data
public class LocalHeader {
	String requestId;
	User user;

	public static LocalHeader getHeader() {
		return AllRequestHandlerInterceptor.threadLocal.get();
	}
}
