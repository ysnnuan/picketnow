package com.nuan.autoconfigure;

import com.nuan.autoconfigure.function.RelectExetor;
import com.nuan.autoconfigure.function.SpringBeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: picketnow
 * @description:
 * @author: yeshengnuan
 * @create: 2021-02-20 10:49
 **/
@Configuration
public class PicketNowAutoConfiguration
{
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(Hello.class)
    public Hello hello()
    {
        return new Hello();
    }

    @Bean
    @ConditionalOnMissingBean
    public Controller controller()
    {
        return new Controller();
    }

    @Bean()
    @ConditionalOnMissingBean
    public SpringBeanUtils springBeanUtils()
    {
        return new SpringBeanUtils();
    }

    @Bean
    @ConditionalOnMissingBean
    public RelectExetor relectExetor()
    {
        return new RelectExetor();
    }
}
