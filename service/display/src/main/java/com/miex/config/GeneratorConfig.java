package com.miex.config;

import com.miex.util.StringUtil;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GeneratorConfig {

    @Autowired
    RestHighLevelClient highLevelClient;
    @Value("${es.index}")
    String[] index;

    /**
     * 初始化elasticsearch
     * 创建索引（product,user,assess,discuss）
     */
    @Bean
    public void elasticsearchGenerator() throws IOException {
        if (null == index || index.length < 1 || !StringUtil.isNotEmpty(index[0])) return;
        GetIndexRequest getIndexRequest = new GetIndexRequest(index[0]);
        if (highLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT)) return;
        for (String i:index) {
            CreateIndexRequest request = new CreateIndexRequest(i);
            request.source("{\n" +
                    "    \"settings\": {\n" +
                    "    \"index\": {\n" +
                    "      \"analysis.analyzer.default.type\": \"ik_max_word\"\n" +
                    "    }\n" +
                    "  }\n" +
                    "}", XContentType.JSON);
            highLevelClient.indices().create(request,RequestOptions.DEFAULT);
        }
    }
}
