package org.spring.ioc.dependency.setter;

import org.spring.domain.entity.Party;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 自动setter注入 by name
 *
 * @author jianglong.li
 * @date 2021-06-27 09:27
 **/
public class AutoWiringSetterByNameDependencyImpl {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(beanFactory);
        String sourcePath="classpath:/META-INF/dependency-setter-autowiring-context.xml";

        reader.loadBeanDefinitions(sourcePath);
        Party party = beanFactory.getBean(Party.class);
        System.out.println(party);
    }
}
