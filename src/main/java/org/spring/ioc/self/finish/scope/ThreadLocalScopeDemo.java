package org.spring.ioc.self.finish.scope;

import lombok.extern.slf4j.Slf4j;
import org.spring.domain.entity.Person;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * 自定义bean scope {@link ThreadLocalScope }demo
 *
 * @author jianglong.li
 * @date 2021-06-29 22:07
 **/
@Slf4j
public class ThreadLocalScopeDemo {


    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    public Person createPerson() throws InterruptedException {
        Person person = new Person();
        person.setAge(12);
        person.setName("123");
        person.setId(System.nanoTime());
        Thread.sleep(500);
        return person;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ThreadLocalScopeDemo.class);
        String sourcePath = "classpath:/META-INF/dependency-constructor-context.xml";
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(context);
        reader.loadBeanDefinitions(sourcePath);
        context.refresh();

        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        //注册自定义scope
        beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        /*
        -----------------------------------如下方法注册自定义scope不生效-----------------------------------------
        context.addBeanFactoryPostProcessor(configurableListableBeanFactory -> {
            //注册自定义scope
            configurableListableBeanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        });
        */
        getUser(context);
        context.close();
    }

    private static void getUser(AnnotationConfigApplicationContext context) {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                Person person = context.getBean("createPerson",Person.class);
                log.info("---------- createPerson -------" + person);
            }).start();
        }
    }
}
