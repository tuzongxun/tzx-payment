package cn.tzxcode.pay.util;

import java.util.Random;

/**
 * 序列号、订单号、随机数工具类
 *
 * @auth tuzongxun
 * @email 1160569243@qq.com
 * @blog https://blog.tzxcode.cn、https://blog.csdn.net/tuzongxun
 */
public class SerialNumUtil {

    private static String[] alphaUpperCase = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * 生成指定长度的随机序列
     *
     * @param length
     * @return
     */
    public static String generateRandomCode(int length) {
        StringBuffer code = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            if (random.nextInt(2) == 0) {// 0取字母
                int index = random.nextInt(26);
                code.append(alphaUpperCase[index]);
            } else {// 1取数字
                code.append(random.nextInt(length));
            }
        }
        return code.toString();
    }

    public static String getOutTradeNo() {
        return System.currentTimeMillis() + "tzxqq";
    }
}
