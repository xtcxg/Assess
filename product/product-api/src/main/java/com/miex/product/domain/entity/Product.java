package com.miex.product.domain.entity;

import com.miex.anno.Id;
import com.miex.anno.Index;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 索引 product 的 po
 */
@Data
@Index("product")
public class Product implements Serializable {

    @Id(strategy = Id.STRATEGY_INCR)
    String prodId;//产品id
    String prodName;//产品完整名称
    /**
     * 所属品牌
     */
    String underBrand;
    /**
     *所属系列
     */
    String underSeries;
    List<String> prodAlias ;// [别名列表]
    String ownerId;//所有者id
    String ownerName;
    Date createTime;//产品实际创建时间
    Date insertTime;//录入系统时间
    Date updateTime;//最后更新时间
    List<String> prodTags ;// [标签列表]
    String broadCategory;   // 大类
    String subCategory ;    // 细类
    String lastCategory;    // 末类
    List<String> evaluate ; // [评价标签列表]
    Map<String,Map<String,Object>> prodParams;
}
