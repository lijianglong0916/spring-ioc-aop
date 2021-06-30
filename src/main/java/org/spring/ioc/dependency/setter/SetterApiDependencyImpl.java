package org.spring.ioc.dependency.setter;

import org.spring.domain.entity.Party;
import org.spring.domain.entity.Person;
import org.spring.domain.entity.Teacher;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 *
 * API setter注入
 *
 * @author jianglong.li
 * @date 2021-06-27 09:16
 **/
public class SetterApiDependencyImpl {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
        context.register(SetterApiDependencyImpl.class);

        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(Party.class);
        definitionBuilder.addPropertyReference("person", "person");
        definitionBuilder.addPropertyReference("teacher", "teacher");
        AbstractBeanDefinition beanDefinition = definitionBuilder.getBeanDefinition();
        context.registerBeanDefinition("party",beanDefinition);
        context.refresh();
        Party party = context.getBean(Party.class);
        System.out.println(party);
        context.close();



    }

//    @Bean
//    public Party getParty(Person person, Teacher teacher) {
//        return new Party().setPerson(person).setTeacher(teacher);
//    }

    @Bean("person")
    public Person getPerson() {
        return new Person().setAge(12).setName("李四").setSex("man");
    }

    @Bean("teacher")
    public Teacher getTeacher(){
        Teacher teacher = new Teacher();
        teacher.setNumber(123L);
        teacher.setAge(13);
        teacher.setName("teacher MA");
        teacher.setSex("woman");
        return teacher;
    }
}
