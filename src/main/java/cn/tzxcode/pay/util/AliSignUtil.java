package cn.tzxcode.pay.util;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *支付宝签名加密工具类
 *@auth tuzongxun
 *@email 1160569243@qq.com
 *@blog https://blog.tzxcode.cn、https://blog.csdn.net/tuzongxun
 */
public class AliSignUtil {

    /**
     * 签名算法 RSA2
     */
    public static final String SIGN_ALGORITHMS_RSA2 = "SHA256WithRSA";

    private final static char[] hexDigits = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 将参数键值Map按参数名进行排序，并按key1=value1&key2=value2&key3=value3的格式进行返回
     * 过滤VALUE为空的参数键值对
     *
     * @param paramsMap
     * @return
     */
    public static String orderParamsMapAndReturnParamsString(Map<String, String> paramsMap) {
        List<String> keys = new ArrayList<String>(paramsMap.keySet());
        Collections.sort(keys);
        StringBuffer paramStringBuffer = new StringBuffer();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = paramsMap.get(key);

            // 过滤value为空的参数键值对
            if (StringUtil.isNotBlank(value)) {
                paramStringBuffer.append(key).append("=").append(value).append("&");
            }
        }
        // 去掉请求字符串末尾的最后一个&号
        if (paramStringBuffer.length() - 1 == paramStringBuffer.lastIndexOf("&")) {
            paramStringBuffer.deleteCharAt(paramStringBuffer.lastIndexOf("&"));
        }
        return paramStringBuffer.toString();
    }

    public static String signRsa2(String content, String privateKey, String input_charset) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64Util.decode(privateKey));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS_RSA2);
            signature.initSign(priKey);
            signature.update(content.getBytes(input_charset));
            byte[] signed = signature.sign();
            return Base64Util.encode(signed);
        } catch (Exception e) {
            System.out.println("签名异常");
            e.printStackTrace();
        }
        return null;
    }

}
