package csci6461;

import co.com.csci.util.BinaryUtil;
import co.com.csci.util.FileReaderUtil;
import co.com.csci.util.InstructionEnum;

import static co.com.csci.util.InstructionEnum.*;

import javax.swing.JOptionPane;

public class HardCodeBuilder {
	
	private static String PROGRAM_1 = "Program 1";
	private static String PROGRAM_2 = "Program 2";
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
	private static String SRCs = "SRC";
	private static String RRCs = "RRC";
	private static String CHKs = "CHK";
	private static String FADDs = "FADD";
	private static String FSUBs = "FSUB";
	private static String LDFRs = "LDFR";
	private static String STFRs = "STFR";
	private static String VADDs = "VADD";
	private static String VSUBs = "VSUB";
	private static String CNVRTs = "CNVRT";
	
	public static void loadProgram(String programName){
		
		if(programName.equals(PROGRAM_1)){
			loadProgram1();
		}
		if(programName.equals(PROGRAM_2)){
			loadProgram2();
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
		if(programName.equals(SRCs)){
			loadSRC();
		}
		if(programName.equals(RRCs)){
			loadRRC();
		}
		if(programName.equals(CHKs)){
			loadCHK();
		}
		if(programName.equals(FADDs)){
			loadFADD();
		}
		if(programName.equals(FSUBs)){
			loadFSUB();
		}
		if(programName.equals(LDFRs)){
			loadLDFR();
		}
		if(programName.equals(STFRs)){
			loadSTFR();
		}
		if(programName.equals(VADDs)){
			loadVADD();
		}
		if(programName.equals(VSUBs)){
			loadVSUB();
		}
		if(programName.equals(CNVRTs)){
			loadCNVRT();
		}
				
	}

	private static void loadCNVRT() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.memory[6].setText(instUIBuilder(CNVRT, 0, 0, 7, 0));
		FrontPanel.setMemory(7, 2); //Convert the number 2 
		FrontPanel.setRegister(0, 0); //Convert from Integer to Float
		//Result 0000000000000000 2f
	}

	private static void loadVSUB() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.memory[6].setText(instUIBuilder(VSUB, 0, 0, 7, 0));
		FrontPanel.txtFR0.setText("0000000010000000"); // 3f
		FrontPanel.setMemory(7, 10);
		FrontPanel.setMemory(8, 15);
		
		FrontPanel.setMemory(10, 4);
		FrontPanel.setMemory(11, 2);
		FrontPanel.setMemory(12, 2);
		
		FrontPanel.setMemory(15, 1);
		FrontPanel.setMemory(16, 2);
		FrontPanel.setMemory(17, 1);
		
