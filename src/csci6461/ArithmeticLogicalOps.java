package csci6461;

import co.com.csci.model.Instruction;
import co.com.csci.util.BinaryUtil;

/*Arithmetical and Logical instructions perform most of the computational work in the machine. 

For immediate instructions, the Address portion is considered to be the Immediate value.
The condition codes are set for the arithmetic operations. The maximum value of the Immediate value is 32 (5 bits).*/


public class ArithmeticLogicalOps {
	
	public String evaluateIndirectAMR(Instruction instruction, String ea) throws Throwable{
		try {
			if(instruction.isIndirect()){
				// Is all what is in the address or only the address part?
				Instruction indirectInstruction = new Instruction(memory[Integer.parseInt(ea, 2)].getText());
				//AddressPart
				ea = indirectInstruction.getAddress();
				// else
				//ea = indirectInstruction.getBinaryInstruction();
			}
		} catch (Exception e){
			throw new Throwable("FAULT");
		}
		return ea;
	}
		
	/*
	 * This method implements the AMR instruction in the UI
	 */
	public void instructionAMR(Instruction instruction) throws Throwable{
		String ea = "";
		//Verify if instruction has index
		if(instruction.getIndexNumber() == 0){
			ea = instruction.getAddress();
		} else {
			Integer addressDecimal = Integer.parseInt(instruction.getAddress(), 2);
			Integer indexDecimal = Integer.parseInt(getIndex(instruction.getIndexNumber()), 2);
			//Get index and sum it with address
			Integer sum = addressDecimal + indexDecimal;
			if(sum > 31){ 
				throw new Throwable("FAULT");
			}
			ea = Integer.toBinaryString(sum);  
		}
		
		ea = evaluateIndirectAMR(instruction, ea); 
		ea = BinaryUtil.fillBinaryString(ea); 
		
		Integer addressDecimal = Integer.parseInt(instruction.getAddress(), 2);
		Integer registerDecimal = Integer.parseInt(getRegister(instruction.getRegisterNumber()), 2);
		Integer sumAMR = registerDecimal + addressDecimal;
		
		//Add Memory to Register
		ea = Integer.toBinaryString(sumAMR);
		setRegister(instruction.getRegisterNumber(), ea);
	}
	

	public String evaluateIndirectSMR(Instruction instruction, String ea) throws Throwable{
		try {
			if(instruction.isIndirect()){
				// Is all what is in the address or only the address part?
				Instruction indirectInstruction = new Instruction(memory[Integer.parseInt(ea, 2)].getText());
				//AddressPart
				ea = indirectInstruction.getAddress();
				// else
				//ea = indirectInstruction.getBinaryInstruction();
			}
		} catch (Exception e){
			throw new Throwable("FAULT");
		}
		return ea;
	}
	
		
	/*
	 * This method implements the SMR instruction in the UI
	 */
	public void instructionSMR(Instruction instruction) throws Throwable{
		String ea = "";
		//Verify if instruction has index
		if(instruction.getIndexNumber() == 0){
			ea = instruction.getAddress();
		} else {
			Integer addressDecimal = Integer.parseInt(instruction.getAddress(), 2);
			Integer indexDecimal = Integer.parseInt(getIndex(instruction.getIndexNumber()), 2);
			//Get index and sum it with address
			Integer sum = addressDecimal + indexDecimal; 
			if(sum > 31){ 
				throw new Throwable("FAULT");
			}
			ea = Integer.toBinaryString(sum);
		}
		
		ea = evaluateIndirectSMR(instruction, ea);
		ea = BinaryUtil.fillBinaryString(ea);
		
		Integer addressDecimal = Integer.parseInt(instruction.getAddress(), 2);
		Integer registerDecimal = Integer.parseInt(getRegister(instruction.getRegisterNumber()), 2);
		Integer diffAMR = registerDecimal - addressDecimal;
		
		//Subtract Memory from Register
		ea = Integer.toBinaryString(diffAMR);
		setRegister(instruction.getRegisterNumber(), ea);
	}
	/*
	 * This method implements the AIR instruction in the UI
	   IX and I are ignored in this instruction
	 */
	public void instructionAIR(Instruction instruction) throws Throwable{
		String ea = "";
		//Assume the Immediate Value is 10
		Integer immediate = 10;
		Integer registerDecimal = Integer.parseInt(getRegister(instruction.getRegisterNumber()), 2);
		
		if(immediate == 0){
		//No action required
		getRegister(instruction.getRegisterNumber());
		}

		if(registerDecimal == 0){
			
		//Subtract Immediate Value to Register
		int sumAIR;
		sumAIR = registerDecimal + immediate;
		
		//Convert the result to binary
		ea = Integer.toBinaryString(sumAIR);
		setRegister(instruction.getRegisterNumber(), ea);
		
	}
	}
	
