package org.spring.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.BeanNameAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author jianglong.li
 * @date 2021-06-29 07:50
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeanScopeEntity implements BeanNameAware {
    Long time;

    String beanName;

    public BeanScopeEntity(Long time) {
        this.time = time;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName=name;
    }

    @PostConstruct
    public void init() throws InterruptedException {
        Thread.sleep(1_000);
        System.out.println("--------------- "+beanName+"  init---------------");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("--------------- "+beanName+"  destroy---------------");
    }
}
