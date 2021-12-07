package com.miex.domain.dao;

import com.alibaba.fastjson.JSONObject;
import com.miex.anno.Id;
import com.miex.anno.Index;
import com.miex.domain.dto.req.Request;
import com.miex.exception.ESException;
import com.miex.util.StringUtil;
import org.elasticsearch.ElasticsearchStatusException;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.*;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
    public RestHighLevelClient highClient;

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
    public String insert(T t) throws ESException {
        IndexRequest indexRequest = new IndexRequest(index);
        try{
            indexRequest.id(getId(t));
            indexRequest.source(JSONObject.toJSONString(t), XContentType.JSON);
            return highClient.index(indexRequest, RequestOptions.DEFAULT).getId();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ESException("插入数据出错：" + JSONObject.toJSONString(t),e);
        }
    }

    /**
     * 通过@Id注解，获取id，如果对象有id，则返回已有的id，如果没有id，则根据注解策略生成id
     * @param t 获取id的对象
     * @return 对象的id
     * @throws ESException
     */
    String getId(T t) throws ESException{
        String id = null;
        try {
            Field[] fields = t.getClass().getDeclaredFields();
            for (Field field:fields) {
                if (null != field.getAnnotation(Id.class)){
                    String fieldName = field.getName();
                    String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    id = (String) t.getClass().getMethod(methodName).invoke(t,new Object[]{});
                    methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                    if (StringUtil.isNotEmpty(id)) {
                        return id;
                    }else{
                        Id anno = field.getAnnotation(Id.class);
                        String strategy = anno.strategy();
                        if (Id.STRATEGY_INCR.equals(strategy)){
                            CountRequest countRequest = new CountRequest(index);
                            CountResponse countResponse = highClient.count(countRequest,RequestOptions.DEFAULT);
                            if (countResponse.getCount() < 1) {
                                id = "000000000001";
                            } else {
                                SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
                                FieldSortBuilder fieldSortBuilder = new FieldSortBuilder(fieldName + ".keyword").order(SortOrder.DESC);
                                searchSourceBuilder.sort(fieldSortBuilder);
                                searchSourceBuilder.size(1);
                                SearchRequest searchRequest = new SearchRequest(index);
                                searchRequest.source(searchSourceBuilder);
                                SearchResponse searchResponse = highClient.search(searchRequest,RequestOptions.DEFAULT);
                                SearchHit searchHit = searchResponse.getHits().getAt(0);
                                JSONObject json = JSONObject.parseObject(searchHit.getSourceAsString());
                                id = String.valueOf(json.getLong(fieldName) + 1);
                                id = "000000000000".substring(0,12 - id.length()) + id;
                            }
                            t.getClass().getMethod(methodName,String.class).invoke(t, id);
                            return id;
                        }
                        if (Id.STRATEGY_RANDOM.equals(strategy)){
                            id = UUID.randomUUID().toString().replace("-","");
                            t.getClass().getMethod(methodName,String.class).invoke(t, id);
                            return id;
                        }
                        if (Id.STRATEGY_SNOWFLAKE.equals(strategy)){
                            // todo 实现snowflake
                            id = UUID.randomUUID().toString().replace("-","");
                            t.getClass().getMethod(methodName,String.class).invoke(t, id);
                            return id;
                        }
                    }
                    break;
                }
            }

            if (null == id){
                throw new ESException("未检测到 \"@Id\" 注解，原始数据：" + JSONObject.toJSONString(t));
            }
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | IOException e){
            e.printStackTrace();
            throw new ESException("数据解析出错：" + JSONObject.toJSONString(t),e);
        }
        return id;
    }

    public String deleteById(String id) throws ESException {
        DeleteRequest deleteRequest = new DeleteRequest(index,id);
        try {
            DeleteResponse deleteResponse = highClient.delete(deleteRequest,RequestOptions.DEFAULT);
            if ("deleted".equals(deleteResponse.getResult().getLowercase())){
                return "success";
            } else {
                return deleteResponse.getResult().getLowercase();
            }
        } catch (IOException e) {
            throw new ESException(String.format("数据删除失败，index：%s，id：%s。%s",index,id ,e.getCause()),e);
        }

    }

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
            throw new ESException("id查询出错：" + id,e);
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

    public String updateById(T t) throws ESException {
        String id = getId(t);
        UpdateRequest request = new UpdateRequest(index,id);
        request.doc(JSONObject.toJSONString(t),XContentType.JSON);
        try {
            UpdateResponse response = highClient.update(request,RequestOptions.DEFAULT);
            if ("updated".equals(response.getResult().getLowercase())){
                return "success";
            } else {
                return response.getResult().getLowercase();
            }
        } catch (ElasticsearchStatusException | IOException e) {
            throw new ESException(String.format("更新出错：index：%s,data: %s" ,index,JSONObject.toJSONString(t)));
        }
    }

    public List<T> selectUseOr(Request<T> param) throws ESException {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        JSONObject jo = JSONObject.parseObject(JSONObject.toJSONString(param.getData()));
        jo.forEach((k,v)->{
            if(StringUtil.isNotEmpty(String.valueOf(v))){
                boolQueryBuilder.should(QueryBuilders.termQuery(k,v));
            }
        });
        try {
            return queryTemplate(boolQueryBuilder,param.getPage(), param.getSize());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ESException("OR 列表查询出错,data:" + JSONObject.toJSONString(param),e);
        }
    }

    public List<T> selectUseAnd(Request<T> param) throws ESException {

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        JSONObject jo = JSONObject.parseObject(JSONObject.toJSONString(param.getData()));
        jo.forEach((k,v)->{
            if(StringUtil.isNotEmpty(String.valueOf(v))){
                boolQueryBuilder.must(QueryBuilders.termQuery(k,v));
            }
        });
        try {
            return queryTemplate(boolQueryBuilder,param.getPage(), param.getSize());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ESException("AND 列表查询出错,data:" + JSONObject.toJSONString(param),e);
        }
    }

    public List<T> queryTemplate(QueryBuilder queryBuilder, Integer page, Integer size) throws IOException {
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().query(queryBuilder);
        searchSourceBuilder.size(size);
        searchSourceBuilder.from(page * size);
        request.source(searchSourceBuilder);
        SearchResponse response = highClient.search(request,RequestOptions.DEFAULT);
        List<T> list = new ArrayList<>();
        response.getHits().forEach((hit)-> list.add(JSONObject.parseObject(hit.getSourceAsString(),entityClass)));
        return list;
    }
}
