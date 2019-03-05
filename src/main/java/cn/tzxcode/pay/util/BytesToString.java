package cn.tzxcode.pay.util;

/**
 * 二进制数组转换成进制字符串
 */
public class BytesToString {

    private final static char[] HEX_DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 转换字节数组为16进制字串
     *
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            //若使用本函数转换则可得到加密结果的16进制表示，即数字字母混合的形式
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 转换字节为10进制字符串
     *
     * @param
     * @return 10进制字符串
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return "" + HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }

}
