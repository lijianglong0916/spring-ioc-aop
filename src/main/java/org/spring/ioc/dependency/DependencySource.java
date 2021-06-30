package org.spring.ioc.dependency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 依赖注入来源
 *
 * @author jianglong.li
 * @date 2021-06-27 18:43
 **/
@Slf4j
public class DependencySource {

    //依赖注入在postProcessProperties中进行，早于setter注入、@PostConstruct注入
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private String value;

    @PostConstruct
    public String sayHello(){
        String hello = "hello";
        log.debug("post construct  "+hello+"\n"+"beanFactory"+beanFactory);
        return hello;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();

        //当前回调是由context.refresh()的invokeBeanFactoryPostProcessors(beanFactory);调用的，
        //早于finishBeanFactoryInitialization(beanFactory);（bean的初始化），所以早于当前类的bean的初始化；
        //（context.register(DependencySource.class);注册为bean），所以当前回调注入没问题，如果不是回调注册resolvable dependency，
        // 早于context.refresh();，容器还未刷新，后于context.refresh();，bean已经初始化完成，@Autowired强依赖的方式再注入会报错
        context.addBeanFactoryPostProcessor(configurableListableBeanFactory->{
            //注册resolvable dependency ，ResolvableDependency只能用于类型依赖注入，不能用于依赖查找，
            configurableListableBeanFactory.registerResolvableDependency(String.class, "hello");
        });

        context.register(DependencySource.class);

        context.refresh();

        DependencySource dependencySource = context.getBean(DependencySource.class);
        log.debug("dependencySource.applicationContext==context  "+(dependencySource.applicationContext==context));
        log.debug("dependencySource.publisher ==context  "+(dependencySource.publisher ==context));
        log.debug("dependencySource.resourceLoader==context  "+(dependencySource.resourceLoader==context));
        log.debug("dependencySource.beanFactory==context.getBeanFactory()  "+(dependencySource.beanFactory==context.getBeanFactory()));

        log.debug("resolvable dependency "+dependencySource.value);


        context.close();
    }
}
