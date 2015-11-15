package co.com.csci.util;

import co.com.csci.model.Instruction;
import csci6461.Cache;
import csci6461.FrontPanel;

public class BinaryUtil {
	
	/**
	 * 
	 * @param binary
	 * @return the binary string with the length needed
	 */
	public static String fillBinaryStringParam(String binary, int lenght){
		if (binary.length() == lenght){
			return binary;
		} else if (binary.length() < lenght){
			int zerosNeeded = lenght - binary.length();
			String zeros = "";
			for (int i = 0; i < zerosNeeded; i++) {
				zeros += 0;
			}
			return zeros + binary;
		} else {
			//Exception
			return "";
		}		
	}
	
	@Deprecated
	public static String fillBinaryString03(String binary){
		if (binary.length() == 3){
			return binary;
		} else if (binary.length() < 3){
			int zerosNeeded = 3 - binary.length();
			String zeros = "";
			for (int i = 0; i < zerosNeeded; i++) {
				zeros += 0;
			}
			return zeros + binary;
		} else {
			//Exception
			return "";
		}		
	}
	
	@Deprecated
	public static String fillBinaryString(String binary){
		if (binary.length() == 16){
			return binary;
		} else if (binary.length() < 16){
			int zerosNeeded = 16 - binary.length();
			String zeros = "";
			for (int i = 0; i < zerosNeeded; i++) {
				zeros += 0;
			}
			return zeros + binary;
		} else {
			//Exception
			return "";
		}		
	}
	
	@Deprecated
	public static String fillBinaryString32(String binary){
		if (binary.length() == 32){
			return binary;
		} else if (binary.length() < 32){
			int zerosNeeded = 32 - binary.length();
			String zeros = "";
			for (int i = 0; i < zerosNeeded; i++) {
				zeros += 0;
			}
			return zeros + binary;
		} else {
			//Exception
			return "";
		}		
	}	
	
	public static String sumBinary(String bin1, String bin2){
		Integer sum = Integer.parseInt(bin1, 2) + Integer.parseInt(bin2,2);
		return Integer.toBinaryString(sum);
	}
	
	public static Integer sumBinaryToInteger(String bin1, String bin2){
		return Integer.parseInt(bin1, 2) + Integer.parseInt(bin2,2);
	}
	
	public static String eaCalculation(Instruction instruction) throws Exception{
		
		String indexValue = FrontPanel.getIndex(instruction.getIndexNumber());
		if(instruction.getIntructionCode() == InstructionEnum.LDX || 
		   instruction.getIntructionCode() == InstructionEnum.STX){
			indexValue = "0";
		}
		
		if(!instruction.isIndirect()){
			if(!instruction.hasIndex()){
				return instruction.getAddress();
			} else {
				return sumBinary(indexValue, instruction.getAddress());
			}
		} else {
			if(!instruction.hasIndex()){
				return Cache.getInstance().checkCache(instruction.getIntegerAddress());
			} else {
				Integer eaPos = sumBinaryToInteger(indexValue, instruction.getAddress());
				return Cache.getInstance().checkCache(eaPos);
			}
		}
	}

}
