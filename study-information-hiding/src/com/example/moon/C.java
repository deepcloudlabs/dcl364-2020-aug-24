package com.example.moon;

import com.example.earth.A;

public class C extends A {
	public void fun() {
		// u = 42; // private: error ✘
		v = 42; // protected ✔
		x = 42; // public ✔
		// y = 42; // default: package private -> error (earth, moon) ✘
	}
}
