package csci6461;

import java.math.BigInteger;

import co.com.csci.model.Instruction;
import co.com.csci.util.BinaryUtil;


/*Floating Point Instructions/Vector Operations:

Do not implement floating point numbers until Part IV

We have limited space in our instruction set, with only six bits for opcodes. 
So, we have to limit our floating point and vector operations. This will give 
you a chance to think about how to write a software routine to do multiplication 
and division for both floating point numbers. 

There are two floating point registers: FR0 and FR1. Each is 16 bits in length.

The format of a floating point number is the same as that for a load/store 
instruction, except that the r field takes only 2 values: 0 or 1 to specify 
the two floating point registers.

Vector operations are performed memory to memory. This was used on several 
models of vector processors as opposed to using lots of expensive registers 
to hold vectors (unless you were Seymour Cray).
*/



public class FloatingPointVectorOps {
	
	/* Floating Add Memory to Register */
	public static void instructionFADD(Instruction instruction) throws Throwable{
		
		String ea = BinaryUtil.eaCalculation(instruction); 
		
		Integer addressDecimal = Integer.parseInt(Cache.getInstance().checkCache(ea), 2);	
		Integer FRDecimal = Integer.parseInt(FrontPanel.txtFR0.getText(), 2);

		float floatFR = addressDecimal + (float)FRDecimal;
		
		//Convert Floating Point to Binary
		int intBits = Float.floatToIntBits(floatFR); 
		String binary = Integer.toBinaryString(intBits);
		
		//Limit the Result from 32 to 16 bits
		String signResult = "0";							//SIGN
		String exponentResult = binary.substring(0, 6);		//EXPONENT
		String mantissaResult = binary.substring(7, 16);	//MANTISSA
		
		binary = signResult + exponentResult + mantissaResult;
		
		//Set OVERFLOW	
		if(Integer.parseInt(exponentResult, 2) > 64){
			FrontPanel.txtCc.setText("1000");
			return;
		}
		FrontPanel.txtFR0.setText(binary);
		
	}	
	
	/* Floating Subtract Memory to Register */
	public static void instructionFSUB(Instruction instruction) throws Throwable{
		
	String ea = BinaryUtil.eaCalculation(instruction); 
		
		Integer addressDecimal = Integer.parseInt(Cache.getInstance().checkCache(ea), 2);	
		Integer FRDecimal = Integer.parseInt(FrontPanel.txtFR0.getText(), 2);

		float floatFR = (float)FRDecimal - addressDecimal;
		
		//Convert Floating Point to Binary
		int intBits = Float.floatToIntBits(floatFR); 
		String binary = Integer.toBinaryString(intBits);
		
		//Limit the Result from 32 to 16 bits
		String signResult = binary.substring(0, 1);			//SIGN
		String exponentResult = binary.substring(1, 7);		//EXPONENT
		String mantissaResult = binary.substring(8, 17);	//MANTISSA
		
		binary = signResult + exponentResult + mantissaResult;
		
		//Set UNDERFLOW	
				if(Integer.parseInt(exponentResult) < -63){
					FrontPanel.txtCc.setText("0100");
					return;
				}
		FrontPanel.txtFR0.setText(binary);
	}
	
	public void instructionVADD(Instruction instruction) throws Throwable{
		//TODO
				
		
	}
	
	public void instructionVSUB(Instruction instruction) throws Throwable{
		//TODO
				
		
	}
	
	public void instructionCNVRT(Instruction instruction) throws Throwable{
		//TODO
				
		
	}
	
	public void instructionLDFR(Instruction instruction) throws Throwable{
		//TODO
				
		
	}
	
	public void instructionSTFR(Instruction instruction) throws Throwable{
		//TODO
				
		
	}
	
	/*public void instructionFAULT(Instruction instruction) throws Throwable{
		//TODO
				
		
	}*/
	
	
	
	
	

}
