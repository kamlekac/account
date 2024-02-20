package com.lambda;

public class MyThread1 extends Thread{
	public void run() {
		for(int i=10;i>=1;i--) {
			System.out.println("the value of i decreasing "+i);
		}
		try{Thread.sleep(2000);}
		catch(Exception e) {}

}
}