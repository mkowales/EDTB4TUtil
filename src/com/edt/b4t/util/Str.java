package com.edt.b4t.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Str
{
    public static String CR = "\r\n",
                         TAB = "\t",
                         BASE_URL = "https://secure.bill4time.com/apinode/";
    
    public static DecimalFormat dollarFormat = new DecimalFormat("$#,###.00"),
                                percentFormat = new DecimalFormat("#,###%"),
                                numericFormat = new DecimalFormat("#,###.00");
    
	public static String convertToURL(String name)
	{
		String convertedName = name;
		
		convertedName = convertedName.replaceAll(" ", "%20");
		convertedName = convertedName.replaceAll("#", "%23");
		convertedName = convertedName.replaceAll("'", "%27");
		convertedName = convertedName.replaceAll("\"", "%34");
		convertedName = convertedName.replaceAll("/", "%47");
		
		return (convertedName);
	}
	
	public static String convertToSpaces(String name)
	{
		String convertedName = name;
		
		convertedName = convertedName.replaceAll("%20", " ");

		return (convertedName);
	}
	
	public static String convertToDate(String str)
	{
		String convertedDate = str;
		
		if (-1 != convertedDate.indexOf("T"))
			convertedDate = convertedDate.substring(0, convertedDate.indexOf("T"));
			
		return (convertedDate);
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
