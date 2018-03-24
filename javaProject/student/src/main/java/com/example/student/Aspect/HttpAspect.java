package com.example.student.Aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
public class HttpAspect {
    private final static Logger LOGGER= LoggerFactory.getLogger(HttpAspect.class);
    @Pointcut("execution(public * com.example.student.Controller.StudentController.*(..))")
    public void log(){}

    @Before("log()")
    public void doBefote(JoinPoint joinPoint){
       ServletRequestAttributes attributes= (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
       HttpServletRequest request=attributes.getRequest();
        //url
        LOGGER.info("url={}" ,request.getRequestURL());
        //ip
        LOGGER.info("ip={}" ,request.getRemoteHost());
        //method
        LOGGER.info("method={}", request.getMethod());
        //请求的类方法
        LOGGER.info("class_method={}",joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        //args
        LOGGER.info("args={}",joinPoint.getArgs());
    }
    @After("log()")
    public void doAfter(){
        LOGGER.info("222");
    }
    //获取返回后的值
    @AfterReturning(returning = "object" ,pointcut = "log()")
    public void doAfterReturning(Object object){
        LOGGER.info("response={}",object.toString());
    }
}
