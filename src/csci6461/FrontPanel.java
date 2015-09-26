package csci6461;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import javax.swing.JButton;
import javax.swing.JToggleButton;

import co.com.csci.model.Instruction;
import co.com.csci.util.BinaryUtil;
import co.com.csci.util.InstructionEnum;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;



public class FrontPanel {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField txtMsr;

	
	JTextPane txtpnR; // TextInput for R0
	JTextPane txtpnR_1;// TextInput for R1
	JTextPane txtpnR_2;// TextInput for R2
	
	JTextPane txtpnX;// TextInput for X1
	JTextPane txtpnX_1;// TextInput for X2
	JTextPane txtpnX_2;// TextInput for X3
	
	JTextPane[] memory; //All memory entries
	
	JTextPane txtpnPc; //PC
	JTextPane txtpnMar; //MAR
	JTextPane txtpnMbr; //MBR
	JTextPane txtpnIr;  //IR
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrontPanel window = new FrontPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrontPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//Main window
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		/*
		 * Input Area - In this area the user can input individual instructions
		 * Opcode	6 bits	Specifies one of 64 possible instructions; Not all may be defined in this project
		 * IX		2 bits	Specifies one of three index registers; may be referred to by X1 – X3. O value indicates no indexing.
		 * R		2 bits	Specifies one of four general purpose registers; may be referred to by R0 – R3
		 * I		1 bits	If I =1, specifies indirect addressing; otherwise, no indirect addressing.
		 * Address	7 bits	Specifies one of 32 locations
		 */
		
