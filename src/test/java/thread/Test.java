package com.thread;


public class Test {

	public static void main(String[] args) {
		Admin admin=new Admin();
		admin.start();
		SuperAdmin superAdmin=new SuperAdmin();
		superAdmin.start();
	}
}
