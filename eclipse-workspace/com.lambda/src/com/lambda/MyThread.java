package com.lambda;

public class MyThread implements Runnable{
	public void run() {
	for(int i=0;i<10;i++) {
		System.out.println("the value of i increasing " +i);
	}
	try{Thread.sleep(1000);}
	catch(Exception e) {}
	}
	public static void main(String[] args) {
	MyThread t1= new MyThread();
	Thread thread= new Thread(t1);
	thread.start();
	MyThread1 t= new MyThread1();
	t.start();
	
		
	}

}
