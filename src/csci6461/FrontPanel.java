package csci6461;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;

import co.com.csci.model.Instruction;
import co.com.csci.util.BinaryUtil;
import co.com.csci.util.InstructionEnum;

import javax.swing.JTextField;



public class FrontPanel {

	private JFrame frame;
	private JSplitPane splitPanel;
	
	private JTextField txtOpCode;
	private JTextField txtR;
	private JTextField txtIX;
	private JTextField txtI;
	private JTextField txtAddress;
	private JTextField txtMsr;

	//Registers
	static JTextPane txtR0;// TextInput for R0
	static JTextPane txtR1;// TextInput for R1
	static JTextPane txtR2;// TextInput for R2
	static JTextPane txtR3;// TextInput for R3
	
	//Indexes
	static JTextPane txtX1;// TextInput for X1
	static JTextPane txtX2;// TextInput for X2
	static JTextPane txtX3;// TextInput for X3
	
	static JTextPane txtPc; //PC
	static JTextPane txtMar; //MAR
	static JTextPane txtMbr; //MBR
	static JTextPane txtIr;  //IR
	
	static JTextPane txtEc; //EC
	static JTextField txtCc; //CC
	
	static JTextField txtInput; //Input
	static JTextPane txtOutput; //Output
	
	static JTextPane[] memory; //All memory entries
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		FrontPanel window = new FrontPanel();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
	 * Creation of the components and basic initialization of 
	 * the contents of the application
	 */
	private void initialize() {
		
		//Main window
		frame = new JFrame();
		frame.setBounds(100, 100, 1176, 618);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		/*
		 * Input Area - In this area the user can input individual instructions
		 * Opcode	6 bits	Specifies one of 64 possible instructions; Not all may be defined in this project
		 * IX		2 bits	Specifies one of three index registers; may be referred to by X1 – X3. O value indicates no indexing.
		 * R		2 bits	Specifies one of four general purpose registers; may be referred to by R0 – R3
		 * I		1 bits	If I =1, specifies indirect addressing; otherwise, no indirect addressing.
		 * Address	7 bits	Specifies one of 32 locations
		 */
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{42, 122, 124, 44, 30, 14, 20, 11, 89, 39, 30, 115, 31, 20, 120, 20, 128, 120, 0};
		gridBagLayout.rowHeights = new int[]{45, 27, 14, 22, 20, 14, 0, 34, 14, 14, 14, 2, 3, 14, 13, 20, 63, 26, 0, 1, 20, 29, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		setDirectInput(new JTextField());
		getDirectInput().setBounds(100, 12, 94, 43);
		GridBagConstraints gbc_directInput = new GridBagConstraints();
		gbc_directInput.fill = GridBagConstraints.BOTH;
		gbc_directInput.insets = new Insets(0, 0, 5, 5);
		gbc_directInput.gridx = 0;
		gbc_directInput.gridy = 0;
		frame.getContentPane().add(getDirectInput(), gbc_directInput);
		getDirectInput().setColumns(10);
		
		JLabel lblInput = new JLabel("INPUT");
		GridBagConstraints gbc_lblInput = new GridBagConstraints();
		gbc_lblInput.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblInput.insets = new Insets(0, 0, 5, 5);
		gbc_lblInput.gridx = 1;
		gbc_lblInput.gridy = 1;
		frame.getContentPane().add(lblInput, gbc_lblInput);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.fill = GridBagConstraints.BOTH;
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.gridx = 2;
		gbc_textField_5.gridy = 1;
		frame.getContentPane().add(textField_5, gbc_textField_5);
		

		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		//Split Panel (main panel)
		splitPanel = new JSplitPane();
		splitPanel.setDividerLocation(900);
		frame.getContentPane().add(splitPanel);
		
		//Memory Panel (right part of the app)
		JScrollPane memoryPanel = new JScrollPane();
		memoryPanel.setViewportBorder(new CompoundBorder());
		memoryPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		splitPanel.setRightComponent(new MemoryPanel(2048, memory).getSplitPane());
		
		//Left Panel (left part of the part - all that isn't memory)
		JPanel panel = new JPanel();
		splitPanel.setLeftComponent(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		panel.setLayout(gbl_panel);
		initLeftPanelComponents(panel);
	}
	
	private void initLeftPanelComponents(JPanel panel){
		initTitle(panel);
		initInstructionInput(panel);
		//Init PC, IR, MBR, IR, IAR, MBR and MAR
		initPCandOtherFields(panel);
		initRegisters(panel);
		initIndexes(panel);
		initButtons(panel);
		//Init CC, Output, Input, CC and MSR
		initInputsAndOutputs(panel);
	}
	
	public static void setRegister(int registerNum, String content){
		if(registerNum == 0){
			txtR0.setText(content);
		} else if(registerNum == 1){
			txtR1.setText(content);
		} else  if(registerNum == 2){
			txtR2.setText(content);
		} else if(registerNum == 3){
			txtR3.setText(content);
		} else {
			//Exception
		}
	}
	
	public static String getRegister(int registerNum){
		if(registerNum == 0){
			return txtR0.getText();
		} else if(registerNum == 1){
			return txtR1.getText();
		} else  if(registerNum == 2){
			return txtR2.getText();
		} else if(registerNum == 3){
			return txtR3.getText();
		} else {
			//Exception
		}
		return "";
	}
	
	public void setIndex(int indexNum, String content){
		if(indexNum == 1){
			txtX1.setText(content);
		} else  if(indexNum == 2){
			txtX2.setText(content);
		} else if(indexNum == 3){
			txtX3.setText(content);
		} else {
			//Exception
		}
	}
	
	public static String getIndex(int indexNum){
		if(indexNum == 1){
			return txtX1.getText();
		} else  if(indexNum == 2){
			return txtX2.getText();
		} else if(indexNum == 3){
			return txtX3.getText();
		} else {
			//Exception
		}
		return "";
	}
	
	public void setCC(int CCNum, String content){
		if(CCNum == 1){
			txtCc.setText(content);
		} else  if(CCNum == 2){
			txtCc.setText(content);
		} else if(CCNum == 3){
			txtCc.setText(content);
		} else if(CCNum == 4){
			txtCc.setText(content);
		} else {
			//Exception
		}
	}
	
	public static String getCC(int CCNum){
		if(CCNum == 1){
			return txtCc.getText();
		} else  if(CCNum == 2){
			return txtCc.getText();
		} else if(CCNum == 3){
			return txtCc.getText();
		} else if(CCNum == 4){
			return txtCc.getText();
		} else {
			//Exception
		}
		return "";
	}
	
	public JTextField getDirectInput() {
		return txtInput;
	}

	public void setDirectInput(JTextField directInput) {
		this.txtInput = directInput;
	}
	
	private void initTitle(JPanel panel){

		JLabel lblCiscSimulator = new JLabel("CISC Simulator");
		lblCiscSimulator.setFont(new Font("Tahoma", Font.BOLD, 25));
		GridBagConstraints gbc_lblCiscSimulator = new GridBagConstraints();
		gbc_lblCiscSimulator.anchor = GridBagConstraints.WEST;
		gbc_lblCiscSimulator.fill = GridBagConstraints.VERTICAL;
		gbc_lblCiscSimulator.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiscSimulator.gridwidth = 6;
		gbc_lblCiscSimulator.gridx = 5;
		gbc_lblCiscSimulator.gridy = 0;
		panel.add(lblCiscSimulator, gbc_lblCiscSimulator);
	}
	
	private void initInstructionInput(JPanel panel){
		
		//OpCode
		JLabel lblOpcode = new JLabel("OpCode");
		GridBagConstraints gbc_lblOpcode = new GridBagConstraints();
		gbc_lblOpcode.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblOpcode.insets = new Insets(0, 0, 5, 5);
		gbc_lblOpcode.gridx = 1;
		gbc_lblOpcode.gridy = 2;
		panel.add(lblOpcode, gbc_lblOpcode);
		
		txtOpCode = new JTextField();
		GridBagConstraints gbc_txtOpCode = new GridBagConstraints();
		gbc_txtOpCode.anchor = GridBagConstraints.SOUTH;
		gbc_txtOpCode.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtOpCode.insets = new Insets(0, 0, 5, 5);
		gbc_txtOpCode.gridwidth = 2;
		gbc_txtOpCode.gridx = 1;
		gbc_txtOpCode.gridy = 3;
		panel.add(txtOpCode, gbc_txtOpCode);
		txtOpCode.setColumns(10);
		
		//R
		JLabel lblR = new JLabel("R");
		GridBagConstraints gbc_lblR = new GridBagConstraints();
		gbc_lblR.anchor = GridBagConstraints.NORTH;
		gbc_lblR.insets = new Insets(0, 0, 5, 5);
		gbc_lblR.gridx = 3;
		gbc_lblR.gridy = 2;
		panel.add(lblR, gbc_lblR);
		
		txtR = new JTextField();
		GridBagConstraints gbc_txtR = new GridBagConstraints();
		gbc_txtR.anchor = GridBagConstraints.SOUTH;
		gbc_txtR.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtR.insets = new Insets(0, 0, 5, 5);
		gbc_txtR.gridx = 3;
		gbc_txtR.gridy = 3;
		panel.add(txtR, gbc_txtR);
		txtR.setColumns(10);
		
		//IX
		JLabel lblIx = new JLabel("IX");
		GridBagConstraints gbc_lblIx = new GridBagConstraints();
		gbc_lblIx.anchor = GridBagConstraints.NORTH;
		gbc_lblIx.insets = new Insets(0, 0, 5, 5);
		gbc_lblIx.gridwidth = 2;
		gbc_lblIx.gridx = 4;
		gbc_lblIx.gridy = 2;
		panel.add(lblIx, gbc_lblIx);
		
		txtIX = new JTextField();
		GridBagConstraints gbc_txtIX = new GridBagConstraints();
		gbc_txtIX.anchor = GridBagConstraints.SOUTH;
		gbc_txtIX.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIX.insets = new Insets(0, 0, 5, 5);
		gbc_txtIX.gridwidth = 2;
		gbc_txtIX.gridx = 4;
		gbc_txtIX.gridy = 3;
		panel.add(txtIX, gbc_txtIX);
		txtIX.setColumns(10);
		
		//I
		JLabel lblI = new JLabel("I");
		GridBagConstraints gbc_lblI = new GridBagConstraints();
		gbc_lblI.anchor = GridBagConstraints.NORTH;
		gbc_lblI.insets = new Insets(0, 0, 5, 5);
		gbc_lblI.gridx = 6;
		gbc_lblI.gridy = 2;
		panel.add(lblI, gbc_lblI);
		txtI = new JTextField();
		GridBagConstraints gbc_txtI = new GridBagConstraints();
		gbc_txtI.anchor = GridBagConstraints.SOUTH;
		gbc_txtI.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtI.insets = new Insets(0, 0, 5, 5);
		gbc_txtI.gridx = 6;
		gbc_txtI.gridy = 3;
		panel.add(txtI, gbc_txtI);
		txtI.setColumns(10);
		
		//Address
		txtAddress = new JTextField();
		txtAddress.setText("1010101010101010");
		GridBagConstraints gbc_txtAddress = new GridBagConstraints();
		gbc_txtAddress.anchor = GridBagConstraints.SOUTH;
		gbc_txtAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAddress.insets = new Insets(0, 0, 5, 5);
		gbc_txtAddress.gridwidth = 2;
		gbc_txtAddress.gridx = 7;
		gbc_txtAddress.gridy = 3;
		panel.add(txtAddress, gbc_txtAddress);
		txtAddress.setColumns(10);
	}
	
	private void initPCandOtherFields(JPanel panel) {
		//Init PC
		JLabel lblPc = new JLabel("PC");
		GridBagConstraints gbc_lblPc = new GridBagConstraints();
		gbc_lblPc.anchor = GridBagConstraints.SOUTH;
		gbc_lblPc.insets = new Insets(0, 0, 5, 5);
		gbc_lblPc.gridx = 10;
		gbc_lblPc.gridy = 3;
		panel.add(lblPc, gbc_lblPc);
		
		txtPc = new JTextPane();
		txtPc.setText("0000000000000000");
		GridBagConstraints gbc_txtPc = new GridBagConstraints();
		gbc_txtPc.anchor = GridBagConstraints.SOUTH;
		gbc_txtPc.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPc.insets = new Insets(0, 0, 5, 0);
		gbc_txtPc.gridx = 11;
		gbc_txtPc.gridy = 3;
		panel.add(txtPc, gbc_txtPc);
		
		//Init MAR
		JLabel lblMar = new JLabel("MAR");
		GridBagConstraints gbc_lblMar = new GridBagConstraints();
		gbc_lblMar.anchor = GridBagConstraints.SOUTH;
		gbc_lblMar.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMar.insets = new Insets(0, 0, 5, 5);
		gbc_lblMar.gridx = 10;
		gbc_lblMar.gridy = 4;
		panel.add(lblMar, gbc_lblMar);
		
		txtMar = new JTextPane();
		txtMar.setText("");
		GridBagConstraints gbc_txtMar = new GridBagConstraints();
		gbc_txtMar.anchor = GridBagConstraints.NORTH;
		gbc_txtMar.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMar.insets = new Insets(0, 0, 5, 0);
		gbc_txtMar.gridx = 11;
		gbc_txtMar.gridy = 4;
		panel.add(txtMar, gbc_txtMar);
		memory = new JTextPane[16];
		
		//Init MBR
		JLabel lblMbr = new JLabel("MBR");
		GridBagConstraints gbc_lblMbr = new GridBagConstraints();
		gbc_lblMbr.anchor = GridBagConstraints.NORTH;
		gbc_lblMbr.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMbr.insets = new Insets(0, 0, 5, 5);
		gbc_lblMbr.gridx = 10;
		gbc_lblMbr.gridy = 5;
		panel.add(lblMbr, gbc_lblMbr);
		
		txtMbr = new JTextPane();
		txtMbr.setText("");
		GridBagConstraints gbc_txtMbr = new GridBagConstraints();
		gbc_txtMbr.anchor = GridBagConstraints.SOUTH;
		gbc_txtMbr.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMbr.insets = new Insets(0, 0, 5, 0);
		gbc_txtMbr.gridx = 11;
		gbc_txtMbr.gridy = 5;
		panel.add(txtMbr, gbc_txtMbr);
		
		//Init IR
		JLabel lblIr = new JLabel("IR");
		GridBagConstraints gbc_lblIr = new GridBagConstraints();
		gbc_lblIr.anchor = GridBagConstraints.NORTH;
		gbc_lblIr.insets = new Insets(0, 0, 5, 5);
		gbc_lblIr.gridx = 10;
		gbc_lblIr.gridy = 6;
		panel.add(lblIr, gbc_lblIr);
		
		txtIr = new JTextPane();
		txtIr.setText("");
		GridBagConstraints gbc_txtIr = new GridBagConstraints();
		gbc_txtIr.anchor = GridBagConstraints.SOUTH;
		gbc_txtIr.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIr.insets = new Insets(0, 0, 5, 0);
		gbc_txtIr.gridx = 11;
		gbc_txtIr.gridy = 6;
		panel.add(txtIr, gbc_txtIr);
		
		//Init IAR
		JLabel lblIar = new JLabel("IAR");
		GridBagConstraints gbc_lblIar = new GridBagConstraints();
		gbc_lblIar.gridheight = 2;
		gbc_lblIar.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblIar.insets = new Insets(0, 0, 5, 5);
		gbc_lblIar.gridx = 10;
		gbc_lblIar.gridy = 7;
		panel.add(lblIar, gbc_lblIar);
		
		JTextPane txtIar = new JTextPane();
		txtIar.setText("");
		GridBagConstraints gbc_txtIar = new GridBagConstraints();
		gbc_txtIar.insets = new Insets(0, 0, 5, 0);
		gbc_txtIar.fill = GridBagConstraints.BOTH;
		gbc_txtIar.gridx = 11;
		gbc_txtIar.gridy = 7;
		panel.add(txtIar, gbc_txtIar);
	}
	
	private void initRegisters(JPanel panel){
		//Init Registers Title
		JLabel lblRegisters = new JLabel("Registers");
		lblRegisters.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblRegisters = new GridBagConstraints();
		gbc_lblRegisters.anchor = GridBagConstraints.SOUTH;
		gbc_lblRegisters.insets = new Insets(0, 0, 5, 0);
		gbc_lblRegisters.gridx = 11;
		gbc_lblRegisters.gridy = 9;
		panel.add(lblRegisters, gbc_lblRegisters);
		
		//Init Register 0
		JLabel lblR0 = new JLabel("R0");
		GridBagConstraints gbc_lblR0 = new GridBagConstraints();
		gbc_lblR0.anchor = GridBagConstraints.SOUTH;
		gbc_lblR0.insets = new Insets(0, 0, 5, 5);
		gbc_lblR0.gridx = 10;
		gbc_lblR0.gridy = 10;
		panel.add(lblR0, gbc_lblR0);
		
		txtR0 = new JTextPane();
		txtR0.setText("");
		GridBagConstraints gbc_txtR0 = new GridBagConstraints();
		gbc_txtR0.anchor = GridBagConstraints.NORTH;
		gbc_txtR0.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtR0.insets = new Insets(0, 0, 5, 0);
		gbc_txtR0.gridx = 11;
		gbc_txtR0.gridy = 10;
		panel.add(txtR0, gbc_txtR0);
		
		//Init Register 1
		JLabel lblR1 = new JLabel("R1");
		GridBagConstraints gbc_lblR1 = new GridBagConstraints();
		gbc_lblR1.anchor = GridBagConstraints.SOUTH;
		gbc_lblR1.insets = new Insets(0, 0, 5, 5);
		gbc_lblR1.gridx = 10;
		gbc_lblR1.gridy = 11;
		panel.add(lblR1, gbc_lblR1);
		
		txtR1 = new JTextPane();
		txtR1.setText("");
		GridBagConstraints gbc_txtR1 = new GridBagConstraints();
		gbc_txtR1.anchor = GridBagConstraints.SOUTH;
		gbc_txtR1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtR1.insets = new Insets(0, 0, 5, 0);
		gbc_txtR1.gridx = 11;
		gbc_txtR1.gridy = 11;
		panel.add(txtR1, gbc_txtR1);
		
		//Init Register 2
		JLabel lblR2 = new JLabel("R2");
		GridBagConstraints gbc_lblR2 = new GridBagConstraints();
		gbc_lblR2.anchor = GridBagConstraints.SOUTH;
		gbc_lblR2.insets = new Insets(0, 0, 5, 5);
		gbc_lblR2.gridx = 10;
		gbc_lblR2.gridy = 12;
		panel.add(lblR2, gbc_lblR2);
		
		txtR2 = new JTextPane();
		txtR2.setText("");
		GridBagConstraints gbc_txtR2 = new GridBagConstraints();
		gbc_txtR2.anchor = GridBagConstraints.NORTH;
		gbc_txtR2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtR2.insets = new Insets(0, 0, 5, 0);
		gbc_txtR2.gridx = 11;
		gbc_txtR2.gridy = 12;
		panel.add(txtR2, gbc_txtR2);
		
		//Init Register 3
		JLabel lblR3 = new JLabel("R3");
		GridBagConstraints gbc_lblR3 = new GridBagConstraints();
		gbc_lblR3.anchor = GridBagConstraints.SOUTH;
		gbc_lblR3.insets = new Insets(0, 0, 5, 5);
		gbc_lblR3.gridx = 10;
		gbc_lblR3.gridy = 13;
		panel.add(lblR3, gbc_lblR3);
		
		txtR3 = new JTextPane();
		txtR3.setText("");
		GridBagConstraints gbc_txtR3 = new GridBagConstraints();
		gbc_txtR3.anchor = GridBagConstraints.SOUTH;
		gbc_txtR3.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtR3.insets = new Insets(0, 0, 5, 0);
		gbc_txtR3.gridx = 11;
		gbc_txtR3.gridy = 13;
		panel.add(txtR3, gbc_txtR3);
	}
	
	private void initIndexes(JPanel panel){
		//Init Index 1
		JLabel lblX1 = new JLabel("X1");
		GridBagConstraints gbc_lblX1 = new GridBagConstraints();
		gbc_lblX1.insets = new Insets(0, 0, 5, 5);
		gbc_lblX1.gridx = 10;
		gbc_lblX1.gridy = 15;
		panel.add(lblX1, gbc_lblX1);
		
		txtX1 = new JTextPane();
		txtX1.setText("");
		GridBagConstraints gbc_txtX1 = new GridBagConstraints();
		gbc_txtX1.insets = new Insets(0, 0, 5, 0);
		gbc_txtX1.fill = GridBagConstraints.BOTH;
		gbc_txtX1.gridx = 11;
		gbc_txtX1.gridy = 15;
		panel.add(txtX1, gbc_txtX1);

		//Init Index 2
		JLabel lblX2 = new JLabel("X2");
		GridBagConstraints gbc_lblX2 = new GridBagConstraints();
		gbc_lblX2.insets = new Insets(0, 0, 5, 5);
		gbc_lblX2.gridx = 10;
		gbc_lblX2.gridy = 16;
		panel.add(lblX2, gbc_lblX2);
		
		txtX2 = new JTextPane();
		txtX2.setText("");
		GridBagConstraints gbc_txtX2 = new GridBagConstraints();
		gbc_txtX2.anchor = GridBagConstraints.SOUTH;
		gbc_txtX2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtX2.insets = new Insets(0, 0, 5, 0);
		gbc_txtX2.gridx = 11;
		gbc_txtX2.gridy = 16;
		panel.add(txtX2, gbc_txtX2);
		
		//Init Index 3
		JLabel lblX3 = new JLabel("X3");
		GridBagConstraints gbc_lblX3 = new GridBagConstraints();
		gbc_lblX3.insets = new Insets(0, 0, 0, 5);
		gbc_lblX3.gridx = 10;
		gbc_lblX3.gridy = 17;
		panel.add(lblX3, gbc_lblX3);
		
		txtX3 = new JTextPane();
		txtX3.setText("");
		GridBagConstraints gbc_txtX3 = new GridBagConstraints();
		gbc_txtX3.anchor = GridBagConstraints.NORTH;
		gbc_txtX3.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtX3.gridx = 11;
		gbc_txtX3.gridy = 17;
		panel.add(txtX3, gbc_txtX3);
	}
	
	private void initBtnSingleInstruction(JPanel panel){
		JButton btnSingle = new JButton("Single Instruction");
		GridBagConstraints gbc_btnSingle = new GridBagConstraints();
		gbc_btnSingle.anchor = GridBagConstraints.NORTH;
		gbc_btnSingle.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSingle.insets = new Insets(0, 0, 5, 5);
		gbc_btnSingle.gridx = 8;
		gbc_btnSingle.gridy = 10;
		panel.add(btnSingle, gbc_btnSingle);
		
		btnSingle.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  txtOutput.setText("Output");
			  //Get PC counter
			  String pc = txtPc.getText();
			  //Convert PC from binary to decimal 
			  Integer pcDecimal = Integer.parseInt(txtPc.getText(), 2);
			  //Get Instruction from memory space
			  String plainInstruction = memory[pcDecimal].getText();
			  Instruction instruction;
			  try
			  {
				  instruction = new Instruction(plainInstruction);
				  //Change MAR
				  txtMar.setText(pc);
				  //Change MBR
				  txtMbr.setText(plainInstruction);
				  //Change IR
				  txtIr.setText(plainInstruction);
				  //Evaluate instruction
				  InstructionEnum iCode = instruction.getIntructionCode();
			
				  switch(iCode){
		  		  		case LDR:
		  		  			LoadStore.instructionLDR(instruction);
		  		  			break;
		  		  		case STR:
		  		  			LoadStore.instructionSTR(instruction);
	  		  				break;
		  		  		/*case AMR:
		  		  			instructionAMR(instruction);
	  		  				break;
		  		  		case SMR:
		  		  			instructionSMR(instruction);
	  		  				break;
		  		  		case AIR:
		  		  			instructionAIR(instruction);
	  		  				break;
		  		  		case SIR:
		  		  			instructionSIR(instruction);
	  		  				break;
		  		  		case MLT:
		  		  			instructionMLT(instruction);
	  		  				break;
		  		  		case DVD:
		  		  			instructionDVD(instruction);
	  		  				break;
		  		  		case TRR:
		  		  			instructionTRR(instruction);
	  		  				break;
		  		  		case AND:
		  		  			instructionAND(instruction);
	  		  				break;
		  		  		case ORR:
		  		  			instructionORR(instruction);
	  		  				break;
		  		  		case NOT:
		  		  			instructionNOT(instruction);
	  		  				break;	*/
		  		  		case HALT:
		  		  			txtOutput.setText("HALT");
			  				break;
		  		  		case FAULT:
		  		  			txtOutput.setText("FAULT");
		  		  			break;
				  }
				  //Increment PC counter
				  if(iCode != InstructionEnum.HALT){
					  pcDecimal++;
				  }
				  txtPc.setText(BinaryUtil.fillBinaryString(Integer.toBinaryString(pcDecimal)));
		  	} catch (Throwable t){
		  		txtOutput.setText("FAULT");
		  		
		  	}
		  }
		});

		
		JLabel lblR_1 = new JLabel("R0");
		GridBagConstraints gbc_lblR_1 = new GridBagConstraints();
		gbc_lblR_1.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblR_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblR_1.gridx = 10;
		gbc_lblR_1.gridy = 10;
		frame.getContentPane().add(lblR_1, gbc_lblR_1);
		
