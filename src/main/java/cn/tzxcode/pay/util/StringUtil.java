package cn.tzxcode.pay.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *字符串操作工具类
 *@auth tuzongxun
 *@email 1160569243@qq.com
 *@blog https://blog.tzxcode.cn、https://blog.csdn.net/tuzongxun
 */
public class StringUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 字符串非空判断
     * @param value
     * @return
     */
    public static boolean isNotBlank(String value) {
        return value != null && !"".equals(value);
    }



}
