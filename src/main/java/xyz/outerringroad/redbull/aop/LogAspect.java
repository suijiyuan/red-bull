package xyz.outerringroad.redbull.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(100)
public class LogAspect {

    private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    @Around("execution(* xyz.outerringroad.redbull.controller..*(..))")
    public Object logAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String method = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();
        String params = Arrays.toString(args);
        LOG.info("invoke http method: {}, param: {}", method, params);

        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } finally {
            String returnVal = String.valueOf(result);
            LOG.info("return val: {}", returnVal);
        }

        return result;
    }

}
