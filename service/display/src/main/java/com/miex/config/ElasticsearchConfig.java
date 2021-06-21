package com.miex.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {

    @Value("${es.host}")
    String host;

    @Bean
    public RestHighLevelClient highClient(){
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(host, 9200, "http"),
                        new HttpHost(host, 9201, "http")));
    }
}
