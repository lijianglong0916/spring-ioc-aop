package org.spring.ioc.dependency.setter;

import org.spring.domain.entity.Party;
import org.spring.domain.entity.Person;
import org.spring.domain.entity.Teacher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 注解的方式setter注入
 *
 * @author jianglong.li
 * @date 2021-06-25 22:21
 **/
public class SetterAnnotationDependencyImpl {

    public static void main(String[] args) {
        //指定默认文件
        String sourcePath = "classpath:/META-INF";
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(SetterAnnotationDependencyImpl.class);

        applicationContext.refresh();

        Party party = applicationContext.getBean(Party.class);

        System.out.println(party);
        applicationContext.close();
    }

    @Bean
    public Party getParty(Person person,Teacher teacher) {
        System.out.println("----------------SetterAnnotation getParty-------------------");
        return new Party().setPerson(person).setTeacher(teacher);
    }

    @Bean("person")
    public Person getPerson() {
        System.out.println("----------------SetterAnnotation getPerson-------------------");
        return new Person().setAge(12).setName("李四").setSex("man");
    }

    @Bean("teacher")
    public Teacher getTeacher(){
        System.out.println("----------------SetterAnnotation getTeacher-------------------");
        Teacher teacher = new Teacher();
        teacher.setNumber(123L);
        teacher.setAge(13);
        teacher.setName("teacher MA");
        teacher.setSex("woman");
        return teacher;
    }
}
