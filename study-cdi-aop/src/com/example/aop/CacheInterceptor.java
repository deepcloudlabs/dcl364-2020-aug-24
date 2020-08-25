package com.example.aop;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Cache
public class CacheInterceptor {
	private static final Map<String, Object> cache = new ConcurrentHashMap<>();

	@AroundInvoke
	public Object audit(InvocationContext ic) throws Exception {
		var methodName = ic.getMethod().getName();
		var key = methodName.concat(Arrays.toString(ic.getParameters()));
		var value = cache.get(key);
		if (Objects.nonNull(value))
			return value;
		var result = ic.proceed();
		cache.put(key, result);
		return result;
	}
}
