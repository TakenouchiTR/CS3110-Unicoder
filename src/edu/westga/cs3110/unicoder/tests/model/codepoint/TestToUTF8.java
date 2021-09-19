package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import edu.westga.cs3110.unicoder.model.Codepoint;

public class TestToUTF8 {
	@ParameterizedTest
	@CsvSource( {"0000,00", "007F,7F", "0044,44"} )
	void testOneByte(String hexValue, String expected) {
		Codepoint codepoint = new Codepoint(hexValue);
		
		assertEquals(expected.toLowerCase(), codepoint.toUTF8().toLowerCase(), "Check one byte UTF8");
	}
	
	@ParameterizedTest
	@CsvSource( {"0080,C280", "07FF,DFBF", "01A6,C6A6"} )
	void testTwoBytes(String hexValue, String expected) {
		Codepoint codepoint = new Codepoint(hexValue);
		
		assertEquals(expected.toLowerCase(), codepoint.toUTF8().toLowerCase(), "Check two byte UTF8");
	}
}
