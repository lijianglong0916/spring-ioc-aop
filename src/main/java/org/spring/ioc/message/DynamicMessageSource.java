package org.spring.ioc.message;

import org.springframework.context.MessageSource;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.text.MessageFormat;
import java.util.Locale;

/**
 *
 * 动态资源{@link MessageSource} 实现
 * @author jianglong.li
 * @date 2021-08-07 13:42
 **/
public class DynamicMessageSource extends AbstractMessageSource implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    private static final String  messagePath="";

    @Override
    protected MessageFormat resolveCode(String s, Locale locale) {
        return null;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader=resourceLoader;
    }

    private ResourceLoader getResourceLoader(){
        return this.resourceLoader==null?new DefaultResourceLoader():this.resourceLoader;
    }
}
