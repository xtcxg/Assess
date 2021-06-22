package com.miex.domain.dto;

import lombok.Data;

/**
 * 分页查询统一请求格式
 * @param <T>
 */
@Data
public class UnifiedRequest<T> {
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
