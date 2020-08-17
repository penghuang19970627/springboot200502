package com.ph.springBoot.aspect;


import java.lang.annotation.*;

/*自定义注解*/

@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceAnnotation {
    //设置默认值
    String value() default ("ph");
}
