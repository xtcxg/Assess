package com.miex.domain.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.miex.domain.Product;
import com.miex.domain.dao.ElasticsearchDAO;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class ProductDAO extends ElasticsearchDAO<Product> {


    @PostConstruct
    public void construct(){
        this.index = "product";
    }

}
