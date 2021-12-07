package com.miex.user.domain.dao;

import com.miex.domain.dao.ElasticsearchDAO;
import com.miex.user.domain.entity.User;
import com.miex.util.StringUtil;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author liutz
 * @date 2021/11/18
 */

@Component
public class UserDAO extends ElasticsearchDAO<User> {

}
