package com.miex.user.domain.entity;

import com.miex.anno.Id;
import com.miex.anno.Index;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author liutz
 * @date 2021/11/9
 */
@Data
@Index("user")
public class User implements Serializable {

	@Id(strategy = Id.STRATEGY_INCR)
	String userId;
	String nickName;
	String phoneNum;
	String password;
	Integer prodNum;
	List<String> prodTags;
	String introduce;
	Date createTime;
	Date updateTime;
}
