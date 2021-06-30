package org.spring.ioc.bean.scope;

import lombok.extern.slf4j.Slf4j;
import org.spring.domain.entity.BeanScopeEntity;
import org.spring.domain.entity.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.annotation.PreDestroy;
import java.util.Map;

/**
 * bean scope
 *
 * @author jianglong.li
 * @date 2021-06-29 07:34
 **/

@Slf4j
public class BeanScopeDemo implements DisposableBean {
    /**
     * Singleton、Prototype两种来进行限制bean的scope，Singleton只会初始一次，Prototype每次调用都会初始化
     * 销毁时{@link PreDestroy } 的destroy钩子Prototype没有被执行回调，只有Singleton会调用一次
     *
     * 只要原型bean本身不持有对另一个资源(如数据库连接或会话对象)的引用,只要删除了对该对象的所有引用或对象超出范围,
     * 就会立即收集垃圾.因此,通常没有必要显式销毁原型bean.
     *
     * 但是,在如上所述可能发生内存泄漏的情况下,可以通过创建单一bean后处理器来销毁原型bean,
     * 其后处理方法显式调用原型bean的销毁挂钩.因为后处理器本身是单例范围的,所以Spring会调用它的破坏钩子：
     *
     *
     */

    @Bean
    public BeanScopeEntity getSingletonEntity() {
        return new BeanScopeEntity(System.currentTimeMillis());
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public BeanScopeEntity getPrototypeEntity() {
        return new BeanScopeEntity(System.currentTimeMillis());
    }

    @Autowired
    @Qualifier("getSingletonEntity")
    private BeanScopeEntity singletonBean;

    @Autowired
    @Qualifier("getSingletonEntity")
    private BeanScopeEntity singletonBean1;

    @Autowired
    @Qualifier("getPrototypeEntity")
    private BeanScopeEntity prototypeBean;

    @Autowired
    @Qualifier("getPrototypeEntity")
    private BeanScopeEntity prototypeBean1;

    @Autowired
    @Qualifier("getPrototypeEntity")
    private BeanScopeEntity prototypeBean2;

    @Autowired
    private Map<String, Person> personMap;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanScopeDemo.class);
        XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(context);
        String sourcePath="classpath:/META-INF/dependency-constructor-context.xml";
        reader.loadBeanDefinitions(sourcePath);
        //处理Prototype
        context.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
                @Override
                public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                    log.info("--------"+bean+"-------post Process Before Initialization--------");
                    return bean;
                }

                @Override
                public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                    log.info("--------"+bean+"-------post Process After Initialization--------");
                    return bean;
                }
            });
        });
        context.refresh();
        BeanScopeDemo beanScopeDemo = context.getBean(BeanScopeDemo.class);
        System.out.println("beanScope.singletonBean " + beanScopeDemo.singletonBean);
        System.out.println("beanScope.singletonBean1 " + beanScopeDemo.singletonBean1);
        System.out.println("beanScope.prototypeBean " + beanScopeDemo.prototypeBean);
        System.out.println("beanScope.prototypeBean1 " + beanScopeDemo.prototypeBean1);
        System.out.println("beanScope.prototypeBean2 " + beanScopeDemo.prototypeBean2);
        context.close();
    }


    @Override
    public void destroy() throws Exception {
        this.singletonBean.destroy();
        this.prototypeBean.destroy();
        this.prototypeBean1.destroy();
        this.prototypeBean2.destroy();
        for (Map.Entry<String, Person> personEntry : this.personMap.entrySet()) {
            String beanName = personEntry.getKey();
            BeanDefinition beanDefinition = this.beanFactory.getBeanDefinition(beanName);
            if (beanDefinition.isPrototype()){
                Person bean = personEntry.getValue();
                log.info("-----prototype bean -----"+ beanName +"-----destroy-----"+ bean);
                bean.destroy();
            }
        }
    }
}
