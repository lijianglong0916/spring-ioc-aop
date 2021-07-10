package org.spring.ioc.metatada.self.xml.authoring;

import org.spring.domain.entity.Person;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * {@link Person} namespace
 *
 * @author jianglong.li
 * @date 2021-07-06 22:02
 **/
public class PersonNameSpaceHandler extends NamespaceHandlerSupport {

    //将person注册到对应的PersonBeanDefinitionParser中

    @Override
    public void init() {
        registerBeanDefinitionParser("person", new PersonBeanDefinitionParser());
    }
}
