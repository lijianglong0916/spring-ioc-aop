package org.spring.ioc.self.finish.inject;

import org.spring.domain.entity.Person;
import org.spring.domain.entity.Teacher;
import org.spring.ioc.self.finish.inject.annotation.AutowiredAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 自定义依赖注入扩展
 *
 * @author jianglong.li
 * @date 2021-06-27 16:17
 **/

public class SelfAutowiredAnnotationBeanPostProcessor {

    @AutowiredAnnotation
    private Person person;

    @Autowired
    private Teacher teacher;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
        context.register(SelfAutowiredAnnotationBeanPostProcessor.class);
        XmlBeanDefinitionReader xmlBeanDefinitionReader=new XmlBeanDefinitionReader(context);
        String sourcePath="classpath:/META-INF/dependency-setter-context.xml";
        //加载解析xml生成对应的beanDefinition
        xmlBeanDefinitionReader.loadBeanDefinitions(sourcePath);
        context.refresh();
        SelfAutowiredAnnotationBeanPostProcessor bean = context.getBean(SelfAutowiredAnnotationBeanPostProcessor.class);
        System.out.println("person----------"+bean.person);
        System.out.println("teacher----------"+bean.teacher);

        context.close();
    }

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE-11)
    public AutowiredAnnotationBeanPostProcessor getAutowiredAnnotationBeanPostProcessor(){
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        Set<Class<?extends Annotation>> annotations=new LinkedHashSet<>();
        annotations.add(Autowired.class);
        annotations.add(AutowiredAnnotation.class);
        beanPostProcessor.setAutowiredAnnotationTypes(annotations);
        return beanPostProcessor;
    }
}
