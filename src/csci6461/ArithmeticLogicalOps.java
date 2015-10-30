package csci6461;

import co.com.csci.model.Instruction;
import co.com.csci.util.BinaryUtil;

/*Arithmetical and Logical instructions perform most of the computational work in the machine. 

For immediate instructions, the Address portion is considered to be the Immediate value.
The condition codes are set for the arithmetic operations. The maximum value of the Immediate value is 32 (5 bits).*/



public class ArithmeticLogicalOps {
	
	

	private Object[] memory;

	/*
	 * This method implements the AMR instruction in the UI
	 */
	public static void instructionAMR(Instruction instruction) throws Throwable{
		
		FrontPanel.txtCc.setText("0000");
		String ea = BinaryUtil.eaCalculation(instruction); 
		
		Integer addressDecimal = Integer.parseInt(Cache.getInstance().checkCache(ea), 2); //content of effective address
		Integer registerDecimal = Integer.parseInt(FrontPanel.getRegister(instruction.getRegisterNumber()), 2);
		Integer addAMR = registerDecimal + addressDecimal;

		//Add Register to Memory
		String resultAMR = Integer.toBinaryString(addAMR);
		FrontPanel.setRegister(instruction.getRegisterNumber(), BinaryUtil.fillBinaryString(resultAMR));
	}
	
	
	/*
	 * This method implements the SMR instruction in the UI
	 */
	public static void instructionSMR(Instruction instruction) throws Throwable{
		
		FrontPanel.txtCc.setText("0000");
		String ea = BinaryUtil.eaCalculation(instruction);
		
		Integer addressDecimal = Integer.parseInt(Cache.getInstance().checkCache(ea), 2);
		Integer registerDecimal = Integer.parseInt(FrontPanel.getRegister(instruction.getRegisterNumber()), 2);
		//Subtract Memory from Register
		Integer diffSMR = registerDecimal - addressDecimal;
		
		//Check for Underflow
		if(diffSMR < 0){
			FrontPanel.txtCc.setText("0010");
			diffSMR = 0;
		}
		String resultSMR = Integer.toBinaryString(diffSMR);
		FrontPanel.setRegister(instruction.getRegisterNumber(), BinaryUtil.fillBinaryString(resultSMR));
	}
	/*
	 * This method implements the AIR instruction in the UI
	   IX and I are ignored in this instruction
	 */
	public static void instructionAIR(Instruction instruction) throws Throwable{
		
		FrontPanel.txtCc.setText("0000");
		//Assume the Immediate Value is 10
		Integer immediate = 10;
		Integer registerDecimal = Integer.parseInt(FrontPanel.getRegister(instruction.getRegisterNumber()), 2);
		
		if(immediate == 0){
		//No action required
			FrontPanel.getRegister(instruction.getRegisterNumber());
		}

		//If register = 0, load r with the immediate
		if(registerDecimal == 0){
		FrontPanel.setRegister(0, "0000000000001010");
			}
		
		else{
		//add immediate to register
		int sumAIR;
		sumAIR = registerDecimal + immediate;
		
		//Convert the result to binary
		String resultAIR = Integer.toBinaryString(sumAIR);
		FrontPanel.setRegister(instruction.getRegisterNumber(), BinaryUtil.fillBinaryString(resultAIR));
		
	}
	}
	
	/*
	 * This method implements the SIR instruction in the UI
	   IX and I are ignored in this instruction
	 */
	public static void instructionSIR(Instruction instruction) throws Throwable{
	
		FrontPanel.txtCc.setText("0000");
		//Assume the Immediate Value is 1
		Integer immediate = 1;
		Integer registerDecimal = Integer.parseInt(FrontPanel.getRegister(instruction.getRegisterNumber()), 2);
		
		if(immediate == 0){
		//No action required
			FrontPanel.getRegister(instruction.getRegisterNumber());
		}

		//If register = 0, load r with the immediate
		if(registerDecimal == 0){
		FrontPanel.setRegister(1, "0000000000000001");
			}
		
		else{
		//Subtract immediate from register
		int diffSIR;
		diffSIR = registerDecimal - immediate;
		
		//Check for Underflow
		if(diffSIR < 0){
			FrontPanel.txtCc.setText("0010");
			diffSIR = 0;
		}
		
		//Convert the result to binary
		String resultSIR = Integer.toBinaryString(diffSIR);
		FrontPanel.setRegister(instruction.getRegisterNumber(), BinaryUtil.fillBinaryString(resultSIR));
		
	}
	}


