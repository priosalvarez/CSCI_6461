package co.com.csci.util;

public enum InstructionEnum {

	HALT("00"),
	LDR("01"),
	STR("02"),
	FAULT("999");
	
	private String value;
	
	InstructionEnum(String value){
		this.value = value;
	}
	
	public static InstructionEnum findInstruction(String value){
		switch(value){
			case "00":
				return HALT;
			case "01":
				return LDR;
			case "02":
				return STR;
			default:
				return FAULT;
		}
	}
	
}
