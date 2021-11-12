package com.miex.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author liutz
 * @date 2021/11/9
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ProductApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class);
	}
}
