package com.example.app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {
	
	// 포인트컷 표현식 
	// "execution(* com.example.app.domain.service.SimpleServiceImpl.get1())"
	// execution : 메서드 실행
	// * : 모든 리턴 타입
	//com.example.app.domain.service.SimpleServiceImpl.get1() : 실행할 함수 위치
	@Before("execution(* com.example.app.domain.service.SimpleServiceImpl.get1())")
	public void logBefore(JoinPoint joinPoint) {
		
		log.info("[AOP] BEFORE.." + joinPoint);
	}
	@After("execution(* com.example.app.domain.service.SimpleServiceImpl.get2())")
	public void logAfter(JoinPoint joinPoint) {
//		log.info("[AOP] AFTER.." + joinPoint.getTarget());
//		log.info("[AOP] AFTER.." + joinPoint.getSignature());
		log.info("[AOP] AFTER.." + joinPoint.getSignature().getName());
	}
//	@Around("execution(* com.example.app.domain.service.SimpleServiceImpl.get3())")
//	public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
//		
//		log.info("[AOP] AROUND BEFORE.." + pjp.getSignature().getName()+ " START!--");
//		long start = System.currentTimeMillis();
//		Object result =   pjp.proceed();
//		long end = System.currentTimeMillis();
//		log.info("[AOP] TIME : " + (end-start) + " ms");
//		log.info("[AOP] RESULT.." + result);
//		log.info("[AOP] AROUND AFTER.." + pjp.getSignature().getName()+ " END!--");
//		return result;
//	}
	@Around("execution(* com.example.app.domain.service.*.*(..))")
	public Object allServicelogAround(ProceedingJoinPoint pjp) throws Throwable {
		
		log.info("[AOP] AROUND BEFORE.." + pjp.getSignature().getName()+ " START!--");
		long start = System.currentTimeMillis();
		Object result =   pjp.proceed();
		long end = System.currentTimeMillis();
		log.info("[AOP] TIME : " + (end-start) + " ms");
		log.info("[AOP] RESULT.." + result);
		log.info("[AOP] AROUND AFTER.." + pjp.getSignature().getName()+ " END!--");
		return result;
	}	
	
}
