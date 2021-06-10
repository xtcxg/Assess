package com.miex.domain;

import lombok.Data;

import java.util.List;

/**
 * 索引 product 的 po
 */
@Data
public
class Product {
    String prodId;//产品id
    String prodName;//产品完整名称
    List<String> prodAlias ;// [别名列表]
    String ownerId;//所有者id
    String createTime;//产品实际创建时间
    String insertTime;//录入系统时间
    String updateTime;//最后更新时间
    List<String> prodTags ;// [标签列表]
    String broadCategory;//所属大类
    List<String> subCategories ;// [细类列表]
    List<String> evaluate ;// [评价标签列表]
}
