package com.miex.user.service;

import com.miex.user.api.RegisterApi;
import org.springframework.stereotype.Service;

/**
 * @author liutz
 * @date 2021/11/9
 */
@Service
public class RegisterService implements RegisterApi {

	@Override
	public Boolean register() {

		return true;
	}
}
