package com.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class StreamObject {

	public static void main(String[] args) {
		// create empty, create blank stream with help of empty();
		//Stream<Object>emptyStream=Stream.empty();
		
		String names[]= {"Durgesh","ankit"};
		// to takes names which start with D
		//Stream <String>stream1= Stream.of(names);
		
		// also use builder to build elements
		//Stream<Object> streambuilder= Stream.builder().build();
		// call stream on collections
		
		List<Integer> list= new ArrayList<>();
		list.add(90);
		list.add(89);
		// here you are using object of arraylist and call stream on it and storing it in stream1, using stream., on stream1, using lambda function
		Stream<Integer>stream1=list.stream();
		stream1.forEach(e->{
			System.out.println(e);
		});
		
		

	}

}
