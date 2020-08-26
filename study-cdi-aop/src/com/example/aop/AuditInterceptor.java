package com.example.aop;

import java.util.Arrays;
import java.util.Date;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Audit
public class AuditInterceptor {

	@AroundInvoke
	public Object audit(InvocationContext ic) throws Exception {
		var methodName = ic.getMethod().getName();
		var now = new Date();
		System.err.println(String.format("%s is called at %s.", methodName, now));
		System.err.println(String.format("Parameters are %s.", Arrays.toString(ic.getParameters())));
		var result = ic.proceed();
		System.err.println(String.format("%s returns %s.", methodName, result));
		return result;
	}
}
