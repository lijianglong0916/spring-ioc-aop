package org.spring.domain.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.spring.domain.entity.Person;
import org.spring.domain.entity.Teacher;
import org.spring.domain.repository.PersonRepository;
import org.spring.infra.util.Utils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.beans.beancontext.BeanContext;
import java.beans.beancontext.BeanContextServicesSupport;

/**
 * @author jianglong.li
 * @date 2021-06-12 15:15
 **/
@Repository
@Slf4j
public class PersonRepositoryImpl implements PersonRepository {
    @Override
    public String getPerson() {
        //根据BeanDefinitionBuilder获取beanDefinition
        BeanDefinitionBuilder personDefinition = BeanDefinitionBuilder.genericBeanDefinition(Person.class);
        log.debug(personDefinition.toString());
        AbstractBeanDefinition beanDefinition = personDefinition.getBeanDefinition();
        beanDefinition.setLazyInit(true);
        log.debug(beanDefinition.toString());
        BeanDefinition definition=new GenericBeanDefinition();
        definition.setBeanClassName(Teacher.class.getName());
        log.debug(definition.toString());
        //手动注册bean
        AnnotationConfigWebApplicationContext context=new AnnotationConfigWebApplicationContext();
        Teacher teacher=new Teacher();
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        beanFactory.registerSingleton("teacher", teacher);

        BeanContext beanContext=new BeanContextServicesSupport();

        return Utils.parseObject2Json(personDefinition);
    }
}
