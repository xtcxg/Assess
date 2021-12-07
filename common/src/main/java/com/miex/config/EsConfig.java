package com.miex.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liutz
 * @date 2021/11/25
 */
@Configuration
public class EsConfig {
	@Value("${es.host}")
	String host;
	@Value("${es.port}")
	Integer port;

	@Bean
	public RestHighLevelClient highClient(){
		return new RestHighLevelClient(
				RestClient.builder(
						new HttpHost(host, 9200, "http")
//						new HttpHost(host, port, "https")
						));
	}
}
