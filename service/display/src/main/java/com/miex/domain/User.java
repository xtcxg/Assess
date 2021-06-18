package com.miex.domain;

import com.miex.util.anno.Id;
import com.miex.util.anno.Index;
import lombok.Data;

import java.util.List;

/**
 * 索引 user 的 po
 */
@Data
@Index("user")
public class User {
    @Id(strategy = Id.STRATEGY_RANDOM)
    String userId;

    String nikeName;
    String phoneNum;
    String password;
    Integer prodNum;
    List<String> prodTags;
    String introduce;
}