		//Result, vector with = 3, 0, 1 in pos 10, 11, 12
	}

	private static void loadVADD() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.memory[6].setText(instUIBuilder(VADD, 0, 0, 7, 0));
		FrontPanel.txtFR0.setText("0000000010000000"); // 3f
		FrontPanel.setMemory(7, 10);
		FrontPanel.setMemory(8, 15);
		
		FrontPanel.setMemory(10, 1);
		FrontPanel.setMemory(11, 1);
		FrontPanel.setMemory(12, 2);
		
		FrontPanel.setMemory(15, 1);
		FrontPanel.setMemory(16, 2);
		FrontPanel.setMemory(17, 1);
		
		//Result, vector with = 2, 3, 3 in pos 10, 11, 12
	}

	private static void loadSTFR() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.memory[6].setText(instUIBuilder(STFR, 0, 0, 7, 0));
		FrontPanel.memory[7].setText("0000000000000000"); // 0f
		FrontPanel.txtFR0.setText("0100000000000000"); // 1f
		//Result = 0100000000000000 1f
	}

	private static void loadLDFR() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.memory[6].setText(instUIBuilder(LDFR, 0, 0, 7, 0));
		FrontPanel.memory[7].setText("0100000000000000"); // 1f
		FrontPanel.txtFR0.setText(""); // 1f
		//Result = 0100000000000000 1f
	}

	private static String initProgram2(){
		//Get paragraph (428 characters)
		String paragraph = FileReaderUtil.getFile("TextProgramTwo.txt");
		//Save in memory 1000+
		int paragraphStart = 1000;
		for(int i = 0; i < paragraph.length(); i++){
			char readChar = paragraph.charAt(i);
			int asciiChar = (int) readChar;
			FrontPanel.setMemory(paragraphStart, asciiChar);
			paragraphStart++;
		}
		
		FrontPanel.txtOutput.setText(paragraph);

		//Get word
		String word = (String)JOptionPane.showInputDialog(
				null,
				"Search word: ",
				"Add",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				"");
		//Save in memory 2000+
		int wordStart = 2000;
		for(int i = 0; i < word.length(); i++){
			char readChar = word.charAt(i);
			int asciiChar = (int) readChar;
			FrontPanel.setMemory(wordStart, asciiChar);
			wordStart++;
		}
		
		//Save paragraph and word length in memory
		FrontPanel.setMemory(7, paragraph.length()+998); //Final position of paragraph letters (Windows add \n at the end)
		FrontPanel.setMemory(8, word.length()); //Number of word letters
		return word;
	}
	
	private static void loadProgram2() {
		String word = initProgram2();
		//Initialize indexes
		FrontPanel.setIndex(1, 1000);
		FrontPanel.setIndex(2, 2000);
		FrontPanel.setPc(50);
		
		FrontPanel.setMemory(9, 46); // Dot (.) ascii value
		FrontPanel.setMemory(10, 32); // Space ( ) ascii value
		FrontPanel.setMemory(14, 0); // Letters finder counter
		FrontPanel.setMemory(17, 1); // Words in sentence counter
		FrontPanel.setMemory(18, 1); // Sentence in paragraph counter
		FrontPanel.setMemory(19, 0); // ZERO
		FrontPanel.setMemory(20, 1); // ONE
		FrontPanel.setMemory(21, 64); // 1st continue
		FrontPanel.setMemory(31, 0); // ZERO FINISH
		
		FrontPanel.memory[50].setText(instUIBuilder(LDR, 0, 1, 0, 0)); //Load paragraph letter
		FrontPanel.memory[51].setText(instUIBuilder(LDR, 1, 2, 0, 0)); //Load word letter
		FrontPanel.memory[52].setText(instUIBuilder(TRR, 0, 1, 0, 0)); //Compare
		FrontPanel.memory[53].setText(instUIBuilder(JCC, 3, 0, 11, 1));//If equal to 1 jump
		FrontPanel.setMemory(11, 200);//Call to incrementParagraphAndWordAndFinderIndex()	
		incrementParagraphAndWordAndFinderIndex();
		//Else
		FrontPanel.memory[54].setText(instUIBuilder(STX, 0, 1, 12, 0));//Bring index paragraph to memory
		FrontPanel.memory[55].setText(instUIBuilder(STX, 0, 2, 13, 0));//Bring index word to memory
		FrontPanel.memory[56].setText(instUIBuilder(LDR, 2, 0, 12, 0));//Load from memory to register the indexes
		FrontPanel.memory[57].setText(instUIBuilder(LDR, 3, 0, 13, 0));//Load from memory to register the indexes
		FrontPanel.memory[58].setText(instUIBuilder(AIR, 2, 0, 1, 0));// +1
		FrontPanel.memory[59].setText(instUIBuilder(AIR, 3, 0, 1, 0));// +1
		FrontPanel.memory[60].setText(instUIBuilder(STR, 2, 0, 12, 0));//Store to memory
		FrontPanel.memory[61].setText(instUIBuilder(STR, 3, 0, 13, 0));//Store to memory
		FrontPanel.memory[62].setText(instUIBuilder(LDX, 0, 1, 12, 0));//Store to index
		FrontPanel.memory[63].setText(instUIBuilder(LDX, 0, 2, 13, 0));//Store to index
		
		FrontPanel.memory[64].setText(instUIBuilder(LDR, 0, 1, 0, 0)); //Load paragraph letter
		FrontPanel.memory[65].setText(instUIBuilder(LDR, 1, 0, 7, 0)); //Load paragraph final position
		FrontPanel.memory[66].setText(instUIBuilder(TRR, 1, 2, 0, 0)); //Compare with index
		FrontPanel.memory[67].setText(instUIBuilder(JCC, 3, 0, 15, 1));//If it's last 
		FrontPanel.setMemory(15, 300);
		//Then
		isWordFound(); // Here program will always finish if Jump
		//Else
		FrontPanel.memory[68].setText(instUIBuilder(LDR, 1, 0, 10, 0)); //Load space
		FrontPanel.memory[69].setText(instUIBuilder(TRR, 0, 1, 0, 0)); //Compare with space and letter
		FrontPanel.memory[70].setText(instUIBuilder(JCC, 3, 0, 23, 1));//If it's space
		FrontPanel.setMemory(23, 400);
		//Then
		isWordFoundSpaceReturn();
		//Else
		FrontPanel.memory[71].setText(instUIBuilder(LDR, 1, 0, 9, 0)); //Load dot(.)
		FrontPanel.memory[72].setText(instUIBuilder(TRR, 0, 1, 0, 0)); //Compare with dot and letter
		FrontPanel.memory[73].setText(instUIBuilder(JCC, 3, 0, 24, 1));//If it's dot then
		FrontPanel.setMemory(24, 500);
		//Then
		isWordFoundDotReturn();
		//Else (Go back to POS 51)
		FrontPanel.memory[74].setText(instUIBuilder(JMA, 0, 0, 30, 1));//Jump to POS 51
		FrontPanel.setMemory(30, 50); // LOOP JUMP
		startAutoProgram(word);
	}
	
	private static void startAutoProgram(String word) {
		
		while( !FrontPanel.isPC(16) && !FrontPanel.isPC(31)){
			FrontPanel.autoSingleStepClicker();
		}
		
		if(FrontPanel.isPC(16)){
			//Mostar resultado exito en consola
			Integer wordNumber = Integer.parseInt(FrontPanel.memory[17].getText(), 2);
			Integer sentenceNumber = Integer.parseInt(FrontPanel.memory[18].getText(), 2); 
			FrontPanel.txtOutput.setText("Found: " + word + " S#: "+ sentenceNumber + " W#: " + wordNumber);
		} else {
			//Mostrar resultado fin en consola
			FrontPanel.txtOutput.setText(FrontPanel.txtOutput.getText() + "\nNot found: " + word);
		}
		
	}

	private static void incrementParagraphAndWordAndFinderIndex(){
		
		FrontPanel.memory[200].setText(instUIBuilder(LDR, 2, 0, 14, 0));//Load from memory to register the counter
		FrontPanel.memory[201].setText(instUIBuilder(AIR, 2, 0, 1, 0));// +1
		FrontPanel.memory[202].setText(instUIBuilder(STR, 2, 0, 14, 0));//Store to memory the counter
		FrontPanel.memory[203].setText(instUIBuilder(STX, 0, 1, 12, 0));//Bring index paragraph to memory
		FrontPanel.memory[204].setText(instUIBuilder(STX, 0, 2, 13, 0));//Bring index word to memory
		FrontPanel.memory[205].setText(instUIBuilder(LDR, 2, 0, 12, 0));//Load from memory to register the indexes
		FrontPanel.memory[206].setText(instUIBuilder(LDR, 3, 0, 13, 0));//Load from memory to register the indexes
		FrontPanel.memory[207].setText(instUIBuilder(AIR, 2, 0, 1, 0));// +1
		FrontPanel.memory[208].setText(instUIBuilder(AIR, 3, 0, 1, 0));// +1
		FrontPanel.memory[209].setText(instUIBuilder(STR, 2, 0, 12, 0));//Store to memory
		FrontPanel.memory[210].setText(instUIBuilder(STR, 3, 0, 13, 0));//Store to memory
		FrontPanel.memory[211].setText(instUIBuilder(LDX, 0, 1, 12, 0));//Store to index
		FrontPanel.memory[212].setText(instUIBuilder(LDX, 0, 2, 13, 0));//Store to index
		FrontPanel.memory[213].setText(instUIBuilder(JMA, 0, 0, 21, 1));//Jump to 1st continue
		
	}
	
	private static void isWordFound(){
		//isWordFound()
		FrontPanel.memory[300].setText(instUIBuilder(LDR,3, 0, 14, 0));//Load from memory to register the counter
		FrontPanel.memory[301].setText(instUIBuilder(LDR, 1, 0, 8, 0));//Load from memory to the word number
		FrontPanel.memory[302].setText(instUIBuilder(TRR, 1, 3, 0, 0)); //Compare them
		FrontPanel.memory[303].setText(instUIBuilder(JCC, 3, 0, 16, 0));//If equal jump then
		FrontPanel.setMemory(16, 0);//Stop in 16 POS <- mean success!
		//Else
		FrontPanel.memory[304].setText(instUIBuilder(JMA, 0, 0, 31, 0));//Stop in 31 POS <- mean finish without finding word!
	}
	
	private static void isWordFoundSpaceReturn(){
		//isWordFound()
		FrontPanel.memory[400].setText(instUIBuilder(LDR, 3, 0, 14, 0));//Load from memory to register the counter
		FrontPanel.memory[401].setText(instUIBuilder(LDR, 1, 0, 8, 0));//Load from memory to the word number
		FrontPanel.memory[402].setText(instUIBuilder(TRR, 1, 3, 0, 0)); //Compare them
		FrontPanel.memory[403].setText(instUIBuilder(JCC, 3, 0, 16, 0));//If equal jump then
		FrontPanel.setMemory(16, 0);//Stop in 16 POS <- mean success!
		//Else
		FrontPanel.memory[404].setText(instUIBuilder(LDR, 1, 0, 17, 0)); //Load word counter
		FrontPanel.memory[405].setText(instUIBuilder(AIR, 1, 0, 1, 0));// +1
		FrontPanel.memory[406].setText(instUIBuilder(STR, 1, 0, 17, 0)); //Store word counter
		FrontPanel.memory[407].setText(instUIBuilder(LDR, 1, 0, 19, 0));//Initialize letter counter to zero
		FrontPanel.memory[408].setText(instUIBuilder(STR, 1, 0, 14, 0));//Initialize letter counter to zero
		FrontPanel.memory[409].setText(instUIBuilder(LDR, 1, 0, 26, 0));//Initialize letter index to 2000
		FrontPanel.setMemory(26, 2000);
		FrontPanel.memory[410].setText(instUIBuilder(STR, 1, 0, 13, 0));//Initialize letter index to 2000
		FrontPanel.memory[411].setText(instUIBuilder(LDX, 0, 2, 13, 0));//Initialize index index to 2000		
		//Paragraph index++
		FrontPanel.memory[412].setText(instUIBuilder(STX, 0, 1, 12, 0));//Bring index paragraph to memory
		FrontPanel.memory[413].setText(instUIBuilder(LDR, 1, 0, 12, 0));//Load from memory to register the indexes
		FrontPanel.memory[414].setText(instUIBuilder(AIR, 1, 0, 1, 0));// +1
		FrontPanel.memory[415].setText(instUIBuilder(STR, 1, 0, 12, 0));//Store to memory
		FrontPanel.memory[416].setText(instUIBuilder(LDX, 0, 1, 12, 0));//Store to index
		
		FrontPanel.memory[417].setText(instUIBuilder(JMA, 0, 0, 30, 1));//Jump to POS 51
	}
	
	private static void isWordFoundDotReturn(){
		//isWordFound()
		FrontPanel.memory[500].setText(instUIBuilder(LDR, 3, 0, 14, 0));//Load from memory to register the counter
		FrontPanel.memory[501].setText(instUIBuilder(LDR, 1, 0, 8, 0));//Load from memory to the word number
		FrontPanel.memory[502].setText(instUIBuilder(TRR, 1, 3, 0, 0)); //Compare them
		FrontPanel.memory[503].setText(instUIBuilder(JCC, 3, 0, 16, 0));//If equal jump then
		FrontPanel.setMemory(16, 0);//Stop in 16 POS <- mean success!
		//Else
		FrontPanel.memory[504].setText(instUIBuilder(LDR, 1, 0, 18, 0)); //Load sentence counter
		FrontPanel.memory[505].setText(instUIBuilder(AIR, 1, 0, 1, 0));// +1
		FrontPanel.memory[506].setText(instUIBuilder(STR, 1, 0, 18, 0)); //Store sentence counter
		
		FrontPanel.memory[507].setText(instUIBuilder(LDR, 1, 0, 19, 0));//Initialize letter counter to zero
		FrontPanel.memory[508].setText(instUIBuilder(STR, 1, 0, 14, 0));//Initialize letter counter to zero
		FrontPanel.memory[509].setText(instUIBuilder(LDR, 1, 0, 26, 0));//Initialize letter index to 2000
		FrontPanel.setMemory(26, 2000);
		FrontPanel.memory[510].setText(instUIBuilder(STR, 1, 0, 13, 0));//Initialize letter index to 2000
		FrontPanel.memory[511].setText(instUIBuilder(LDX, 0, 2, 13, 0));//Initialize index index to 2000		
		//Paragraph index++
		FrontPanel.memory[512].setText(instUIBuilder(STX, 0, 1, 12, 0));//Bring index paragraph to memory
		FrontPanel.memory[513].setText(instUIBuilder(LDR, 1, 0, 12, 0));//Load from memory to register the indexes
		FrontPanel.memory[514].setText(instUIBuilder(AIR, 1, 0, 1, 0));// +1
		FrontPanel.memory[515].setText(instUIBuilder(STR, 1, 0, 12, 0));//Store to memory
		FrontPanel.memory[516].setText(instUIBuilder(LDX, 0, 1, 12, 0));//Store to index
		//Initialize word counter to (1)
		FrontPanel.memory[517].setText(instUIBuilder(LDR, 1, 0, 20, 0));//Initialize register to one
		FrontPanel.memory[518].setText(instUIBuilder(STR, 1, 0, 17, 0));//Initialize word counter to one
		
		FrontPanel.memory[519].setText(instUIBuilder(JMA, 0, 0, 30, 1));//Jump to POS 51
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
		FrontPanel.memory[502].setText(instUIBuilder(JZ, 2, 0, 15, 1));
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
		FrontPanel.memory[542].setText(instUIBuilder(JZ, 2, 0, 21, 1));
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
		
		FrontPanel.setMemory(6, 32);
		FrontPanel.setMemory(7, 100);
		FrontPanel.setMemory(8, 16);
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
		
		FrontPanel.txtInput.setText("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 5");
		
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
		FrontPanel.setPc(6);
		FrontPanel.setRegister(0, "0000000000000010");
		FrontPanel.setRegister(2, "0000000000000010");
		FrontPanel.memory[6].setText(instUIBuilder(TRR, 0, 2, 0, 0));
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
		FrontPanel.setPc(6);
		FrontPanel.memory[6].setText(instUIBuilder(JCC, 1, 0, 8, 1));
		FrontPanel.setMemory(8, 130);
		FrontPanel.txtCc.setText("0100");
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
	private static void loadSRC() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.setRegister(0, "0000000000000110");
		FrontPanel.memory[6].setText("0110010000000111");
		FrontPanel.memory[7].setText("0000000000000001");
	}
	private static void loadRRC() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.setRegister(0, "0000000000000011");
		FrontPanel.memory[6].setText("0110100000000111");
		FrontPanel.memory[7].setText("0000000000000001");
	}
	private static void loadCHK() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.setRegister(0, "0000000000000011");
		FrontPanel.memory[6].setText("1100110000000111");
		FrontPanel.memory[7].setText("0000000000000001");
		FrontPanel.txtInput.setText("3");
	}
	private static void loadFADD() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.memory[6].setText(instUIBuilder(FADD, 0, 0, 7, 0));
		FrontPanel.setMemory(7, 1); // 1
		FrontPanel.txtFR0.setText("0100000000000000"); // 1f
		//Result = 0000000000000000 2f
	}
	private static void loadFSUB() {
		FrontPanel.txtPc.setText("0000000000000110");
		FrontPanel.memory[6].setText(instUIBuilder(FSUB, 0, 0, 7, 0));
		FrontPanel.setMemory(7, 1); // 1
		FrontPanel.txtFR0.setText("0000000000000000"); // 2f
		//Result = 0100000000000000 1f
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
 