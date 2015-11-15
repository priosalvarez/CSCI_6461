package csci6461;

import co.com.csci.model.Instruction;
import co.com.csci.util.BinaryUtil;

public class TrapCode {
	
	
	/*036	TRAP code	Traps to memory address 0, which contains the 
	address of a table in memory. Stores the PC+1 in memory location 2.
	 
	The table can have a maximum of 16 entries representing 16 routines 
	for user-specified instructions stored elsewhere in memory. Trap code 
	contains an index into the table, e.g. it takes values 0 – 15. When a 
	TRAP instruction is executed, it goes to the routine whose address is 
	in memory location 0, executes those instructions, and returns to the 
	instruction stored in memory location 2. The PC+1 of the TRAP instruction 
	is stored in memory location 2.
	
	*Reserved Locations:
		Memory Address 		Usage
				0			Reserved for the Trap instruction for Part III.
				1			Reserved for a machine fault (see below).
				2			Store PC for Trap
				3			Not Used
				4			Store PC for Machine Fault
				5			Not Used 

	*Machine Fault (Part III):
	An erroneous condition in the machine will cause a machine fault. The machine 
	traps to memory address 1, which contains the address of a routine to handle 
	machine faults. Your simulator must check for faults.

	The possible machine faults that are predefined are:

		ID	Fault
		0	Illegal Memory Address to Reserved Locations
		1	Illegal TRAP code
		2	Illegal Operation Code
		3	Illegal Memory Address beyond 2048 (memory installed)

	When a Trap instruction or a machine fault occurs, the processor saves the 
	current PC and MSR contents to the locations specified above, then fetches 
	the address from Location 0 (Trap) or 1 (Machine Fault) into the PC which 
	becomes the next instruction to be executed. This address will be the first 
	instruction of a routine which handles the trap or machine fault.
	
	 */
	
	public static Integer instructionTRAP(Instruction instruction, Integer trapCode) throws Throwable{
		String ea = BinaryUtil.eaCalculation(instruction);
		Integer pcDecimal = Integer.parseInt(FrontPanel.txtPc.getText(), 2);
		//Integer msr = Integer.parseInt(FrontPanel.txtmsr.getText(), 2);
		pcDecimal++;
		//Save PC content in memory location 1
		FrontPanel.setMemory(1, pcDecimal);
		//read trap code and execute routine
		if(trapCode == 0){
			//execute "Routine0";
		} else  if(trapCode == 1){
			//execute "Routine1";
		} else if(trapCode == 2){
			//execute "Routine2";
		} else if(trapCode == 3){
			//execute "Routine3";
		} else if(trapCode == 4){
			//execute "Routine4";
		} else if(trapCode == 5){
			//execute "Routine5";
		} else if(trapCode == 6){
			//execute "Routine6";
		} else if(trapCode == 7){
			//execute "Routine7";
		} else if(trapCode == 8){
			//execute "Routine8";
		} else if(trapCode == 9){
			//execute "Routine9";
		} else if(trapCode == 10){
			//execute "Routine10";
		} else if(trapCode == 11){
			//execute "Routine11";
		} else if(trapCode == 12){
			//execute "Routine12";
		} else if(trapCode == 13){
			//execute "Routine13";
		} else if(trapCode == 14){
			//execute "Routine14";
		} else if(trapCode == 15){
			//execute "Routine15";
		} else {
			//Exception
		}
		//Return pcDecimal so the program can continue its execution
		return pcDecimal;
	}	
	
	
	

}
