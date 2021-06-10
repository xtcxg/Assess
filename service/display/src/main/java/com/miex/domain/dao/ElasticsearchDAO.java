package com.miex.domain.dao;

import com.alibaba.fastjson.JSONObject;
import com.miex.exception.ESException;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * ES 操作父类
 * @param <T> 操作索引的PO
 */
public abstract class ElasticsearchDAO<T> {

    Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    /**
     * 索引，由实现类确定
     */
    public String index;

    @Autowired
    RestHighLevelClient highClient;

    /**
     * @param t 插入的数据
     * @return IndexResponse 插入结果
     * @throws ESException
     */
    public IndexResponse insert(T t) throws ESException {
        IndexRequest indexRequest = new IndexRequest(index);
        indexRequest.source(JSONObject.toJSONString(t), XContentType.JSON);
        try{
            return highClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ESException("插入数据出错：" + JSONObject.toJSONString(t),e.getCause());
        }
    }

//    public IndexResponse deleteById(String id){
//
//    }

    public T selectById(String id) throws ESException{
        GetRequest request = new GetRequest(index,id);
        try {
            GetResponse response = highClient.get(request,RequestOptions.DEFAULT);
            if (response.isExists()){
                return JSONObject.parseObject( response.getSourceAsString(),entityClass);
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            throw new ESException("id查询出错：" + id,e.getCause());
        }
    }
}
