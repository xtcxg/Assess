package com.miex.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author liutz
 * @date 2022/1/25
 */
@Configuration
public class WebHandlerConfig extends WebMvcConfigurationSupport {
	@Qualifier("allRequestHandlerInterceptor")
	HandlerInterceptor handlerInterceptor;

	public WebHandlerConfig(HandlerInterceptor handlerInterceptor) {
		this.handlerInterceptor = handlerInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration registration = registry.addInterceptor(handlerInterceptor);
		registration.addPathPatterns("/**");
	}
}
