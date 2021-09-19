package edu.westga.cs3110.unicoder.model;

public class Codepoint {
	private int rawData;
	
	public Codepoint(String hexValue) {
		this.rawData = Integer.parseInt(hexValue, 16);
	}
	
	public String toUTF32() {
		return null;
	}
	
	public String toUTF16() {
		return null;
	}
	
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
	
	private String toOneByteUTF8() {
		String result = Integer.toHexString(this.rawData);
		result = padZeroes(result, 2);
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
		
		result = Integer.toHexString(formattedData);
		
		result = padZeroes(result, 4);
		
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
		
		result = Integer.toHexString(formattedData);
		
		result = padZeroes(result, 4);
		
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
		
		result = Integer.toHexString(formattedData);
		
		result = padZeroes(result, 4);
		
		return result;
	}
	
	private String padZeroes(String hex, int totalLength) {
		StringBuilder sb = new StringBuilder(totalLength);
		
		for (int i = 0; i < totalLength - hex.length(); ++i) {
			sb.append('0');
		}
		sb.append(hex);
		
		return sb.toString();
	}
}

