package com.example.firstproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {

    // 특정 어노테이션 지정
    @Pointcut("@annotation(com.example.firstproject.annotation.RunningTime)")
    private void enableRunningTime() {}

    // 기본 패키지
//    @Pointcut("execution(* com.example.firstproject.service.CommentService.*(..))")
    @Pointcut("execution(* com.example.firstproject..*.*(..))")
    private void cut() {}

    @Around("cut() && enableRunningTime()")
//    @Around("cut()")
    public void loggingRunningTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // 메서드 수행전
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 메서드 수행
        joinPoint.proceed();

        // 종료 후
        stopWatch.stop();
        String methodName = joinPoint.getSignature().getName();
        log.info("[Aspect]메서드 {} 총 수행시간 {}ms", methodName, stopWatch.getTotalTimeMillis());
    }
}
