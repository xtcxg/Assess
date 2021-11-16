package com.miex.product.service;

import com.alibaba.fastjson.JSONObject;
import com.miex.product.api.ProductApi;
import com.miex.product.domain.entity.Product;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @author liutz
 * @date 2021/11/9
 */
@Component
@Service
public class ProductService implements ProductApi {
	@Override
	public Boolean add(Product product) {
		System.out.println(JSONObject.toJSONString(product));
		return true;
	}
}
