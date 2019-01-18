package cn.tzxcode.pay.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 *map转换工具类
 *@auth tuzongxun
 *@email 1160569243@qq.com
 *@blog https://blog.tzxcode.cn、https://blog.csdn.net/tuzongxun
 */
public class MapUtil {

    /**
     * java对象转map
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, String> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        System.out.println(clazz);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if(field.get(obj)!=null) {
                String value = field.get(obj).toString();
                map.put(fieldName, value);
            }
        }
        return map;
    }

}
