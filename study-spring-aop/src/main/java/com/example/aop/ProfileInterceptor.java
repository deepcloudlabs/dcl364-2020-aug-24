package com.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ProfileInterceptor {

	@Around("methodProfileAnnotated() || classProfileAnnonated()")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		var methodName = pjp.getSignature().getName();
		var start = System.nanoTime();
		var result = pjp.proceed();
		var stop = System.nanoTime();
		System.err.println(String.format("%s runs %d.", methodName, stop - start));
		return result;
	}

	@Pointcut("within(@com.example.aop.Profile *)")
	public void methodProfileAnnotated() {
	}

	@Pointcut("@annotation(com.example.aop.Profile)")
	public void classProfileAnnonated() {
	}
}
