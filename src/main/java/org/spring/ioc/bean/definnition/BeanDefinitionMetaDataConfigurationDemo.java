package org.spring.ioc.bean.definnition;

import lombok.extern.slf4j.Slf4j;
import org.spring.domain.entity.Person;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

/**
 * bean definition {@link BeanDefinition}配置元信息
 *
 * @author jianglong.li
 * @date 2021-06-30 06:58
 **/
@Slf4j
public class BeanDefinitionMetaDataConfigurationDemo {
    public static void main(String[] args) {
        //实例化BeanDefinitionRegistry，DefaultListableBeanFactory是其的一个实现
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        //实例化PropertiesBeanDefinitionReader
        PropertiesBeanDefinitionReader beanDefinitionReader=new PropertiesBeanDefinitionReader(beanFactory);
        //加载properties资源
        String sourcePath="classpath:/META-INF/person.properties";
        beanDefinitionReader.loadBeanDefinitions(sourcePath);
        Person person = beanFactory.getBean(Person.class);
        log.info("------------"+person+"-------------");
    }
}
