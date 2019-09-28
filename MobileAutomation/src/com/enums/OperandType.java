package com.enums;

import java.util.HashMap;

public enum OperandType {
	ZERO(0),
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9);
	
	private static HashMap<Integer, Enum<?>> map = new HashMap<Integer, Enum<?>>();
	private int value;
	
	private OperandType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	static {
		for (OperandType type : OperandType.values()) {
			map.put(type.value, type);
		}
	}
	
	public static OperandType valueOf(int operandType) {
		return (OperandType) map.get(operandType);
	}
}
