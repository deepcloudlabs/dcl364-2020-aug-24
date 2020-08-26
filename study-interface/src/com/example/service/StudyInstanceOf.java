package com.example.service;

public class StudyInstanceOf {
	public static void main(String[] args) {
        XYZ x = new XYZ();
        if (x instanceof ABC) {} // ✔
        // if (x instanceof DEF) {} ✘
	}
}

interface ABC {
}

abstract class DEF {
}

class XYZ {
}