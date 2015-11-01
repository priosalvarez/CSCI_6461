package csci6461;

import co.com.csci.util.BinaryUtil;
import co.com.csci.util.InstructionEnum;

import static co.com.csci.util.InstructionEnum.*;

public class HardCodeBuilder {
	
	private static String PROGRAM_1 = "Program 1";
	private static String AMRs = "AMR";
	private static String SMRs = "SMR";
	private static String AIRs = "AIR";
	private static String SIRs = "SIR";
	private static String MLTs = "MLT";
	private static String DVDs = "DVD";
	private static String TRRs = "TRR";
	private static String ANDs = "AND";	
	private static String ORRs = "ORR";
	private static String NOTs = "NOT";
	private static String JZs = "JZ";
	private static String JNEs = "JNE";
	private static String JCCs = "JCC";
	private static String JMAs = "JMA";
	private static String JSRs = "JSR";
	private static String RFSs = "RFS";
	private static String SOBs = "SOB";
	private static String JGEs = "JGE";
	private static String INs = "IN";
	private static String OUTs = "OUT";
	private static String LDRs = "LDR";
	private static String STRs = "STR";
	private static String LDXs = "LDX";
	private static String STXs = "STX";
	private static String LDAs = "LDA";
	
	public static void loadProgram(String programName){
		
		if(programName.equals(PROGRAM_1)){
			loadProgram1();
		}
		if(programName.equals(AMRs)){
			loadAMR();
		}
		if(programName.equals(SMRs)){
			loadSMR();
		}
		if(programName.equals(AIRs)){
			loadAIR();
		}
		if(programName.equals(SIRs)){
			loadSIR();
		}
		if(programName.equals(MLTs)){
			loadMLT();
		}
		if(programName.equals(DVDs)){
			loadDVD();
		}
		if(programName.equals(TRRs)){
			loadTRR();
		}
		if(programName.equals(ANDs)){
			loadAND();
		}
		if(programName.equals(ORRs)){
			loadORR();
		}
		if(programName.equals(NOTs)){
			loadNOT();
		}
		if(programName.equals(JZs)){
			loadJZ();
		}
		if(programName.equals(JNEs)){
			loadJNE();
		}
		if(programName.equals(JCCs)){
			loadJCC();
		}
		if(programName.equals(JMAs)){
			loadJMA();
		}
		if(programName.equals(JSRs)){
			loadJSR();
		}
		if(programName.equals(RFSs)){
			loadRFS();
		}
		if(programName.equals(SOBs)){
			loadSOB();
		}
		if(programName.equals(JGEs)){
			loadJGE();
		}
		if(programName.equals(INs)){
			loadIN();
		}
		if(programName.equals(LDRs)){
			loadLDR();
		}
		if(programName.equals(STRs)){
			loadSTR();
		}
		if(programName.equals(OUTs)){
			loadOUT();
		}
		if(programName.equals(LDXs)){
			loadLDX();
		}
		if(programName.equals(STXs)){
			loadSTX();
		}
		if(programName.equals(LDAs)){
			loadLDA();
		}
				
	}

	private static void loadOUT() {
		FrontPanel.setPc(6);
		FrontPanel.memory[6].setText(instUIBuilder(OUT, 1, 0, 1, 0));
		FrontPanel.txtOutput.setText("");
		FrontPanel.setRegister(1, 97);
	}

	private static void loadSTR() {
		FrontPanel.setPc(6);
		FrontPanel.memory[6].setText(instUIBuilder(STR, 2, 0, 8, 0));
		FrontPanel.setRegister(2, 123);
	}

	private static void loadLDR() {
		FrontPanel.setPc(6);
		FrontPanel.memory[6].setText(instUIBuilder(LDR, 1, 0, 7, 0));
		FrontPanel.setMemory(7, 100);
	}

	private static void loadIN() {
		FrontPanel.setPc(6);
		FrontPanel.txtInput.setText("a");
		FrontPanel.memory[6].setText(instUIBuilder(IN, 0, 0, 0, 0));
	}
	
