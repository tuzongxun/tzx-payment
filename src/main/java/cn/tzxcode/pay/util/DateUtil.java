package cn.tzxcode.pay.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *时间操作工具类
 *@auth tuzongxun
 *@email 1160569243@qq.com
 *@blog https://blog.tzxcode.cn、https://blog.csdn.net/tuzongxun
 */
public class DateUtil {

    /**
     * 根据传入的字符串格式获取时间字符串
     * @param formatStr
     * @return
     */
    public static String dateFormat(String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(new Date());
    }
}
