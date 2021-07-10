package org.spring.ioc.metatada;

import org.spring.domain.entity.Person;
import org.springframework.beans.BeanMetadataAttributeAccessor;
import org.springframework.beans.BeanMetadataElement;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * bean 配置元信息
 *
 * @author jianglong.li
 * @date 2021-07-04 13:51
 **/
public class BeanConfigurationMetadataDemo {
    public static void main(String[] args) {
        /**
         * beanDefinition定义 {@link GenericBeanDefinition}继承{@link BeanDefinition}
         * {@link BeanDefinition} extends {@link AttributeAccessor}{@link BeanMetadataElement},这里继承{@link AttributeAccessor}{@link BeanMetadataElement}
         * 两个接口的属性都是辅助属性，不影响原始bean的初始化，例如{@link BeanMetadataElement}的getSource()就是仅返回branDefinition的来源，
         * 通过{@link BeanMetadataAttributeAccessor}的setSource()进行设置
         *
         * */
        BeanDefinitionBuilder beanDefinitionBuilder=BeanDefinitionBuilder.genericBeanDefinition(Person.class);
        //为BeanDefinition设置属性
        beanDefinitionBuilder.addPropertyValue("name", "王五");
        AbstractBeanDefinition abstractBeanDefinition = beanDefinitionBuilder.getBeanDefinition();
        //为BeanDefinition设置附属属性，不影响bean的原始信息
        abstractBeanDefinition.setSource(BeanConfigurationMetadataDemo.class);
        abstractBeanDefinition.setAttribute("name", "李四");
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        beanFactory.registerBeanDefinition("person", abstractBeanDefinition);
        Person person = beanFactory.getBean(Person.class);
        System.out.println(person);
    }
}
