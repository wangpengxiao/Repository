/**
 * FileName:	DateUtils.java
 * Created:		2011-12-16
 * CreatedBy:	<a href="mailto:13901155280@139.com">ZhangPeng</a>
 */
package com.tydic.android.usp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具类
 * 
 * @version 1.0
 */
public class DateUtils {
	/**
	 * 获取耗时
	 * @param timeMillis
	 * @return
	 */
	public static String getSpentTime(long timeMillis){
		long currentTimeMillis = System.currentTimeMillis();
		long spentTimeMillis = currentTimeMillis - timeMillis;
		
		long ms = spentTimeMillis % 1000;
		long s = (spentTimeMillis / 1000)%60;
		long m = (spentTimeMillis / 1000 / 60)%60;
		long h = (spentTimeMillis / 1000 / 60 / 60);
		StringBuffer sb = new StringBuffer();
		if(h > 0) sb.append(h+"h");
		if(m > 0) sb.append(m+"m");
		if(s > 0) sb.append(s+"s");
		if(ms > 0) sb.append(ms+"ms");
		return sb.toString();
	}
	/**
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date parseDate(String dateString) {
		return parseDate(dateString, "yyyy-MM-dd");
	}

	/**
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date parseDateTime(String dateString) {
		return parseDate(dateString, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 
	 * @param dateString
	 * @param parsePartten
	 * @return
	 */
	public static Date parseDate(String dateString, String parsePartten) {
		Date ld_Value;
		SimpleDateFormat lsdf_Format = new SimpleDateFormat(parsePartten);

		if (dateString == null)
			return null;

		try {
			lsdf_Format.setLenient(false); //to be precise
			ld_Value = lsdf_Format.parse(dateString);
		} catch (ParseException e) {
			//logger.log(Log4j.LEVEL_ERROR, "DateUtil.parseDate() failed." + e);
			e.printStackTrace();
			return null;
		}

		return ld_Value;
	}
	public static String formatDate(Date date){
		return format(date, "yyyy-MM-dd");
	}
	public static String formatDateTime(Date date){
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 日期格式化
	 * 
	 * @param date
	 *            日期对象
	 * @param pattern
	 *            日期格式,如：yyyy-MM-dd HH:mm:ss
	 * @return 返回一个String类型的日期。
	 */
	public static String format(Date date, String pattern) {
		SimpleDateFormat lsdf_Format;
		String ls_Formatted;

		if (pattern == null) {
			System.err.println("DateUtil.format(): pattern is null");
			return null;
		}
		if (date == null) {
			System.err.println("DateUtil.format(): date is null");
			return null;
		}

		try {
			lsdf_Format = new SimpleDateFormat(pattern);
			ls_Formatted = lsdf_Format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("DateUtil.formatDate(): " + e.getMessage());
			lsdf_Format = new SimpleDateFormat("yyyy-MM-dd");
			ls_Formatted = lsdf_Format.format(date);
		}

		return ls_Formatted;
	}

	/**
	 * 
	 * @param ad_Date
	 * @param ai_Number
	 * @return 返回相差指定年数的日期对象
	 */
	public static Date relativeYear(Date ad_Date, int ai_Number) {
		return relativeDate(ad_Date, Calendar.YEAR, ai_Number);
	}

	/**
	 * 
	 * @param ad_Date
	 * @param ai_Number
	 * @return 返回相差指定月份数的日期对象
	 */
	public static Date relativeMonth(Date ad_Date, int ai_Number) {
		return relativeDate(ad_Date, Calendar.MONTH, ai_Number);
	}

	/**
	 * 
	 * @param ad_Date
	 * @param ai_Number
	 * @return 返回相差指定天数的日期对象
	 */
	public static Date relativeDay(Date ad_Date, int ai_Number) {
		return relativeDate(ad_Date, Calendar.DATE, ai_Number);
	}

	/**
	 * 
	 * @param ad_Date
	 * @param ai_Type
	 * @param ai_Number
	 * @return 返回处理过的日期
	 */
	public static Date relativeDate(Date ad_Date, int ai_Type, int ai_Number) {
		if (ad_Date == null)
			return null;

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(ad_Date);
		calendar.add(ai_Type, ai_Number);

		return calendar.getTime();
	}

	/**
	 * 当前日期
	 * 
	 * @return String
	 */
	public static String currentDate() {
		return formatDate(Calendar.getInstance().getTime());
	}

	/**
	 * 当前日期
	 * 
	 * @return Date
	 */
	public static Date getCurrentDate() {
		return parseDate(currentDate());
	}

	/**
	 * 当前的日期和时间
	 * 
	 * @return Date
	 */
	public static Date getCurrentDateTime(){
	    return Calendar.getInstance().getTime();
	}

	/**
	 * 当前日期的前一天
	 * 
	 * @return Date
	 */
	public static Date getYesterday() {
		return relativeDay(getCurrentDate(),-1);
	}

	/**
	 * 当前日期的前一天
	 * 
	 * @return String
	 */
	public static String yesterday() {
		return formatDate(getYesterday());
	}
	
	/**
	 * 当前日期7天后
	 * 
	 * @return String
	 */
	public static String after7days() {
		return formatDate(get7days());
	}
	
	/**
	 * 当前日期的前一天
	 * 
	 * @return Date
	 */
	public static Date get7days() {
		return relativeDay(getCurrentDate(),6);
	}

	/**
	 * 当前时间
	 * 
	 * @return String
	 */
	public static String currentTime() {
		return format(Calendar.getInstance().getTime(), "HH:mm:ss");
	}

	/**
	 * 当前日期时间
	 * 
	 * @return String
	 */
	public static String currentDateTime() {
		return formatDateTime(Calendar.getInstance().getTime());
	}
	public static int currentDay(){
	    return getCurrent(Calendar.DAY_OF_MONTH);
	}
	public static int currentMonth(){
	    return getCurrent(Calendar.MONTH)+1;
	}
	public static int currentYear(){
	    return getCurrent(Calendar.YEAR);
	}
	private static int getCurrent(int type){
	    return Calendar.getInstance().get(type);
	}

	/**
	 * 当前秒
	 * 
	 * @return long
	 */
	public static long currentTimeMillis() {
		return System.currentTimeMillis();
	}
	public static Calendar getCalendar(long muiliseconds) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(muiliseconds);
		return c;
	}
	public static Date getDate(long muiliseconds) {
		Date date = Calendar.getInstance().getTime();
		date.setTime(muiliseconds);
		return date;
	}

