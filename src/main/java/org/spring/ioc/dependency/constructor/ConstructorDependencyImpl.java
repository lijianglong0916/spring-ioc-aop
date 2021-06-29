package org.spring.ioc.dependency.constructor;

import org.spring.domain.entity.Party;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 *
 * 构造器注入
 *
 * @author jianglong.li
 * @date 2021-06-27 10:05
 **/
public class ConstructorDependencyImpl {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader=new XmlBeanDefinitionReader(beanFactory);
        String sourcePath="classpath:/META-INF/dependency-constructor-context.xml";
        xmlBeanDefinitionReader.loadBeanDefinitions(sourcePath);
        Party party = beanFactory.getBean(Party.class);
        System.out.println(party);
    }
}
