package org.spring.ioc.metatada.self.yaml;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.env.YamlPropertySourceLoader;

import java.util.Map;

/**
 *
 * 基于xml
 * {@link YamlPropertiesFactoryBean }{@link YamlPropertySourceLoader} Yaml资源加载
 * @author jianglong.li
 * @date 2021-07-10 12:26
 **/
public class XmlBasedYamlPropertySourceDemo {
    /**
     * Yaml资源解析
     * 1.添加依赖
     *      <dependency>
     *          <groupId>org.yaml</groupId>
     *          <artifactId>snakeyaml</artifactId>
     *          <version>1.27</version>
     *      </dependency>
     * 2.定义属性配置文件 person.yaml
     * 3.配置资源加载配置文件 yaml-property-source-context.xml,关联person.yaml
     *
     *
     */


    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory=new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:/META-INF/yaml-property-source-context.xml");
        Map<String,Object> yamlMap = beanFactory.getBean("yamlMap", Map.class);
        System.out.println(yamlMap.toString());
    }
}