		//OpCode
		textField = new JTextField();
		textField.setBounds(44, 91, 113, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		//R
		textField_1 = new JTextField();
		textField_1.setBounds(167, 91, 44, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		//IX
		textField_2 = new JTextField();
		textField_2.setBounds(221, 91, 44, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		//I
		textField_3 = new JTextField();
		textField_3.setBounds(275, 91, 20, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		//Address
		textField_4 = new JTextField();
		textField_4.setText("1010101010101010");
		textField_4.setBounds(305, 91, 134, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		
		
		//Output area for processing messages (i.e. troubleshooting, trap messages
		
		JTextPane txtpnEc = new JTextPane();
		txtpnEc.setText("EC");
		txtpnEc.setBounds(43, 122, 396, 51);
		frame.getContentPane().add(txtpnEc);
		
		
		//Area for general output
		
		JTextPane txtrOutput = new JTextPane();
		txtrOutput.setText("Output");
		txtrOutput.setBounds(44, 203, 207, 123);
		frame.getContentPane().add(txtrOutput);
		
		//Button to trigger a single step in the process (i.e. fetch instruction etc.)
		
		//JButton btnSingleStep = new JButton("Single Step");
		//btnSingleStep.setBounds(286, 204, 153, 23);
		//frame.getContentPane().add(btnSingleStep);
		
		
		//Main Execution Area
		
		/*Button to load instruction from input or file (may have to add another feature 
		 * or create another to load instructions from a file.
		 */
		JToggleButton tglbtnLoad = new JToggleButton("Load");
		tglbtnLoad.setBounds(44, 353, 94, 23);
		frame.getContentPane().add(tglbtnLoad);
		
		JToggleButton tglbtnExecute = new JToggleButton("Execute");
		tglbtnExecute.setBounds(148, 353, 93, 23);
		frame.getContentPane().add(tglbtnExecute);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(251, 353, 89, 23);
		frame.getContentPane().add(btnSave);
		
		JButton btnNewButton = new JButton("Stop");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(350, 353, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnSingle = new JButton("Single Instruction");
		btnSingle.setBounds(286, 253, 153, 23);
		frame.getContentPane().add(btnSingle);
		
		btnSingle.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  //Get PC counter
			  String pc = txtpnPc.getText();
			  //Convert PC from binary to decimal 
			  Integer pcDecimal = Integer.parseInt(txtpnPc.getText(), 2);
			  //Get Instruction from memory space
			  String plainInstruction = memory[pcDecimal].getText();
			  Instruction instruction;
			  try
			  {
				  instruction = new Instruction(plainInstruction);
				  //Change MAR
				  txtpnMar.setText(pc);
				  //Change MBR
				  txtpnMbr.setText(plainInstruction);
				  //Change IR
				  txtpnIr.setText(plainInstruction);
				  //Evaluate instruction
				  InstructionEnum iCode = instruction.getIntructionCode();
			
				  switch(iCode){
		  		  		case LDR:
		  		  			instructionLDR(instruction);
		  		  			break;
		  		  		case STR:
		  		  			instructionSTR(instruction);
	  		  				break;
		  		  		case HALT:
		  		  			txtrOutput.setText("HALT");
			  				break;
		  		  		case FAULT:
		  		  			txtrOutput.setText("FAULT");
		  		  			break;
				  }
				  //Increment PC counter
				  if(iCode != InstructionEnum.HALT){
					  pcDecimal++;
				  }
				  txtpnPc.setText(BinaryUtil.fillBinaryString(Integer.toBinaryString(pcDecimal)));
		  	} catch (Throwable t){
		  		txtrOutput.setText("FAULT");
		  		
		  	}
		  }
		});
		
		txtMsr = new JTextField();
		txtMsr.setText("MSR");
		txtMsr.setBounds(326, 306, 113, 20);
		frame.getContentPane().add(txtMsr);
		txtMsr.setColumns(10);
		
		txtpnPc = new JTextPane();
		txtpnPc.setText("0000000000000000");
		txtpnPc.setBounds(524, 91, 120, 20);
		frame.getContentPane().add(txtpnPc);
		
		txtpnMar = new JTextPane();
		txtpnMar.setText("");
		txtpnMar.setBounds(524, 122, 120, 20);
		frame.getContentPane().add(txtpnMar);
		
		txtpnMbr = new JTextPane();
		txtpnMbr.setText("");
		txtpnMbr.setBounds(524, 153, 120, 20);
		frame.getContentPane().add(txtpnMbr);
		
		txtpnIr = new JTextPane();
		txtpnIr.setText("");
		txtpnIr.setBounds(524, 184, 120, 20);
		frame.getContentPane().add(txtpnIr);
		
		JTextPane txtpnIar = new JTextPane();
		txtpnIar.setText("IAR");
		txtpnIar.setBounds(524, 215, 120, 20);
		frame.getContentPane().add(txtpnIar);
		
		txtpnR = new JTextPane();
		txtpnR.setText("");
		txtpnR.setBounds(524, 269, 120, 20);
		frame.getContentPane().add(txtpnR);
		
		txtpnR_1 = new JTextPane();
		txtpnR_1.setText("");
		txtpnR_1.setBounds(524, 300, 120, 20);
		frame.getContentPane().add(txtpnR_1);
		
		txtpnR_2 = new JTextPane();
		txtpnR_2.setText("");
		txtpnR_2.setBounds(524, 331, 120, 20);
		frame.getContentPane().add(txtpnR_2);
		
		txtpnX = new JTextPane();
		txtpnX.setText("0000000000000001");
		txtpnX.setBounds(683, 269, 120, 20);
		frame.getContentPane().add(txtpnX);
		
		txtpnX_1 = new JTextPane();
		txtpnX_1.setText("");
		txtpnX_1.setBounds(683, 300, 120, 20);
		frame.getContentPane().add(txtpnX_1);
		
		txtpnX_2 = new JTextPane();
		txtpnX_2.setText("");
		txtpnX_2.setBounds(683, 331, 120, 20);
		frame.getContentPane().add(txtpnX_2);
		
		//StartsMemory
		memory = new JTextPane[16];
		
		JTextPane textPane = new JTextPane();
		//No indirect
		textPane.setText("0000010001000001");
		//Indirect
		//textPane.setText("0000010001100001");
		textPane.setBounds(862, 139, 120, 20);
		frame.getContentPane().add(textPane);
		memory[0] = textPane;
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("0000000001100011");
		textPane_1.setBounds(862, 170, 120, 20);
		frame.getContentPane().add(textPane_1);
		memory[1] = textPane_1;
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("");
		textPane_2.setBounds(862, 201, 120, 20);
		frame.getContentPane().add(textPane_2);
		memory[2] = textPane_2;
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setText("");
		textPane_3.setBounds(862, 232, 120, 20);
		frame.getContentPane().add(textPane_3);
		memory[3] = textPane_3;
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setText("");
		textPane_4.setBounds(862, 263, 120, 20);	
		frame.getContentPane().add(textPane_4);
		memory[4] = textPane_4;
		
		JTextPane textPane_10 = new JTextPane();
		textPane_10.setText("");
		textPane_10.setBounds(862, 294, 120, 20);
		frame.getContentPane().add(textPane_10);
		memory[5] = textPane_10;
		
		JTextPane textPane_11 = new JTextPane();
		textPane_11.setText("");
		textPane_11.setBounds(862, 325, 120, 20);
		frame.getContentPane().add(textPane_11);
		memory[6] = textPane_11;
		
		JTextPane textPane_12 = new JTextPane();
		textPane_12.setText("");
		textPane_12.setBounds(862, 356, 120, 20);
		frame.getContentPane().add(textPane_12);
		memory[7] = textPane_12;
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setText("");
		textPane_5.setBounds(1024, 139, 120, 20);
		frame.getContentPane().add(textPane_5);
		memory[8] = textPane_5;
		
		JTextPane textPane_6 = new JTextPane();
		textPane_6.setText("");
		textPane_6.setBounds(1024, 170, 120, 20);
		frame.getContentPane().add(textPane_6);
		memory[9] = textPane_6;
		
		JTextPane textPane_7 = new JTextPane();
		textPane_7.setText("");
		textPane_7.setBounds(1024, 203, 120, 20);
		frame.getContentPane().add(textPane_7);
		memory[10] = textPane_7;
		
		JTextPane textPane_8 = new JTextPane();
		textPane_8.setText("");
		textPane_8.setBounds(1024, 232, 120, 20);
		frame.getContentPane().add(textPane_8);
		memory[11] = textPane_8;
		
		JTextPane textPane_9 = new JTextPane();
		textPane_9.setText("");
		textPane_9.setBounds(1024, 263, 120, 20);
		frame.getContentPane().add(textPane_9);
		memory[12] = textPane_9;
		
		JTextPane textPane_13 = new JTextPane();
		textPane_13.setText("");
		textPane_13.setBounds(1024, 294, 120, 20);
		frame.getContentPane().add(textPane_13);
		memory[13] = textPane_13;
		
		JTextPane textPane_14 = new JTextPane();
		textPane_14.setText("");
		textPane_14.setBounds(1024, 325, 120, 20);
		frame.getContentPane().add(textPane_14);
		memory[14] = textPane_14;
		
		JTextPane textPane_15 = new JTextPane();
		textPane_15.setText("");
		textPane_15.setBounds(1024, 356, 120, 20);
		frame.getContentPane().add(textPane_15);
		memory[15] = textPane_15;
		
		JLabel lblOpcode = new JLabel("OpCode");
		lblOpcode.setBounds(44, 66, 46, 14);
		frame.getContentPane().add(lblOpcode);
		
		JLabel lblR = new JLabel("R");
		lblR.setBounds(180, 66, 20, 14);
		frame.getContentPane().add(lblR);
		
		JLabel lblIx = new JLabel("IX");
		lblIx.setBounds(233, 66, 20, 14);
		frame.getContentPane().add(lblIx);
		
		JLabel lblI = new JLabel("   I");
		lblI.setBounds(275, 66, 20, 14);
		frame.getContentPane().add(lblI);
		
		JLabel lblAddress = new JLabel("ADDRESS");
		lblAddress.setBounds(321, 66, 69, 14);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblMsr = new JLabel("MSR");
		lblMsr.setBounds(286, 312, 30, 14);
		frame.getContentPane().add(lblMsr);
		
		JLabel lblPc = new JLabel("PC");
		lblPc.setBounds(494, 97, 20, 14);
		frame.getContentPane().add(lblPc);
		
		JLabel lblMar = new JLabel("MAR");
		lblMar.setBounds(494, 128, 30, 14);
		frame.getContentPane().add(lblMar);
		
		JLabel lblMbr = new JLabel("MBR");
		lblMbr.setBounds(494, 159, 30, 14);
		frame.getContentPane().add(lblMbr);
		
		JLabel lblIr = new JLabel("IR");
		lblIr.setBounds(494, 190, 20, 14);
		frame.getContentPane().add(lblIr);
		
		JLabel lblIar = new JLabel("IAR");
		lblIar.setBounds(494, 221, 20, 14);
		frame.getContentPane().add(lblIar);
		
		JLabel lblR_1 = new JLabel("R0");
		lblR_1.setBounds(494, 275, 20, 14);
		frame.getContentPane().add(lblR_1);
		
		JLabel lblR_2 = new JLabel("R1");
		lblR_2.setBounds(494, 306, 20, 14);
		frame.getContentPane().add(lblR_2);
		
		JLabel lblR_3 = new JLabel("R2");
		lblR_3.setBounds(494, 337, 20, 14);
		frame.getContentPane().add(lblR_3);
		
		JLabel lblX = new JLabel("  X1");
		lblX.setBounds(654, 275, 30, 14);
		frame.getContentPane().add(lblX);
		
		JLabel lblX_1 = new JLabel("  X2");
		lblX_1.setBounds(654, 306, 30, 14);
		frame.getContentPane().add(lblX_1);
		
		JLabel lblX_2 = new JLabel("  X3");
		lblX_2.setBounds(654, 337, 30, 14);
		frame.getContentPane().add(lblX_2);
		
		JLabel label = new JLabel("00");
		label.setBounds(832, 145, 20, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("01");
		label_1.setBounds(832, 176, 20, 14);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("02");
		label_2.setBounds(832, 207, 20, 14);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("08");
		label_3.setBounds(998, 145, 20, 14);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("09");
		label_4.setBounds(998, 176, 20, 14);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("10");
		label_5.setBounds(998, 208, 20, 14);
		frame.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("11");
		label_6.setBounds(998, 238, 20, 14);
		frame.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("12");
		label_7.setBounds(998, 269, 20, 14);
		frame.getContentPane().add(label_7);
		
		JLabel label_8 = new JLabel("13");
		label_8.setBounds(998, 300, 20, 14);
		frame.getContentPane().add(label_8);
		
		JLabel label_9 = new JLabel("14");
		label_9.setBounds(998, 331, 20, 14);
		frame.getContentPane().add(label_9);
		
		JLabel label_10 = new JLabel("15");
		label_10.setBounds(998, 362, 20, 14);
		frame.getContentPane().add(label_10);
		
		JLabel label_11 = new JLabel("03");
		label_11.setBounds(832, 238, 20, 14);
		frame.getContentPane().add(label_11);
		
		JLabel label_12 = new JLabel("04");
		label_12.setBounds(832, 269, 20, 14);
		frame.getContentPane().add(label_12);
		
		JLabel label_13 = new JLabel("05");
		label_13.setBounds(832, 300, 20, 14);
		frame.getContentPane().add(label_13);
		
		JLabel label_14 = new JLabel("06");
		label_14.setBounds(832, 331, 20, 14);
		frame.getContentPane().add(label_14);
		
		JLabel label_15 = new JLabel("07");
		label_15.setBounds(832, 362, 20, 14);
		frame.getContentPane().add(label_15);
		
		JLabel lblCiscSimulator = new JLabel("CISC Simulator");
		lblCiscSimulator.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblCiscSimulator.setBounds(524, 11, 207, 33);
		frame.getContentPane().add(lblCiscSimulator);
		
		JLabel lblMemory = new JLabel("Memory");
		lblMemory.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMemory.setBounds(961, 89, 80, 20);
		frame.getContentPane().add(lblMemory);
		
		JLabel lblRegisters = new JLabel("Registers");
		lblRegisters.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegisters.setBounds(620, 246, 80, 20);
		frame.getContentPane().add(lblRegisters);
		
		JLabel lblR_4 = new JLabel("R3");
		lblR_4.setBounds(494, 368, 20, 14);
		frame.getContentPane().add(lblR_4);
		
		JTextPane textPane_16 = new JTextPane();
		textPane_16.setText("");
		textPane_16.setBounds(524, 362, 120, 20);
		frame.getContentPane().add(textPane_16);
		
		//This portion is for a number format converter
		//that we hope to implement in a future phase
		
		/*JRadioButton rdbtnDecimal = new JRadioButton("Decimal");
		rdbtnDecimal.setSelected(true);
		rdbtnDecimal.setBounds(676, 122, 109, 23);
		frame.getContentPane().add(rdbtnDecimal);
		
		JRadioButton rdbtnOctal = new JRadioButton("Octal");
		rdbtnOctal.setBounds(676, 153, 109, 23);
		frame.getContentPane().add(rdbtnOctal);
		
		JRadioButton rdbtnHexadecimal = new JRadioButton("Hexadecimal");
		rdbtnHexadecimal.setBounds(676, 184, 109, 23);
		frame.getContentPane().add(rdbtnHexadecimal);
		
		JLabel lblNumberFormat = new JLabel("Number Format");
		lblNumberFormat.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNumberFormat.setBounds(676, 94, 134, 20);
		frame.getContentPane().add(lblNumberFormat);*/
	}
	
	public void setRegister(int registerNum, String content){
		if(registerNum == 0){
			txtpnR.setText(content);
		} else if(registerNum == 1){
			txtpnR_1.setText(content);
		} else  if(registerNum == 2){
			txtpnR_2.setText(content);
		} else if(registerNum == 3){
//			txtpnR_3.setText(content);
		} else {
			//Exception
		}
	}
	
	public String getRegister(int registerNum){
		if(registerNum == 0){
			return txtpnR.getText();
		} else if(registerNum == 1){
			txtpnR_1.getText();
		} else  if(registerNum == 2){
			txtpnR_2.getText();
		} else if(registerNum == 3){
//			txtpnR_3.getText();
		} else {
			//Exception
		}
		return "";
	}
	
	public void setIndex(int indexNum, String content){
		if(indexNum == 1){
			txtpnX.setText(content);
		} else  if(indexNum == 2){
			txtpnX_1.setText(content);
		} else if(indexNum == 3){
			txtpnX_2.setText(content);
		} else {
			//Exception
		}
	}
	
	public String getIndex(int indexNum){
		if(indexNum == 1){
			return txtpnX.getText();
		} else  if(indexNum == 2){
			return txtpnX_1.getText();
		} else if(indexNum == 3){
			return txtpnX_2.getText();
		} else {
			//Exception
		}
		return "";
	}
	/*
	 * This method implements the condition in which an instruction has the 
	 * indirect flag for processing.
	 */
	public String evaluateIndirectLDR(Instruction instruction, String ea) throws Throwable{
		try {
			if(instruction.isIndirect()){
				// Is all what is in the address or only the address part?
				Instruction indirectInstruction = new Instruction(memory[Integer.parseInt(ea, 2)].getText());
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
	
	public Integer evaluateIndirectSTR(Instruction instruction, String ea) throws Throwable{
		try {
			if(instruction.isIndirect()){
				// Is all what is in the address or only the address part?
				Instruction indirectInstruction = new Instruction(memory[Integer.parseInt(ea, 2)].getText());
				//AddressPart
				ea = indirectInstruction.getAddress();
				return Integer.parseInt(ea,2);
			}
		} catch (Exception e){
			throw new Throwable("FAULT");
		}
		return Integer.parseInt(ea,2);
	}
	
	/*
	 * This method implements the Load instruction in the UI
	 */
	public void instructionLDR(Instruction instruction) throws Throwable{
		String ea = "";
		//Verify if instruction has index
		if(instruction.getIndexNumber() == 0){
			ea = instruction.getAddress();
		} else {
			Integer addressDecimal = Integer.parseInt(instruction.getAddress(), 2);
			Integer indexDecimal = Integer.parseInt(getIndex(instruction.getIndexNumber()), 2);
			//Get index and sum it with address
			Integer sum = addressDecimal + indexDecimal;
			if(sum > 31){
				throw new Throwable("FAULT");
			}
			ea = Integer.toBinaryString(sum);
		}
		
		ea = evaluateIndirectLDR(instruction, ea);
		ea = BinaryUtil.fillBinaryString(ea);
		
		setRegister(instruction.getRegisterNumber(), ea);
	}
	
	
	public void instructionSTR(Instruction instruction) throws Throwable{
		String content = "";
		//Verify if instruction has index
		if(instruction.getIndexNumber() == 0){
			content = getRegister(instruction.getRegisterNumber());
		} else {
			content = getRegister(instruction.getRegisterNumber());
			Integer addressDecimal = instruction.getIntegerAddress();
			Integer indexDecimal = Integer.parseInt(getIndex(instruction.getIndexNumber()), 2);
			//Get index and sum it with address
			Integer sum = addressDecimal + indexDecimal;
			if(sum > 31){
				throw new Throwable("FAULT");
			}
			content = Integer.toBinaryString(sum);
		}
		
		Integer ea = evaluateIndirectSTR(instruction, content);
		content = BinaryUtil.fillBinaryString(content);
		memory[ea].setText(content);
	}
}
