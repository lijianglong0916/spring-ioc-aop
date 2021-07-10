package org.spring.ioc.metatada.self.xml.authoring;

import org.spring.domain.entity.Person;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 *
 * spring 元素扩展实例
 *
 * @author jianglong.li
 * @date 2021-07-08 22:04
 **/
public class ExtensibleXmlAuthoringDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:/META-INF/person-context.xml");
        Person bean = beanFactory.getBean(Person.class);
        System.out.println(bean);
    }
}
