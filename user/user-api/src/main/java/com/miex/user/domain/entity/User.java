package com.miex.user.domain.entity;

import com.miex.anno.Id;
import lombok.Data;

import java.util.List;

/**
 * @author liutz
 * @date 2021/11/9
 */
@Data
public class User {

	@Id
	Integer userId;
	String nickName;
	String phoneNum;
	String password;
	Integer prodNum;
	List<String> prodTags;
	String introduce;
}
