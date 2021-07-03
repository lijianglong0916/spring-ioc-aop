package org.spring.ioc.entity;

import org.spring.domain.entity.Party;
import org.spring.domain.entity.Person;
import org.spring.domain.entity.Teacher;
import org.springframework.context.annotation.Bean;

/**
 * @author jianglong.li
 * @date 2021-07-01 21:40
 **/

public class EntityBean {

    @Bean("person")
    public Person getPerson(){
        return new Person().setAge(12).setName("张三").setSex("man");
    }

    @Bean("teacher")
    public Teacher getTeacher() {
        Teacher teacher = new Teacher();
        teacher.setNumber(11L);
        teacher.setName("teacher 马");
        teacher.setSex("man");
        return teacher;
    }
    @Bean("party")
    public Party getParty(){
        return new Party();
    }
}
