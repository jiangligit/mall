package com.rimi.mall.utils;

/**
 * 字符串工具类
 *
 * @author Administrator
 * @date 2019-04-18 14:18
 */
public class StringUtils {


    /**
     * 判断字符串是否为为空
     * @param str 需要判断的字符串
     * @return 结果
     */
    public static boolean isNotBlank(String str){
//        return "".equalsIgnoreCase(str);
        return str!=null && !str.equals("");
    }
}
