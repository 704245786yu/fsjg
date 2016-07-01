package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	
	/**比较两个日期相差月数*/
	public static int monthsOfTwo(Date fDate, Date tDate){
		Calendar fCal = Calendar.getInstance();
		fCal.setTime(fDate);
		int fYear = fCal.get(Calendar.YEAR);
		int fMonth = fCal.get(Calendar.MONTH);
		Calendar tCal = Calendar.getInstance();
		tCal.setTime(tDate);
		int tYear = tCal.get(Calendar.YEAR);
		int tMonth = tCal.get(Calendar.MONTH);
		return (tYear-fYear)*12 + (tMonth-fMonth);
	}
	
	/**比较两个日期相差天数*/
	public static int daysOfTwo(Date fDate, Date oDate) {
       Calendar aCalendar = Calendar.getInstance();
       aCalendar.setTime(fDate);
       int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
       aCalendar.setTime(oDate);
       int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
       return day2 - day1;

    }
	
	/**获取某月的最后一天*/
	public static int getLastDayOfMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);//获取某月最大天数
	}
	
	/**获取上月时间*/
	public static Date getLastMonth(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		//日期减一,取得上月最后一天时间对象
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}
	
}
