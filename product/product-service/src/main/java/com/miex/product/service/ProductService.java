package com.miex.product.service;

import com.alibaba.fastjson.JSONObject;
import com.miex.product.api.ProductApi;
import com.miex.product.domain.entity.Product;
import org.springframework.stereotype.Service;

/**
 * @author liutz
 * @date 2021/11/9
 */
@Service
public class ProductService implements ProductApi {
	@Override
	public Boolean add(Product product) {
		System.out.println(JSONObject.toJSONString(product));
		return true;
	}
}
