package com.miex.domain.dao;

import com.alibaba.fastjson.JSONObject;
import com.miex.exception.ESException;
import com.miex.util.anno.Id;
import com.miex.util.anno.Index;
import org.apache.http.client.methods.RequestBuilder;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.TopHitsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
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
     * 插入的数据, _id 使用数据的 id
     * @param t
     * @return IndexResponse 插入结果
     * @throws ESException
     */
    public IndexResponse insert(T t) throws ESException {
        IndexRequest indexRequest = new IndexRequest(index);
        indexRequest.source(JSONObject.toJSONString(t), XContentType.JSON);
        try{
            indexRequest.id(getId(t));
            return highClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ESException("插入数据出错：" + JSONObject.toJSONString(t),e.getCause());
        }
    }

    // 通过注解信息
    String getId(T t) throws IOException{
        String id = null;
        try {
            Field[] fields = t.getClass().getDeclaredFields();
            for (Field field:fields) {
                if (null != field.getAnnotation(Id.class)){
                    String fieldName = field.getName();
                    String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    id = (String) t.getClass().getMethod(methodName).invoke(t,new Object[]{});

                    if (null == id || "".equals(id)) {
                        return id;
                    }else{
                        Id anno = field.getAnnotation(Id.class);
                        String strategy = anno.strategy();
                        if (Id.STRATEGY_INCR.equals(strategy)){
                            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

                            TermsAggregationBuilder termsAggregation = AggregationBuilders
                                    .terms("id_hits")
                                    .field(fieldName + ".keyword")
                                    .size(1);
                            searchSourceBuilder.aggregation(termsAggregation);

                            FieldSortBuilder fieldSortBuilder = new FieldSortBuilder(fieldName + ".keyword").order(SortOrder.DESC);
                            searchSourceBuilder.sort(fieldSortBuilder);

                            SearchRequest searchRequest = new SearchRequest(index);
                            searchRequest.source(searchSourceBuilder);

                            SearchResponse searchResponse = highClient.search(searchRequest,RequestOptions.DEFAULT);
                            SearchHit searchHit = searchResponse.getHits().getAt(0);
                            if (null == searchHit) {
                                id = "000000000001";
                            } else {
                                JSONObject json = JSONObject.parseObject(searchHit.getSourceAsString());
                                id = String.valueOf(json.getLong(fieldName) + 1);
                            }
                        }
                    }
                    break;
                }
            }

            if (null == id){
                throw new IOException("未检测到 \"@Id\" 注解，原始数据：" + JSONObject.toJSONString(t));
            }
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e){
            e.printStackTrace();
            throw new IOException("数据解析出错：" + JSONObject.toJSONString(t),e);
        }
        return id;
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
