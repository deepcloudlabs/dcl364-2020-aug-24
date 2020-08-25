package com.example.app;

import java.lang.reflect.Proxy;

import com.example.aop.ProfilingHandler;
import com.example.service.Calculator;
import com.example.service.business.StandardCalculator;

public class CalculatorApp {

	public static void main(String[] args) {
		Calculator calculator = new StandardCalculator();
		var clazz= calculator.getClass();
		var profilingHandler = new ProfilingHandler(calculator);
		calculator = (Calculator) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), profilingHandler);
		System.err.println(calculator.getClass());
		System.err.println("4+3= "+calculator.add(4, 3));
		System.err.println("4*3= "+calculator.mul(4, 3));
		System.err.println("4-3= "+calculator.sub(4, 3)); 
		System.err.println("4/3= "+calculator.div(4, 3));
	}

}
