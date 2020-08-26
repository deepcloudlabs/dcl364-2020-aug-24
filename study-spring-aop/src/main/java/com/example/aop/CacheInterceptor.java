package com.example.aop;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(Integer.MIN_VALUE)
public class CacheInterceptor {
	private static final Map<String, Object> cache = new ConcurrentHashMap<>();

	@Around("methodCacheAnnotated() || classCacheAnnonated()")
	public Object cache(ProceedingJoinPoint pjp) throws Throwable {
		var methodName = pjp.getSignature().getName();
		var key = methodName.concat(Arrays.toString(pjp.getArgs()));
		var value = cache.get(key);
		if (Objects.nonNull(value))
			return value;
		var result = pjp.proceed();
		cache.put(key, result);
		return result;
	}
	
	@Pointcut("within(@com.example.aop.Cache *)")
	public void methodCacheAnnotated() {}
	
	@Pointcut("@annotation(com.example.aop.Cache)")
	public void classCacheAnnonated() {}
}
