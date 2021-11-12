package com.miex.enums;

public enum ResponseEnum {
    SUCCESS(0,"success"),
    SYSTEM_ERROR(1001,"system internal exception"),
    ES_OPERATION_FAIL(2001,"elasticsearch operation failed"),
    PARAMS_VALID_FAIL(3001,"parameter verification failed");

    Integer code;
    String msg;
    ResponseEnum(Integer code, String msg){
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
