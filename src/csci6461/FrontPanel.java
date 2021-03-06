package csci6461;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
import javax.swing.JComboBox;



public class FrontPanel {

	private JFrame frame;
	private JSplitPane splitPanel;
	
	private JTextField txtOpCode;
	private JTextField txtR;
	private JTextField txtIX;
	private JTextField txtI;
	private JTextField txtAddress;
	static JTextField txtMsr;

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
	static JTextField txtMfr;  //MFR
	static JTextField txtFR0;  //FR0
	static JTextField txtFR1;  //FR1
	
	static JTextPane txtEc; //EC
	static JTextField txtCc; //CC
	static JTextPane txtOutput; //Output
	static JTextPane txtInput;
	
	static public JTextPane[] memory; //All memory entries
	private JPanel panel_1;
	
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
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		//Split Panel (main panel)
		splitPanel = new JSplitPane();
		splitPanel.setDividerLocation(900);
		frame.getContentPane().add(splitPanel);
		
		//Memory Panel (right part of the app)
		JScrollPane memoryPanel = new JScrollPane();
		memoryPanel.setViewportBorder(new CompoundBorder());
		memoryPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//Init memory
		memory = new JTextPane[2048];
		splitPanel.setRightComponent(new MemoryPanel(2048, memory).getSplitPane());
		
