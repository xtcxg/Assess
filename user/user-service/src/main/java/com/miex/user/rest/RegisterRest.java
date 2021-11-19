package com.miex.user.rest;

import com.miex.domain.dto.res.Response;
import com.miex.product.api.ProductApi;
import com.miex.product.domain.entity.Product;
import com.miex.user.api.RegisterApi;
import com.miex.user.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liutz
 * @date 2021/11/9
 */
@RestController
@RequestMapping("register")
@Slf4j
public class RegisterRest {
	@Qualifier("registerService")
	@Autowired
	RegisterApi registerApi;

	@Reference
	ProductApi productApi;

	@PostMapping("/")
	public Response<Boolean> register(@RequestBody User user) {
		Boolean res = registerApi.register();
		return new Response<>(res);
	}
}
