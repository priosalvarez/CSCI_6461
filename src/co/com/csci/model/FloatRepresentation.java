package co.com.csci.model;

import co.com.csci.util.BinaryUtil;

/**
 * 
 * There are 7 bits for the exponent and 8 bits for the mantissa. 
 * The first bit of the exponent is its sign bit. 
 * The S bit (bit 0) is the sign of the entire floating point number. 
 * The exponent ranges from –63 to 64.
 * So, exponent 0 = 1000000
 * And, exponent 1 = 0000000
 *
 */
public class FloatRepresentation {
	
	private String s;
	private String exponent;
	private String mantissa;
	
	/**
	 * Create a binary float reprensentation from the binary chain
	 * @param number
	 */
	public FloatRepresentation(String number){
		this.s = number.substring(0, 1);
		this.exponent = number.substring(1, 8);
		this.mantissa = number.substring(8);
	}
	
	/**
	 * Create a binary float representation from a float number
	 * @param number
	 */
	public FloatRepresentation(Float number){
		Integer binaryInteger  = Float.floatToIntBits(number);
		String binary = Integer.toBinaryString(binaryInteger);
		binary = BinaryUtil.fillBinaryStringParam(binary, 32);
		s = binary.charAt(0) + "";
		String expS = binary.substring(1, 9);
		exponent = evaluateExp(expS);
		mantissa = binary.substring(9, 17);
	}
	
	public String toString(){
		return s + exponent + mantissa;
	}
	
	/**
	 * Convert a binary representation of a float to the number in base 10
	 */
	public Float calculateDecimalNumber(){
		Integer sign = (s.charAt(0) == '0' ? 1 : -1);
		Double valueExponent = Math.pow(2d, calculateExponent().doubleValue());
		return sign * (calculateMantissa() * valueExponent.floatValue());
	}
	
	/**
	 * calculate the value of the exponent in decimal following the rules.
	 */
	private Integer calculateExponent(){
		Integer sign = (exponent.charAt(0) == '0' ? 1 : -1);
		Integer number = Integer.parseInt(exponent.substring(1), 2);
		if(sign > 0){
			number++;
		}
		return sign * number;
	}
	
	/**
	 * calculate decimal value of mantissa
	 */
	private Float calculateMantissa(){
		Float[] mantissaVector = {1/2f, 1/4f, 1/8f, 1/16f, 1/32f, 1/64f, 1/128f, 1/256f};
		Float mantissaFraction = 0f;
		for(int i = 0; i < 8; i++){
			char bit = mantissa.charAt(i);
			if(bit == '1'){
				mantissaFraction += mantissaVector[i];
			}
		}
		return 1 + mantissaFraction;
	}
	
	/**
	 * convert exponent from IEEE 754 to how the project asked for
	 */
	private String evaluateExp(String exp){
		String exPart1;
		String exPart2;
		
		Integer num = Integer.parseInt(exp, 2);
		num = num - 127;
		
		//If exponent is too big or small then return null
		if(num > 64 || num < -63){
			return null;
		}
		
		//Sign of the exponent
		if(num <= 0){
			exPart1 = "1";
		} else {
			exPart1 = "0";
		}
		
		//Because the way 0 operate to avoid repetition
		if(num > 0){
			num = num - 1;
		}
		
		//Absolute value of the number
		if(num < 0){
			num = num * -1;
		}
		
		exPart2 = Integer.toBinaryString(num);
		
		return exPart1 + BinaryUtil.fillBinaryStringParam(exPart2, 6);
	}
	
	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getExponent() {
		return exponent;
	}

	public void setExponent(String exponent) {
		this.exponent = exponent;
	}

	public String getMantissa() {
		return mantissa;
	}

	public void setMantissa(String mantissa) {
		this.mantissa = mantissa;
	}

	public static void main(String[] args) {
		
		String s = "0";
		String exp = "1000000";
		String mantissa = "00000000";
		FloatRepresentation f = new FloatRepresentation(s+exp+mantissa);
		System.out.println(f.calculateDecimalNumber());
		
		FloatRepresentation f2 = new FloatRepresentation(3f);
		System.out.println(f2.toString());
		 
	}

}
