package com.miex.domain.dto;

import com.miex.enums.ResponseEnum;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class UnifiedResponse<T> {
    Integer code = ResponseEnum.SUCCESS.getCode();
    String msg = ResponseEnum.SUCCESS.getMsg();
    String error = "";
    String timestamp = new SimpleDateFormat("yyyy-MM-dd ss:hh:mm").format(new Date());
    T data = (T)"";

    /**
     * 无返回值的成功情况
     */
    public UnifiedResponse(){ }

    /**
     * 有返回值成功的情况
     * @param t 返回值
     */
    public UnifiedResponse(T t){
        data = t;
    }

    /**
     * 有失败类型，无具体失败原因
     * @param r 失败类型
     */
    public UnifiedResponse(ResponseEnum r){
        this.code = r.getCode();
        this.msg = r.getMsg();
    }

    /**
     * 有具体失败原因的情况
     * @param r 失败类型
     * @param error 具体原因
     */
    public UnifiedResponse(ResponseEnum r,String error){
        this.code = r.getCode();
        this.msg = r.getMsg();
        this.error = error;
    }

    /**
     * 当 T 为 String 时，只会使用 UnifiedResponse(ResponseEnum r,String msg)，
     * 因此使用 code 进行区分
     * @param code
     * @param t
     */
//    public UnifiedResponse(Integer code,T t){
//        for(ResponseEnum responseEnum : ResponseEnum.values()){
//            if (code.equals(responseEnum.getCode())){
//                this.code = code;
//                this.msg = responseEnum.getMsg();
//            }
//        }
//        this.data = t;
//    }

//    public UnifiedResponse(ResponseEnum r,String msg,T t){
//        this.code = r.getCode();
//        this.msg = msg;
//        this.data = t;
//    }
}
