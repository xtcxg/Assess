package com.miex.product.rest;

import com.miex.domain.dto.res.Response;
import com.miex.product.api.ProductApi;
import com.miex.product.domain.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

	@Qualifier("productService")
	@Autowired
	ProductApi productApi;

	@PutMapping("/add")
	public Response<Boolean> add(@RequestBody Product product) {
		Boolean res = productApi.add(product);
		return new Response<>(res);
	}
}
