package com.miex.domain.dto.res;

import com.miex.enums.RequestEnum;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Response<T> {
    Integer code = RequestEnum.SUCCESS.getCode();
    String msg = RequestEnum.SUCCESS.getMsg();
    Integer count;
    Integer page;
    Integer size;
    String error = "";
    String timestamp = new SimpleDateFormat("yyyy-MM-dd ss:hh:mm").format(new Date());
    T data = (T)"";

    /**
     * 无返回值的成功情况
     */
    public Response(){ }

    /**
     * 有返回值成功的情况
     * @param t 返回值
     */
    public Response(T t){
        data = t;
    }

    /**
     * 有失败类型，无具体失败原因
     * @param r 失败类型
     */
    public Response(RequestEnum r){
        this.code = r.getCode();
        this.msg = r.getMsg();
    }

    /**
     * 有具体失败原因的情况
     * @param r 失败类型
     * @param error 具体原因
     */
    public Response(RequestEnum r, String error){
        this.code = r.getCode();
        this.msg = r.getMsg();
        this.error = error;
    }

    /**
     * 默认的失败情况，code = 5001
     * @param msg 错误信息
     * @return
     */
    public Response<String> fail(String msg) {
        Response<String> response = new Response<>();
        response.setCode(5001);
        response.setMsg(msg);
        return response;
    }

    /**
     * 当 T 为 String 时，只会使用 Response(ResponseEnum r,String msg)，
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
