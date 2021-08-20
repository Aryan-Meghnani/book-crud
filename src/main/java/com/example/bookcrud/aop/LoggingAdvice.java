package com.example.bookcrud.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy
public class LoggingAdvice {
    Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

    @Pointcut(value = "execution(* com.example.bookcrud.*.*.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object appLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().toString();
        Object[] array = proceedingJoinPoint.getArgs();
        logger.info("Method Invoked:" + className + ": Method Name:" + methodName + "() : Arguments : " +
                mapper.writeValueAsString(array));
        Object object = proceedingJoinPoint.proceed();
        logger.info(className + ": Method Name:" + methodName + "() : Response : " +
                mapper.writeValueAsString(object));
        return object;
    }
}
