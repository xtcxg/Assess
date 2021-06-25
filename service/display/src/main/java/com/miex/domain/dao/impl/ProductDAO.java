package com.miex.domain.dao.impl;

import com.alibaba.fastjson.JSONObject;
import com.miex.domain.Product;
import com.miex.domain.dao.ElasticsearchDAO;
import com.miex.domain.dto.UnifiedRequest;
import com.miex.exception.ESException;
import org.elasticsearch.index.query.CombinedFieldsQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ProductDAO extends ElasticsearchDAO<Product> {
    public List<Product> selectOnFields(UnifiedRequest<String> params) throws ESException {
        CombinedFieldsQueryBuilder cf = QueryBuilders.combinedFieldsQuery(params.getData(),
                "prodName","prodAlias","prodTags","underBrand","underSeries","broadCategory","subCategories","lastCategory");
        cf.operator(Operator.AND);
        try {
            return queryTemplate(cf,params.getPage(), params.getSize());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ESException("字段查询出错，data：" + JSONObject.toJSONString(params),e);
        }
    }
}
