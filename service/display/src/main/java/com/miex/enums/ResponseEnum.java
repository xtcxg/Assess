package com.miex.enums;

public enum ResponseEnum {
    SUCCESS(0,"success");

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
