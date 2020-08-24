package com.example.moon;

import com.example.earth.A;

public class E {
	public void fun(A a) {
		// a.u = 42; // private: error ✘
		// a.v = 42; // protected -> default ✘
		a.x = 42; // public ✔
		// a.y = 42; // default ✘
	}
}
