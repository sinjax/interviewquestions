import static org.junit.Assert.*;

import org.junit.Test;


public class ArrayStringProblemsTest {
	@Test public void testFirstNonRepeat(){
		assertTrue(ArrayStringProblems.firstNonRepeated("teeter").equals("r"));
		assertTrue(ArrayStringProblems.firstNonRepeated("total").equals("o"));
	}
	@Test public void testRemoveString(){
		String in = "Battle of the Vowels: Hawaii vs. Grozny";
		String out = "Bttl f th Vwls: Hw vs. Grzny";
		assertTrue(ArrayStringProblems.removeChars(in, "aeiou").equals(out));
	}
	
	@Test public void testStringToInt(){
		assertTrue(ArrayStringProblems.stringToInt("1000") == 1000);
		assertTrue(ArrayStringProblems.stringToInt("213412124") == 213412124);
		assertTrue(ArrayStringProblems.stringToInt("-978234") == -978234);
		assertTrue(ArrayStringProblems.stringToInt("-0") == -0);
		assertTrue(ArrayStringProblems.stringToInt("0") == 0);
		assertTrue(ArrayStringProblems.stringToInt("1") == 1);
		assertTrue(ArrayStringProblems.stringToInt("-1") == -1);
	}
	
	@Test public void testIntToString(){
		assertTrue(ArrayStringProblems.intToString(1000).equals("1000"));
		assertTrue(ArrayStringProblems.intToString(-1).equals("-1"));
		assertTrue(ArrayStringProblems.intToString(1).equals("1"));
		assertTrue(ArrayStringProblems.intToString(0).equals("0"));
		assertTrue(ArrayStringProblems.intToString(41289421).equals("41289421"));
		assertTrue(ArrayStringProblems.intToString(-923189).equals("-923189"));
	}
}
