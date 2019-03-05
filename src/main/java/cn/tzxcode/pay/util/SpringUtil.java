package cn.tzxcode.pay.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *spring工具类
 *@auth tuzongxun
 *@email 1160569243@qq.com
 *@blog https://blog.tzxcode.cn、https://blog.csdn.net/tuzongxun
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext=applicationContext;
    }

    public static Object getBean(String beanName){
        return applicationContext.getBean(beanName);
    }
}
