package com.example.aop;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Profile
public class ProfileInterceptor {

	@AroundInvoke
	public Object audit(InvocationContext ic) throws Exception {
		var methodName = ic.getMethod().getName();
		var start = System.nanoTime();
		var result = ic.proceed();
		var stop = System.nanoTime();
		System.err.println(String.format("%s runs %d.", methodName, stop - start));
		return result;
	}
}
