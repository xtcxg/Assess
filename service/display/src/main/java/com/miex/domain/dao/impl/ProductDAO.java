package com.miex.domain.dao.impl;

import com.miex.domain.Product;
import com.miex.domain.dao.ElasticsearchDAO;
import com.miex.exception.ESException;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductDAO extends ElasticsearchDAO<Product> {

//    public IndexResponse insert(Product product) throws ESException {
//        // todo 使用统一的 id 生产方法
//        return super.insert(product);
//    }

}
