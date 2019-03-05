package cn.tzxcode.pay.util;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    public static String mapToXml(Map<String,String> map){
        synchronized (MapUtil.class)
        {
            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("<xml>");
            Set<String> objSet = map.keySet();
            for (Object key : objSet)
            {
                if (key == null)
                {
                    continue;
                }
                strBuilder.append("\n");
                strBuilder.append("<").append(key.toString()).append(">");
                Object value = map.get(key);
                strBuilder.append(coverter(value));
                strBuilder.append("</").append(key.toString()).append(">");
            }
            strBuilder.append("\n</xml>");
            return strBuilder.toString();
        }
    }

    public static String coverter(Object object)
    {
        if (object instanceof Object[])
        {
            return coverter((Object[]) object);
        }
        if (object instanceof Collection)
        {
            return coverter((Collection<?>) object);
        }
        StringBuilder strBuilder = new StringBuilder();
        if (isObject(object))
        {
            Class<? extends Object> clz = object.getClass();
            Field[] fields = clz.getDeclaredFields();

            for (Field field : fields)
            {
                field.setAccessible(true);
                if (field == null)
                {
                    continue;
                }
                String fieldName = field.getName();
                Object value = null;
                try
                {
                    value = field.get(object);
                }
                catch (IllegalArgumentException e)
                {
                    continue;
                }
                catch (IllegalAccessException e)
                {
                    continue;
                }
                strBuilder.append("<").append(fieldName)
                        .append(" className=\"").append(
                        value.getClass().getName()).append("\">\n");
                if (isObject(value))
                {
                    strBuilder.append(coverter(value));
                }
                else if (value == null)
                {
                    strBuilder.append("null\n");
                }
                else
                {
                    strBuilder.append(value.toString());
                }
                strBuilder.append("</").append(fieldName).append(">\n");
            }
        }
        else if (object == null)
        {
            strBuilder.append("null\n");
        }
        else
        {
            strBuilder.append(object.toString() );
        }
        return strBuilder.toString();
    }

    private static boolean isObject(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (obj instanceof String)
        {
            return false;
        }
        if (obj instanceof Integer)
        {
            return false;
        }
        if (obj instanceof Double)
        {
            return false;
        }
        if (obj instanceof Float)
        {
            return false;
        }
        if (obj instanceof Byte)
        {
            return false;
        }
        if (obj instanceof Long)
        {
            return false;
        }
        if (obj instanceof Character)
        {
            return false;
        }
        if (obj instanceof Short)
        {
            return false;
        }
        if (obj instanceof Boolean)
        {
            return false;
        }
        return true;
    }

}
