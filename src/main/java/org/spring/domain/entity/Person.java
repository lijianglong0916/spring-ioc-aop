package org.spring.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.BeanNameAware;

import javax.annotation.PreDestroy;

/**
 * @author jianglong.li
 * @date 2021-06-12 14:25
 **/

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Accessors(chain = true)
public class Person implements BeanNameAware {

    private Long id;

    private Integer age;

    private String name;

    private String sex;

    private String beanName;

    @PreDestroy
    public void destroy(){
        System.out.println("------"+this.beanName+" destroy------");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName=name;
    }
}
