package com.edt.b4t.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeDate
{
	public static int DAY = 0,
			   			MONTH = 1,
			   			YEAR = 2;
	
	public static void main(String[] args)
	{
		Date curDate = new Date();
		
	    SimpleDateFormat format = new SimpleDateFormat();
        String DateToStr = format.format(curDate);
        System.out.println("Default pattern: " + DateToStr);
        
        format = new SimpleDateFormat("yyyy-MM-dd");
        DateToStr = format.format(curDate);
        System.out.println(DateToStr);

        DateToStr = format.format(TimeDate.yesterday());
        System.out.println(DateToStr);
	}
	
	public static String convertDate(Date date)
	{
	    SimpleDateFormat format = new SimpleDateFormat();
        format = new SimpleDateFormat("yyyy-MM-dd");
        
        String conversion = format.format(date);
        
        return (conversion);
	}
	
	public static Date convertStringToDate(String date)
	{
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        Date conversion = null;
        
		try
		{
			conversion = format.parse(date);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
        
        return (conversion);
	}
	
	public static String convertDate(int type, Date date)
	{
		SimpleDateFormat dateFormat = null;
		
		String str = "NA";
		
		switch (type)
		{
//			case TimeDate.DAY:
			case 0:
				dateFormat = new SimpleDateFormat("dd");
				str = dateFormat.format(date);
				break;
				
//			case TimeDate.MONTH:
			case 1:
				dateFormat = new SimpleDateFormat("MM");
				str = dateFormat.format(date);
				break;
			
//			case TimeDate.YEAR:
			case 2:
				dateFormat = new SimpleDateFormat("yyyy");
				str = dateFormat.format(date);
				break;
		}
		
		return (str);
	}
	
	public static String month()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
		Date date = new Date();
		
		return (dateFormat.format(date));
	}
	
	public static String year()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		Date date = new Date();
		
		return (dateFormat.format(date));
	}
	
	public static Date tomorrow()
	{
	    final Calendar cal = Calendar.getInstance();
	    
	    cal.add(Calendar.DATE, 1);
	    
	    return (cal.getTime());
	}

	
	public static Date today()
	{
	    final Calendar cal = Calendar.getInstance();
	    
	    cal.add(Calendar.DATE, 0);
	    
	    return (cal.getTime());
	}
	
	public static Date yesterday()
	{
	    final Calendar cal = Calendar.getInstance();
	    
	    cal.add(Calendar.DATE, -1);
	    
	    return (cal.getTime());
	}
	
	public static Date todayMinus(int days)
	{
	    final Calendar cal = Calendar.getInstance();
	    
	    cal.add(Calendar.DATE, (-1 * days));
	    
	    return (cal.getTime());
	}
	
	public static String getDateString(String calType)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String date = null;
		
		switch (calType)
		{
			case "Yesterday":
				date = dateFormat.format(TimeDate.yesterday());
				break;
		
			case "Today":
				date = dateFormat.format(TimeDate.today());
				break;
			
			default:
			case "Tomorrow":
				date = dateFormat.format(TimeDate.tomorrow());
				break;
		}
		    
        return date;
	}
	
	public static String convertToDate(String str)
	{
		String convertedDate = str;
		
		if (-1 != convertedDate.indexOf("T"))
			convertedDate = convertedDate.substring(0, convertedDate.indexOf("T"));
			
		return (convertedDate);
	}

	public static String convertToDateUtil(String str)
	{
		String convertedDate = str;
		
		if (-1 != convertedDate.indexOf("T"))
			convertedDate = convertedDate.substring(0, convertedDate.indexOf("T"));
		
		String year = convertedDate.substring(0, 4),
				month = convertedDate.substring(5, 7),
				day = convertedDate.substring(8, 10);
        
		return (month + "-" + day + "-" + year);
	}

	public static Date convertToDateObj(String str)
	{
		Date date = null;
		
		try
		{
			date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
			
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		return (date);
	}
}
