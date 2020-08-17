package com.ph.springBoot.aspect;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {

    /*日志系统*/
    private final static Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);

    @Pointcut("@annotation(com.ph.springBoot.aspect.ServiceAnnotation)")
    @Order(2)
    public void servicePointCut(){}

    @Before(value = "com.ph.springBoot.aspect.ServiceAspect.servicePointCut()")
    public void beforeService(){
        LOGGER.debug("——————————————————————service前置控制——————————————————————");
    }

    @Around(value = "com.ph.springBoot.aspect.ServiceAspect.servicePointCut()")
    public Object aroundService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.debug("——————————————————————service环绕控制——————————————————————");
        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }

    @After(value = "com.ph.springBoot.aspect.ServiceAspect.servicePointCut()")
    public void afterService(){
        LOGGER.debug("——————————————————————service后置控制——————————————————————");
    }
}
