package xyz.outerringroad.redbull.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import xyz.outerringroad.redbull.bean.vo.ResponseVO;

@Aspect
@Component
@Order(3)
public class TraceAspect {

    @Around("execution(* xyz.outerringroad.redbull.controller..*(..))")
    public Object traceAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();
        ResponseVO<?> responseVO = (ResponseVO<?>) result;
        responseVO.setTraceId(MDC.get("traceId"));
        return responseVO;
    }

}
