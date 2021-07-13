package org.spring.ioc.metatada.self.yaml;

import org.spring.domain.entity.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PropertySourceFactory;

/**
 * 基于Java注解
 * {@link YamlPropertiesFactoryBean }{@link YamlPropertySourceLoader} Yaml资源加载
 *
 * @author jianglong.li
 * @date 2021-07-10 12:26
 **/

@PropertySource(
        name = "personSource",
        value="classpath:/META-INF/person.yaml",
        factory = YamlPropertySourceFactory.class)
public class AnnotatedYamlPropertySourceDemo {
    /**
     * Yaml资源解析
     * 1.添加依赖
     * <dependency>
     * <groupId>org.yaml</groupId>
     * <artifactId>snakeyaml</artifactId>
     * <version>1.27</version>
     * </dependency>
     * 2.定义属性配置文件 person.yaml
     * 3.实现资源工厂{@link YamlPropertySourceFactory} implement {@link PropertySourceFactory}
     * 4.在加载类加入{@link PropertySource}引入资源配置文件和{@link YamlPropertySourceFactory}资源处理
     */


    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotatedYamlPropertySourceDemo.class);
        context.refresh();


        Person person = context.getBean(Person.class);
        System.out.println(person);

        context.close();
    }

    @Bean
    public Person getPerson(@Value ("${person.id}")Long id,@Value ("${person.name}")String name){
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        return person;
    }

}
