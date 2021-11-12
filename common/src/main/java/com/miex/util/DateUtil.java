package com.miex.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static String FORMAT_1 = "yyyy-MM-dd hh:mm:ss";
    public static String FORMAT_2 = "yyyy-MM-dd";

    /**
     * 日期转字符串
     * @param time
     * @return
     */
    public static String toString(LocalDateTime time){
        return time.format(DateTimeFormatter.ofPattern(FORMAT_1));
    }

    /**
     * 日期转字符串，
     * 指定格式
     * @param time 时间
     * @param format 格式
     * @return 格式化字符串
     */
    public static String toString(LocalDateTime time,String format){
        return time.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * 字符串转日期
     * @param str 字符串
     * @return 日期
     */
    public static LocalDateTime toDate(String str){
        return LocalDateTime.parse(str,DateTimeFormatter.ofPattern(FORMAT_1));
    }
}
