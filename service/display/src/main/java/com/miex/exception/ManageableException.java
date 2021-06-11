package com.miex.exception;

import com.miex.enums.ResponseEnum;
import static com.miex.enums.ResponseEnum.*;

/**
 * 使用该异常，可以使请求返回 UnifiedResponse 类型的异常信息
 */
public class ManageableException extends Exception{

    ResponseEnum type;
    String info = "";

    /**
     * 默认为系统内部异常
     */
    public ManageableException(){
        this.type = SYSTEM_ERROR;
    }

    /**
     * 指定错误类型
     * @param re 错误类型
     */
    public ManageableException(ResponseEnum re){
        this.type = re;
    }

    /**
     * 指定错误类型，并描述具体原因
     * @param re 错误类型
     * @param info 具体原因
     */
    public ManageableException(ResponseEnum re,String info){
        this.type = re;
        this.info = info;
    }

    public ResponseEnum getType() {
        return type;
    }

    public String getInfo() {
        return info;
    }
}
