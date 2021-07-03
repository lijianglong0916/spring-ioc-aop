package org.spring.ioc.bean.definnition;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

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
        //register可以将非标注为bean的实体注册到ioc容器,不需要refresh()
        beanDefinitionReader.register(AnnotatedBeanDefinitionParsingDemo.class);
        /**
         * {@link BeanDefinition}通过{@link BeanNameGenerator}生成bean name
         * {@link AnnotatedBeanDefinition}通过{@link AnnotationBeanNameGenerator}生成bean name
         */
        AnnotatedBeanDefinitionParsingDemo bean = beanFactory.getBean(AnnotatedBeanDefinitionParsingDemo.class);
        System.out.println(bean);
    }
}
