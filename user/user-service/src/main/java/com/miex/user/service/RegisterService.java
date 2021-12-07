package com.miex.user.service;

import com.miex.user.api.RegisterApi;
import com.miex.user.domain.dao.UserDAO;
import com.miex.user.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author liutz
 * @date 2021/11/9
 */
@Slf4j
@Component
@Service
public class RegisterService implements RegisterApi {
	@Autowired
	UserDAO userDAO;

	@Override
	public Boolean register(User user) {
		String res = userDAO.insert(user);
		log.info("register user:{},result:{}",user,res);
		return true;
	}

	@Override
	public String login(User user) {
		String token = "";
		return token;
	}
}