		txtpnR = new JTextPane();
		txtpnR.setText("");
		GridBagConstraints gbc_txtpnR = new GridBagConstraints();
		gbc_txtpnR.anchor = GridBagConstraints.NORTH;
		gbc_txtpnR.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtpnR.insets = new Insets(0, 0, 5, 5);
		gbc_txtpnR.gridx = 11;
		gbc_txtpnR.gridy = 10;
		frame.getContentPane().add(txtpnR, gbc_txtpnR);
		
		JLabel label_13 = new JLabel("05");
		GridBagConstraints gbc_label_13 = new GridBagConstraints();
		gbc_label_13.anchor = GridBagConstraints.EAST;
		gbc_label_13.insets = new Insets(0, 0, 5, 5);
		gbc_label_13.gridx = 13;
		gbc_label_13.gridy = 10;
		frame.getContentPane().add(label_13, gbc_label_13);
		
		JTextPane textPane_10 = new JTextPane();
		textPane_10.setText("");
		GridBagConstraints gbc_textPane_10 = new GridBagConstraints();
		gbc_textPane_10.anchor = GridBagConstraints.NORTH;
		gbc_textPane_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPane_10.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_10.gridx = 14;
		gbc_textPane_10.gridy = 10;
		frame.getContentPane().add(textPane_10, gbc_textPane_10);
		memory[5] = textPane_10;
		
