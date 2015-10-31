package co.com.csci.model;

import co.com.csci.util.InstructionEnum;

public class Instruction {
	
	/*
	 * Input Area - In this area the user can input individual instructions
	 * Opcode	6 bits	Specifies one of 64 possible instructions; Not all may be defined in this project
	 * IX		2 bits	Specifies one of three index registers; may be referred to by X1 – X3. O value indicates no indexing.
	 * R		2 bits	Specifies one of four general purpose registers; may be referred to by R0 – R3
	 * I		1 bits	If I =1, specifies indirect addressing; otherwise, no indirect addressing.
	 * Address	7 bits	Specifies one of 32 locations
	 */
	
	private String opCode;
	private String ix;
	private String r;
	private String i;
	private String address;
	private String cc;
	
	private Integer base;
	private Integer ccNumber;
	private Integer registerNumber;
	
	public Instruction(String opCode, String r, String ix, String i, String address){
		this.opCode = opCode;
		this.r = r;
		this.ix = ix;
		this.i = i;
		this.address = address;
		base = 2;
	}
	
	public Instruction(String instruction){
		this.opCode = instruction.substring(0, 6);
		this.r = instruction.substring(6, 8);
		this.ix = instruction.substring(8, 10);
		this.i = instruction.substring(10, 11);
		this.address = instruction.substring(11);
		base = 2;
	}

	public String getOpCode() {
		return opCode;
	}

	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}

	public String getIx() {
		return ix;
	}

	public void setIx(String ix) {
		this.ix = ix;
	}
	
	public Integer getIndexNumber() {
		return Integer.parseInt(ix, 2);
	}
	
	public boolean hasIndex(){
		int index = Integer.parseInt(ix, 2);
		return index == 0 ? false : true;
	}

	public String getR() {
		return r;
	}
	
	public Integer getRegisterNumber() {
		return Integer.parseInt(r, 2);
	}

	public void setR(String r) {
		this.r = r;
	}
	
	public Integer setR(Integer registerNumber) {
		
		return Integer.parseInt(r, 2);
	}

	public String getI() {
		return i;
	}
	
	public Boolean isIndirect(){
		return (getI().equals("1") ? true : false);
	}

	public void setI(String i) {
		this.i = i;
	}

	public String getAddress() {
		return address;
	}
	
	public Integer getIntegerAddress() {
		return Integer.parseInt(address);
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getBase() {
		return base;
	}

	public void setBase(Integer base) {
		this.base = base;
	}
	
	@Override
	public String toString() {
		return "Instruction [opCode=" + opCode + ", ix=" + ix + ", r=" + r + ", i=" + i + ", address=" + address
				+ ", base=" + base + "]";
	} 
	
	public String getBinaryInstruction(){
		return opCode + r + ix + i + address;
	}
	
	public Integer getCCNumber(int j) {
		return Integer.parseInt(cc, 2);
	}
	
	public void setCCNumber(Integer ccNumber, int j) {
		this.ccNumber = ccNumber;
	}
	
	
	public InstructionEnum getIntructionCode(){
		String part1 = opCode.substring(0, 3);
		String part2 = opCode.substring(3);
		int decimalValue = Integer.parseInt(part1, 2);
		int decimalValue2 = Integer.parseInt(part2, 2);
		return InstructionEnum.findInstruction(decimalValue + "" + decimalValue2);
	}

	
	

}
