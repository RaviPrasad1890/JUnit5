package com.ravi.junit5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MathTest {

	@Test
	void testMathMin() {
		int a=4;
		int b=5;
		int actualMin=Math.min( a, b);
		int expectedMin=a;
		assertEquals(expectedMin,actualMin);
	}
	
	@Test
	void testMathMax() {
		int a=4;
		int b=5;
		int actualMax=Math.max( a, b);
		int expectedMax=b;
		assertEquals(expectedMax,actualMax);
	}

}
