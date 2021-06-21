package com.miex.util;

public class StringUtil {
    /**
     * 如果为空返回true，不为空返回false
     * @param str
     * @return
     */
    public static Boolean isEmpty(String str){
        if (null == str || "".equals(str.trim())){
            return true;
        } else {
            return false;
        }
    }
}
