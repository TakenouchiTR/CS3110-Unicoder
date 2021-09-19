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
}
