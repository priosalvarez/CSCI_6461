package co.com.csci.util;

public enum InstructionEnum {

	HALT("00"),
	LDR("01"),
	STR("02"),
	
	//Load/Store Instructions
	LDA("03"),
	LDX("41"),	
	STX("42"),
	//Transfer Instructions
	JZ("10"),
	JNE("11"),
	JCC("12"),
	JMA("13"),
	JSR("14"),
	RFS("15"),
	SOB("16"),
	JGE("17"),		
	//Arithmetic and Logical Ops
	AMR("04"),
	SMR("05"),
	AIR("06"),
	SIR("07"),
	MLT("020"),//
	DVD("021"),
	TRR("022"),
	AND("023"),
	ORR("024"),
	NOT("025"),		
	SRC("031"),
	RRC("032"),
	//Floating Point/Vector Ops
	FADD("033"),
	FSUB("034"),
	VADD("035"),
	VSUB("036"),
	CNVRT("037"),
	LDFR("050"),
	STFR("051"),
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
			case "10":
				return JZ;
			case "11":
				return JNE;
			case "12":
				return JCC;
			case "13":
				return JMA;
			case "14":
				return JSR;
			case "15":
				return RFS;
			case "16":
				return SOB;
			case "17":
				return JGE;			
			case "20":
				return MLT;
			case "21":
				return DVD;
			case "22":
				return TRR;
			case "23":
				return AND;
			case "24":
				return ORR;
			case "25":
				return NOT;
			case "31":
				return SRC;
			case "32":
				return RRC;
			case "33":
				return FADD;
			case "34":
				return FSUB;
			case "35":
				return VADD;
			case "36":
				return VSUB;
			case "37":
				return CNVRT;
			case "50":
				return LDFR;
			case "51":
				return STFR;
			default:
				return FAULT;
		}
	}
	
}
