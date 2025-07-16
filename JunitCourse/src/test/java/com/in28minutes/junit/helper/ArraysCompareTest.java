package com.in28minutes.junit.helper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class ArraysCompareTest {
	
	//Arrays.sort
	

	@Test
	public void testArraySort_RandomArray() {
		int[] numbers = {12,3,4,1};
		int[] expected = {1,3,4,12};
		
		// Arrays.sort(numbers);
		 
		 assertArrayEquals(expected,numbers);
		//array contents differ at index [0], expected: <1> but was: <12>
			
	}
	
	
	@Test
	public void testArraySort_NullArray() {
	    int[] numbers = null;
	    assertThrows(NullPointerException.class, () -> {
	        //This is the line of code that is expected to throw the exception
	        Arrays.sort(numbers);
	    });
	}
	
	@Test
	@Timeout(value=1000, unit=TimeUnit.MILLISECONDS)
	//Test pass-> it is telling us that 1 million arrays are sorted within 1000 ms
	void testSort_Performance() {
		int array[] = {12,23,4};
		for(int i=1;i<=1000000;i++) {
			
			array[0] = i;
			Arrays.sort(array);
		}
	}

}
