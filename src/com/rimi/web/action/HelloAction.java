package com.rimi.web.action;

public class HelloAction {
	public String sayHello() {
		System.out.println("sayHello被调用");
		return "success";
	}
	public void test() {
		System.out.println("hot_fix");
	}
}
