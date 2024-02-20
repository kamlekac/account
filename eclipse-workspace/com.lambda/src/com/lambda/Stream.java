package com.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Stream {

	public static void main(String[] args) {
		// create a list and filter all even numbers from a list, we can use List.of() also to create list and it is immutable.
		List<Integer> list=List.of(1,4,6,8,39,67,88,56);
		//System.out.println(list);
		// another way of add elements into list
		List<Integer> list2= new ArrayList<>();
		list2.add(88);
		list2.add(86);
		// another way of list and it is immutable
		List<Integer>list3=Arrays.asList(23,76,87,45);
		
		// without stream and it is boiler plate code
		List<Integer>evenlist= new ArrayList<>();
		for(Integer i:list) {
			if(i%2==0) {
				evenlist.add(i);
				}
		}
			System.out.println(list);
			//System.out.println(evenlist);
			
			// Using stream APIS now
//			Stream<Integer>stream1=list.stream();
//			List<Integer>newList=stream1.filter(i-> i%2==0).collect(Collectors.toList());
//			System.out.println(newList);
			List <Integer> list1= list.stream().filter(i -> i%2==0).collect(Collectors.toList());
			System.out.println(list1);
		}

	}


