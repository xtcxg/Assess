package com.miex.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author liutz
 * @since 2022/1/27
 */
@Configuration
public class AuthConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.formLogin().permitAll()
		.and().authorizeRequests()
		.antMatchers("/oauth/**","/register/**").permitAll()
		.anyRequest().authenticated()
		.and().logout().permitAll()
		.and().csrf().disable();
	}
}
