package com.ravi.junit5;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class StringTest {
	
	
	//@BeforeAll and @AfterAll must be used with static method
	@BeforeAll
	static void beforeAll() {		
		System.out.println("Connect to DB or do some other data setup");
	}	
	@AfterAll
	static void afterAll() {
		System.out.println("flush all connection");
	}
	
	/*
	@BeforeEach
	void beforeEach() {
		//Initialize test data before execution of each method
		System.out.println("Run before execution of each method");
	}
	
	@AfterEach
	void afterEach() {
		//Reset test data after execution of each method
		System.out.println("Run after each method");
	}
	*/
	
	//Parameterized version of beforeEach and afterEach
	@BeforeEach
	void beforeEach(TestInfo info) {
		System.out.println("Initialize test data for "+info.getDisplayName());
	}
	
	@AfterEach
	void afterEach(TestInfo info) {
		System.out.println("Clean up test data for "+info.getDisplayName());
	}
	
	/*
	@Test
	void whatEverNameYouWant() {
		fail("Not yet implemented");
		//Absence of failure is success
		
		//Step 1: Write Test Code(Prepare data)
		//Step 2: Invoke method--Square(40)=> Code under test
		//Step 3: Put Checks-16=> Assertion
	}

	 */
	
	@Test
	void testLengthBasic() {
		int actualLength="ABCD".length();
		int expectedLength=4;
		assertEquals(expectedLength, actualLength);
	}
	
	@Test
	void testToUppercaseBasic() {
		String str= "match";
		String res=str.toUpperCase();
		assertEquals("MATCH",res);
	}
	
	@Test
	void testContains() {
		String str= "abcdefgh";
		boolean res=str.contains("bcd");
		//assertEquals(true,res);
		assertTrue(res);
		//Similarly we have below assert methods:
		//assertFalse,assertNull,assertNotNull
		
	}
	
	@Test
	void testSplit() {
		String str="abc def ghi jkl";
		String actualResult[] = str.split(" ");
		String[] expectedResult= {"abc","def","ghi","jkl"};
		assertArrayEquals(expectedResult,actualResult);
		//We can use assertEquals but assertEqualsArray will give
		//more specific message related to failure
		
	}
	
	@ParameterizedTest
	@ValueSource(strings= {"ABCD","ABC","DE","dsdsh"})//doubles,ints,longs are also available
	void testBasicParameterizedTest(String str) {
		assertTrue(str.length()>0);
	}
	
	@ParameterizedTest
	@CsvSource(value= {"abcde,ABCDE","'',''","abc,ABC","ravi,RAVI","xyz,XYZ"})
	void testParemetrizedFromCsv(String word, String xpectedResult) {
		assertEquals(xpectedResult,word.toUpperCase());
	}
	
	
	//Couple of Tricks
	//Trick 1:
	@Test
	void testLengthException() {
		String str=null;
		assertThrows(NullPointerException.class,
				()->{
					str.length();
					}
				);
		
	}
	
	//Trick 2:
	//With JUnit 5, all test methods not required to be public
	//Which was mandatory in JUnit4
	
	//Trick 3:
	@Test
	@DisplayName("When I/P String is null, throw an Exception")
	void testStringEquals() {
		String str=null;
		assertThrows(NullPointerException.class,
				()->{
					str.equals(new String());
				});
	}
	
	//Trick 4: Giving name to ParameterizedTest
	@ParameterizedTest(name="{0} length is {1}")
	@CsvSource(value= {"abcde,5","'',0","abc,3","ravi,4","xyz,3"})
	void testParemeterizedLength(String word, int expectedLength) {
		assertEquals(expectedLength,word.length());
	}
	
	//Trick 5: Repeat same test multiple times
	@RepeatedTest(5)
	void testBasic() {
		int a=2;
		int b=3;
		int actualSum=a+b;
		int expectedSum=5;
		assertEquals(expectedSum,actualSum);
	}
	
	//Trick 6: Performance Test
	@Test
	void testSimplePerformance() {
		assertTimeout(Duration.ofSeconds(5),
				()->{
					for (int i = 0; i < 5; i++) {						
						System.out.println("Hi Der");						
					}
				});
	}
	
}
