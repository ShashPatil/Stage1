package com.in28minutes.junit.helper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class StringHelperTest {
	
	StringHelper helper;
	
	@BeforeEach
	public void before() {
		helper = new StringHelper();
	}

	@Test
	public void testTruncateAInFirst2Positions_AinFirst2Positions() {
		
		//AACD ->CD ,ACD->CD, CDEF->CD, CDAA->CDAA
         assertEquals("CD", helper.truncateAInFirst2Positions("AACD"));
	   // assertEquals("CD", helper.truncateAInFirst2Positions("ACD"));
        //expected,actual
         
	}
	
	@Test
	public void testTruncateAInFirst2Positions2_AinFirstPosition() {
	    assertEquals("CD", helper.truncateAInFirst2Positions("ACD"));
		
	}
	
	//ABCD->false, ABAB->true, AB->true, A->false
	@Test
	public void testAreFirstAndLastTwoCharactersTheSame_BasicNegativeScenerio() {
		assertFalse(
				helper.areFirstAndLastTwoCharactersTheSame("ABCD"));
		//assertFalse checks if the value of this in bracket is false..
		
		//assertFalse(true,"dummy string"); -> prints message/description on failing
		
		}
	
	@Test
	public void testAreFirstAndLastTwoCharactersTheSame_BasicPositiveScenerio() {
		assertTrue(
				helper.areFirstAndLastTwoCharactersTheSame("ABAB"));
		//assertFalse checks if the value of this in bracket is false..
		
		//assertFalse(true,"dummy string"); -> prints message/description on failing
		
		
		
	}
	


}
