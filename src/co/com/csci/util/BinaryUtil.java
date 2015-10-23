package co.com.csci.util;

public class BinaryUtil {
	
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


}
