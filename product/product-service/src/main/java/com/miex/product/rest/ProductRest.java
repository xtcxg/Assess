package com.miex.product.rest;

import com.miex.domain.dto.res.Response;
import com.miex.enums.RequestEnum;
import com.miex.product.api.ProductApi;
import com.miex.product.domain.entity.Product;
import com.miex.user.api.RegisterApi;
import com.miex.user.domain.entity.User;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liutz
 * @date 2021/11/9
 */
@RestController
@RequestMapping("product")
public class ProductRest {

	@Reference
	ProductApi productApi;
	@Reference
	RegisterApi registerApi;

	@PutMapping("/add")
	public Response<Boolean> add(@RequestBody Product product) {
		Boolean res = productApi.add(product);
		return new Response<>(res);
	}

	@PostMapping("/query/user")
	public Response<User> queryUserInfo(@RequestBody Product product) {
		if (product.getOwnerId() == null) {
			return new Response<>(RequestEnum.PARAMS_VALID_FAIL,"ownerId can,t be null");
		}
		User user = registerApi.getUserInfo(product.getOwnerId());
		return new Response<>(user);
	}
}