	/*
	 * This method implements the MLT instruction in the UI
	 */
	public static void instructionMLT(Instruction instruction) throws Throwable{
		String ea = BinaryUtil.eaCalculation(instruction);
		
	try{
		//Multiply R0 by R2

				Integer RxDecimal = Integer.parseInt(FrontPanel.getRegister(0), 2);
				Integer RyDecimal = Integer.parseInt(FrontPanel.getRegister(2), 2);
				Integer productMLT = RxDecimal * RyDecimal;
				String productBinary = Integer.toBinaryString(productMLT);
				productBinary = BinaryUtil.fillBinaryString32(productBinary);
				
				//Find High Order Bit and Low Order Bit
				String LSB = productBinary.substring(0, 16);
				String MSB = productBinary.substring(16, 32);
				  
				//Store the MSB in R0 and LSB in R1
				FrontPanel.setRegister(0, MSB);
				FrontPanel.setRegister(1, LSB);
	}
		//Overflow Flag if R0 or R1 already have content
	finally{
		if(instruction.getRegisterNumber() != null || instruction.getRegisterNumber() != null){//(1), also here '' means 'null' incompatible types check
			instruction.setCCNumber(instruction.getCCNumber(4), 0);
		}
		}
	}
	
	/*
	 * This method implements the DVD instruction in the UI
	 */
	public static void instructionDVD(Instruction instruction) throws Throwable{
		
		String ea = BinaryUtil.eaCalculation(instruction);
		
		Integer RxDecimal = Integer.parseInt(FrontPanel.getRegister(0), 2);
		Integer RyDecimal = Integer.parseInt(FrontPanel.getRegister(2), 2);
		
	try{
		//Divide R0 by R2
		Integer quotientDVD = RxDecimal / RyDecimal;
		Integer remainderDVD = RxDecimal % RyDecimal; 
		String DVDQuotient = BinaryUtil.fillBinaryString(Integer.toBinaryString(quotientDVD));
		String DVDRemainder = BinaryUtil.fillBinaryString(Integer.toBinaryString(remainderDVD));
		
		//Store the quotient in R0 and the remainder in R1
		FrontPanel.setRegister(0, DVDQuotient);
		FrontPanel.setRegister(1, DVDRemainder);
	}
	//Exception Handler if the denominator was 0
	catch (ArithmeticException ae) {
	if (RyDecimal == 0){
		//set cc to 1
		FrontPanel.txtCc.setText("0001");
    }
}
	}

	/*
	 * This method implements the TRR instruction in the UI
	 */
	public static void instructionTRR(Instruction instruction) throws Throwable{
		String ea = BinaryUtil.eaCalculation(instruction);
		
		//Test the Equality of R0 and R2
		Integer RxDecimal = Integer.parseInt(FrontPanel.getRegister(0));
		Integer RyDecimal = Integer.parseInt(FrontPanel.getRegister(2));
		
	if(RxDecimal == RyDecimal){
	//set cc to 1
		FrontPanel.txtCc.setText("0001");
	}
	else{
	//set cc to 0
		FrontPanel.txtCc.setText("0000");
	}
	}

	/*
	 * This method implements the AND instruction in the UI
	 */
	public static void instructionAND(Instruction instruction) throws Throwable{
			String ea = BinaryUtil.eaCalculation(instruction);
			
		//Logical AND for R0 and R2
		Integer RxDecimal = Integer.parseInt(FrontPanel.getRegister(0), 2);
		Integer RyDecimal = Integer.parseInt(FrontPanel.getRegister(2), 2);
		Integer AndDecimal = RxDecimal & RyDecimal;
		
		//Store the result in R0
		String ANDResult = BinaryUtil.fillBinaryString(Integer.toBinaryString(AndDecimal));
		FrontPanel.setRegister(1, ANDResult);
	}

	/*
	 * This method implements the ORR instruction in the UI
	 */
	public static void instructionORR(Instruction instruction) throws Throwable{
		String ea = BinaryUtil.eaCalculation(instruction);
		
		//Logical OR of R0 and R2
		Integer RxDecimal = Integer.parseInt(FrontPanel.getRegister(0), 2);
		Integer RyDecimal = Integer.parseInt(FrontPanel.getRegister(2), 2);
		Integer ORRDecimal = RxDecimal | RyDecimal;
		
		//Store the result in R0
		String ORRresult = BinaryUtil.fillBinaryString(Integer.toBinaryString(ORRDecimal));
		FrontPanel.setRegister(1, ORRresult);
	}
	
	/*
	 * This method implements the NOT instruction in the UI
	 */
	public static void instructionNOT(Instruction instruction) throws Throwable{
		String ea = BinaryUtil.eaCalculation(instruction);

		//Logical NOT of R0
		Integer RxDecimal = Integer.parseInt(FrontPanel.getRegister(0));
		Integer NOTDecimal = ~RxDecimal;
		
		//Store the result in R1
		String NOTresult = Integer.toBinaryString(NOTDecimal);
		NOTresult = NOTresult.substring(16, 32);
		FrontPanel.setRegister(1, NOTresult);
	}
	
	public void instructionSRC(Instruction instruction) throws Throwable{
		//TODO
				
		
	}
	
	public void instructionRRC(Instruction instruction) throws Throwable{
		//TODO
				
		
	}
	

}
