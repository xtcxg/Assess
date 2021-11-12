package com.miex.domain.dto.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liutz
 * @date 2021/11/5
 */
@Data
public class Request<T> implements Serializable {
    /**
     * 幂等性接口需要序列号
     */
    String serial;
    /**
     * 页数，从0开始
     */
    Integer page = 0;
    /**
     * 每页数据，默认20
     */
    Integer size = 20;


    T data;
}
