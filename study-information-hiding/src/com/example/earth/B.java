package com.example.earth;

public class B extends A {
	public void fun() {
		// u = 42; // private: error ✘
		v = 42; // protected ✔
		x = 42; // public ✔
		y = 42; // default ✔
	}
}
