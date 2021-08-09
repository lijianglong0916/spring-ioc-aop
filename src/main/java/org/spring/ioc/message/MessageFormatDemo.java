package org.spring.ioc.message;

import org.springframework.context.support.ResourceBundleMessageSource;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * @author jianglong.li
 * @date 2021-07-30 20:24
 **/
public class MessageFormatDemo {
    public static void main(String[] args) {
        String format = MessageFormat.format("", "");
        MessageFormat messageFormat=new MessageFormat("");
        messageFormat.setLocale(Locale.US);

        //重置pattern
        String pattern="";
        messageFormat.applyPattern(pattern);

        //线程安全
        ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
        messageSource.getMessage("", new Object[]{}, Locale.US);
    }
}
