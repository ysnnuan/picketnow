package com.nuan.autoconfigure.function;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @program: learnspringboot
 * @description:
 * @author: yeshengnuan
 * @create: 2021-02-20 10:08
 **/

public class SpringBeanUtils implements ApplicationContextAware
{

    private static ApplicationContext localApplicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        SpringBeanUtils.localApplicationContext = applicationContext;
    }

    /**
     * 从静态变量applicationContext中得到Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        if(name == null || localApplicationContext == null)
        {
            return null;
        }
        T t=null;
        try
        {
            t= (T) localApplicationContext.getBean(name);
        }
        catch (Exception e)
        {
            return null;
        }
        return t;
    }

    /**
     * 从静态变量applicationContext中得到Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> clazz) {

        if(clazz == null || localApplicationContext == null)
        {
            return null;
        }
        T t=null;
        try
        {
            t=localApplicationContext.getBean(clazz);
        }
        catch (Exception e)
        {
            return null;
        }

        return t;
    }
}
