package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import edu.westga.cs3110.unicoder.model.Codepoint;

public class TestToUTF16 {
	@ParameterizedTest
	@CsvSource( {"0000,0000", "D7FF,D7FF", "E000,E000", "FFFF,FFFF"} )
	void testTwoBytes(String hexValue, String expected) {
		Codepoint codepoint = new Codepoint(hexValue);
		
		try {
			assertEquals(expected.toLowerCase(), codepoint.toUTF16().toLowerCase(), "Check two byte UTF16");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@ParameterizedTest
	@ValueSource( strings = {"D800", "DFFF"} )
	void testInvalidBytes(String hexValue) {
		Codepoint codepoint = new Codepoint(hexValue);
		
		assertThrows(
				Exception.class, 
				() -> {codepoint.toUTF16();},
				"Check if exception is thrown when using an illegal UTF16 value"
		);
	}
	
	@ParameterizedTest
	@CsvSource( {"10000,D800DC00", "10FFFF,DBFFDFFF", "106DF4,DBDBDDF4"} )
	void testFourBytes(String hexValue, String expected) {
		Codepoint codepoint = new Codepoint(hexValue);
		
		try {
			assertEquals(expected.toLowerCase(), codepoint.toUTF16().toLowerCase(), "Check four byte UTF16");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}