package cn.tzxcode.pay.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间操作工具类
 *
 * @auth tuzongxun
 * @email 1160569243@qq.com
 */
public class DateUtil {

    public static String dateFormat(String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(new Date());
    }
}
