package com.example.service;

public class StudyInheritance {

}

interface A {
	default void fun() {}
}

interface B {
	default void fun() {}
}

interface C {
	default void fun() {}
}

interface D extends A, B, C { // multiple inheritance
	default void fun() {}	
}

class X implements A , B{

	@Override
	public void fun() {
	}
	
}

abstract class F {}
abstract class G {}
abstract class H extends F{} // single inheritance