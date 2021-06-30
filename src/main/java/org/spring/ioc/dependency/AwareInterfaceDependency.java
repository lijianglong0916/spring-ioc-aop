package org.spring.ioc.dependency;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * {@link Aware} 接口回调注入
 * @author jianglong.li
 * @date 2021-06-27 10:47
 **/
public class AwareInterfaceDependency implements BeanFactoryAware , ApplicationContextAware {

    private static BeanFactory beanFactory;

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
        //将当前类作为配置类注入
        context.register(AwareInterfaceDependency.class);

        context.refresh();

        System.out.println(beanFactory==context.getBeanFactory());
        System.out.println(applicationContext==context);
        context.close();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AwareInterfaceDependency.applicationContext=applicationContext;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AwareInterfaceDependency.beanFactory=beanFactory;
    }
}