		JLabel label_8 = new JLabel("13");
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_8.insets = new Insets(0, 0, 5, 5);
		gbc_label_8.gridx = 15;
		gbc_label_8.gridy = 10;
		frame.getContentPane().add(label_8, gbc_label_8);
		
		JTextPane textPane_13 = new JTextPane();
		textPane_13.setText("");
		GridBagConstraints gbc_textPane_13 = new GridBagConstraints();
		gbc_textPane_13.anchor = GridBagConstraints.NORTH;
		gbc_textPane_13.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPane_13.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_13.gridx = 16;
		gbc_textPane_13.gridy = 10;
		frame.getContentPane().add(textPane_13, gbc_textPane_13);
		memory[13] = textPane_13;
		
		txtMsr = new JTextField();
		txtMsr.setText("MSR");
		GridBagConstraints gbc_txtMsr = new GridBagConstraints();
		gbc_txtMsr.anchor = GridBagConstraints.SOUTH;
		gbc_txtMsr.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMsr.insets = new Insets(0, 0, 5, 5);
		gbc_txtMsr.gridx = 8;
		gbc_txtMsr.gridy = 11;
		frame.getContentPane().add(txtMsr, gbc_txtMsr);
		txtMsr.setColumns(10);
		
		JLabel lblR_2 = new JLabel("R1");
		GridBagConstraints gbc_lblR_2 = new GridBagConstraints();
		gbc_lblR_2.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblR_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblR_2.gridx = 10;
		gbc_lblR_2.gridy = 11;
		frame.getContentPane().add(lblR_2, gbc_lblR_2);
		
