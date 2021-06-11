package com.miex.domain.dao;

import com.alibaba.fastjson.JSONObject;
import com.miex.exception.ESException;
import com.miex.util.anno.Id;
import com.miex.util.anno.Index;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.concurrent.TimeUnit;

/**
 * ES 操作父类
 * @param <T> 操作索引的PO
 */
public abstract class ElasticsearchDAO<T> {

    /**
     * 泛型的类型
     */
    Class<T> entityClass;

    /**
     * 索引，在 PO 的 @Index 设置
     */
    public String index;

    @Autowired
    RestHighLevelClient highClient;

    @PostConstruct
    public void construct(){
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.index = entityClass.getAnnotation(Index.class).value();
    }

    /**
     * @param t 插入的数据, 如果有 @Id 注解的字段且值不为空，则id使用该字段的值, 如果没有，则由es自行分配
     * @return IndexResponse 插入结果
     * @throws ESException
     */
    public IndexResponse insert(T t) throws ESException {
        IndexRequest indexRequest = new IndexRequest(index);
        indexRequest.source(JSONObject.toJSONString(t), XContentType.JSON);
        try{
            String id = null;
            Field[] fields = t.getClass().getDeclaredFields();
            for (Field field:fields) {
                if (null != field.getAnnotation(Id.class)){
                    String fieldName = field.getName();
                    String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    id = (String) t.getClass().getMethod(methodName).invoke(t,new Object[]{});
                }
            }
            if (null != id && !"".equals(id) ) {
                indexRequest.id(id);
            }
            return highClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ESException("插入数据出错：" + JSONObject.toJSONString(t),e.getCause());
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
            throw new ESException("数据解析出错：" + JSONObject.toJSONString(t),e.getCause());
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

    public T selectOne(T t) throws ESException {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("prodId","00000011231"));

        sourceBuilder.timeout(new TimeValue(3, TimeUnit.SECONDS));
        SearchRequest request = new SearchRequest(index);
        request.source(sourceBuilder);
        try {
            SearchResponse response = highClient.search(request,RequestOptions.DEFAULT);
            if (response.getHits().getTotalHits().value > 0L) {
                return JSONObject.parseObject(response.getHits().getAt(0).getSourceAsString(),entityClass);
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ESException("单条查询出错：" + t.toString(),e);
        }
    }
}
