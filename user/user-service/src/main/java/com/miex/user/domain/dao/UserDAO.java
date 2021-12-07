package com.miex.user.domain.dao;

import com.miex.domain.dao.ElasticsearchDAO;
import com.miex.user.domain.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author liutz
 * @date 2021/11/18
 */

@Component
public class UserDAO extends ElasticsearchDAO<User> {

}
