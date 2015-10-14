package csci6461;

import co.com.csci.model.Instruction;
import co.com.csci.util.BinaryUtil;
import csci6461.FrontPanel;

public class LoadStore {	
	
/* This method implements the Load instruction in the UI*/
	
	public static void instructionLDR(Instruction instruction) throws Throwable{
		String ea = "";
		//Verify if instruction has index
		if(instruction.getIndexNumber() == 0){
			ea = instruction.getAddress();
		} else {
			Integer addressDecimal = Integer.parseInt(instruction.getAddress(), 2);
			Integer indexDecimal = Integer.parseInt(FrontPanel.getIndex(instruction.getIndexNumber()), 2);
			//Get index and sum it with address
			Integer sum = addressDecimal + indexDecimal;
			if(sum > 31){
				throw new Throwable("FAULT");
			}
			ea = Integer.toBinaryString(sum);
		}
		
		ea = evaluateIndirectLDR(instruction, ea);
		ea = BinaryUtil.fillBinaryString(ea);
		
		FrontPanel.setRegister(instruction.getRegisterNumber(), ea);
	}
	
	
	public static void instructionSTR(Instruction instruction) throws Throwable{
		String ea = "";
		//Verify if instruction has index
		if(instruction.getIndexNumber() == 0){
			ea = FrontPanel.txtpnPc.getText();
		} else {
			Integer addressDecimal = instruction.getIntegerAddress();
			Integer indexDecimal = Integer.parseInt(FrontPanel.getIndex(instruction.getIndexNumber()), 2);
			//Get index and sum it with address
			Integer sum = addressDecimal + indexDecimal;
			if(sum > 31){
				throw new Throwable("FAULT");
			}
			ea = Integer.toBinaryString(sum);
		}
		
		Integer eaDecimal = evaluateIndirectSTR(instruction, ea);
		FrontPanel.memory[eaDecimal].setText(FrontPanel.getRegister(instruction.getRegisterNumber()));
	}
	
	
	/*
	 * This method implements the condition in which an instruction has the 
	 * indirect flag for processing.*/
	 
	public static String evaluateIndirectLDR(Instruction instruction, String ea) throws Throwable{
		try {
			if(instruction.isIndirect()){
				// Is all what is in the address or only the address part?
				Instruction indirectInstruction = new Instruction(FrontPanel.memory[Integer.parseInt(ea, 2)].getText());
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
	
	public static Integer evaluateIndirectSTR(Instruction instruction, String ea) throws Throwable{
		try {
			if(instruction.isIndirect()){
				// Is all what is in the address or only the address part?
				Instruction indirectInstruction = new Instruction(FrontPanel.memory[Integer.parseInt(ea, 2)].getText());
				//AddressPart
				ea = indirectInstruction.getAddress();
				return Integer.parseInt(ea,2);
			}
		} catch (Exception e){
			throw new Throwable("FAULT");
		}
		return Integer.parseInt(ea,2);
	}
	
	
	
	public void instructionLDA(Instruction instruction) throws Throwable{
		//TODO
		String ea = "";
		/*Load Register with Address, r = 0..3
				r <- EA
				r <- c(EA), if I bit set*/
		
		
	}
	
	public void instructionLDX(Instruction instruction) throws Throwable{
		//TODO
		String ea = "";
		/*Load Index Register from Memory, x = 1..3
				Xx <- c(EA)*/
		
		
	}
	public void instructionSTX(Instruction instruction) throws Throwable{
		//TODO
		String ea = "";
		/*Store Index Register to Memory. X = 1..3
				EA <- c(X0)
				C(EA) <- c(Xx), if I-bit set*/
		
	}
	
	
	

}
