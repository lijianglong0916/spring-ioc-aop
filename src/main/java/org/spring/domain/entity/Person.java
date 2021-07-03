package org.spring.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanNameAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author jianglong.li
 * @date 2021-06-12 14:25
 **/

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Accessors(chain = true)
@Slf4j
@RequiredArgsConstructor
public class Person implements BeanNameAware {

    private Long id;

    private Integer age;

    private String name;

    private String sex;

    private String beanName;

    @PostConstruct
    public void init(){
        log.info("-------@PostConstruct init---------");
        this.name="@PostConstruct init";
    }


    @PreDestroy
    public void destroy(){
        log.info("------"+this.beanName+" destroy------");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName=name;
    }
}
