package com.miex.domain;

import lombok.Data;

@Data
public
class Product {
    String prod_id;//产品id
    String prod_name;//产品完整名称
    String prod_alias ;// [别名列表]
    String owner_id;//所有者id
    String create_time;//产品实际创建时间
    String insert_time;//录入系统时间
    String update_time;//最后更新时间
    String prod_tags ;// [标签列表]
    String broad_category;//所属大类
    String sub_categories ;// [String 细类列表]
    String evaluate ;// [评价标签列表]
}
