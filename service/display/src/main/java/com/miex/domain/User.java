package com.miex.domain;

import lombok.Data;

import java.util.List;

/**
 * 索引 user 的 po
 */
@Data
public class User {
    String nikeName;
    String userId;
    String phoneNum;
    String password;
    Integer prodNum;
    List<String> prodTags;
    String introduce;
}
