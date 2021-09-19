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
		return null;
	}
}
