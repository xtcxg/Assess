package com.miex.domain.dao.impl;


import com.miex.domain.User;
import com.miex.domain.dao.ElasticsearchDAO;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *
 */
@Component
public class UserDAO extends ElasticsearchDAO<User> {

    @PostConstruct
    public void construct(){
        this.index = "user";
    }
}
