package com.miex.product;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author liutz
 * @date 2021/11/9
 */
@SpringBootApplication
@EnableDiscoveryClient
//@EnableAutoConfiguration
@EnableDubbo
public class ProductApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class);
	}
}
