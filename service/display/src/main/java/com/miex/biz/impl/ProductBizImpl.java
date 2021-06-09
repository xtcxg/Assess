package com.miex.biz.impl;

import com.miex.biz.ElasticsearchBiz;
import com.miex.domain.Product;
import org.elasticsearch.action.index.IndexRequest;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ProductBizImpl extends ElasticsearchBiz<Product> {
    IndexRequest request = new IndexRequest("product");;

    @PostConstruct
    public void ProductBizImpl(){

    }
}
