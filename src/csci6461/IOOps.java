package csci6461;

import co.com.csci.model.Instruction;

/*I/O Operations 
I/O operations communicate with the peripherals attached to the computer system. 
This is a really simple model of I/O meant to give you a flavor of how I/O works. 

061	IN r, devid
062	OUT r, devid
063	CHK r, devid
*/



public class IOOps {
	
	/*
	 * 0	Console Keyboard
	 * 1	Console Printer
	 * 2	Card Reader
	 * 3-31	Console Registers, switches, etc
	 */
	private static Integer ID_KEYBOARD = 0;
	private static Integer ID_CONSOLE_PRINTER = 1;
	private static Integer ID_CARD_READER = 2;
	private static Integer OTHER = 3; //Or mayor
	
	/**
	 * Input Character To Register from Device, r = 0..3
	 * @param instruction
	 * @throws Throwable
	 */
	public void instructionIN(Instruction instruction) throws Throwable{
		char readChar;
		//Read from keyboard
		if(instruction.getIntegerAddress() == ID_KEYBOARD){
			if(FrontPanel.txtInput.getText() != null &&
				FrontPanel.txtInput.getText().length() > 0){
				readChar = FrontPanel.txtInput.getText().charAt(0);
				int asciiChar = (int) readChar;
				FrontPanel.setRegister(instruction.getRegisterNumber(), asciiChar);
				if(FrontPanel.txtInput.getText().length() >= 2){
					FrontPanel.txtInput.setText(FrontPanel.txtInput.getText().substring(1));
				} else {
					FrontPanel.txtInput.setText(null);
				}
			} else {
				throw new Exception("No input to read in the interface, nothing done.");
			}
		}
	}
	

}
