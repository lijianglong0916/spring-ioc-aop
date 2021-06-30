package org.spring.ioc.bean.definnition;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;

/**
 * bean definition {@link BeanDefinition} 配置元信息基于注解实现demo
 *
 * @author jianglong.li
 * @date 2021-06-30 07:23
 **/
public class AnnotatedBeanDefinitionParsingDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        AnnotatedBeanDefinitionReader beanDefinitionReader=new AnnotatedBeanDefinitionReader(beanFactory);
        beanDefinitionReader.register(AnnotatedBeanDefinitionParsingDemo.class);
    }
}