	/**
	 * 获取流逝时间
	 * 
	 * @param foretimeMillis
	 *            过去时间
	 * @return 返回流逝时间
	 */
	public static String elaps(long foretimeMillis) {
		return elaps(foretimeMillis, System.currentTimeMillis());
	}

	/**
	 * 获取流逝时间
	 * 
	 * @param foretimeMillis
	 *            过去时间
	 * @param pattern
	 *            时间格式(如:HH:mm:ss.SSS)
	 * @return 返回流逝时间
	 */
	public static String elaps(long foretimeMillis, String pattern) {
		return elaps(foretimeMillis, System.currentTimeMillis(), pattern);
	}

	/**
	 * 获取流逝时间
	 * 
	 * @param foretimeMillis
	 *            过去时间
	 * @param currentTimeMillis
	 *            当前时间
	 * @return 返回流逝时间
	 */
	public static String elaps(long foretimeMillis, long currentTimeMillis) {
		return elaps(foretimeMillis, currentTimeMillis, "HH:mm:ss.SSS");
	}

	/**
	 * 获取流逝时间
	 * 
	 * @param foretimeMillis
	 *            过去时间
	 * @param currentTimeMillis
	 *            当前时间
	 * @param pattern
	 *            时间格式(如:HH:mm:ss.SSS)
	 * @return 返回流逝时间
	 */
	public static String elaps(long foretimeMillis, long currentTimeMillis,
			String pattern) {
		if (pattern == null)
			pattern = "HH:mm:ss.SSS";
		String elapsTime = pattern;

		if (currentTimeMillis <= foretimeMillis) {
			elapsTime = elapsTime.replace("HH", "00");// 时
			elapsTime = elapsTime.replace("mm", "00");// 分
			elapsTime = elapsTime.replace("ss", "00");// 秒
			elapsTime = elapsTime.replace("SSS", "000");// 毫秒
			return elapsTime;
		}
		long elapsTimeMills = currentTimeMillis - foretimeMillis;
		String hh = NumberUtils.formatNumber(
				(elapsTimeMills / (60 * 60 * 1000)), "####");
		if (hh.length() == 1)
			hh = "0" + hh;

		elapsTimeMills = elapsTimeMills % (60 * 60 * 1000);
		String mm = NumberUtils.formatNumber((elapsTimeMills / (60 * 1000)),
				"##");
		if (mm.length() == 1)
			mm = "0" + mm;

		elapsTimeMills = elapsTimeMills % (60 * 1000);
		String ss = NumberUtils.formatNumber((elapsTimeMills / 1000), "##");
		if (ss.length() == 1)
			ss = "0" + ss;
		elapsTimeMills = elapsTimeMills % 1000;
		String SSS = NumberUtils.formatNumber((elapsTimeMills), "###");
		while (SSS.length() < 3) {
			SSS = "0" + SSS;
		}
		elapsTime = elapsTime.replace("HH", hh);// 时
		elapsTime = elapsTime.replace("mm", mm);// 分
		elapsTime = elapsTime.replace("ss", ss);// 秒
		elapsTime = elapsTime.replace("SSS", SSS);// 毫秒

		return elapsTime;
	}
	/**
	 * 获取当前是星期几
	 * @return 返回星期几
	 */
	public static int getDayOfMonth(){
		return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
	}
	/***
	 * 获取当前是一年当中的第几周
	 * @return 返回周数
	 */
	public static int getWeekOfYear(){
		return Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
	}
	/**
	 * 获取当前是一月当中的第几周
	 * @return 返回周数
	 */
	public static int getWeekOfMonth(){
		return Calendar.getInstance().get(Calendar.WEEK_OF_MONTH);
	}
	
	
	
	
	
	/** 
     * 获取今天凌晨 
     * @return 
     */  
    public static Date getMorning() {  
        return getMorning(new Date());  
    }  
  
    /** 
     * 获取指定时间当天的凌晨 
     * @param date 给定时间当天的凌晨 
     * @return 
     */  
    public static Date getMorning(Date date) {  
        Calendar c = Calendar.getInstance();  
        c.setTime(date);  
        c.set(Calendar.HOUR_OF_DAY, 0);  
        c.set(Calendar.MINUTE, 0);  
        c.set(Calendar.SECOND, 0);  
        return c.getTime();  
    }  
    
    /**
     * @return  星期一 星期二...
     */
    public static String getDayString(int day){
    	switch (day) {
		case Calendar.SUNDAY:
			return "星期天";
		case Calendar.MONDAY:
			return "星期一";
		case Calendar.TUESDAY:
			return "星期二";
		case Calendar.WEDNESDAY:
			return "星期三";
		case Calendar.THURSDAY:
			return "星期四";
		case Calendar.FRIDAY:
			return "星期五";
		case Calendar.SATURDAY:
			return "星期六";
		default:
			return "";
		}
    	
    }
    
    public static String date2Week(Date date){
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	int w = calendar.get(Calendar.DAY_OF_WEEK);
    	return getDayString(w);
    }
}
