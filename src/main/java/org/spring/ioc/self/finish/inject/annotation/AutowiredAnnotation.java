package org.spring.ioc.self.finish.inject.annotation;

import java.lang.annotation.*;

/**
 * 自定义依赖注入注解
 *
 * @author jianglong.li
 * @date 2021-06-27 16:19
 **/

@Target({ElementType.METHOD,ElementType.FIELD,ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutowiredAnnotation {


}
