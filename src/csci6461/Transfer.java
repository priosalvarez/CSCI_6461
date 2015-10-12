package csci6461;

import co.com.csci.model.Instruction;


/*Transfer Instructions:
The Transfer instructions change control of program execution. Conditional 
transfer instructions test the value of a register. Note R = 0…3. They have 
the same format as the Load/Store instructions.*/


public class Transfer {
	
	
	//Transfer Instructions
		public void instructionJZ(Instruction instruction) throws Throwable{
			//TODO
			String ea = "";
			
			/*Jump If Zero:
				If c(r) = 0, then PC ?? EA or c(EA), if I bit set;
				Else PC <- PC+1*/
			
			
		}
		
		public void instructionJNE(Instruction instruction) throws Throwable{
			//TODO
			String ea = "";
			
			/*Jump If Not Equal:
				If c(r) != 0, then PC ??- EA or c(EA) , if I bit set;
				Else PC <- PC + 1*/
			
		}
		public void instructionJCC(Instruction instruction) throws Throwable{
			//TODO
			String ea = "";
			
			/*Jump If Condition Code
			cc replaces r for this instruction
			cc takes values 0, 1, 2, 3 as above and specifies the bit in the Condition Code Register to check;
			If cc bit  = 1, PC ?? EA or c(EA), if I bit set;
			Else PC <- PC + 1*/
			
			
			
		}
		public void instructionJMA(Instruction instruction) throws Throwable{
			//TODO
			String ea = "";
			
			/*Unconditional Jump To Address
			PC <- EA, if I bit not set; PC <- c(EA), if I bit set
			Note: r is ignored in this instruction*/
			
			
		}
		public void instructionJSR(Instruction instruction) throws Throwable{
			//TODO
			String ea = "";
			
			/*Jump and Save Return Address:
				R3 <- PC+1;
				PC <- EA    or      PC <- c(EA), if I bit set
				R0 should contain pointer to arguments
				Argument list should end with –17777 value*/
			
			
		}
		public void instructionRFS(Instruction instruction) throws Throwable{
			//TODO
			String ea = "";
			
			/*Return From Subroutine w/ return code as Immed portion (optional) stored in the instruction’s address field. 
					R0 <- Immed; PC ?? c(R3)
					IX, I fields are ignored.*/
			
			
		}
		public void instructionSOB(Instruction instruction) throws Throwable{
			//TODO
			String ea = "";
			
			/*Subtract One and Branch. R = 0..3
					r ?? c(r) – 1
					If c(r) > 0,  PC <- EA; but PC ?? c(EA), if I bit set;
					Else PC <- PC + 1*/
			
			
		}
		public void instructionJGE(Instruction instruction) throws Throwable{
			//TODO
			String ea = "";
			
			/*Jump Greater Than or Equal To:
				If c(r) >= 0, then PC <- EA or c(EA) , if I bit set;
				Else PC <- PC + 1*/
			
			
		}	
		
	
	
	
	
	

}
