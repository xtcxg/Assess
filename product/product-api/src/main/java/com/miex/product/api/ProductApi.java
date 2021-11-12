package com.miex.product.api;

import com.miex.product.domain.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author liutz
 * @date 2021/11/9
 */
@FeignClient(value = "product-api",path = "/product")
public interface ProductApi {
	Boolean add(Product product);
}
