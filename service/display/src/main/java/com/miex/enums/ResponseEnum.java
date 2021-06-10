package com.miex.enums;

public enum ResponseEnum {
    SUCCESS(0,"success"),
    ES_OPERATION_FAIL(1001,"elasticsearch operation failed"),
    PARAMS_VALID_FAIL(2001,"Parameter verification failed");

    Integer code;
    String msg;
    ResponseEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode(){
        return this.code;
    }
    public String getMsg(){
        return this.msg;
    }
}
