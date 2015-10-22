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
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;



public class FrontPanel {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField txtMsr;

	
	static JTextPane txtpnR; // TextInput for R0
	static JTextPane txtpnR_1;// TextInput for R1
	static JTextPane txtpnR_2;// TextInput for R2
	static JTextPane textPane_16;// TextInput for R3
	static JTextPane txtpnX;// TextInput for X1
	static JTextPane txtpnX_1;// TextInput for X2
	static JTextPane txtpnX_2;// TextInput for X3
	
	static JTextPane[] memory; //All memory entries
	
	static JTextPane txtpnPc; //PC
	static JTextPane txtpnMar; //MAR
	static JTextPane txtpnMbr; //MBR
	static JTextPane txtpnIr;  //IR
	private static JTextField cc;
	private static JTextField directInput;
	private JTextField textField_5;
	
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
		gbc_textField_5.anchor = GridBagConstraints.NORTH;
		gbc_textField_5.insets = new Insets(0, 0, 5, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 2;
		gbc_textField_5.gridy = 1;
		frame.getContentPane().add(textField_5, gbc_textField_5);
		
		JLabel lblCiscSimulator = new JLabel("CISC Simulator");
		lblCiscSimulator.setFont(new Font("Tahoma", Font.BOLD, 25));
		GridBagConstraints gbc_lblCiscSimulator = new GridBagConstraints();
		gbc_lblCiscSimulator.anchor = GridBagConstraints.WEST;
		gbc_lblCiscSimulator.fill = GridBagConstraints.VERTICAL;
		gbc_lblCiscSimulator.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiscSimulator.gridwidth = 6;
		gbc_lblCiscSimulator.gridx = 9;
		gbc_lblCiscSimulator.gridy = 1;
		frame.getContentPane().add(lblCiscSimulator, gbc_lblCiscSimulator);
		
		JLabel lblOpcode = new JLabel("OpCode");
		GridBagConstraints gbc_lblOpcode = new GridBagConstraints();
		gbc_lblOpcode.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblOpcode.insets = new Insets(0, 0, 5, 5);
		gbc_lblOpcode.gridx = 1;
		gbc_lblOpcode.gridy = 2;
		frame.getContentPane().add(lblOpcode, gbc_lblOpcode);
		
		JLabel lblR = new JLabel("R");
		GridBagConstraints gbc_lblR = new GridBagConstraints();
		gbc_lblR.anchor = GridBagConstraints.NORTH;
		gbc_lblR.insets = new Insets(0, 0, 5, 5);
		gbc_lblR.gridx = 3;
		gbc_lblR.gridy = 2;
		frame.getContentPane().add(lblR, gbc_lblR);
		
		JLabel lblIx = new JLabel("IX");
		GridBagConstraints gbc_lblIx = new GridBagConstraints();
		gbc_lblIx.anchor = GridBagConstraints.NORTH;
		gbc_lblIx.insets = new Insets(0, 0, 5, 5);
		gbc_lblIx.gridwidth = 2;
		gbc_lblIx.gridx = 4;
		gbc_lblIx.gridy = 2;
		frame.getContentPane().add(lblIx, gbc_lblIx);
		
		JLabel lblI = new JLabel("   I");
		GridBagConstraints gbc_lblI = new GridBagConstraints();
		gbc_lblI.anchor = GridBagConstraints.NORTH;
		gbc_lblI.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblI.insets = new Insets(0, 0, 5, 5);
		gbc_lblI.gridx = 6;
		gbc_lblI.gridy = 2;
		frame.getContentPane().add(lblI, gbc_lblI);
		
		JLabel lblMemory = new JLabel("Memory");
		lblMemory.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblMemory = new GridBagConstraints();
		gbc_lblMemory.anchor = GridBagConstraints.NORTH;
		gbc_lblMemory.insets = new Insets(0, 0, 5, 5);
		gbc_lblMemory.gridwidth = 2;
		gbc_lblMemory.gridx = 14;
		gbc_lblMemory.gridy = 2;
		frame.getContentPane().add(lblMemory, gbc_lblMemory);
		
		//OpCode
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.SOUTH;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridwidth = 2;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 3;
		frame.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		//R
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.anchor = GridBagConstraints.SOUTH;
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 3;
		gbc_textField_1.gridy = 3;
		frame.getContentPane().add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		//IX
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.anchor = GridBagConstraints.SOUTH;
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridwidth = 2;
		gbc_textField_2.gridx = 4;
		gbc_textField_2.gridy = 3;
		frame.getContentPane().add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		//I
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.anchor = GridBagConstraints.SOUTH;
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.gridx = 6;
		gbc_textField_3.gridy = 3;
		frame.getContentPane().add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		//Address
		textField_4 = new JTextField();
		textField_4.setText("1010101010101010");
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.anchor = GridBagConstraints.SOUTH;
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.gridwidth = 2;
		gbc_textField_4.gridx = 7;
		gbc_textField_4.gridy = 3;
		frame.getContentPane().add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JLabel lblPc = new JLabel("PC");
		GridBagConstraints gbc_lblPc = new GridBagConstraints();
		gbc_lblPc.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblPc.insets = new Insets(0, 0, 5, 5);
		gbc_lblPc.gridx = 10;
		gbc_lblPc.gridy = 3;
		frame.getContentPane().add(lblPc, gbc_lblPc);
		
		txtpnPc = new JTextPane();
		txtpnPc.setText("0000000000000000");
		GridBagConstraints gbc_txtpnPc = new GridBagConstraints();
		gbc_txtpnPc.anchor = GridBagConstraints.SOUTH;
		gbc_txtpnPc.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtpnPc.insets = new Insets(0, 0, 5, 5);
		gbc_txtpnPc.gridx = 11;
		gbc_txtpnPc.gridy = 3;
		frame.getContentPane().add(txtpnPc, gbc_txtpnPc);
		
		
		
		//Output area for processing messages (i.e. troubleshooting, trap messages
		
		JTextPane txtpnEc = new JTextPane();
		txtpnEc.setText("EC");
		GridBagConstraints gbc_txtpnEc = new GridBagConstraints();
		gbc_txtpnEc.fill = GridBagConstraints.BOTH;
		gbc_txtpnEc.insets = new Insets(0, 0, 5, 5);
		gbc_txtpnEc.gridheight = 3;
		gbc_txtpnEc.gridwidth = 8;
		gbc_txtpnEc.gridx = 1;
		gbc_txtpnEc.gridy = 5;
		frame.getContentPane().add(txtpnEc, gbc_txtpnEc);
		
		JLabel lblMar = new JLabel("MAR");
		GridBagConstraints gbc_lblMar = new GridBagConstraints();
		gbc_lblMar.anchor = GridBagConstraints.SOUTH;
		gbc_lblMar.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMar.insets = new Insets(0, 0, 5, 5);
		gbc_lblMar.gridx = 10;
		gbc_lblMar.gridy = 4;
		frame.getContentPane().add(lblMar, gbc_lblMar);
		
		txtpnMar = new JTextPane();
		txtpnMar.setText("");
		GridBagConstraints gbc_txtpnMar = new GridBagConstraints();
		gbc_txtpnMar.anchor = GridBagConstraints.NORTH;
		gbc_txtpnMar.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtpnMar.insets = new Insets(0, 0, 5, 5);
		gbc_txtpnMar.gridx = 11;
		gbc_txtpnMar.gridy = 4;
		frame.getContentPane().add(txtpnMar, gbc_txtpnMar);
		
		JTextPane textPane = new JTextPane();
		//No indirect
		textPane.setText("0000010001000001");
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.anchor = GridBagConstraints.SOUTH;
		gbc_textPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPane.insets = new Insets(0, 0, 5, 5);
		gbc_textPane.gridheight = 2;
		gbc_textPane.gridx = 14;
		gbc_textPane.gridy = 4;
		frame.getContentPane().add(textPane, gbc_textPane);
		memory = new JTextPane[16];
		memory[0] = textPane;
		
		JLabel lblMbr = new JLabel("MBR");
		GridBagConstraints gbc_lblMbr = new GridBagConstraints();
		gbc_lblMbr.anchor = GridBagConstraints.NORTH;
		gbc_lblMbr.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMbr.insets = new Insets(0, 0, 5, 5);
		gbc_lblMbr.gridx = 10;
		gbc_lblMbr.gridy = 5;
		frame.getContentPane().add(lblMbr, gbc_lblMbr);
		
		txtpnMbr = new JTextPane();
		txtpnMbr.setText("");
		GridBagConstraints gbc_txtpnMbr = new GridBagConstraints();
		gbc_txtpnMbr.anchor = GridBagConstraints.SOUTH;
		gbc_txtpnMbr.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtpnMbr.insets = new Insets(0, 0, 5, 5);
		gbc_txtpnMbr.gridx = 11;
		gbc_txtpnMbr.gridy = 5;
		frame.getContentPane().add(txtpnMbr, gbc_txtpnMbr);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setText("");
		GridBagConstraints gbc_textPane_5 = new GridBagConstraints();
		gbc_textPane_5.anchor = GridBagConstraints.SOUTH;
		gbc_textPane_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPane_5.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_5.gridx = 16;
		gbc_textPane_5.gridy = 5;
		frame.getContentPane().add(textPane_5, gbc_textPane_5);
		memory[8] = textPane_5;
		
		JLabel lblIr = new JLabel("IR");
		GridBagConstraints gbc_lblIr = new GridBagConstraints();
		gbc_lblIr.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblIr.insets = new Insets(0, 0, 5, 5);
		gbc_lblIr.gridx = 10;
		gbc_lblIr.gridy = 6;
		frame.getContentPane().add(lblIr, gbc_lblIr);
		
		txtpnIr = new JTextPane();
		txtpnIr.setText("");
		GridBagConstraints gbc_txtpnIr = new GridBagConstraints();
		gbc_txtpnIr.anchor = GridBagConstraints.SOUTH;
		gbc_txtpnIr.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtpnIr.insets = new Insets(0, 0, 5, 5);
		gbc_txtpnIr.gridx = 11;
		gbc_txtpnIr.gridy = 6;
		frame.getContentPane().add(txtpnIr, gbc_txtpnIr);
		
		JLabel label_1 = new JLabel("01");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.NORTHEAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 13;
		gbc_label_1.gridy = 6;
		frame.getContentPane().add(label_1, gbc_label_1);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("0000000001100011");
		GridBagConstraints gbc_textPane_1 = new GridBagConstraints();
		gbc_textPane_1.anchor = GridBagConstraints.SOUTH;
		gbc_textPane_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_1.gridx = 14;
		gbc_textPane_1.gridy = 6;
		frame.getContentPane().add(textPane_1, gbc_textPane_1);
		memory[1] = textPane_1;
		
		JLabel label_4 = new JLabel("09");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.anchor = GridBagConstraints.NORTH;
		gbc_label_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 15;
		gbc_label_4.gridy = 6;
		frame.getContentPane().add(label_4, gbc_label_4);
		
		JTextPane textPane_6 = new JTextPane();
		textPane_6.setText("");
		GridBagConstraints gbc_textPane_6 = new GridBagConstraints();
		gbc_textPane_6.anchor = GridBagConstraints.SOUTH;
		gbc_textPane_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPane_6.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_6.gridx = 16;
		gbc_textPane_6.gridy = 6;
		frame.getContentPane().add(textPane_6, gbc_textPane_6);
		memory[9] = textPane_6;
		
		JLabel label = new JLabel("00");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.NORTHEAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 13;
		gbc_label.gridy = 5;
		frame.getContentPane().add(label, gbc_label);
		
		JLabel label_3 = new JLabel("08");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.NORTH;
		gbc_label_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 15;
		gbc_label_3.gridy = 5;
		frame.getContentPane().add(label_3, gbc_label_3);
		
		JLabel lblIar = new JLabel("IAR");
		GridBagConstraints gbc_lblIar = new GridBagConstraints();
		gbc_lblIar.gridheight = 2;
		gbc_lblIar.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblIar.insets = new Insets(0, 0, 5, 5);
		gbc_lblIar.gridx = 10;
		gbc_lblIar.gridy = 7;
		frame.getContentPane().add(lblIar, gbc_lblIar);
		
		JTextPane textPane_17 = new JTextPane();
		textPane_17.setText("");
		GridBagConstraints gbc_textPane_17 = new GridBagConstraints();
		gbc_textPane_17.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_17.fill = GridBagConstraints.BOTH;
		gbc_textPane_17.gridx = 11;
		gbc_textPane_17.gridy = 7;
		frame.getContentPane().add(textPane_17, gbc_textPane_17);
		
		JLabel label_2 = new JLabel("02");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.NORTHEAST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 13;
		gbc_label_2.gridy = 7;
		frame.getContentPane().add(label_2, gbc_label_2);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("");
		GridBagConstraints gbc_textPane_2 = new GridBagConstraints();
		gbc_textPane_2.anchor = GridBagConstraints.SOUTH;
		gbc_textPane_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPane_2.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_2.gridx = 14;
		gbc_textPane_2.gridy = 7;
		frame.getContentPane().add(textPane_2, gbc_textPane_2);
		memory[2] = textPane_2;
		
		JLabel label_5 = new JLabel("10");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 15;
		gbc_label_5.gridy = 7;
		frame.getContentPane().add(label_5, gbc_label_5);
		
		JTextPane textPane_7 = new JTextPane();
		textPane_7.setText("");
		GridBagConstraints gbc_textPane_7 = new GridBagConstraints();
		gbc_textPane_7.anchor = GridBagConstraints.SOUTH;
		gbc_textPane_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPane_7.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_7.gridx = 16;
		gbc_textPane_7.gridy = 7;
		frame.getContentPane().add(textPane_7, gbc_textPane_7);
		memory[10] = textPane_7;
		
		JLabel label_11 = new JLabel("03");
		GridBagConstraints gbc_label_11 = new GridBagConstraints();
		gbc_label_11.anchor = GridBagConstraints.NORTHEAST;
		gbc_label_11.insets = new Insets(0, 0, 5, 5);
		gbc_label_11.gridx = 13;
		gbc_label_11.gridy = 8;
		frame.getContentPane().add(label_11, gbc_label_11);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setText("");
		GridBagConstraints gbc_textPane_3 = new GridBagConstraints();
		gbc_textPane_3.anchor = GridBagConstraints.NORTH;
		gbc_textPane_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPane_3.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_3.gridx = 14;
		gbc_textPane_3.gridy = 8;
		frame.getContentPane().add(textPane_3, gbc_textPane_3);
		memory[3] = textPane_3;
		
		JLabel label_6 = new JLabel("11");
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.anchor = GridBagConstraints.NORTH;
		gbc_label_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_6.insets = new Insets(0, 0, 5, 5);
		gbc_label_6.gridx = 15;
		gbc_label_6.gridy = 8;
		frame.getContentPane().add(label_6, gbc_label_6);
		
		JTextPane textPane_8 = new JTextPane();
		textPane_8.setText("");
		GridBagConstraints gbc_textPane_8 = new GridBagConstraints();
		gbc_textPane_8.anchor = GridBagConstraints.NORTH;
		gbc_textPane_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPane_8.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_8.gridx = 16;
		gbc_textPane_8.gridy = 8;
		frame.getContentPane().add(textPane_8, gbc_textPane_8);
		memory[11] = textPane_8;
		
		
		//Area for general output
		
		JTextPane txtrOutput = new JTextPane();
		txtrOutput.setText("Output");
		GridBagConstraints gbc_txtrOutput = new GridBagConstraints();
		gbc_txtrOutput.fill = GridBagConstraints.BOTH;
		gbc_txtrOutput.insets = new Insets(0, 0, 5, 5);
		gbc_txtrOutput.gridheight = 5;
		gbc_txtrOutput.gridwidth = 6;
		gbc_txtrOutput.gridx = 1;
		gbc_txtrOutput.gridy = 9;
		frame.getContentPane().add(txtrOutput, gbc_txtrOutput);
		
		//output for the condition code (CC)
		cc = new JTextField();
		GridBagConstraints gbc_cc = new GridBagConstraints();
		gbc_cc.fill = GridBagConstraints.HORIZONTAL;
		gbc_cc.anchor = GridBagConstraints.SOUTH;
		gbc_cc.insets = new Insets(0, 0, 5, 5);
		gbc_cc.gridx = 8;
		gbc_cc.gridy = 9;
		frame.getContentPane().add(cc, gbc_cc);
		cc.setColumns(10);
		
		JLabel lblRegisters = new JLabel("Registers");
		lblRegisters.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblRegisters = new GridBagConstraints();
		gbc_lblRegisters.anchor = GridBagConstraints.SOUTH;
		gbc_lblRegisters.insets = new Insets(0, 0, 5, 5);
		gbc_lblRegisters.gridx = 11;
		gbc_lblRegisters.gridy = 9;
		frame.getContentPane().add(lblRegisters, gbc_lblRegisters);
		
		JLabel label_12 = new JLabel("04");
		GridBagConstraints gbc_label_12 = new GridBagConstraints();
		gbc_label_12.anchor = GridBagConstraints.NORTHEAST;
		gbc_label_12.insets = new Insets(0, 0, 5, 5);
		gbc_label_12.gridx = 13;
		gbc_label_12.gridy = 9;
		frame.getContentPane().add(label_12, gbc_label_12);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setText("");
		GridBagConstraints gbc_textPane_4 = new GridBagConstraints();
		gbc_textPane_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPane_4.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_4.gridx = 14;
		gbc_textPane_4.gridy = 9;
		frame.getContentPane().add(textPane_4, gbc_textPane_4);
		memory[4] = textPane_4;
		
		JLabel label_7 = new JLabel("12");
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.anchor = GridBagConstraints.NORTH;
		gbc_label_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_label_7.insets = new Insets(0, 0, 5, 5);
		gbc_label_7.gridx = 15;
		gbc_label_7.gridy = 9;
		frame.getContentPane().add(label_7, gbc_label_7);
		
		JTextPane textPane_9 = new JTextPane();
		textPane_9.setText("");
		GridBagConstraints gbc_textPane_9 = new GridBagConstraints();
		gbc_textPane_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPane_9.insets = new Insets(0, 0, 5, 5);
		gbc_textPane_9.gridx = 16;
		gbc_textPane_9.gridy = 9;
		frame.getContentPane().add(textPane_9, gbc_textPane_9);
		memory[12] = textPane_9;
		
		JButton btnSingle = new JButton("Single Instruction");
		GridBagConstraints gbc_btnSingle = new GridBagConstraints();
		gbc_btnSingle.anchor = GridBagConstraints.NORTH;
		gbc_btnSingle.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSingle.insets = new Insets(0, 0, 5, 5);
		gbc_btnSingle.gridx = 8;
		gbc_btnSingle.gridy = 10;
		frame.getContentPane().add(btnSingle, gbc_btnSingle);
		
		btnSingle.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  txtrOutput.setText("Output");
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
		
		txtpnX = new JTextPane();
		txtpnX.setText("");
		GridBagConstraints gbc_txtpnX = new GridBagConstraints();
		gbc_txtpnX.insets = new Insets(0, 0, 5, 5);
		gbc_txtpnX.fill = GridBagConstraints.BOTH;
		gbc_txtpnX.gridx = 11;
		gbc_txtpnX.gridy = 15;
		frame.getContentPane().add(txtpnX, gbc_txtpnX);
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
		gbc_tglbtnExecute.fill = GridBagConstraints.HORIZONTAL;
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
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 8;
		gbc_btnNewButton.gridy = 16;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		JLabel lblX_1 = new JLabel("X2");
		GridBagConstraints gbc_lblX_1 = new GridBagConstraints();
		gbc_lblX_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblX_1.gridx = 10;
		gbc_lblX_1.gridy = 16;
		frame.getContentPane().add(lblX_1, gbc_lblX_1);
		
		txtpnX_1 = new JTextPane();
		txtpnX_1.setText("");
		GridBagConstraints gbc_txtpnX_1 = new GridBagConstraints();
		gbc_txtpnX_1.anchor = GridBagConstraints.SOUTH;
		gbc_txtpnX_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtpnX_1.insets = new Insets(0, 0, 5, 5);
		gbc_txtpnX_1.gridx = 11;
		gbc_txtpnX_1.gridy = 16;
		frame.getContentPane().add(txtpnX_1, gbc_txtpnX_1);
		
		JLabel lblX_2 = new JLabel("X3");
		GridBagConstraints gbc_lblX_2 = new GridBagConstraints();
		gbc_lblX_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblX_2.gridx = 10;
		gbc_lblX_2.gridy = 17;
		frame.getContentPane().add(lblX_2, gbc_lblX_2);
		
		txtpnX_2 = new JTextPane();
		txtpnX_2.setText("");
		GridBagConstraints gbc_txtpnX_2 = new GridBagConstraints();
		gbc_txtpnX_2.anchor = GridBagConstraints.NORTH;
		gbc_txtpnX_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtpnX_2.insets = new Insets(0, 0, 5, 5);
		gbc_txtpnX_2.gridx = 11;
		gbc_txtpnX_2.gridy = 17;
		frame.getContentPane().add(txtpnX_2, gbc_txtpnX_2);
		
		//Button to trigger a single step in the process (i.e. fetch instruction etc.)
		
		//JButton btnSingleStep = new JButton("Single Step");
		//btnSingleStep.setBounds(286, 204, 153, 23);
		//frame.getContentPane().add(btnSingleStep);
		
		
		//Main Execution Area
		
		/*Button to load instruction from input or file (may have to add another feature 
		 * or create another to load instructions from a file.
		 */
		
		//StartsMemory
		memory = new JTextPane[16];
		
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
	
	public static void setRegister(int registerNum, String content){
		if(registerNum == 0){
			txtpnR.setText(content);
		} else if(registerNum == 1){
			txtpnR_1.setText(content);
		} else  if(registerNum == 2){
			txtpnR_2.setText(content);
		} else if(registerNum == 3){
			textPane_16.setText(content);
		} else {
			//Exception
		}
	}
	
	public static String getRegister(int registerNum){
		if(registerNum == 0){
			return txtpnR.getText();
		} else if(registerNum == 1){
			return txtpnR_1.getText();
		} else  if(registerNum == 2){
			return txtpnR_2.getText();
		} else if(registerNum == 3){
			return textPane_16.getText();
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
	
	public static String getIndex(int indexNum){
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
	
	public void setCC(int CCNum, String content){
		if(CCNum == 1){
			cc.setText(content);
		} else  if(CCNum == 2){
			cc.setText(content);
		} else if(CCNum == 3){
			cc.setText(content);
		} else if(CCNum == 4){
			cc.setText(content);
		} else {
			//Exception
		}
	}
	
	public static String getCC(int CCNum){
		if(CCNum == 1){
			return cc.getText();
		} else  if(CCNum == 2){
			return cc.getText();
		} else if(CCNum == 3){
			return cc.getText();
		} else if(CCNum == 4){
			return cc.getText();
		} else {
			//Exception
		}
		return "";
	}

	public JTextField getDirectInput() {
		return directInput;
	}

	public void setDirectInput(JTextField directInput) {
		this.directInput = directInput;
	}
}
