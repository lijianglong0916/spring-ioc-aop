package org.spring.ioc.metatada.self.xml.authoring;

import org.spring.domain.entity.Person;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * {@link Person} {@link BeanDefinitionParser}
 *
 * @author jianglong.li
 * @date 2021-07-06 22:06
 **/
public class PersonBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    private String setPropertyValue(String attributeName,Element element, BeanDefinitionBuilder builder){
        String attributeValue = element.getAttribute(attributeName);
        if (StringUtils.hasText(attributeName)){
            builder.addPropertyValue(attributeName, attributeValue);
        }
        return attributeValue;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        setPropertyValue("id",element,builder);
        setPropertyValue("name",element,builder);
        super.doParse(element, builder);
    }

    @Override
    protected Class<?> getBeanClass(Element element) {
        return Person.class;
    }
}
