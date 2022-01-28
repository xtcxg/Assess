package com.miex.user.service;

import com.alibaba.fastjson.JSONObject;
import com.miex.domain.dto.res.Response;
import com.miex.user.api.RegisterApi;
import com.miex.user.domain.LocalHeader;
import com.miex.user.domain.dao.UserDAO;
import com.miex.user.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;


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

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Boolean register(User user) {
		String res = userDAO.insert(user);
		log.info("register user:{},result:{}",user,res);
		return true;
	}

	@Override
	public String login(User user) {
		List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
		return passwordEncoder.encode(user.getPassword());
	}

	@Override
	public User getUserInfo(String userId) {
		log.info("requestId:" + LocalHeader.getHeader().getRequestId());
		return userDAO.selectById(userId);
	}
}
