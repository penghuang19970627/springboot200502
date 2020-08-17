package com.ph.springBoot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


@Aspect
@Component
public class ControllerAspect {
    //日志系统
    private final static Logger LOGGER = (Logger) LoggerFactory.getLogger(ControllerAspect.class);
    @Pointcut("execution(public * com.ph.springBoot.modules.*.controller.*.*(..))")
    @Order(1)
    public void controllerPointCut(){}

    @Before(value = "com.ph.springBoot.aspect.ControllerAspect.controllerPointCut()")
    public void beforeController(JoinPoint joinPoint){
        LOGGER.debug("————————————————————前置控制————————————————————");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LOGGER.debug("请求来源"+request.getRemoteAddr());
        LOGGER.debug("请求URL"+request.getRequestURL().toString());
        LOGGER.debug("响应方式"+request.getMethod());
        LOGGER.debug("响应方法"+joinPoint.getSignature().getDeclaringTypeName()
                +"."+joinPoint.getSignature().getName());
        LOGGER.debug("请求参数"+ Arrays.toString(joinPoint.getArgs()));
    }

    @Around(value = "com.ph.springBoot.aspect.ControllerAspect.controllerPointCut()")
    public Object arroundController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.debug("———————————————————————环绕控制—————————————————————————");
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    @After(value = "com.ph.springBoot.aspect.ControllerAspect.controllerPointCut()")
    public void afterController(){
        LOGGER.debug("———————————————————————————后置控制—————————————————————————————");
    }
}
