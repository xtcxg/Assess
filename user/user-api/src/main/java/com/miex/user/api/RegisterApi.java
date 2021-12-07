package com.miex.user.api;

import com.miex.user.domain.entity.User;

/**
 * @author liutz
 * @date 2021/11/9
 */
public interface RegisterApi {
	Boolean register(User user);

	String login(User user);
}
