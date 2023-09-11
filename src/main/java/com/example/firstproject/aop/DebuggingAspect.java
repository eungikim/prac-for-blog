package com.example.firstproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class DebuggingAspect {

//    @Pointcut("execution(* com.example.firstproject.service.CommentService.*(..))")
    @Pointcut("execution(* com.example.firstproject.api.*.*(..))")
    private void cut() { }

    @Before("cut()")
    public void loggingArgs(JoinPoint joinPoint) {
        // 입력값 가져오기
        Object[] args = joinPoint.getArgs();

        // 클래스명
        String className = joinPoint.getTarget().getClass().getSimpleName();
        // 메서드명
        String methodName = joinPoint.getSignature().getName();
        // 입력값 로깅
        for (Object obj : args) {
            log.info("[Aspect]{}#{} 의 입력값: {}", className, methodName, obj);
        }
    }

    @AfterReturning(value = "cut()", returning = "returnObj")
    public void loggingReturnValue(JoinPoint joinPoint, Object returnObj) {
        // 클래스명
        String className = joinPoint.getTarget().getClass().getSimpleName();
        // 메서드명
        String methodName = joinPoint.getSignature().getName();
        // 리턴값 로깅
        log.info("[Aspect]{}#{} 의 리턴값: {}", className, methodName, returnObj);
    }
}
