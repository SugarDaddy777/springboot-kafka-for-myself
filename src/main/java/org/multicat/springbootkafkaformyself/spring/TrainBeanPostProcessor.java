package org.multicat.springbootkafkaformyself.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author wenzhi.huang huangwenzhi@leelen.cn
 * @since 2021/10/9 10:32
 */
@Slf4j
@Component
public class TrainBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("Before --- beanName is {} --- ", beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("After --- beanName is {} --- ", beanName);
        return bean;
    }
}