		//Left Panel (left part of the part - all that isn't memory)
		panel_1 = new JPanel();
		splitPanel.setLeftComponent(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		panel_1.setLayout(gbl_panel_1);
		initLeftPanelComponents(panel_1);
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
		initLoadComboBox(panel);
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
	
	public static void setIndex(int indexNum, String content){
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
	
	public JTextPane getDirectInput() {
		return txtInput;
	}

	public void setDirectInput(JTextPane directInput) {
		txtInput = directInput;
	}
	
	private void initLoadComboBox(JPanel panel){
		JLabel lblLoad = new JLabel("Upload");
		GridBagConstraints gbc_lblLoad = new GridBagConstraints();
		gbc_lblLoad.anchor = GridBagConstraints.EAST;
		gbc_lblLoad.insets = new Insets(0, 0, 5, 5);
		gbc_lblLoad.gridx = 10;
		gbc_lblLoad.gridy = 1;
		panel_1.add(lblLoad, gbc_lblLoad);
		
		JComboBox<String> cmbLoad = new JComboBox();
		GridBagConstraints gbc_cmbLoad = new GridBagConstraints();
		gbc_cmbLoad.insets = new Insets(0, 0, 5, 0);
		gbc_cmbLoad.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbLoad.gridx = 11;
		gbc_cmbLoad.gridy = 1;
		panel_1.add(cmbLoad, gbc_cmbLoad);
		
		cmbLoad.addItem("");
		cmbLoad.addItem("Program 1");
		cmbLoad.addItem("Program 2");
		cmbLoad.addItem("AMR");
		cmbLoad.addItem("SMR");
		cmbLoad.addItem("AIR");
		cmbLoad.addItem("SIR");
		cmbLoad.addItem("MLT");
		cmbLoad.addItem("DVD");
		cmbLoad.addItem("TRR");
		cmbLoad.addItem("AND");
		cmbLoad.addItem("ORR");
		cmbLoad.addItem("NOT");
		cmbLoad.addItem("JZ");
		cmbLoad.addItem("JNE");
		cmbLoad.addItem("JCC");
		cmbLoad.addItem("JMA");
		cmbLoad.addItem("JSR");
		cmbLoad.addItem("RFS");
		cmbLoad.addItem("SOB");
		cmbLoad.addItem("JGE");
		cmbLoad.addItem("IN");
		cmbLoad.addItem("OUT");
		cmbLoad.addItem("LDR");
		cmbLoad.addItem("STR");
		cmbLoad.addItem("SRC");
		cmbLoad.addItem("RRC");
		cmbLoad.addItem("CHK");
		cmbLoad.addItem("FADD");
		cmbLoad.addItem("FSUB");
		cmbLoad.addItem("LDFR");
		cmbLoad.addItem("STFR");
		cmbLoad.addItem("VADD");
		cmbLoad.addItem("VSUB");
		cmbLoad.addItem("CNVRT");
		
		cmbLoad.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
					ItemSelectable is = itemEvent.getItemSelectable();
					Cache.getInstance().resetCache();
					resetInterface();
					resetMemory();
					HardCodeBuilder.loadProgram(selectedString(is));
				}
			}
		});
	}
	
	private String selectedString(ItemSelectable is) {
	    Object selected[] = is.getSelectedObjects();
	    return ((selected.length == 0) ? "null" : (String) selected[0]);
	}
	
	private void resetInterface(){
		setIndex(1, null);
		setIndex(2, null);
		setIndex(3, null);
		setRegister(0, null);
		setRegister(1, null);
		setRegister(2, null);
		setRegister(3, null);
		txtCc.setText("CC");
		//txtMsr.setText("MSR");
		txtEc.setText("EC");
		txtMar.setText(null);
		txtMbr.setText(null);
		txtIr.setText(null);
		txtInput.setText(null);
		txtOutput.setText("Output");
	}
	
	private void resetMemory(){
		for(int i = 0; i < 2048; i++){
			setMemory(i, 0);
		}
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
		
		//Init MBR
		JLabel lblMbr = new JLabel("MBR");
		GridBagConstraints gbc_lblMbr = new GridBagConstraints();
		gbc_lblMbr.anchor = GridBagConstraints.SOUTH;
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
		gbc_lblIr.anchor = GridBagConstraints.SOUTH;
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
		gbc_lblIar.anchor = GridBagConstraints.SOUTH;
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
		gbc_btnStop.gridx = 6;
		gbc_btnStop.gridy = 17;
		panel.add(btnStop, gbc_btnStop);
		
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
	
	private void initBtnResetCache(JPanel panel){
		JButton btnEmptyCache = new JButton("Reset Cache");
		GridBagConstraints gbc_btnEmptyCache = new GridBagConstraints();
		gbc_btnEmptyCache.insets = new Insets(0, 0, 5, 5);
		gbc_btnEmptyCache.anchor = GridBagConstraints.NORTH;
		gbc_btnEmptyCache.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEmptyCache.gridx = 8;
		gbc_btnEmptyCache.gridy = 11;
		panel_1.add(btnEmptyCache, gbc_btnEmptyCache);
		
		btnEmptyCache.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e){
			  Cache.getInstance().resetCache();
		  }
		});
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
			  //Get MSR
			  String MSR = txtMsr.getText();
			  //Get MFR
			  String MFR = txtMfr.getText();
			  //Get FR0
			  String FR0 = txtFR0.getText();
			  //Get FR1
			  String FR1 = txtFR1.getText();
			  //Get PC counter
			  String pc = txtPc.getText();
			  //Convert PC from binary to decimal 
			  Integer pcDecimal = Integer.parseInt(txtPc.getText(), 2);
			  //Get Instruction from memory space
			  
			  try
			  {
				  String plainInstruction = Cache.getInstance().checkCache(pcDecimal);
				  //String plainInstruction = memory[pcDecimal].getText();
				  Instruction instruction;
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
		  		  		case AMR:
		  		  			ArithmeticLogicalOps.instructionAMR(instruction);
	  		  				break;
		  		  		case SMR:
		  		  			ArithmeticLogicalOps.instructionSMR(instruction);
	  		  				break;
		  		  		case AIR:
		  		  			ArithmeticLogicalOps.instructionAIR(instruction);
	  		  				break;
		  		  		case SIR:
		  		  			ArithmeticLogicalOps.instructionSIR(instruction);
	  		  				break;
		  		  		case MLT:
		  		  			ArithmeticLogicalOps.instructionMLT(instruction);
	  		  				break; 
		  		  		case DVD:
		  		  			ArithmeticLogicalOps.instructionDVD(instruction);
	  		  				break;
		  		  		case TRR:
		  		  			ArithmeticLogicalOps.instructionTRR(instruction);
	  		  				break;
		  		  		case AND:
		  		  			ArithmeticLogicalOps.instructionAND(instruction);
	  		  				break;
		  		  		case ORR:
		  		  			ArithmeticLogicalOps.instructionORR(instruction);
	  		  				break;
		  		  		case NOT:
		  		  			ArithmeticLogicalOps.instructionNOT(instruction);
	  		  				break;
		  		  		case JZ:
		  		  			Transfer.instructionJZ(instruction);
		  		  			break;
			  		  	case JNE:
		  		  			Transfer.instructionJNE(instruction);
		  		  			break;	
				  		case JCC:
				  			Transfer.instructionJCC(instruction);
			  		  		break;	
				  		case JMA:
		  		  			Transfer.instructionJMA(instruction);
		  		  			break;	
				  		case JSR:
		  		  			Transfer.instructionRFS(instruction);
		  		  			break;	
				  		case RFS:
		  		  			Transfer.instructionSOB(instruction);
		  		  			break;	
				  		case SOB:
		  		  			Transfer.instructionJGE(instruction);
		  		  			break;	
				  		case JGE:
		  		  			Transfer.instructionJZ(instruction);
		  		  			break;
				  		case IN:
		  		  			IOOps.instructionIN(instruction);
		  		  			break;
				  		case OUT:
		  		  			IOOps.instructionOUT(instruction);
		  		  			break;
				  		case CHK:
		  		  			IOOps.instructionCHK(instruction);
		  		  			break;
		  		  		case SRC:
		  		  			ArithmeticLogicalOps.instructionSRC(instruction);
	  		  				break;
		  		  		case RRC:
		  		  			ArithmeticLogicalOps.instructionRRC(instruction);
		  		  			break;
		  		  		case FADD:
		  		  			FloatingPointVectorOps.instructionFADD(instruction);
	  		  				break;
		  		  		case FSUB:
		  		  			FloatingPointVectorOps.instructionFSUB(instruction);
	  		  				break;
		  		  		case LDFR:
		  		  			FloatingPointVectorOps.instructionLDFR(instruction);
	  		  				break;
		  		  		case STFR:
		  		  			FloatingPointVectorOps.instructionSTFR(instruction);
	  		  				break;
		  		  		case VADD:
		  		  			FloatingPointVectorOps.instructionVADD(instruction);
	  		  				break;
		  		  		case VSUB:
		  		  			FloatingPointVectorOps.instructionVSUB(instruction);
	  		  				break;
		  		  		case CNVRT:
		  		  			FloatingPointVectorOps.instructionCNVRT(instruction);
	  		  				break;
		  		  		case HALT:
		  		  			txtOutput.setText("HALT");
			  				break;
		  		  		case FAULT:
		  		  			txtOutput.setText("FAULT");
			  		  		/* Machine Fault
			  		  		 * Illegal Operation Code */
			  		  			pc = txtPc.getText();
			  		  			String msr = txtMsr.getText();
			  		  			memory[1].setText(msr);
			  		  			memory[4].setText(pc);
			  		  			txtMfr.setText("2");
		  		  			break;
				  }
				  //Increment PC counter
				  if((iCode != InstructionEnum.HALT) && (iCode != InstructionEnum.JZ)
					  && (iCode != InstructionEnum.JCC) && (iCode != InstructionEnum.JNE) 
					  && (iCode != InstructionEnum.JMA)){
					  pcDecimal++;
					  txtPc.setText(BinaryUtil.fillBinaryString(Integer.toBinaryString(pcDecimal)));
				  }
			  } catch(IndexOutOfBoundsException m){
						pc = txtPc.getText();
			  			String msr = txtMsr.getText();
			  			memory[1].setText(msr);
			  			memory[4].setText(pc);
			  			txtMfr.setText("3"); 	
			  			}
		  	 catch (Throwable t){
		  		txtOutput.setText("FAULT");
		  		
		  	}
		  }
		});
	}
	
	private void initBtnStop(JPanel panel) {
	}

	private void initBtnLoad(JPanel panel) {
		JToggleButton btnExecute = new JToggleButton("Execute");
		GridBagConstraints gbc_btnExecute = new GridBagConstraints();
		gbc_btnExecute.anchor = GridBagConstraints.NORTH;
		gbc_btnExecute.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExecute.insets = new Insets(0, 0, 0, 5);
		gbc_btnExecute.gridwidth = 2;
		gbc_btnExecute.gridx = 3;
		gbc_btnExecute.gridy = 17;
		panel.add(btnExecute, gbc_btnExecute);
	}

	private void initBtnExecute(JPanel panel) {
		JToggleButton btnLoad = new JToggleButton("Load");
		GridBagConstraints gbc_btnLoad = new GridBagConstraints();
		gbc_btnLoad.gridwidth = 2;
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
		initBtnResetCache(panel);
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
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 14;
		txtInput = new JTextPane();
		scrollPane.add(txtInput);
		panel_1.add(scrollPane, gbc_scrollPane);
		scrollPane.setViewportView(txtInput);
		
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
		
		//Msr Label
		JLabel lblMsr = new JLabel("MSR");
		GridBagConstraints gbc_lblMsr = new GridBagConstraints();
		gbc_lblMsr.anchor = GridBagConstraints.SOUTH;
		gbc_lblMsr.insets = new Insets(0, 0, 5, 5);
		gbc_lblMsr.gridx = 8;
		gbc_lblMsr.gridy = 12;
		panel.add(lblMsr, gbc_lblMsr);
		
		//Init MSR
		txtMsr = new JTextField();
		txtMsr.setText("0000000000000001");
		GridBagConstraints gbc_txtMsr = new GridBagConstraints();
		gbc_txtMsr.anchor = GridBagConstraints.SOUTH;
		gbc_txtMsr.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMsr.insets = new Insets(0, 0, 5, 5);
		gbc_txtMsr.gridx = 8;
		gbc_txtMsr.gridy = 13;
		panel.add(txtMsr, gbc_txtMsr);
		txtMsr.setColumns(10);
		
		//Mfr Label
		JLabel lblMfr = new JLabel("MFR");
		GridBagConstraints gbc_lblMfr = new GridBagConstraints();
		gbc_lblMfr.anchor = GridBagConstraints.SOUTH;
		gbc_lblMfr.insets = new Insets(0, 0, 5, 5);
		gbc_lblMfr.gridx = 8;
		gbc_lblMfr.gridy = 14;
		panel.add(lblMfr, gbc_lblMfr);
		
		//Init MFR
		txtMfr = new JTextField();
		txtMfr.setText("");
		GridBagConstraints gbc_txtMfr = new GridBagConstraints();
		gbc_txtMfr.anchor = GridBagConstraints.SOUTH;
		gbc_txtMfr.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMfr.insets = new Insets(0, 0, 5, 5);
		gbc_txtMfr.gridx = 8;
		gbc_txtMfr.gridy = 15;
		panel.add(txtMfr, gbc_txtMfr);
		txtMfr.setColumns(10);
		
		//FR0 Label
		JLabel lblFR0 = new JLabel("FR0");
		GridBagConstraints gbc_lblFR0 = new GridBagConstraints();
		gbc_lblFR0.anchor = GridBagConstraints.SOUTH;
		gbc_lblFR0.insets = new Insets(0, 0, 5, 5);
		gbc_lblFR0.gridx = 8;
		gbc_lblFR0.gridy = 16;
		panel.add(lblFR0, gbc_lblFR0);
		
		//Init FR0
		txtFR0 = new JTextField();
		txtFR0.setText("");
		GridBagConstraints gbc_txtFR0 = new GridBagConstraints();
		gbc_txtFR0.anchor = GridBagConstraints.SOUTH;
		gbc_txtFR0.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFR0.insets = new Insets(0, 0, 5, 5);
		gbc_txtFR0.gridx = 8;
		gbc_txtFR0.gridy = 17;
		panel.add(txtFR0, gbc_txtFR0);
		txtFR0.setColumns(10);
		
		
		//FR1 Label
		JLabel lblFR1 = new JLabel("FR1");
		GridBagConstraints gbc_lblFR1 = new GridBagConstraints();
		gbc_lblFR1.anchor = GridBagConstraints.SOUTH;
		gbc_lblFR1.insets = new Insets(0, 0, 5, 5);
		gbc_lblFR1.gridx = 8;
		gbc_lblFR1.gridy = 18;
		panel.add(lblFR1, gbc_lblFR1);
		
		//Init FR1
		txtFR1 = new JTextField();
		txtFR1.setText("");
		GridBagConstraints gbc_txtFR1 = new GridBagConstraints();
		gbc_txtFR1.anchor = GridBagConstraints.SOUTH;
		gbc_txtFR1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFR1.insets = new Insets(0, 0, 5, 5);
		gbc_txtFR1.gridx = 8;
		gbc_txtFR1.gridy = 19;
		panel.add(txtFR1, gbc_txtFR1);
		txtFR1.setColumns(10);
	}
	
	public static void setPc(Integer pc){
		txtPc.setText(BinaryUtil.fillBinaryStringParam(Integer.toBinaryString(pc), 16));
	}
	
	public static void setMemory(Integer pos, Integer content){
		memory[pos].setText(BinaryUtil.fillBinaryStringParam(Integer.toBinaryString(content), 16));
	}
	
	public static void setRegister(Integer registerNum, Integer content){
		if(registerNum == 0){
			txtR0.setText(BinaryUtil.fillBinaryStringParam(Integer.toBinaryString(content), 16));
		} else if(registerNum == 1){
			txtR1.setText(BinaryUtil.fillBinaryStringParam(Integer.toBinaryString(content), 16));
		} else  if(registerNum == 2){
			txtR2.setText(BinaryUtil.fillBinaryStringParam(Integer.toBinaryString(content), 16));
		} else if(registerNum == 3){
			txtR3.setText(BinaryUtil.fillBinaryStringParam(Integer.toBinaryString(content), 16));
		} else {
			//Exception
		}
	}
	
	public static void setIndex(Integer indexNum, Integer content){
		if(indexNum == 1){
			txtX1.setText(BinaryUtil.fillBinaryStringParam(Integer.toBinaryString(content), 16));
		} else  if(indexNum == 2){
			txtX2.setText(BinaryUtil.fillBinaryStringParam(Integer.toBinaryString(content), 16));
		} else if(indexNum == 3){
			txtX3.setText(BinaryUtil.fillBinaryStringParam(Integer.toBinaryString(content), 16));
		} else {
			//Exception
		}
	}
	
	public static void autoSingleStepClicker(){
		//Get PC counter
		String pc = txtPc.getText();
		//Convert PC from binary to decimal 
		Integer pcDecimal = Integer.parseInt(txtPc.getText(), 2);
		
		Instruction instruction;
		try
		{
			//Get Instruction from memory space
			String plainInstruction = Cache.getInstance().checkCache(pcDecimal);
			instruction = new Instruction(plainInstruction);
			//Change MAR
			txtMar.setText(pc);
			//Change MBR
			txtMbr.setText(plainInstruction);
			//Change IR
			txtIr.setText(plainInstruction);
			//Evaluate instruction
			InstructionEnum iCode = instruction.getIntructionCode();
			
			System.out.println("Program counter: " + pcDecimal);
			System.out.println("Running instruction: " + iCode);
			System.out.println("Register 0: " + getRegister(0));
			System.out.println("Register 1: " + getRegister(1));
			System.out.println("Paragraph index: " + (Integer.parseInt(getIndex(1), 2)));
			System.out.println("Word index: " + (Integer.parseInt(getIndex(2), 2)));
			System.out.println("Letters found counter: " + (Integer.parseInt(memory[14].getText(), 2)));
			System.out.println("Word #: " + (Integer.parseInt(memory[17].getText(), 2)));
			System.out.println("Sentence #: " + (Integer.parseInt(memory[18].getText(), 2)));
			
			switch(iCode){
			case LDR:
				LoadStore.instructionLDR(instruction);
				break;
			case STR:
				LoadStore.instructionSTR(instruction);
				break;
			case AMR:
				ArithmeticLogicalOps.instructionAMR(instruction);
				break;
			case SMR:
				ArithmeticLogicalOps.instructionSMR(instruction);
				break;
			case AIR:
				ArithmeticLogicalOps.instructionAIR(instruction);
				break;
			case SIR:
				ArithmeticLogicalOps.instructionSIR(instruction);
				break;
			case MLT:
				ArithmeticLogicalOps.instructionMLT(instruction);
				break; 
			case DVD:
				ArithmeticLogicalOps.instructionDVD(instruction);
				break;
			case TRR:
				ArithmeticLogicalOps.instructionTRR(instruction);
				break;
			case AND:
				ArithmeticLogicalOps.instructionAND(instruction);
				break;
			case ORR:
				ArithmeticLogicalOps.instructionORR(instruction);
				break;
			case NOT:
				ArithmeticLogicalOps.instructionNOT(instruction);
				break;
			case JZ:
				Transfer.instructionJZ(instruction);
				break;
			case JNE:
				Transfer.instructionJNE(instruction);
				break;	
			case JCC:
				Transfer.instructionJCC(instruction);
				break;	
			case JMA:
				Transfer.instructionJMA(instruction);
				break;	
			case JSR:
				Transfer.instructionRFS(instruction);
				break;	
			case RFS:
				Transfer.instructionSOB(instruction);
				break;	
			case SOB:
				Transfer.instructionJGE(instruction);
				break;	
			case JGE:
				Transfer.instructionJZ(instruction);
				break;
			case IN:
				IOOps.instructionIN(instruction);
				break;
			case OUT:
				IOOps.instructionOUT(instruction);
				break;
			case LDA:
				LoadStore.instructionLDA(instruction);
				break;
			case STX:
				LoadStore.instructionSTX(instruction);
				break;
			case LDX:
				LoadStore.instructionLDX(instruction);
			case HALT:
				txtOutput.setText("HALT");
				break;
			case FAULT:
				txtOutput.setText("FAULT");
				break;
			}
			//Increment PC counter
			if((iCode != InstructionEnum.HALT) && (iCode != InstructionEnum.JZ)
					&& (iCode != InstructionEnum.JCC) && (iCode != InstructionEnum.JNE) 
					&& (iCode != InstructionEnum.JMA)){
				pcDecimal++;
				txtPc.setText(BinaryUtil.fillBinaryString(Integer.toBinaryString(pcDecimal)));
			}
		} catch (Throwable t){
			txtOutput.setText("FAULT");

		}
	}
	
	public static Boolean isPC(Integer x){
		Integer num = Integer.parseInt(txtPc.getText(), 2);
		return num == x;
	}
	
	public static String getFRField(Integer num){
		if(num == 0){
			return txtFR0.getText();
		} else if (num == 1){
			return txtFR1.getText();
		} else {
			return null;
		}
	}
	
	public static void setFRField(int num, String content){
		if(num == 0){
			txtFR0.setText(content);
		} else if(num == 1){
			txtFR1.setText(content);
		} else {
			//Exception
		}
	}

}
