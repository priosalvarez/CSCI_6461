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
	 * Add Memory To Register, r = 0..3
	 * r-> c(r) + c(EA)
	 */
	public static void instructionAMR(Instruction instruction) throws Throwable{
		
		FrontPanel.txtCc.setText("0000");
		String ea = BinaryUtil.eaCalculation(instruction); 
		
		Integer addressDecimal = Integer.parseInt(Cache.getInstance().checkCache(ea), 2); //content of effective address
		Integer registerDecimal = Integer.parseInt(FrontPanel.getRegister(instruction.getRegisterNumber()), 2);
		Integer addAMR = registerDecimal + addressDecimal;

		//Add Register to Memory
		String resultAMR = Integer.toBinaryString(addAMR);
		FrontPanel.setRegister(instruction.getRegisterNumber(), BinaryUtil.fillBinaryStringParam(resultAMR, 16));
	}
	
	
	/*
	 * This method implements the SMR instruction in the UI
	 * Subtract Memory From Register, r = 0..3
	 * r-> c(r) – c(EA)
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
			FrontPanel.txtCc.setText("0100");
			diffSMR = 0;
		}
		String resultSMR = Integer.toBinaryString(diffSMR);
		FrontPanel.setRegister(instruction.getRegisterNumber(), BinaryUtil.fillBinaryStringParam(resultSMR, 16));
	}
	
	/*
	 * This method implements the AIR instruction in the UI
	 * IX and I are ignored in this instruction
	 * Add  Immediate to Register, r = 0..3
	 *	r -> c(r) + Immed
     *	Note:
     *	1. if Immed = 0, does nothing
	 *	2. if c(r) = 0, loads r with Immed
	 *	IX and I are ignored in this instruction
	 */
	public static void instructionAIR(Instruction instruction) throws Throwable{
		
		FrontPanel.txtCc.setText("0000");
		// Get immmediate value.
		Integer immediate = instruction.getIntegerAddress();
		// Get register value. 
		Integer registerDecimal = Integer.parseInt(FrontPanel.getRegister(instruction.getRegisterNumber()), 2);
		
		//If inmmediate is not 0
		if(immediate != 0){
			if(registerDecimal == 0){
				FrontPanel.setRegister(instruction.getRegisterNumber(), BinaryUtil.fillBinaryStringParam(Integer.toBinaryString(immediate), 16));
			} else {
				int sumAIR = registerDecimal + immediate;
				FrontPanel.setRegister(instruction.getRegisterNumber(), BinaryUtil.fillBinaryStringParam(Integer.toBinaryString(sumAIR), 16));
			}
		}
	}
	
	/*
	 * This method implements the SIR instruction in the UI
	 * IX and I are ignored in this instruction Subtract  Immediate  from Register, r = 0..3 
	 * r -> c(r) - Immed 
	 * Note: 
	 * 1. if Immed = 0, does nothing 
	 * 2. if c(r) = 0, loads r1 with –(Immed) 
	 * IX and I are ignored in this instruction
	 */
	public static void instructionSIR(Instruction instruction) throws Throwable{
		FrontPanel.txtCc.setText("0000");
		// Get immmediate value.
		Integer immediate = instruction.getIntegerAddress();
		// Get register value. 
		Integer registerDecimal = Integer.parseInt(FrontPanel.getRegister(instruction.getRegisterNumber()), 2);
		
		//If inmmediate is not 0
		if(immediate != 0){
			if(registerDecimal == 0){
				FrontPanel.setRegister(instruction.getRegisterNumber(), BinaryUtil.fillBinaryStringParam(Integer.toBinaryString(immediate), 16));
			} else {
				int diffSMR = registerDecimal - immediate;
				if(diffSMR < 0){
					FrontPanel.txtCc.setText("0100");
					diffSMR = 0;
				}
				FrontPanel.setRegister(instruction.getRegisterNumber(), BinaryUtil.fillBinaryStringParam(Integer.toBinaryString(diffSMR), 16));
			}
		}
	}


	/*
	 * This method implements the MLT instruction in the UI
	 */
	public static void instructionMLT(Instruction instruction) throws Throwable{

		//Multiply R0 by R2
		Integer RxDecimal = Integer.parseInt(FrontPanel.getRegister(instruction.getRegisterNumber()), 2);
		Integer RyDecimal = Integer.parseInt(FrontPanel.getRegister(instruction.getIndexNumber()), 2);
		
		if( (RxDecimal == 0 || RxDecimal == 2) && (RyDecimal == 0 || RyDecimal == 2) ){
			Integer productMLT = RxDecimal * RyDecimal;
			String productBinary = Integer.toBinaryString(productMLT);
	
			//Check if overflow
			if(productBinary.length() > 32){
				FrontPanel.txtCc.setText("1000");
				return;
			}
	
			productBinary = BinaryUtil.fillBinaryStringParam(productBinary, 32);
	
			//Find High Order Bit and Low Order Bit
			String hob = productBinary.substring(0, 16);
			String lob = productBinary.substring(16, 32);
	
			//Store in Rx and Rx+1
			FrontPanel.setRegister(RxDecimal, hob);
			FrontPanel.setRegister(RxDecimal + 1, lob);
		}
	}
	
	/*
	 * This method implements the DVD instruction in the UI
	 */
	public static void instructionDVD(Instruction instruction) throws Throwable{
		
		Integer RxDecimal = Integer.parseInt(FrontPanel.getRegister(instruction.getRegisterNumber()), 2);
		Integer RyDecimal = Integer.parseInt(FrontPanel.getRegister(instruction.getIndexNumber()), 2);
		
		if( (RxDecimal == 0 || RxDecimal == 2) && (RyDecimal == 0 || RyDecimal == 2) ){
			try{
				//Divide R0 by R2
				Integer quotientDVD = RxDecimal / RyDecimal;
				Integer remainderDVD = RxDecimal % RyDecimal; 
				String DVDQuotient = BinaryUtil.fillBinaryStringParam(Integer.toBinaryString(quotientDVD), 16);
				String DVDRemainder = BinaryUtil.fillBinaryStringParam(Integer.toBinaryString(remainderDVD), 16);
	
				//Store the quotient in R0 and the remainder in R1
				FrontPanel.setRegister(RxDecimal, DVDQuotient);
				FrontPanel.setRegister(RyDecimal, DVDRemainder);
			}
			//Exception Handler if the denominator was 0
			catch (ArithmeticException ae) {
				if (RyDecimal == 0){
					//set cc to 1
					FrontPanel.txtCc.setText("0010");
				}
			}
		}
	}

	/*
	 * This method implements the TRR instruction in the UI
	 */
	public static void instructionTRR(Instruction instruction) throws Throwable{

		//Test the Equality of R0 and R2
		Integer RxDecimal = Integer.parseInt(FrontPanel.getRegister(instruction.getRegisterNumber()), 2);
		Integer RyDecimal = Integer.parseInt(FrontPanel.getRegister(instruction.getIndexNumber()), 2);

		if(RxDecimal.equals(RyDecimal)){
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
			
		//Logical AND for R0 and R2
		Integer RxDecimal = Integer.parseInt(FrontPanel.getRegister(instruction.getRegisterNumber()), 2);
		Integer RyDecimal = Integer.parseInt(FrontPanel.getRegister(instruction.getIndexNumber()), 2);
		Integer AndDecimal = RxDecimal & RyDecimal;
		
		//Store the result in R0
		String ANDResult = BinaryUtil.fillBinaryStringParam(Integer.toBinaryString(AndDecimal), 16);
		FrontPanel.setRegister(RxDecimal, ANDResult);
	}

	/*
	 * This method implements the ORR instruction in the UI
	 */
	public static void instructionORR(Instruction instruction) throws Throwable{
		
		//Logical OR of R0 and R2
		Integer RxDecimal = Integer.parseInt(FrontPanel.getRegister(instruction.getRegisterNumber()), 2);
		Integer RyDecimal = Integer.parseInt(FrontPanel.getRegister(instruction.getIndexNumber()), 2);
		Integer ORRDecimal = RxDecimal | RyDecimal;
		
		//Store the result in R0
		String ORRresult = BinaryUtil.fillBinaryStringParam(Integer.toBinaryString(ORRDecimal), 16);
		FrontPanel.setRegister(RxDecimal, ORRresult);
	}
	
	/*
	 * This method implements the NOT instruction in the UI
	 */
	public static void instructionNOT(Instruction instruction) throws Throwable{

		//Logical NOT of R0
		Integer RxDecimal = Integer.parseInt(FrontPanel.getRegister(instruction.getRegisterNumber()), 2);
		Integer NOTDecimal = ~RxDecimal;
		
		//Store the result in R1
		String NOTresult = Integer.toBinaryString(NOTDecimal);
		NOTresult = NOTresult.substring(16, 32);
		FrontPanel.setRegister(RxDecimal, NOTresult);
	}
	
	/**
	 * @param instruction
	 * @throws Throwable
	 */
	public static void instructionSRC(Instruction instruction) throws Throwable{
		/*Shift Register by Count
		c(r) is shifted left (L/R =1) or right (L/R = 0) either logically (A/L = 1) or arithmetically (A/L = 0)
		XX, XXX are ignored
		Count = 0…15
		If Count = 0, no shift occurs*/
			String ea = BinaryUtil.eaCalculation(instruction); 

			Integer registerDecimal = Integer.parseInt(FrontPanel.getRegister(instruction.getRegisterNumber()), 2);
			
			//Shift Right, Count = 2
			Integer SRC = registerDecimal >> 2;
			String resultSRC = BinaryUtil.fillBinaryString(Integer.toBinaryString(SRC));
			//Arithmetic Shift
			//resultSRC = String.format("%1"+(16-resultSRC.length())+"d%s", 1, resultSRC);
			FrontPanel.setRegister(instruction.getRegisterNumber(), (resultSRC));
			
	}
	
	/**
	 * @param instruction
	 * @throws Throwable
	 */
	public static void instructionRRC(Instruction instruction) throws Throwable{
		/*Rotate Register by Count
		c(r) is rotated left (L/R = 1) or right (L/R =0) either logically (A/L =1)
		XX, XXX is ignored
		Count = 0…15
		If Count = 0, no rotate occurs*/
		
		String ea = BinaryUtil.eaCalculation(instruction); 
		Integer registerDecimal = Integer.parseInt(FrontPanel.getRegister(instruction.getRegisterNumber()), 2);
		
		//Rotate Left Count 3
		   int RRC = (registerDecimal << 3) | (registerDecimal >> (16 - 3));

		String resultRRC = BinaryUtil.fillBinaryString(Integer.toBinaryString(RRC));
		FrontPanel.setRegister(instruction.getRegisterNumber(), (resultRRC)); 
	

				
		
	}
	

}
