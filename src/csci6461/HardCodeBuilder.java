package csci6461;

public class HardCodeBuilder {
	
	private static String PROGRAM_1 = "Program 1";
	private static String AMR = "AMR";
	private static String SMR = "SMR";
	private static String AIR = "AIR";
	private static String SIR = "SIR";
	private static String MLT = "MLT";
	private static String DVD = "DVD";
	private static String TRR = "TRR";
	private static String AND = "AND";	
	private static String ORR = "ORR";
	private static String NOT = "NOT";
	
	public static void loadProgram(String programName){
		
		if(programName.equals(PROGRAM_1)){
			loadProgram1();
		}
		if(programName.equals(AMR)){
			loadAMR();
		}
		if(programName.equals(SMR)){
			loadSMR();
		}
		if(programName.equals(AIR)){
			loadAIR();
		}
		if(programName.equals(SIR)){
			loadSIR();
		}
		if(programName.equals(MLT)){
			loadMLT();
		}
		if(programName.equals(DVD)){
			loadDVD();
		}
		if(programName.equals(TRR)){
			loadTRR();
		}
		if(programName.equals(AND)){
			loadAND();
		}
		if(programName.equals(ORR)){
			loadORR();
		}
		if(programName.equals(NOT)){
			loadNOT();
		}
	}

	private static void loadProgram1() {
		System.out.println("Entra");
	}
	private static void loadAMR() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.setRegister(0, "0000000000000010");
		FrontPanel.memory[6].setText("0001000000000111");
		FrontPanel.memory[7].setText("0000000000000001");
	}
	private static void loadSMR() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.setRegister(0, "0000000000000010");
		FrontPanel.memory[6].setText("0001010000000111");
		FrontPanel.memory[7].setText("0000000000000001");
	}
	private static void loadAIR() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.setRegister(0, "0000000000000010");
		FrontPanel.memory[6].setText("0001100000000111");
		FrontPanel.memory[7].setText("0000000000000001");
	}
	private static void loadSIR() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.setRegister(0, "0000000000000010");
		FrontPanel.memory[6].setText("0001110000000111");
		FrontPanel.memory[7].setText("0000000000000001");
	}
	private static void loadMLT() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.setRegister(0, "0000000000000010");
		FrontPanel.setRegister(2, "0000000000000011");
		FrontPanel.memory[6].setText("0100000000000111");
		FrontPanel.memory[7].setText("0000000000000001");
	}
	private static void loadDVD() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.setRegister(0, "0000000000000100");
		FrontPanel.setRegister(2, "0000000000000010");
		FrontPanel.memory[6].setText("0100010000000111");
		FrontPanel.memory[7].setText("0000000000000001");
	}
	private static void loadTRR() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.setRegister(0, "0000000000000010");
		FrontPanel.setRegister(2, "0000000000000010");
		FrontPanel.memory[6].setText("0100100000000111");
		FrontPanel.memory[7].setText("0000000000000001");
	}
	private static void loadAND() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.setRegister(0, "0000000000000011");
		FrontPanel.setRegister(2, "0000000000000010");
		FrontPanel.memory[6].setText("0100110000000111");
		FrontPanel.memory[7].setText("0000000000000001");
	}
	private static void loadORR() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.setRegister(0, "0000000000000011");
		FrontPanel.setRegister(2, "0000000000000010");
		FrontPanel.memory[6].setText("0101000000000111");
		FrontPanel.memory[7].setText("0000000000000001");
	}
	private static void loadNOT() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.setRegister(0, "0000000000000001");
		FrontPanel.memory[6].setText("0101010000000111");
		FrontPanel.memory[7].setText("0000000000000001");
	}


}
 