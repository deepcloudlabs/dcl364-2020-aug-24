package com.example.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

// Proxy-based AOP (Java SE 6+)
public class ProfilingHandler implements InvocationHandler {
	private Object target;
	
	public ProfilingHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		var start = System.nanoTime();
		var result = method.invoke(target, args);
		var stop = System.nanoTime();
		System.err.println(String.format("%s runs %d ns.", method.getName(),(stop-start)));	
		return result;
	}

}
