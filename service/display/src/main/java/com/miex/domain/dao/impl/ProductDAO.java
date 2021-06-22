package com.miex.domain.dao.impl;

import com.miex.domain.Product;
import com.miex.domain.dao.ElasticsearchDAO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDAO extends ElasticsearchDAO<Product> {
    public List<Product> selectOnFields(List<String> params){
        return null;
    }
}