		txtpnR_1 = new JTextPane();
		txtpnR_1.setText("");
		GridBagConstraints gbc_txtpnR_1 = new GridBagConstraints();
		gbc_txtpnR_1.anchor = GridBagConstraints.SOUTH;
		gbc_txtpnR_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtpnR_1.insets = new Insets(0, 0, 5, 5);
		gbc_txtpnR_1.gridx = 11;
		gbc_txtpnR_1.gridy = 11;
		frame.getContentPane().add(txtpnR_1, gbc_txtpnR_1);
		
		JLabel label_14 = new JLabel("06");
		GridBagConstraints gbc_label_14 = new GridBagConstraints();
		gbc_label_14.anchor = GridBagConstraints.NORTHEAST;
		gbc_label_14.insets = new Insets(0, 0, 5, 5);
		gbc_label_14.gridx = 13;
		gbc_label_14.gridy = 11;
		frame.getContentPane().add(label_14, gbc_label_14);
		
		JTextPane textPane_11 = new JTextPane();
		textPane_11.setText("");
		GridBagConstraints gbc_textPane_11 = new GridBagConstraints();
		gbc_textPane_11.anchor = GridBagConstraints.NORTH;
		gbc_textPane_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPane_11.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_11.gridx = 14;
		gbc_textPane_11.gridy = 11;
		frame.getContentPane().add(textPane_11, gbc_textPane_11);
		memory[6] = textPane_11;
		