	/*
	 * This method implements the SIR instruction in the UI
	   IX and I are ignored in this instruction
	 */
	public void instructionSIR(Instruction instruction) throws Throwable{
		String ea = "";
		//Assume the Immediate Value is 10
		Integer immediate = 10;
		Integer registerDecimal = Integer.parseInt(getRegister(instruction.getRegisterNumber()), 2);
		
		if(immediate == 0){
		//No action required
		getRegister(instruction.getRegisterNumber());
		}

		if(registerDecimal == 0){
			
		//Subtract Immediate Value to Register
		int diffSIR;
		diffSIR = registerDecimal - immediate;
		
		//Convert the result to binary
		ea = Integer.toBinaryString(diffSIR);
		setRegister(instruction.getRegisterNumber(), ea);
	}
}


	public String evaluateIndirectMLT(Instruction instruction, String ea) throws Throwable{
		try {
			if(instruction.isIndirect()){
				// Is all what is in the address or only the address part?
				Instruction indirectInstruction = new Instruction(memory[Integer.parseInt(ea, 2)].getText());
				//AddressPart
				ea = indirectInstruction.getAddress();
				// else
				//ea = indirectInstruction.getBinaryInstruction();
			}
		} catch (Exception e){
			throw new Throwable("FAULT");
		}
		return ea;
	}
	
	/*
	 * This method implements the MLT instruction in the UI
	 */
	public void instructionMLT(Instruction instruction) throws Throwable{
		String ea = "";
		//Verify if instruction has index
		if(instruction.getIndexNumber() == 0){
			ea = instruction.getAddress();
		} else {
			Integer addressDecimal = Integer.parseInt(instruction.getAddress(), 2);
			Integer indexDecimal = Integer.parseInt(getIndex(instruction.getIndexNumber()), 2);
			//Get index and sum it with address
			Integer sum = addressDecimal + indexDecimal;
			if(sum > 31){
				throw new Throwable("FAULT");
			}
			ea = Integer.toBinaryString(sum);
		}
		ea = evaluateIndirectMLT(instruction, ea);
		ea = BinaryUtil.fillBinaryString(ea); 
		
	try{
		//Multiply R0 by R2
		Integer RxDecimal = Integer.parseInt(getRegister(0));
		Integer RyDecimal = Integer.parseInt(getRegister(2));
		Integer productMLT = RxDecimal * RyDecimal;
		
		//Find High Order Bit and Low Order Bit
		Integer MSB = Integer.highestOneBit(productMLT);
		Integer LSB = Integer.lowestOneBit(productMLT);
		
		//Store the MSB in R0 and LSB in R1
		String eaMSB = Integer.toBinaryString(MSB);
		String eaLSB = Integer.toBinaryString(LSB);
		setRegister(instruction.getRegisterNumber(0), eaMSB);
		setRegister(instruction.getRegisterNumber(1), eaLSB);
	}
	//Overflow Flag if R0 or R1 already have content
	catch{
		if(instruction.getRegisterNumber(0) != "" || instruction.getRegisterNumber(1) != ""){
		setCC(instruction.getCCNum(4), 0);
		}
		}
	}
	
	

