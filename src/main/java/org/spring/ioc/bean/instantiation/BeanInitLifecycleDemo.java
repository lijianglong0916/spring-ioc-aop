package org.spring.ioc.bean.instantiation;

import lombok.extern.slf4j.Slf4j;
import org.spring.ioc.bean.aware.BeanAwareDemo;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * bean 实例化生命周期demo
 *
 * 1.实例化
 * 2.赋值
 * 3.Aware接口回调 {@link BeanAwareDemo}
 * 4.初始化{
 *     a.@PostCon
 * }
 *
 *
 * <p>
 * {@link AbstractAutowireCapableBeanFactory} doCreateBean()
 *
 * @author jianglong.li
 * @date 2021-07-01 07:47
 **/

@Slf4j
public class BeanInitLifecycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(defaultListableBeanFactory);
        context.register(BeanAwareDemo.class,BeanInitLifecycleDemo.class);
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();


        /**
         * {@link AbstractAutowireCapableBeanFactory}
         *
         * @Nullable
         * protected Object applyBeanPostProcessorsBeforeInstantiation(Class<?> beanClass, String beanName) {
         * //遍历需要初始化{@link Bean} 对应的BeanPostProcessors，
         *    for (BeanPostProcessor bp : getBeanPostProcessors()) {
         *    	if (bp instanceof InstantiationAwareBeanPostProcessor) {
         *    		InstantiationAwareBeanPostProcessor ibp = (InstantiationAwareBeanPostProcessor) bp;
         *
         *
         *<<---------------*如果当前{@link BeanPostProcessor}	bp返回了新的bean实例，就会将返回的bean实例直接返回，------------------------->>
         *<<---------------*剩余的{@link BeanPostProcessor}将不会再被执行，这里就有一个{@link BeanPostProcessor}的顺序问题，------------------>>
         *<<---------------*按照我们手动添加的顺序，最先添加的最先执行，然后是springCont相关的{@link BeanPostProcessor}------------------------->>
         *    		Object result = ibp.postProcessBeforeInstantiation(beanClass, beanName);
         *    		if (result != null) {
         *    			return result;
         *              }
         *          }
         *      }
         *    return null;
         * }
         */

        /**
         * 如果getBeanPostProcessors()的所有{@link BeanPostProcessor}都不会定制新的bean或者生成新的代理对象，
         * {@link AbstractAutowireCapableBeanFactory}的protected Object createBean(String beanName, RootBeanDefinition mbd, @Nullable Object[] args)
         * 方法会继续doCreateBean(beanName, mbdToUse, args)去调用bean的init属性去初始化bean实例，如下：
         *try {
         *	// Give BeanPostProcessors a chance to return a proxy instead of the target bean instance.
         *《---------{@link BeanPostProcessor}都不会定制新的bean或者生成新的代理对象时，bean为null-------------------》
         *	Object bean = resolveBeforeInstantiation(beanName, mbdToUse);
         *	if (bean != null) {
         *		return bean;
         *	}
         *}
         *catch (Throwable ex) {
         *	throw new BeanCreationException(mbdToUse.getResourceDescription(), beanName,
         *			"BeanPostProcessor before instantiation of bean failed", ex);
         *
         *}
         *try {
         *《---------{@link BeanPostProcessor}都不会定制新的bean或者生成新的代理对象时，bean为null，这里调用init属性初始化bean-------------------》
         *	Object beanInstance = doCreateBean(beanName, mbdToUse, args);
         *	if (logger.isTraceEnabled()) {
         *		logger.trace("Finished creating instance of bean '" + beanName + "'");
         *	}
         *	return beanInstance;
         */

        /**
         * 不添加BeanPostProcessor时默认为{@link CommonAnnotationBeanPostProcessor}
         * 解决{@linK BeanAwareDemo } -----> {@link PostConstruct}初始化
         * 添加{@link CommonAnnotationBeanPostProcessor};
         * 以及自定义{@link InstantiationAwareBeanPostProcessor} 、{@link SelfDestructionAwareBeanPostProcessor}
         *
         */
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new SelfInstantiationAwareBeanPostProcessor());
        beanFactory.addBeanPostProcessor(new SelfDestructionAwareBeanPostProcessor());
        context.refresh();
        BeanAwareDemo beanAwareDemo = beanFactory.getBean( BeanAwareDemo.class);
        log.info(beanAwareDemo.toString());
        beanFactory.destroyBean("beanAwareDemo",beanAwareDemo);
        context.close();
    }

    /**
     * 自定义destroy回调{@link DestructionAwareBeanPostProcessor}
     * 销毁顺序
     * 1.requiresDestruction（这一步返回true时第三步才会被回调）
     * 2.@PostConstruct
     * 3.postProcessBeforeDestruction
     */
    static class SelfDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor{

        @Override
        public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
            if (bean.getClass().equals(BeanAwareDemo.class) && beanName .equals("beanAwareDemo") ) {
                log.info("------- postProcessBeforeDestruction destroy ---------------------");
            }
        }

        @Override
        public boolean requiresDestruction(Object bean) {
            log.info("------- requiresDestruction destroy ---------------------");
            return true;
        }
    }



    static class SelfInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            //----------------------------bean实例化before----------------------------------
            //找到原有的person bean进行替换
            if (beanClass.equals(BeanAwareDemo.class) && beanName .equals("beanAwareDemo") ) {
                BeanAwareDemo beanAwareDemo = new BeanAwareDemo().setName("postProcessBeforeInstantiation init");
                log.info("------- postProcessBeforeInstantiation init ---------"+beanAwareDemo+"------------");
                /**
                 * -------------------------这里返回bean实例时，当前bean剩余对应的---------------------------------
                 * -------------------------{@link BeanPostProcessor}将不会再被执行-----------------------------
                 */
//                return beanAwareDemo;
            }
            return null;
        }

        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            //----------------------------bean实例化after----------------------------------
            //return false时，后续InstantiationAwareBeanPostProcessor的操作都将被跳过
            return true;
        }

        @Override
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
            //----------------------------bean属性赋值----------------------------------
            if (bean.getClass().equals(BeanAwareDemo.class) && beanName.equals("beanAwareDemo")) {
                log.info("----------------postProcessBeforeInstantiation beanName------------");
                MutablePropertyValues propertyValue = new MutablePropertyValues();
                propertyValue.addPropertyValue("name", "postProcessProperties set");
                log.info("----------------"+bean+"------------");
                return propertyValue;
            }
            return null;
        }

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            //----------------------------bean初始化Before----------------------------------
            if (bean.getClass().equals(BeanAwareDemo.class)){
                log.info("-------- postProcessBeforeInitialization --------"+bean+"------------");
            }
            return null;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            //----------------------------bean初始化After----------------------------------
            if (bean.getClass().equals(BeanAwareDemo.class)) {
                log.info("------- postProcessAfterInitialization ---------" + bean + "------------");
            }
            return null;
        }
    }

}