		JLabel label_9 = new JLabel("14");
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.anchor = GridBagConstraints.NORTH;
		gbc_label_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_9.insets = new Insets(0, 0, 5, 5);
		gbc_label_9.gridx = 15;
		gbc_label_9.gridy = 11;
		frame.getContentPane().add(label_9, gbc_label_9);
		
		JTextPane textPane_14 = new JTextPane();
		textPane_14.setText("");
		GridBagConstraints gbc_textPane_14 = new GridBagConstraints();
		gbc_textPane_14.anchor = GridBagConstraints.NORTH;
		gbc_textPane_14.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPane_14.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_14.gridx = 16;
		gbc_textPane_14.gridy = 11;
		frame.getContentPane().add(textPane_14, gbc_textPane_14);
		memory[14] = textPane_14;
		
		JLabel lblR_3 = new JLabel("R2");
		GridBagConstraints gbc_lblR_3 = new GridBagConstraints();
		gbc_lblR_3.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblR_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblR_3.gridx = 10;
		gbc_lblR_3.gridy = 12;
		frame.getContentPane().add(lblR_3, gbc_lblR_3);
		
		txtpnR_2 = new JTextPane();
		txtpnR_2.setText("");
		GridBagConstraints gbc_txtpnR_2 = new GridBagConstraints();
		gbc_txtpnR_2.anchor = GridBagConstraints.NORTH;
		gbc_txtpnR_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtpnR_2.insets = new Insets(0, 0, 5, 5);
		gbc_txtpnR_2.gridx = 11;
		gbc_txtpnR_2.gridy = 12;
		frame.getContentPane().add(txtpnR_2, gbc_txtpnR_2);
		
