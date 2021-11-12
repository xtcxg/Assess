package com.miex.user.api;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author liutz
 * @date 2021/11/9
 */
@FeignClient(value = "user-api",path = "/register")
public interface RegisterApi {
	Boolean register();
}
