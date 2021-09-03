package com.eklanfar.library.aspect;

import com.eklanfar.library.util.Helper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggerAspect.class);

    /**
     * Logs before a method execution
     *
     * @param joinPoint
     */
    @Before("execution(* com.eklanfar.library.service..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info("ENTERED - {}; {}", joinPoint.getSignature().getName(), Helper.arrayToString(args));
    }


    /**
     * Logs after a method execution
     *
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "execution(* com.eklanfar.library.service..*(..))", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        log.info("PROCESSED - {}; {}", joinPoint.getSignature().getName(), (result != null ? result : ""));
    }
}
