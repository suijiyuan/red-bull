package xyz.outerringroad.redbull.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import xyz.outerringroad.redbull.bean.vo.ResponseVO;
import xyz.outerringroad.redbull.constant.CodeEnum;
import xyz.outerringroad.redbull.exception.BizException;

@Aspect
@Component
@Order(300)
public class ExceptionAspect {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionAspect.class);

    @Around("execution(* xyz.outerringroad.redbull.controller..*(..))")
    public Object exceptionAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (BizException e) {
            LOG.info("exceptionAspect, BizException: {}", e.getMessage());
            return ResponseVO.createResponseVO(e);
        } catch (Exception e) {
            LOG.error("exceptionAspect, Exception", e);
            return ResponseVO.createResponseVO(CodeEnum.FAILURE.getCode());
        }

        return result;
    }

}
