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
		return null;
	}
}
