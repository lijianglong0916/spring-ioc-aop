package org.spring.ioc.dependency.setter;

import org.spring.domain.entity.Person;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * setter xml依赖注入
 *
 * @author jianglong.li
 * @date 2021-06-25 21:49
 **/
public class SetterXmlDependencyImpl {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader=new XmlBeanDefinitionReader(beanFactory);
        String sourcePath="classpath:/META-INF/dependency-setter-context.xml";
        //加载解析xml生成对应的beanDefinition
        xmlBeanDefinitionReader.loadBeanDefinitions(sourcePath);
        //依赖查找并创建Bean
        Person person = beanFactory.getBean(Person.class);
        System.out.println(person);
    }
}
