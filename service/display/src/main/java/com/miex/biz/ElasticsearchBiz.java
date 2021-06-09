package com.miex.biz;

import com.alibaba.fastjson.JSONObject;
import com.miex.exception.ESException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class ElasticsearchBiz<T> {

    @Autowired
    RestHighLevelClient highClient;
    IndexRequest request;

    public void insert(T t) throws ESException {
        try{
            request.source(JSONObject.toJSONString(t)
                    , XContentType.JSON);
            IndexResponse response = highClient.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new ESException("插入数据出错：" + JSONObject.toJSONString(t),e.getCause());
        }
    }
}
