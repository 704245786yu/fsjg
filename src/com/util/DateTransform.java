package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTransform {

	/**
	 * 将字符串类型的日期，转换为Date对象
	 * @param pattern str的格式，如：yyyy-MM-dd HH:mm:ss
	 * */
	public static Date String2Date(String str, String pattern)
	{
		SimpleDateFormat sdf = new SimpleDateFormat( pattern );
		if(str == null || str.equals(""))
			return null;
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			System.out.println("string2Date出错");
			e.printStackTrace();
		}
		return date;
	}
	
	public static String Date2String(Date date, String pattern)
	{
		SimpleDateFormat sdf = new SimpleDateFormat( pattern );
		return sdf.format(date);
	}
}
