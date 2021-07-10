package org.spring.ioc.bean.aware;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Aware接口回调
 *
 * @author jianglong.li
 * @date 2021-07-02 22:01
 **/
@Slf4j
@ToString
@Accessors(chain = true)
@Data
public class BeanAwareDemo implements BeanNameAware, BeanFactoryAware,
        BeanClassLoaderAware , EnvironmentAware ,
        ResourceLoaderAware , InitializingBean
,SmartInitializingSingleton{

    private ClassLoader classLoader;

    private String  beanName;

    private BeanFactory beanFactory;

    private Environment environment;

    private ResourceLoader resourceLoader;

    private String name;

    /**
     * 当前bean初始化依赖于注解驱动
     */
    @PostConstruct
    public void init(){
        log.info("-------@PostConstruct init---------");
        this.name="@PostConstruct init";
    }

    @PreDestroy
    public void destroy(){
        /**
         * {@link DisposableBeanAdapter}的destroy()方法销毁bean
         */
        log.info("------------@PreDestroy destroy---------");
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
        context.register(BeanAwareDemo.class);
        context.refresh();
        BeanAwareDemo beanAwareDemo = context.getBean(BeanAwareDemo.class);
        log.info("-------------"+beanAwareDemo+"-----------");
        context.close();
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader=classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory=beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        log.info("--------setBeanName--------------"+name);
        this.beanName=beanName;
    }

    @Override
    public void setEnvironment(Environment environment) {
        //ApplicationContext上下文持有的属性，需要ApplicationContext上下文才能回调，
        // 简单DefaultListableBeanFactory的BeanFactory容器无法获取上下文中的该属性
        /**
         * {@link ApplicationContextAwareProcessor} 回调上下文Aware属性
         * private void invokeAwareInterfaces(Object bean) {
         * 		if (bean instanceof EnvironmentAware) {
         * 			((EnvironmentAware) bean).setEnvironment(this.applicationContext.getEnvironment());
         *                }
         * 		if (bean instanceof EmbeddedValueResolverAware) {
         * 			((EmbeddedValueResolverAware) bean).setEmbeddedValueResolver(this.embeddedValueResolver);
         *        }
         * 		if (bean instanceof ResourceLoaderAware) {
         * 			((ResourceLoaderAware) bean).setResourceLoader(this.applicationContext);
         *        }
         * 		if (bean instanceof ApplicationEventPublisherAware) {
         * 			((ApplicationEventPublisherAware) bean).setApplicationEventPublisher(this.applicationContext);
         *        }
         * 		if (bean instanceof MessageSourceAware) {
         * 			((MessageSourceAware) bean).setMessageSource(this.applicationContext);
         *        }
         * 		if (bean instanceof ApplicationContextAware) {
         * 			((ApplicationContextAware) bean).setApplicationContext(this.applicationContext);
         *        }* 	}
         */


        this.environment=environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader=resourceLoader;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("----------------- afterPropertiesSet --------------------------");
    }

    @Override
    public void afterSingletonsInstantiated() {
        log.info("----------------- afterSingletonsInstantiated --------------------------");
    }
}