	public String evaluateIndirectDVD(Instruction instruction, String ea) throws Throwable{
		try {
			if(instruction.isIndirect()){
				// Is all what is in the address or only the address part?
				Instruction indirectInstruction = new Instruction(memory[Integer.parseInt(ea, 2)].getText());
				//AddressPart
				ea = indirectInstruction.getAddress();
				// else
				//ea = indirectInstruction.getBinaryInstruction();
			}
		} catch (Exception e){
			throw new Throwable("FAULT");
		}
		return ea;
	}
	/*
	 * This method implements the DVD instruction in the UI
	 */
	public void instructionDVD(Instruction instruction) throws Throwable{
		String ea = "";
		//Verify if instruction has index
		if(instruction.getIndexNumber() == 0){
			ea = instruction.getAddress();
		} else {
			Integer addressDecimal = Integer.parseInt(instruction.getAddress(), 2);
			Integer indexDecimal = Integer.parseInt(getIndex(instruction.getIndexNumber()), 2);
			//Get index and sum it with address
			Integer sum = addressDecimal + indexDecimal;
			if(sum > 31){
				throw new Throwable("FAULT");
			}
			ea = Integer.toBinaryString(sum);
		}
		ea = evaluateIndirectDVD(instruction, ea);
		ea = BinaryUtil.fillBinaryString(ea); 
		
		Integer RxDecimal = Integer.parseInt(getRegister(0));
		Integer RyDecimal = Integer.parseInt(getRegister(2));
		
	try{
		//Divide R0 by R2
		Integer quotientDVD = RxDecimal / RyDecimal;
		Integer remainderDVD = RxDecimal % RyDecimal; 
		
		//Store the quotient in Rx and the remainder in Rx+1
		String eaQuotient = Integer.toBinaryString(quotientDVD);
		String eaRemainder = Integer.toBinaryString(remainderDVD);
		setRegister(instruction.getRegisterNumber(), eaQuotient);
		setRegister(instruction.getRegisterNumber(), eaRemainder);
	}
	//Exception Handler if the denominator was 0
	catch (ArithmeticException ae) {
	if (RyDecimal == 0){
		//set cc(3) to 1
		setCC(instruction.getCCNum(3), 1);
    }
}

	}
	

	public String evaluateIndirectTRR(Instruction instruction, String ea) throws Throwable{
		try {
			if(instruction.isIndirect()){
				// Is all what is in the address or only the address part?
				Instruction indirectInstruction = new Instruction(memory[Integer.parseInt(ea, 2)].getText());
				//AddressPart
				ea = indirectInstruction.getAddress();
				// else
				//ea = indirectInstruction.getBinaryInstruction();
			}
		} catch (Exception e){
			throw new Throwable("FAULT");
		}
		return ea;
	}
	
	/*
	 * This method implements the TRR instruction in the UI
	 */
	public void instructionTRR(Instruction instruction) throws Throwable{
		String ea = "";
		//Verify if instruction has index
		if(instruction.getIndexNumber() == 0){
			ea = instruction.getAddress();
		} else {
			Integer addressDecimal = Integer.parseInt(instruction.getAddress(), 2);
			Integer indexDecimal = Integer.parseInt(getIndex(instruction.getIndexNumber()), 2);
			//Get index and sum it with address
			Integer sum = addressDecimal + indexDecimal;
			if(sum > 31){
				throw new Throwable("FAULT");
			}
			ea = Integer.toBinaryString(sum);
		}
		ea = evaluateIndirectTRR(instruction, ea);
		ea = BinaryUtil.fillBinaryString(ea); 
		
		//Test the Equality of R0 and R2
		Integer RxDecimal = Integer.parseInt(getRegister(0));
		Integer RyDecimal = Integer.parseInt(getRegister(2));
		
	if(RxDecimal == RyDecimal){
	//set cc(3) to 1
		setCC(instruction.getCCNum(3), 1);
	}
	else{
	//set cc(4) to 0
		setCC(instruction.getCCNum(4), 0);
	}
	}
	

	public String evaluateIndirectAND(Instruction instruction, String ea) throws Throwable{
		try {
			if(instruction.isIndirect()){
				// Is all what is in the address or only the address part?
				Instruction indirectInstruction = new Instruction(memory[Integer.parseInt(ea, 2)].getText());
				//AddressPart
				ea = indirectInstruction.getAddress();
				// else
				//ea = indirectInstruction.getBinaryInstruction();
			}
		} catch (Exception e){
			throw new Throwable("FAULT");
		}
		return ea;
	}
	
