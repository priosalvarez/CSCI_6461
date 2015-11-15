		package csci6461;

import co.com.csci.model.Instruction;
import co.com.csci.util.BinaryUtil;


/*Transfer Instructions:
The Transfer instructions change control of program execution. Conditional 
transfer instructions test the value of a register. Note R = 0…3. They have 
the same format as the Load/Store instructions.*/


public class Transfer {
	
	
	
	//Transfer Instructions
	public static void instructionJZ(Instruction instruction) throws Throwable{
		/*Jump If Zero:
			If c(r) = 0, then PC <- EA or c(EA), if I bit set;
			Else PC <- PC+1*/
		String ea = BinaryUtil.eaCalculation(instruction);

		//Convert PC from binary to decimal 
		Integer pcDecimal = Integer.parseInt(FrontPanel.txtPc.getText(), 2);


		try {
			if(Integer.parseInt(FrontPanel.getRegister(instruction.getRegisterNumber()), 2) == 0){
				FrontPanel.txtPc.setText(BinaryUtil.fillBinaryStringParam(ea, 16));
			}
			else {
				pcDecimal = pcDecimal + 1;
				FrontPanel.txtPc.setText(BinaryUtil.fillBinaryStringParam(Integer.toBinaryString(pcDecimal), 16));
			}						

		} catch (Exception e){
			throw new Throwable("FAULT");
		}			
	}
		
	public static void instructionJNE(Instruction instruction) throws Throwable{

		String ea = BinaryUtil.eaCalculation(instruction);

		/*Jump If Not Equal:
				If c(r) != 0, then PC <- EA or c(EA) , if I bit set;
				Else PC <- PC + 1*/


		//Convert PC from binary to decimal 
		Integer pcDecimal = Integer.parseInt(FrontPanel.txtPc.getText(), 2);


		try {
			if(Integer.parseInt(FrontPanel.getRegister(instruction.getRegisterNumber()), 2) != 0){
				FrontPanel.txtPc.setText(BinaryUtil.fillBinaryStringParam(ea, 16));
			}
			else {
				pcDecimal = pcDecimal + 1;
				FrontPanel.txtPc.setText(BinaryUtil.fillBinaryStringParam(Integer.toBinaryString(pcDecimal), 16));
			}						

		} catch (Exception e){
			throw new Throwable("FAULT");
		}			

	}
		
		/*
		 * Condition Code: set when arithmetic/logical operations are executed; 
		 * it has four 1-bit elements: overflow, underflow, division by zero, 
		 * equal-or-not. They may be referenced as cc(0), cc(1), cc(2), cc(3). 
		 * Or by the names OVERFLOW, UNDERFLOW, DIVZERO, EQUALORNOT
		 */
		
		/*Jump If Condition Code
		cc replaces r for this instruction
		cc takes values 0, 1, 2, 3 as above and specifies the bit in the Condition Code Register to check;
		If cc bit  = 1, PC <- EA or c(EA), if I bit set;
		Else PC <- PC + 1*/
		public static void instructionJCC(Instruction instruction) throws Throwable{
			String ea = BinaryUtil.eaCalculation(instruction);
			Integer pcDecimal = Integer.parseInt(FrontPanel.txtPc.getText(), 2);
			if( FrontPanel.txtCc.getText().charAt(instruction.getRegisterNumber()) == '1'){
				FrontPanel.txtPc.setText(BinaryUtil.fillBinaryStringParam(ea, 16));
			} else {
				pcDecimal++;
				FrontPanel.txtPc.setText(BinaryUtil.fillBinaryStringParam((Integer.toBinaryString(pcDecimal)), 16));
			}
			
		}
		
		public static void instructionJMA(Instruction instruction) throws Throwable{
			/*Unconditional Jump To Address
			PC <- EA, if I bit not set; PC <- c(EA), if I bit set
			Note: r is ignored in this instruction*/
			String ea = BinaryUtil.eaCalculation(instruction);
			//String pc = FrontPanel.txtpnPc.getText();
			try {
				FrontPanel.txtPc.setText(ea);											
			} catch (Exception e){
				throw new Throwable("FAULT");
			}
		}
		
