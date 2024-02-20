package com.lambda;

import java.util.List;
import java.util.stream.Collectors;

public class Methods {

	public static void main(String[] args) {
		// printing name starting with s
		List<String>name=List.of("Aman","Sure","Santos","harsha");
		List<String>names1=name.stream().filter(i->i.startsWith("S")).collect(Collectors.toList());
		System.out.println(names1);
		
		
		// list of numbers and each numbers should be squared, here there is no need of condition, no filter used here
		List<Integer>list1=List.of(1,6,8,4,76,88);
		List <Integer>newlist=list1.stream().map(i->i*i).collect(Collectors.toList());
		System.out.println(newlist);
		
	}

}