	/*
	 * This method implements the AND instruction in the UI
	 */
	public void instructionAND(Instruction instruction) throws Throwable{
		String ea = "";
		//Verify if instruction has index
		if(instruction.getIndexNumber() == 0){
			ea = instruction.getAddress();
		} else {
			Integer addressDecimal = Integer.parseInt(instruction.getAddress(), 2);
			Integer indexDecimal = Integer.parseInt(getIndex(instruction.getIndexNumber()), 2);
			//Get index and sum it with address
			Integer sum = addressDecimal + indexDecimal;
			if(sum > 31){
				throw new Throwable("FAULT");
			}
			ea = Integer.toBinaryString(sum);
		}
		ea = evaluateIndirectAND(instruction, ea);
		ea = BinaryUtil.fillBinaryString(ea); 
		
		//Logical AND for R0 and R2
		Integer RxDecimal = Integer.parseInt(getRegister(0));
		Integer RyDecimal = Integer.parseInt(getRegister(2));
		Integer AndDecimal = RxDecimal & RyDecimal;
		
		//Store the result in R0
		ea = Integer.toBinaryString(AndDecimal);
		setRegister(instruction.getRegisterNumber(), ea);
	}
	

	public String evaluateIndirectORR(Instruction instruction, String ea) throws Throwable{
		try {
			if(instruction.isIndirect()){
				// Is all what is in the address or only the address part?
				Instruction indirectInstruction = new Instruction(memory[Integer.parseInt(ea, 2)].getText());
				//AddressPart
				ea = indirectInstruction.getAddress();
				// else
				//ea = indirectInstruction.getBinaryInstruction();
			}
		} catch (Exception e){
			throw new Throwable("FAULT");
		}
		return ea;
	}
	
	/*
	 * This method implements the ORR instruction in the UI
	 */
	public void instructionORR(Instruction instruction) throws Throwable{
		String ea = "";
		//Verify if instruction has index
		if(instruction.getIndexNumber() == 0){
			ea = instruction.getAddress();
		} else {
			Integer addressDecimal = Integer.parseInt(instruction.getAddress(), 2);
			Integer indexDecimal = Integer.parseInt(getIndex(instruction.getIndexNumber()), 2);
			//Get index and sum it with address
			Integer sum = addressDecimal + indexDecimal;
			if(sum > 31){
				throw new Throwable("FAULT");
			}
			ea = Integer.toBinaryString(sum);
		}
		ea = evaluateIndirectORR(instruction, ea);
		ea = BinaryUtil.fillBinaryString(ea); 
		
		//Logical OR of R0 and R2
		Integer RxDecimal = Integer.parseInt(getRegister(0));
		Integer RyDecimal = Integer.parseInt(getRegister(2));
		Integer ORRDecimal = RxDecimal ^ RyDecimal;
		
		//Store the result in R0
		ea = Integer.toBinaryString(ORRDecimal);
		setRegister(instruction.getRegisterNumber(), ea);
	}
	

	public String evaluateIndirectNOT(Instruction instruction, String ea) throws Throwable{
		try {
			if(instruction.isIndirect()){
				// Is all what is in the address or only the address part?
				Instruction indirectInstruction = new Instruction(memory[Integer.parseInt(ea, 2)].getText());
				//AddressPart
				ea = indirectInstruction.getAddress();
				// else
				//ea = indirectInstruction.getBinaryInstruction();
			}
		} catch (Exception e){
			throw new Throwable("FAULT");
		}
		return ea;
	}
	
	/*
	 * This method implements the NOT instruction in the UI
	 */
	public void instructionNOT(Instruction instruction) throws Throwable{
		String ea = "";
		//Verify if instruction has index
		if(instruction.getIndexNumber() == 0){
			ea = instruction.getAddress();
		} else {
			Integer addressDecimal = Integer.parseInt(instruction.getAddress(), 2);
			Integer indexDecimal = Integer.parseInt(getIndex(instruction.getIndexNumber()), 2);
			//Get index and sum it with address
			Integer sum = addressDecimal + indexDecimal;
			if(sum > 31){
				throw new Throwable("FAULT");
			}
			ea = Integer.toBinaryString(sum);
		}
		ea = evaluateIndirectNOT(instruction, ea);
		ea = BinaryUtil.fillBinaryString(ea); 
		
		//Multiply R0 by R2
		Integer RxDecimal = Integer.parseInt(getRegister(2));
		Integer NOTDecimal = ~RxDecimal;
		
		//Store the result in R0
		ea = Integer.toBinaryString(NOTDecimal);
		setRegister(instruction.getRegisterNumber(), ea);
	}
	
	
	
	public void instructionSRC(Instruction instruction) throws Throwable{
		//TODO
				
		
	}
	
	public void instructionRRC(Instruction instruction) throws Throwable{
		//TODO
				
		
	}
	

}
