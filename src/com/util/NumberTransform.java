package com.util;

import java.text.DecimalFormat;

public class NumberTransform {
	
	/**@param pattern #.00
	 * */
	public static double decimalformat(double number,String pattern){
		DecimalFormat df = new DecimalFormat(pattern);
		double d = Double.parseDouble(df.format(number));
		return d;
	}
	
	public static Long getLong(String str){
		if(str.trim().isEmpty())
			return null;
		Double d = Double.parseDouble(str);
		long l = d.longValue();
		return l;
	}
	
	public static Short getShort(String str){
		if(str.trim().isEmpty())
			return null;
		Double d = Double.parseDouble(str);
		short s = d.shortValue();
		return s;
	}
	
	public static Byte getByte(String str){
		if(str.trim().isEmpty())
			return null;
		Double d = Double.parseDouble(str);
		byte b = d.byteValue();
		return b;
	}
}
