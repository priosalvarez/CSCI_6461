package csci6461;

public class HardCodeBuilder {
	
	private static String PROGRAM_1 = "Program 1";
	private static String SMR = "SMR";
	
	public static void loadProgram(String programName){
		
		if(programName.equals(PROGRAM_1)){
			loadProgram1();
		}
		if(programName.equals(SMR)){
			loadSMR();
		}
		
	}

	private static void loadProgram1() {
		System.out.println("Entra");
	}
	
	private static void loadSMR() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.setRegister(0, "0000000000000010");
		FrontPanel.memory[6].setText("0001010000000111");
		FrontPanel.memory[7].setText("0000000000000001");
	}
	

}
 