		JLabel label_15 = new JLabel("07");
		GridBagConstraints gbc_label_15 = new GridBagConstraints();
		gbc_label_15.anchor = GridBagConstraints.EAST;
		gbc_label_15.insets = new Insets(0, 0, 5, 5);
		gbc_label_15.gridx = 13;
		gbc_label_15.gridy = 12;
		frame.getContentPane().add(label_15, gbc_label_15);
		
		JTextPane textPane_12 = new JTextPane();
		textPane_12.setText("");
		GridBagConstraints gbc_textPane_12 = new GridBagConstraints();
		gbc_textPane_12.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPane_12.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_12.gridx = 14;
		gbc_textPane_12.gridy = 12;
		frame.getContentPane().add(textPane_12, gbc_textPane_12);
		memory[7] = textPane_12;
		
		JLabel label_10 = new JLabel("15");
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_10.insets = new Insets(0, 0, 5, 5);
		gbc_label_10.gridx = 15;
		gbc_label_10.gridy = 12;
		frame.getContentPane().add(label_10, gbc_label_10);
		
		JTextPane textPane_15 = new JTextPane();
		textPane_15.setText("");
		GridBagConstraints gbc_textPane_15 = new GridBagConstraints();
		gbc_textPane_15.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_15.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPane_15.gridx = 16;
		gbc_textPane_15.gridy = 12;
		frame.getContentPane().add(textPane_15, gbc_textPane_15);
		memory[15] = textPane_15;
		
