package com.example.aop;

import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(Integer.MAX_VALUE)
public class AuditInterceptor {

	@Around("methodAuditAnnotated() || classAuditAnnonated()")
	public Object audit(ProceedingJoinPoint pjp) throws Throwable {
		var methodName = pjp.getSignature().getName();
		var now = new Date();
		System.err.println(String.format("%s is called at %s.", methodName, now));
		System.err.println(String.format("Parameters are %s.", Arrays.toString(pjp.getArgs())));
		var result = pjp.proceed();
		System.err.println(String.format("%s returns %s.", methodName, result));
		return result;
	}

	@Pointcut("within(@com.example.aop.Audit *)")
	public void methodAuditAnnotated() {
	}

	@Pointcut("@annotation(com.example.aop.Audit)")
	public void classAuditAnnonated() {
	}
}
