package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import edu.westga.cs3110.unicoder.model.Codepoint;

public class TestToUTF32 {
	@ParameterizedTest
	@CsvSource( {"0000,00000000", "10FFFF,0010FFFF", "001A, 0000001A"} )
	void testTwoBytes(String hexValue, String expected) {
		Codepoint codepoint = new Codepoint(hexValue);
		
		assertEquals(expected.toLowerCase(), codepoint.toUTF32().toLowerCase(), "Check UTF32 decode");
	}
}