		public Integer instructionJSR(Instruction instruction) throws Throwable{
			
			String ea = BinaryUtil.eaCalculation(instruction);
			
			/*Jump and Save Return Address:
				R3 <- PC+1;
				PC <- EA    or      PC <- c(EA), if I bit set
				R0 should contain pointer to arguments
				Argument list should end with –17777 value*/
			
			 //String pc = FrontPanel.txtpnPc.getText();
			  //Convert PC from binary to decimal 
			  Integer pcDecimal = Integer.parseInt(FrontPanel.txtPc.getText(), 2);
				
				
				try {
					if(Integer.parseInt(FrontPanel.getRegister(instruction.getRegisterNumber()), 2) == 0){
						ea = instruction.getAddress();
						
						//verify that bit is set
						if(instruction.isIndirect()){
							
							Instruction indirectInstruction = new Instruction(FrontPanel.memory[Integer.parseInt(ea, 2)].getText());						
							ea = indirectInstruction.getAddress();						
						}
					}
					else {
						pcDecimal = pcDecimal + 1;
						FrontPanel.txtPc.setText((Integer.toBinaryString(pcDecimal)));
					}						
						
				} catch (Exception e){
					throw new Throwable("FAULT");
				}
				return pcDecimal;
			
			
			
		}
		public static void instructionRFS(Instruction instruction) throws Throwable{
			
			
			/*Return From Subroutine w/ return code as Immed portion (optional) stored in the instruction’s address field. 
					R0 <- Immed; PC <-- c(R3)
					IX, I fields are ignored.*/
			
			//get content from immediate, in this case it is assuming that immediate is 10
			int immediate = 10;
			String immediateString = Integer.toString(immediate);			
			
			try {		
				
				FrontPanel.setRegister(0, immediateString);
				
				FrontPanel.txtPc.setText((FrontPanel.getRegister(3)));
				
									
					
			} catch (Exception e){
				throw new Throwable("FAULT");		
			}
			
		}
		public static void instructionSOB(Instruction instruction) throws Throwable{
			
			String ea = BinaryUtil.eaCalculation(instruction);
			
			/*Subtract One and Branch. R = 0..3
					r <- c(r) – 1
					If c(r) > 0,  
						PC <- EA; 
						if I bit set 
 							PC <-- c(EA), ;
					Else PC <- PC + 1*/			
			
			  //String pc = FrontPanel.txtpnPc.getText();
			  //Convert PC from binary to decimal 
			  Integer pcDecimal = Integer.parseInt(FrontPanel.txtPc.getText(), 2);
			  
			  FrontPanel.setRegister(instruction.getRegisterNumber(),Integer.toString(Integer.parseInt(FrontPanel.getRegister(instruction.getRegisterNumber()), 2) - 1));
				
			  
				if (Integer.parseInt(FrontPanel.getRegister(instruction.getRegisterNumber()), 2) > 0)				
					ea = instruction.getAddress();
						//verify that bit is set
					if(instruction.isIndirect()){
						
						Instruction indirectInstruction = new Instruction(FrontPanel.memory[Integer.parseInt(ea, 2)].getText());						
						ea = indirectInstruction.getAddress();						
					}
			
			else {
				pcDecimal = pcDecimal + 1;
				FrontPanel.txtPc.setText((Integer.toBinaryString(pcDecimal)));
			}						
			
			
			
		}
		public static Integer instructionJGE(Instruction instruction) throws Throwable{
			
			String ea = BinaryUtil.eaCalculation(instruction);
			
			/*Jump Greater Than or Equal To:
				If c(r) >= 0, then PC <- EA or c(EA) , if I bit set;
				Else PC <- PC + 1*/
			
			
			
			  //String pc = FrontPanel.txtpnPc.getText();
			  //Convert PC from binary to decimal 
			  Integer pcDecimal = Integer.parseInt(FrontPanel.txtPc.getText(), 2);
				
				
				try {
					if(instruction.getRegisterNumber()>=0){
						ea = FrontPanel.txtPc.getText();
						
						//verify that bit is set
						if(instruction.isIndirect()){
							
							Instruction indirectInstruction = new Instruction(FrontPanel.memory[Integer.parseInt(ea, 2)].getText());						
							ea = indirectInstruction.getAddress();						
						}
					}
					else {
						
						pcDecimal = pcDecimal + 1;					
					}						
						
				} catch (Exception e){
					throw new Throwable("FAULT");
				}
				return pcDecimal;
			
			
			
		}	
		
		public String sumBinary(String bin1, String bin2){
			Integer sum = Integer.parseInt(bin1, 2) + Integer.parseInt(bin2,2);
			return Integer.toBinaryString(sum);
		}
		
		public Integer sumBinaryToInteger(String bin1, String bin2){
			return Integer.parseInt(bin1, 2) + Integer.parseInt(bin2,2);
		}
		
		public String eaCalculation(Instruction instruction) throws Exception{
			if(!instruction.isIndirect()){
				if(!instruction.hasIndex()){
					return instruction.getAddress();
				} else {
					return sumBinary(FrontPanel.getIndex(instruction.getIndexNumber()), instruction.getAddress());
				}
			} else {
				if(!instruction.hasIndex()){
					return FrontPanel.memory[instruction.getIntegerAddress()].getText();
				} else {
					Integer eaPos = sumBinaryToInteger(FrontPanel.getIndex(instruction.getIndexNumber()), instruction.getAddress());
					return Cache.getInstance().checkCache(eaPos);
				}
			}
		}
		
	
	
	
	
	

}
