package edu.westga.cs3110.unicoder.tests.model.codepoint;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import edu.westga.cs3110.unicoder.model.Codepoint;

public class TestConstructor {
	@ParameterizedTest
	@ValueSource(strings = {"FFFFFFFF", "110000"})
	void testHexOutOfRange(String hexValue) {
		assertThrows(
				IllegalArgumentException.class, 
				() -> {new Codepoint(hexValue);},
				"Check if exception is thrown with out of bounds value"
		);
	}
	
	@Test
	void testHexIsEmpty() {
		assertThrows(
				IllegalArgumentException.class, 
				() -> {new Codepoint("");},
				"Check if exception is thrown when using empty string"
		);
	}
	
	@Test
	void testHexIsNull() {
		assertThrows(
				IllegalArgumentException.class, 
				() -> {new Codepoint(null);},
				"Check if exception is thrown when using null string"
		);
	}
	
	@Test
	void testHexUsesInvalidCharacters() {
		assertThrows(
				NumberFormatException.class, 
				() -> {new Codepoint("ASDF");},
				"Check if exception is thrown when using invalid characters"
		);
	}
	
	@Test
	void testHexTooLong() {
		assertThrows(
				NumberFormatException.class, 
				() -> {new Codepoint("1234567890");},
				"Check if exception is thrown when hex is too long"
		);
	}
}
