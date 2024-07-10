package com.tanlaplatforms.hrishikeshpatil.employeeapp.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Component
@Aspect
public class LoggingAspect {

    private Logger logger = LogManager.getLogger();

    @Around("execution(* com.tanlaplatforms.hrishikeshpatil.employeeapp.restcontroller.*.*(..))")
    public Object controllerAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        logger.info("Request took time: " + (end - start) + "ms");
        return result;
    }

    @Order(2)
    @Around("execution(* com.tanlaplatforms.hrishikeshpatil.employeeapp.repository.*.*(..))")
    public Object repositoryAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        logger.info("Database access took time: " + (end - start) + "ms");
        return result;
    }

    @Order(1)
    @Around("execution(* com.tanlaplatforms.hrishikeshpatil.employeeapp.*.*.*(..))")
    public Object commonAspectExecutionOrder(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("Entering method: " + proceedingJoinPoint.getSignature().toLongString());
        Object result = proceedingJoinPoint.proceed();
        logger.info("Exiting method: " + proceedingJoinPoint.getSignature().toLongString());
        return result;
    }

}
