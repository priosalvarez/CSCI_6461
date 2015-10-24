package csci6461;

public class HardCodeBuilder {
	
	private static String PROGRAM_1 = "Program 1";
	
	public static void loadProgram(String programName){
		
		if(programName.equals(PROGRAM_1)){
			loadProgram1();
		}
		
	}

	private static void loadProgram1() {
		System.out.println("Entra");
	}
	

}
 