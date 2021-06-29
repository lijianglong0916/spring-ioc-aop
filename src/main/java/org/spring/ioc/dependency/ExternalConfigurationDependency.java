package org.spring.ioc.dependency;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 外部化配置依赖注入{@link Value}
 *
 * @author jianglong.li
 * @date 2021-06-28 07:33
 **/

@PropertySource("classpath:/META-INF/external-configuration-dependency.properties")
@Configuration
public class ExternalConfigurationDependency {

    @Value("${id}")
    private Integer id;

    @Value("${name}")
    private String name;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
        context.register(ExternalConfigurationDependency.class);
        context.refresh();
        ExternalConfigurationDependency bean = context.getBean(ExternalConfigurationDependency.class);
        System.out.println(bean.id);
        System.out.println(bean.name);
        context.close();
    }
}