	private static void loadSTX() {
		FrontPanel.setPc(6);
		FrontPanel.memory[6].setText(instUIBuilder(STX, 1, 0, 7, 0));
		FrontPanel.setRegister(1, 101);
	}
	
	private static void loadLDX() {
		FrontPanel.setPc(6);
		FrontPanel.memory[6].setText(instUIBuilder(LDX, 2, 0, 8, 0));
		FrontPanel.setRegister(7, 100);
	}
	private static void loadLDA() {
		FrontPanel.setPc(6);
		FrontPanel.memory[6].setText(instUIBuilder(LDA, 2, 0, 7, 0));
		FrontPanel.setRegister(7, 100);
	}
	
	

	private static void loadProgram1() {
		
		//Init PC en 500
		FrontPanel.setPc(500);
		
		//Memory Instructions
		FrontPanel.memory[499].setText(instUIBuilder(AIR, 3, 0, 1, 0));
		FrontPanel.memory[500].setText(instUIBuilder(IN, 2, 0, 0, 0));
		FrontPanel.memory[501].setText(instUIBuilder(SMR, 2, 0, 6, 0));
		FrontPanel.memory[502].setText(instUIBuilder(JCC, 1, 0, 15, 1));
		FrontPanel.memory[503].setText(instUIBuilder(SMR, 2, 0, 8, 0));
		FrontPanel.memory[504].setText(instUIBuilder(JCC, 1, 0, 7, 1));
		FrontPanel.memory[505].setText(instUIBuilder(STR, 2, 0, 13, 0));
		FrontPanel.memory[506].setText(instUIBuilder(LDR, 2, 0, 1, 1));
		FrontPanel.memory[507].setText(instUIBuilder(LDR, 0, 0, 10, 0));
		FrontPanel.memory[508].setText(instUIBuilder(MLT, 0, 2, 0, 0));
		FrontPanel.memory[509].setText(instUIBuilder(AMR, 1, 0, 13, 0));
		FrontPanel.memory[510].setText(instUIBuilder(STR, 1, 1, 1, 0));
		FrontPanel.memory[511].setText(instUIBuilder(LDR, 2, 0, 13, 0));
		FrontPanel.memory[512].setText(instUIBuilder(SMR, 2, 0, 10, 0));
		FrontPanel.memory[513].setText(instUIBuilder(JCC, 1, 0, 11, 1));
		FrontPanel.memory[514].setText(instUIBuilder(HALT, 0, 0, 0, 0));
		FrontPanel.memory[515].setText(instUIBuilder(JZ, 3, 0, 12, 1));
		
		FrontPanel.memory[520].setText(instUIBuilder(STX, 0, 1, 16, 0));
		FrontPanel.memory[521].setText(instUIBuilder(LDR, 0, 0, 16, 0));
		FrontPanel.memory[522].setText(instUIBuilder(AIR, 0, 0, 1, 0));
		FrontPanel.memory[523].setText(instUIBuilder(STR, 0, 0, 16, 0));
		FrontPanel.memory[524].setText(instUIBuilder(LDX, 0, 1, 16, 0));
		FrontPanel.memory[525].setText(instUIBuilder(LDR, 3, 0, 17, 0));
		FrontPanel.memory[526].setText(instUIBuilder(LDR, 2, 0, 14, 0));
		FrontPanel.memory[527].setText(instUIBuilder(TRR, 2, 0, 0, 0));
		FrontPanel.memory[528].setText(instUIBuilder(JCC, 3, 0, 18, 1));
		FrontPanel.memory[529].setText(instUIBuilder(JZ, 3, 0, 19, 1));
		
		FrontPanel.memory[539].setText(instUIBuilder(AIR, 3, 0, 1, 0));
		FrontPanel.memory[540].setText(instUIBuilder(IN, 2, 0, 0, 0));
		FrontPanel.memory[541].setText(instUIBuilder(SMR, 2, 0, 6, 0));
		FrontPanel.memory[542].setText(instUIBuilder(JCC, 1, 0, 21, 1));
		FrontPanel.memory[543].setText(instUIBuilder(SMR, 2, 0, 8, 0));
		FrontPanel.memory[544].setText(instUIBuilder(JCC, 1, 0, 7, 1));
		FrontPanel.memory[545].setText(instUIBuilder(STR, 2, 0, 13, 0));
		FrontPanel.memory[546].setText(instUIBuilder(LDR, 2, 1, 1, 0));
		FrontPanel.memory[547].setText(instUIBuilder(LDR, 0, 0, 10, 0));
		FrontPanel.memory[548].setText(instUIBuilder(MLT, 0, 2, 0, 0));
		FrontPanel.memory[549].setText(instUIBuilder(AMR, 1, 0, 13, 0));
		FrontPanel.memory[550].setText(instUIBuilder(STR, 1, 0, 20, 0));
		FrontPanel.memory[551].setText(instUIBuilder(LDR, 2, 0, 13, 0));
		FrontPanel.memory[552].setText(instUIBuilder(SMR, 2, 0, 10, 0));
		FrontPanel.memory[553].setText(instUIBuilder(JCC, 1, 0, 11, 1));
		FrontPanel.memory[554].setText(instUIBuilder(HALT,0, 0, 0, 0));
		FrontPanel.memory[555].setText(instUIBuilder(JZ, 3, 0, 22, 1));
		
		FrontPanel.memory[558].setText(instUIBuilder(LDR, 2, 0, 26, 0));
		FrontPanel.memory[559].setText(instUIBuilder(LDR, 3, 0, 17, 0));
		FrontPanel.memory[560].setText(instUIBuilder(LDR, 0, 3, 1, 0));
		FrontPanel.memory[561].setText(instUIBuilder(SMR, 0, 0, 20, 0));
		FrontPanel.memory[562].setText(instUIBuilder(JCC, 1, 0, 23, 1));
		FrontPanel.memory[563].setText(instUIBuilder(STR, 0, 2, 1, 0));
		FrontPanel.memory[564].setText(instUIBuilder(AIR, 3, 0, 1, 0));
		FrontPanel.memory[565].setText(instUIBuilder(JNE, 3, 0, 24, 1));
		
		FrontPanel.memory[580].setText(instUIBuilder(LDR, 0, 0, 20, 0));
		FrontPanel.memory[581].setText(instUIBuilder(SMR, 0, 3, 1, 0));
		FrontPanel.memory[582].setText(instUIBuilder(STR, 0, 2, 1, 0));
		FrontPanel.memory[583].setText(instUIBuilder(AIR, 3, 0, 1, 0));
		FrontPanel.memory[584].setText(instUIBuilder(JNE, 3, 0, 24, 1));
		
		FrontPanel.memory[600].setText(instUIBuilder(STX, 0, 3, 16, 0));
		FrontPanel.memory[601].setText(instUIBuilder(STX, 0, 2, 27, 0));
		FrontPanel.memory[602].setText(instUIBuilder(LDR, 0, 0, 27, 0));
		FrontPanel.memory[603].setText(instUIBuilder(AIR, 0, 0, 1, 0));
		FrontPanel.memory[604].setText(instUIBuilder(STR, 0, 0, 27, 1));
		FrontPanel.memory[605].setText(instUIBuilder(LDX, 0, 2, 27, 0));
		FrontPanel.memory[606].setText(instUIBuilder(LDR, 0, 0, 16, 0));
		FrontPanel.memory[607].setText(instUIBuilder(AIR, 0, 0, 1, 0));
		FrontPanel.memory[608].setText(instUIBuilder(STR, 0, 0, 16, 0));
		FrontPanel.memory[609].setText(instUIBuilder(LDX, 0, 3, 16, 0));
		FrontPanel.memory[610].setText(instUIBuilder(LDR, 2, 0, 14, 0));
		FrontPanel.memory[611].setText(instUIBuilder(TRR, 2, 0, 0, 0));
		FrontPanel.memory[612].setText(instUIBuilder(JCC, 3, 0, 18, 1));
		FrontPanel.memory[613].setText(instUIBuilder(JNE, 3, 0, 25, 1));
		
		FrontPanel.memory[620].setText(instUIBuilder(STX, 0, 2, 27, 0));
		FrontPanel.memory[621].setText(instUIBuilder(LDR, 0, 0, 27, 0));
		FrontPanel.memory[622].setText(instUIBuilder(SMR, 0, 0, 26, 0));
		FrontPanel.memory[623].setText(instUIBuilder(STR, 0, 0, 27, 0));
		FrontPanel.memory[624].setText(instUIBuilder(LDX, 0, 2, 27, 0));
		FrontPanel.memory[625].setText(instUIBuilder(AIR, 0, 0, 1, 0));
		FrontPanel.memory[626].setText(instUIBuilder(STR, 0, 0, 11, 0));
		FrontPanel.memory[627].setText(instUIBuilder(LDX, 0, 3, 11, 0));
		FrontPanel.memory[628].setText(instUIBuilder(LDR, 3, 0, 17, 0));
		FrontPanel.memory[629].setText(instUIBuilder(LDX, 0, 1, 7, 0));
		FrontPanel.memory[630].setText(instUIBuilder(LDA, 2, 0, 20, 0));
		FrontPanel.memory[631].setText(instUIBuilder(LDR, 0, 2, 1, 0));
		FrontPanel.memory[632].setText(instUIBuilder(STX, 0, 3, 11, 0));
		FrontPanel.memory[633].setText(instUIBuilder(LDR, 1, 0, 27, 0));
		FrontPanel.memory[634].setText(instUIBuilder(AIR, 1, 0, 1, 0));
		FrontPanel.memory[635].setText(instUIBuilder(STR, 1, 0, 11, 0));
		FrontPanel.memory[636].setText(instUIBuilder(LDX, 0, 3, 11, 0));
		FrontPanel.memory[637].setText(instUIBuilder(SMR, 0, 3, 1, 0));
		FrontPanel.memory[638].setText(instUIBuilder(JCC, 1, 1, 1, 0));
		FrontPanel.memory[639].setText(instUIBuilder(LDX, 0, 2, 11, 0));
		FrontPanel.memory[640].setText(instUIBuilder(AIR, 3, 0, 1, 0));
		FrontPanel.memory[641].setText(instUIBuilder(TRR, 2, 3, 0, 0));
		FrontPanel.memory[642].setText(instUIBuilder(JCC, 3, 0, 30, 1));
		FrontPanel.memory[643].setText(instUIBuilder(JMA, 0, 0, 31, 1));
		
		FrontPanel.memory[700].setText(instUIBuilder(LDX, 0, 2, 27, 0));
		FrontPanel.memory[701].setText(instUIBuilder(LDR, 0, 0, 27, 0));
		FrontPanel.memory[702].setText(instUIBuilder(SMR, 0, 0, 19, 0));
		FrontPanel.memory[703].setText(instUIBuilder(STR, 0, 0, 29, 0));
		
		FrontPanel.memory[101].setText(instUIBuilder(AIR, 3, 0, 1, 0));
		FrontPanel.memory[102].setText(instUIBuilder(TRR, 2, 3, 0, 0));
		FrontPanel.memory[103].setText(instUIBuilder(JCC, 3, 0, 30, 1));
		FrontPanel.memory[104].setText(instUIBuilder(JMA, 0, 0, 31, 1));
		
		FrontPanel.memory[100].setText(instUIBuilder(HALT, 0, 0, 0, 0));
		
		FrontPanel.setRegister(0, 0);
		FrontPanel.setRegister(1, 0);
		FrontPanel.setRegister(2, 0);
		FrontPanel.setRegister(3, 0);
		
		FrontPanel.setIndex(1, 1000);
		FrontPanel.setIndex(2, 1500);
		FrontPanel.setIndex(3, 1000);
		
		FrontPanel.setMemory(6, 13);
		FrontPanel.setMemory(7, 100);
		FrontPanel.setMemory(8, 35);
		FrontPanel.setMemory(10, 10);
		FrontPanel.setMemory(11, 515);
		FrontPanel.setMemory(12, 499);
		//FrontPanel.setMemory(13, 13);
		FrontPanel.setMemory(14, 1020);
		FrontPanel.setMemory(15, 520);
		//FrontPanel.setMemory(16, 13);
		FrontPanel.setMemory(17, 0);
		FrontPanel.setMemory(18, 540);
		FrontPanel.setMemory(19, 500);
		//FrontPanel.setMemory(20, 500);
		FrontPanel.setMemory(21, 558);
		FrontPanel.setMemory(22, 539);
		FrontPanel.setMemory(23, 580);
		FrontPanel.setMemory(24, 600);
		FrontPanel.setMemory(25, 560);
		FrontPanel.setMemory(26, 20);
		FrontPanel.setMemory(27, 500);
		FrontPanel.setMemory(28, 500);
		//FrontPanel.setMemory(29, 500);
		FrontPanel.setMemory(30, 500);
		FrontPanel.setMemory(31, 500);
		
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
	
	private static void loadJZ() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.setRegister(0, "0000000000000010");
		FrontPanel.memory[6].setText("0001000000000111");
		FrontPanel.memory[7].setText("0000000000000001");
	}
	private static void loadJNE() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.setRegister(0, "0000000000000010");
		FrontPanel.memory[6].setText("0001010000000111");
		FrontPanel.memory[7].setText("0000000000000001");
	}
	private static void loadJCC() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.setRegister(0, "0000000000000010");
		FrontPanel.memory[6].setText("0001100000000111");
		FrontPanel.memory[7].setText("0000000000000001");
	}
	private static void loadJMA() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.setRegister(0, "0000000000000010");
		FrontPanel.memory[6].setText("0001110000000111");
		FrontPanel.memory[7].setText("0000000000000001");
	}
	private static void loadJSR() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.setRegister(0, "0000000000000010");
		FrontPanel.memory[6].setText("0100000000000111");
		FrontPanel.memory[7].setText("0000000000000001");
	}
	private static void loadRFS() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.setRegister(0, "0000000000000100");
		FrontPanel.memory[6].setText("0100010000000111");
		FrontPanel.memory[7].setText("0000000000000001");
	}
	private static void loadSOB() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.setRegister(0, "0000000000000010");
		FrontPanel.memory[6].setText("0100100000000111");
		FrontPanel.memory[7].setText("0000000000000001");
	}
	private static void loadJGE() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.setRegister(0, "0000000000000011");
		FrontPanel.memory[6].setText("0100110000000111");
		FrontPanel.memory[7].setText("0000000000000001");
	}
	
	
	

	/**
	 * Function to make easier hard code instructions to UI
	 * receiving the opCode with the instruction name and the
	 * other parameter as decimal numbers.
	 * @param opCodeEnum
	 * @param r
	 * @param ix
	 * @param address
	 * @param i
	 * @return 16 bits long binary instruction
	 */
	public static String instUIBuilder(InstructionEnum opCodeEnum,
												 Integer r, Integer ix,
												 Integer address, Integer i){
		String opCode = InstructionEnum.getValue(opCodeEnum);
		String rS = BinaryUtil.fillBinaryStringParam(Integer.toBinaryString(r), 2);
		String ixS = BinaryUtil.fillBinaryStringParam(Integer.toBinaryString(ix), 2);
		String iS = Integer.toBinaryString(i);
		String addressS = BinaryUtil.fillBinaryStringParam(Integer.toBinaryString(address), 5);
		return opCode + rS + ixS + iS + addressS; 
	}

}
 