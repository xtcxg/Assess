package com.miex.product.api;

import com.miex.product.domain.entity.Product;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author liutz
 * @date 2021/11/9
 */

public interface ProductApi {
	Boolean add(Product product);
}
