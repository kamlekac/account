package com.lambda;

public class LearnLambd {

	public static void main(String[] args) {
		System.out.println("system startt");
		// we can also call the method here itself as shown in 2.
//		MyInterface my= new Myimplement();
//        my.hello();
		
		
		// 2. instead a creating a new keyword and creating object eveytime, we can create anonymous class and use it.
		/* MyInterface my= new MyInterface() {

			@Override
			public void hello() {
				System.out.println(" functional interface anonymous class");
			}
			
		};
		my.hello();
		MyInterface my1= new MyInterface() {

			@Override
			public void hello() {
				System.out.println("this second anonymous class");	
			}
	};
	my1.hello(); */
	
	//3. using the lambda expression, if we are using single statement, then we can even remove the {    } 
//	MyInterface my=()->System.out.println("This is first lambda");
//	
//	my.hello();
//	
//	MyInterface my1=()->{
//		System.out.println("second lambda");
//	};
//	my1.hello();
//	
//	MyInterface my2= ()->{
//		System.out.println("third lambda");
//	};
//	my2.hello();
	MyInterface m1=()->{
		System.out.println(" first lambda");
	};// without { } 
	m1.hello();
	MyInterface m2=()->
		System.out.println("second lambda");
		m2.hello();
	Sums s1=(a,b)-> a+b;
	System.out.println("add:"+s1.sum(4, 4));
	Sums s2=(c,d)->c-d;
	System.out.println("sub:"+s2.sum(8, 8));
	Sums s3=(e,f)->e*f;
	System.out.println("multiply "+s3.sum(2, 2));
	Sums s5=(g,k)->g^k;
	System.out.println("powers "+s5.sum(2, 2));
	
		
		
		
		
		
		
		
		
		
// can also remove brackets and type, since has only one return statement

	Sums sum=(a,b)-> a+b;
		
System.out.println(sum.sum(8, 8));
	

	Subtractions sum1=(int a, int b)->{
		return a-b;
	};
	System.out.println(sum1.sub(8, 8));
	System.out.println(sum1.sub(2, 1));
	Multiplication multi=(a,b)->a*b;
	Multiplication multi1=(c,f)->c+f;
	System.out.println(multi1.multiplication(8, 12));
	System.out.println(multi.multiplication(8, 10));
	Multiplication multiply=(a,b)->a*b;
	System.out.println("multiply: " +multiply.multiplication(4, 8));
	System.out.println("multiply1: "+multiply.multiplication(4, 2));
     
	}
	
}