package xyz.outerringroad.redbull.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import xyz.outerringroad.redbull.bean.vo.ResponseVO;

@Aspect
@Component
@Order(3)
public class TraceAspect {

    private static final Logger LOG = LoggerFactory.getLogger(TraceAspect.class);

    @Around("execution(* xyz.outerringroad.redbull.controller..*(..))")
    public Object traceAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();
        if (result instanceof ResponseVO) {
            ResponseVO<?> responseVO = (ResponseVO<?>) result;
            responseVO.setTraceId(MDC.get("traceId"));
            return responseVO;
        } else {
            LOG.warn("the return value of the http interface should be a variable of type ResponseVO");
            return result;
        }

    }

}
