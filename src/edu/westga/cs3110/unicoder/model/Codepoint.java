package edu.westga.cs3110.unicoder.model;

/** Encode a given codepoint into UTF-8, UTF-16, or UTF-32
 * 
 * @author Shawn Carter
 * @version Fall 2021
 */
public class Codepoint {
	private static final int HIGHEST_VALUE = 0x10FFFF;
	private static final int SURROGATE_RANGE_START = 0xD800;
	private static final int SURROGATE_RANGE_END = 0xDFFF;
	private int rawData;
	
	/** Creates a new Codepoint from a specified UTF codepoint
	 * 
	 * @precondition hexValue <= 0x10FFFF && !(hexValue >= 0xD800 && hexValue < 0xDFFF)
	 * @postcondition None
	 * 
	 */
	public Codepoint(String hexValue) {
		if (hexValue == null) {
			throw new IllegalArgumentException("hexValue must not be null");
		}
		if (hexValue.isEmpty()) {
			throw new IllegalArgumentException("hexValue must not be empty");
		}
		
		this.rawData = Integer.parseInt(hexValue, 16);
		
		if (isCodepointAboveMaxValue()) {
			throw new IllegalArgumentException("Hex value must not be above 0x10FFFF.");
		}
		if (isCodepointInSurrogateRange()) {
			throw new IllegalArgumentException(
					"Hex value must not be between 0xD800 and 0xDFFF."
			);
		}
	}
	
	/** Encodes the Codepoint with UTF-8 encoding
	 * 
	 * @precondition None
	 * @postcondition None
	 * 
	 */
	public String toUTF8() {
		if (this.rawData < 0x0080) {
			return this.toOneByteUTF8();
		}
		if (this.rawData < 0x0800) {
			return this.toTwoByteUTF8();
		}
		if (this.rawData < 0x10000) {
			return this.toThreeByteUTF8();
		}
		return this.toFourByteUTF8();
	}

	private boolean isCodepointAboveMaxValue() {
		return this.rawData > HIGHEST_VALUE;
	}
	
	private boolean isCodepointInSurrogateRange() {
		return this.rawData >= SURROGATE_RANGE_START && this.rawData <= SURROGATE_RANGE_END;
	}

	private String toOneByteUTF8() {
		String result = String.format("%02X", this.rawData);
		return result;
	}
	
	private String toTwoByteUTF8() {
		String result = "";
		int formattedData  = 0b1100000010000000;
		int firstByteMask  = 0b0000011111000000;
		int secondByteMask = 0b0000000000111111;
		
		int firstByte = (firstByteMask & this.rawData);
		int secondByte = (secondByteMask & this.rawData);
		
		firstByte <<= 2;
		
		formattedData |= firstByte;
		formattedData |= secondByte;

		result = String.format("%04X", formattedData);
		
		return result;
	}
	
	private String toThreeByteUTF8() {
		String result = "";
		int formattedData  = 0b111000001000000010000000;
		int firstByteMask  = 0b000000001111000000000000;
		int secondByteMask = 0b000000000000111111000000;
		int thirdByteMask  = 0b000000000000000000111111;
		
		int firstByte = (firstByteMask & this.rawData);
		int secondByte = (secondByteMask & this.rawData);
		int thirdByte = (thirdByteMask & this.rawData);
		
		firstByte <<= 4;
		secondByte <<= 2;
		
		formattedData |= firstByte;
		formattedData |= secondByte;
		formattedData |= thirdByte;

		result = String.format("%06X", formattedData);
		
		return result;
	}
	
	private String toFourByteUTF8() {
		String result = "";
		int formattedData  = 0b11110000_10000000_10000000_10000000;
		int firstByteMask  = 0b00000000_00011100_00000000_00000000;
		int secondByteMask = 0b00000000_00000011_11110000_00000000;
		int thirdByteMask  = 0b00000000_00000000_00001111_11000000;
		int fourthByteMask = 0b00000000_00000000_00000000_00111111;
		
		int firstByte = (firstByteMask & this.rawData);
		int secondByte = (secondByteMask & this.rawData);
		int thirdByte = (thirdByteMask & this.rawData);
		int fourthByte = (fourthByteMask & this.rawData);
		
		firstByte <<= 6;
		secondByte <<= 4;
		thirdByte <<= 2;
		
		formattedData |= firstByte;
		formattedData |= secondByte;
		formattedData |= thirdByte;
		formattedData |= fourthByte;
		
		result = String.format("%02X", formattedData);
		
		return result;
	}
	
	/** Encodes the Codepoint with UTF-16 encoding
	 * 
	 * @precondition None
	 * @postcondition None
	 * 
	 */
	public String toUTF16() {
		if (this.rawData < 0x10000) {
			return toTwoByteUTF16();
		}
		
		return toFourByteUTF16();
	}
	
	private String toTwoByteUTF16() {
		String result = String.format("%04X", this.rawData); 
		return result;
	}
	
	private String toFourByteUTF16() {
		int highMask = 0b11111111110000000000;
		int lowMask  = 0b00000000001111111111;
		int p = rawData - 0x10000;
		
		int highSurrogate = 0xD800 + ((p & highMask) >> 10);
		int lowSurrogate  = 0xDC00 + (p & lowMask);
		
		highSurrogate <<= 16;
		
		int surrogateCombination = highSurrogate | lowSurrogate;
		
		String result = String.format("%08X", surrogateCombination);
		
		return result;
	}
	
	/** Encodes the Codepoint with UTF-32 encoding
	 * 
	 * @precondition None
	 * @postcondition None
	 * 
	 */
	public String toUTF32() {
		String result = String.format("%08X", this.rawData);
		return result;
	}
}

