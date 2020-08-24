package com.example.service;


// interface vs abstract
//               attribute (1)
//               constructor (2)
// implements    extends (3)
// multiple      single (4)
// inheritance   inheritance
// functional interface (functional programming)
// instanceof
public abstract interface EmployeeService { // abstract class
	public static final int x = 42; // can not have attributes ✘  but can have global constants
	// EmployeeService(){} // ✘ can not have constructor
    // public void fun() {} // ✘ can not have concrete method until Java SE 8
	public abstract void gun();
	// since Java SE 8
	default public void fun() { // public default implementation
		sun();
	}
	public static void run() { // public static -> utility function for functional programming 
		tun();
	} 
	// since Java SE 9
	private void sun() { // private method
		
	}	
	private static void tun() {} // private static method
}
 