		JLabel lblR_4 = new JLabel("R3");
		GridBagConstraints gbc_lblR_4 = new GridBagConstraints();
		gbc_lblR_4.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblR_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblR_4.gridx = 10;
		gbc_lblR_4.gridy = 13;
		frame.getContentPane().add(lblR_4, gbc_lblR_4);
		
		textPane_16 = new JTextPane();
		textPane_16.setText("");
		GridBagConstraints gbc_textPane_16 = new GridBagConstraints();
		gbc_textPane_16.anchor = GridBagConstraints.SOUTH;
		gbc_textPane_16.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPane_16.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_16.gridx = 11;
		gbc_textPane_16.gridy = 13;
		frame.getContentPane().add(textPane_16, gbc_textPane_16);
		
		JLabel lblX = new JLabel("X1");
		GridBagConstraints gbc_lblX = new GridBagConstraints();
		gbc_lblX.insets = new Insets(0, 0, 5, 5);
		gbc_lblX.gridx = 10;
		gbc_lblX.gridy = 15;
		frame.getContentPane().add(lblX, gbc_lblX);
		
		txtpnX_1 = new JTextPane();
		txtpnX_1.setText("");
		GridBagConstraints gbc_txtpnX_1 = new GridBagConstraints();
		gbc_txtpnX_1.anchor = GridBagConstraints.SOUTH;
		gbc_txtpnX_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtpnX_1.insets = new Insets(0, 0, 5, 5);
		gbc_txtpnX_1.gridx = 11;
		gbc_txtpnX_1.gridy = 15;
		frame.getContentPane().add(txtpnX_1, gbc_txtpnX_1);
		JToggleButton tglbtnLoad = new JToggleButton("Load");
		GridBagConstraints gbc_tglbtnLoad = new GridBagConstraints();
		gbc_tglbtnLoad.anchor = GridBagConstraints.NORTH;
		gbc_tglbtnLoad.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnLoad.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnLoad.gridx = 1;
		gbc_tglbtnLoad.gridy = 16;
		frame.getContentPane().add(tglbtnLoad, gbc_tglbtnLoad);
		
		JToggleButton tglbtnExecute = new JToggleButton("Execute");
		GridBagConstraints gbc_tglbtnExecute = new GridBagConstraints();
		gbc_tglbtnExecute.anchor = GridBagConstraints.NORTH;
		gbc_tglbtnExecute.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnExecute.gridwidth = 2;
		gbc_tglbtnExecute.gridx = 2;
		gbc_tglbtnExecute.gridy = 16;
		frame.getContentPane().add(tglbtnExecute, gbc_tglbtnExecute);
		
		JButton btnSave = new JButton("Save");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.anchor = GridBagConstraints.NORTH;
		gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridwidth = 4;
		gbc_btnSave.gridx = 4;
		gbc_btnSave.gridy = 16;
		frame.getContentPane().add(btnSave, gbc_btnSave);
		
