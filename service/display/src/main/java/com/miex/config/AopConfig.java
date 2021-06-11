package com.miex.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class AopConfig {
    @Pointcut("execution(* com.miex.rest..*.*(..))")
    public void restPointcut() { }

    @Around("restPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object val;
        log.info(">> 开始处理请求：{},参数: {}",joinPoint.getSignature().toShortString(), Arrays.toString(joinPoint.getArgs()));
        try{
            val = joinPoint.proceed();
        } catch (Throwable e) {
            log.info("<< 请求异常：{}",joinPoint.getSignature().getName(),e);
            throw e;
        }
        if (null == val) {
            log.info("<< 结束处理请求：{}",joinPoint.getSignature().getName());
        } else {
            log.info("<< 结束处理请求：{},结果: {}",joinPoint.getSignature().getName(),val.toString());
        }
        return val;
    }
}
