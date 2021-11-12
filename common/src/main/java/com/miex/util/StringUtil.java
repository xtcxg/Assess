package com.miex.util;

public class StringUtil {
    /**
     * 如果为空返回false，不为空返回true
     * @param str
     * @return
     */
    public static Boolean isNotEmpty(String str){
        if (null == str || "".equals(str.trim())){
            return false;
        } else {
            return true;
        }
    }
}