		JButton btnNewButton = new JButton("Stop");
		btnNewButton.addActionListener(new ActionListener() {

	}
	
	private void initBtnStop(JPanel panel) {
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnStop = new GridBagConstraints();
		gbc_btnStop.gridwidth = 2;
		gbc_btnStop.anchor = GridBagConstraints.NORTH;
		gbc_btnStop.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnStop.insets = new Insets(0, 0, 0, 5);
		gbc_btnStop.gridx = 7;
		gbc_btnStop.gridy = 17;
		panel.add(btnStop, gbc_btnStop);
	}

	private void initBtnLoad(JPanel panel) {
		JToggleButton btnExecute = new JToggleButton("Execute");
		GridBagConstraints gbc_btnExecute = new GridBagConstraints();
		gbc_btnExecute.anchor = GridBagConstraints.NORTH;
		gbc_btnExecute.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExecute.insets = new Insets(0, 0, 0, 5);
		gbc_btnExecute.gridwidth = 3;
		gbc_btnExecute.gridx = 2;
		gbc_btnExecute.gridy = 17;
		panel.add(btnExecute, gbc_btnExecute);
	}

	private void initBtnExecute(JPanel panel) {
		JToggleButton btnLoad = new JToggleButton("Load");
		GridBagConstraints gbc_btnLoad = new GridBagConstraints();
		gbc_btnLoad.anchor = GridBagConstraints.NORTH;
		gbc_btnLoad.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLoad.insets = new Insets(0, 0, 0, 5);
		gbc_btnLoad.gridx = 1;
		gbc_btnLoad.gridy = 17;
		panel.add(btnLoad, gbc_btnLoad);
	}

	private void initBtnSave(JPanel panel) {
		JButton btnSave = new JButton("Save");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.anchor = GridBagConstraints.NORTH;
		gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridwidth = 2;
		gbc_btnSave.gridx = 5;
		gbc_btnSave.gridy = 17;
		panel.add(btnSave, gbc_btnSave);
	}
	
	private void initButtons(JPanel panel) {
		initBtnSingleInstruction(panel);
		initBtnSave(panel);
		initBtnExecute(panel);
		initBtnLoad(panel);
		initBtnStop(panel);
	}
	
	private void initInputsAndOutputs(JPanel panel) {
		//Init area for general output
		txtOutput = new JTextPane();
		txtOutput.setText("Output");
		GridBagConstraints gbc_txtOutput = new GridBagConstraints();
		gbc_txtOutput.fill = GridBagConstraints.BOTH;
		gbc_txtOutput.insets = new Insets(0, 0, 5, 5);
		gbc_txtOutput.gridheight = 5;
		gbc_txtOutput.gridwidth = 6;
		gbc_txtOutput.gridx = 1;
		gbc_txtOutput.gridy = 9;
		panel.add(txtOutput, gbc_txtOutput);

		//Init Input field
		JLabel lblInput = new JLabel("INPUT");
		lblInput.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblInput = new GridBagConstraints();
		gbc_lblInput.insets = new Insets(0, 0, 5, 5);
		gbc_lblInput.gridx = 1;
		gbc_lblInput.gridy = 14;
		panel.add(lblInput, gbc_lblInput);
		
		setDirectInput(new JTextField());
		getDirectInput().setBounds(100, 12, 94, 43);
		GridBagConstraints gbc_txtInput = new GridBagConstraints();
		gbc_txtInput.gridheight = 2;
		gbc_txtInput.gridwidth = 5;
		gbc_txtInput.fill = GridBagConstraints.BOTH;
		gbc_txtInput.insets = new Insets(0, 0, 5, 5);
		gbc_txtInput.gridx = 2;
		gbc_txtInput.gridy = 14;
		panel.add(getDirectInput(), gbc_txtInput);
		getDirectInput().setColumns(10);
		
		//Init output area for processing messages (i.e. troubleshooting, trap messages)
		txtEc = new JTextPane();
		txtEc.setText("EC");
		GridBagConstraints gbc_txtEc = new GridBagConstraints();
		gbc_txtEc.fill = GridBagConstraints.BOTH;
		gbc_txtEc.insets = new Insets(0, 0, 5, 5);
		gbc_txtEc.gridheight = 3;
		gbc_txtEc.gridwidth = 8;
		gbc_txtEc.gridx = 1;
		gbc_txtEc.gridy = 5;
		panel.add(txtEc, gbc_txtEc);
		
		//Init output for the condition code (CC)
		txtCc = new JTextField();
		txtCc.setText("CC");
		GridBagConstraints gbc_txtCc = new GridBagConstraints();
		gbc_txtCc.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCc.anchor = GridBagConstraints.SOUTH;
		gbc_txtCc.insets = new Insets(0, 0, 5, 5);
		gbc_txtCc.gridx = 8;
		gbc_txtCc.gridy = 9;
		panel.add(txtCc, gbc_txtCc);
		txtCc.setColumns(10);
		
		//Init MSR
		txtMsr = new JTextField();
		txtMsr.setText("MSR");
		GridBagConstraints gbc_txtMsr = new GridBagConstraints();
		gbc_txtMsr.anchor = GridBagConstraints.SOUTH;
		gbc_txtMsr.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMsr.insets = new Insets(0, 0, 5, 5);
		gbc_txtMsr.gridx = 8;
		gbc_txtMsr.gridy = 11;
		panel.add(txtMsr, gbc_txtMsr);
		txtMsr.setColumns(10);
	}

}
