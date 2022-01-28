package com.miex.user.rest;

import com.miex.domain.dto.res.Response;
import com.miex.enums.RequestEnum;
import com.miex.exception.RequestException;
import com.miex.product.api.ProductApi;
import com.miex.user.api.RegisterApi;
import com.miex.user.domain.entity.User;
import com.miex.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

/**
 * @author liutz
 * @date 2021/11/9
 */
@Validated
@RestController
@RequestMapping("register")
@Slf4j
public class RegisterRest {
	@Qualifier("registerService")
	@Autowired
	RegisterApi registerApi;

	@Reference(lazy = true,check = false)
	ProductApi productApi;

	@PostMapping("/")
	public Response<Boolean> register(@RequestBody User user) {
		Boolean res = registerApi.register(user);
		return new Response<>(res);
	}

	@PostMapping("/login")
	public Response<String> login(@RequestBody User user) {
		if (StringUtil.isEmpty(user.getUserId())) {
			throw new RequestException(RequestEnum.PARAMS_VALID_FAIL,"userId can't be null");
		}
		String token = registerApi.login(user);
		return new Response<>(token);
	}

	@GetMapping(value = "/query/user",produces = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Response<User> queryUser(@RequestParam("userId") @NotEmpty String userId) {
		User user = registerApi.getUserInfo(userId);
		return new Response<>(user);
	}
}
