package com.miex.domain.dto;

import com.alibaba.fastjson.JSONObject;
import com.miex.enums.ResponseEnum;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class UnifiedResponse<T> {
    Integer code = ResponseEnum.SUCCESS.getCode();
    String msg = ResponseEnum.SUCCESS.getMsg();
    String timestamp = new SimpleDateFormat("yyyy-MM-dd ss:hh:mm").format(new Date());
    T data;
    public UnifiedResponse(){
        data = (T)"";
    }

    public UnifiedResponse(T t){
        data = t;
    }

    public UnifiedResponse(ResponseEnum r){
        this.code = r.getCode();
        this.msg = r.getMsg();
    }

    public UnifiedResponse(ResponseEnum r,String msg){
        this.code = r.getCode();
        this.msg = msg;
    }
}
