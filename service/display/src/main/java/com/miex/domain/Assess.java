package com.miex.domain;

import com.miex.util.anno.Id;
import com.miex.util.anno.Index;
import lombok.Data;

import java.util.List;

@Data
@Index("assess")
public class Assess {

    @Id(strategy = Id.STRATEGY_INCR)
    String assId;	//测评id
    String assTitle;	//标题
    String ownerId;	//所有者id
    String ownerName;	//所有者名称
    String assType;     //类型video/text
    String originSource; //来源
    String address; //地址
    Integer assent; //赞成数
    Integer dissent; //反对数
    List<String> prodIdList;	//测评产品id列表
    List<String> prodNameList;	//测评产品名称列表
    String broadCategory;	//产品大类
    String subCategory;	//产品子类
    String lastCategory;	//产品末类
    List<String> assTags;	//标签列表
    String assIntroduce;	//介绍
    String createTime;	//创建时间
    String inputTime;	//录入系统时间
    String inputUserId;	//录入者id
    String updateTime;	//最后更新时间
    String updateUserId;	//最后更新者id
}
