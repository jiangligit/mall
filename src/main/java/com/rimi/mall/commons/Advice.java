package com.rimi.mall.commons;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;

/**
 * 通知
 *
 * @author Administrator
 * @date 2019-04-18 14:59
 */
@Aspect
public class Advice {
    public Object aroundCache(ProceedingJoinPoint point) throws Throwable {
        Object proceed = point.proceed();
        return proceed;
    }
}
