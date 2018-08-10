package com.edt.b4t;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.edt.b4t.util.ParseOData;
import com.edt.b4t.util.Str;

public class B4TUsers
{
	private ArrayList<String> ids = null,
						fnames = null,
						lnames = null,
						emails = null,
						address1s = null,
						address2s = null,
						cities = null,
						states = null,
						postalCodes = null,
						countries = null,
						homePhones = null,
						officePhones = null,
						mobilePhones = null,
 						userTypes = null,
						statuses = null,
						departments = null,
						dateOfBirths = null,
						billigRates = null,
						overtimeRates = null,
						doubleRates = null,
						payableRates = null,
						payableRateOvertime = null,
						position = null;
			
	private String url = Str.BASE_URL + "/v1/users";
	
	public static double LOADED_OVERHEAD = 1.1,
	                    LOADED_ADMINISTATION = 20000,
	                    TOTAL_WORK_HOURS = 1840;
	
	public static void main(String[] args)
			throws IOException
	{
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println("Start: " + format.format(new Date()));

//		B4TUsers sample = new B4TUsers("?$select=id,fname,lname,email,userType,status,billingRate&$filter=id%20eq%201");
		
		B4TUsers users = new B4TUsers(Str.convertToURL("?$filter=status eq 'Active'"));
		System.out.println("Total Dreamers are " + users.getIds().size());
		
		String demoFile = "user-report.html",
				fullFile = "full-report.html";
		
		File file = new File(demoFile),
			full = new File(fullFile);
		
		if (file.exists())
			file.delete();
		
		if (full.exists())
			full.delete();
		
		FileWriter fname = new FileWriter(demoFile),
				allFname = new FileWriter(fullFile);
		
		PrintWriter buffer = new PrintWriter(fname),
					fullBuffer = new PrintWriter(allFname);

		users.report(buffer, users, false);
		users.report(fullBuffer, users, true);
		
        System.out.println("Done: " + format.format(new Date()));
	}
	
	private void reportHeader(PrintWriter buffer, boolean annualCost)
	{
		buffer.println("<html>");
		buffer.println("<head>");
		buffer.println("<meta charset=\"UTF-8\">");
		buffer.println("<link rel=\"icon\" href=\"https://edtssl.eagledream-hosting.com/wp-content/uploads/2015/02/edt_fav_2.png\"/>");
		buffer.println("<title>EDT - Bill4Time Users</title>");
		buffer.println("<script src=\"https://www.w3schools.com/lib/w3.js\"></script>");

		buffer.println("</head>");
		buffer.println("<body>");
		buffer.println("<center>");
		
//		buffer.println("<div id=\"under\" style=\"left: 0px; top: 0px; position: relative\">");
//		buffer.println("<img src=\"http://static.wixstatic.com/media/ec9883_3b4237ae544c4fb188e9162b3dbd48ea.jpg\" width=\"50%\" length=\"50%\">");
//		buffer.println("<br>");
//		buffer.println("</div>");
		
//		buffer.println("<div id=\"top\" style=\"left: 250px; top: 20px; position: absolute\">");
//		buffer.println("<img src=\"https://d3rdz1nqt7l291.cloudfront.net/wp-content/uploads/2015/02/edt_logo_white_2-e1454088284511.png?x59865\" width=\"50%\" length=\"50%\">");
//		buffer.println("</div>");
		
		buffer.println("<table border=\"1\" removed=#FFFFFF cellspacing=\"1\" cellpadding=\"10\">");
		buffer.println("<thead>");
		buffer.println("<tr>");
		
//		id,fname,lname,userType,status,billingRate,overtimeRate,payableRate,payableRateOvertime,department
		buffer.println("<th bgcolor=\"midnightblue\"><font color=\"white\">Id</font></th>");
		buffer.println("<th bgcolor=\"midnightblue\"><font color=\"white\">User</font></th>");
		buffer.println("<th bgcolor=\"midnightblue\"><font color=\"white\">Std. Hourly Rate</font></th>");
		buffer.println("<th bgcolor=\"midnightblue\"><font color=\"white\">Type</font></th>");
		
		buffer.println("<th bgcolor=\"midnightblue\"><font color=\"white\">B4T Hourly Rate</font></th>");
		
		buffer.println("<th bgcolor=\"midnightblue\"><font color=\"white\">Position</font></th>");
		buffer.println("<th bgcolor=\"midnightblue\"><font color=\"white\">Department</font></th>");

		if (annualCost)
		{
			buffer.println("<th bgcolor=\"midnightblue\"><font color=\"white\">Overtime Rate</font></th>");
			buffer.println("<th bgcolor=\"midnightblue\"><font color=\"white\">(Contractor)<br>Rate</font></th>");
			buffer.println("<th bgcolor=\"midnightblue\"><font color=\"white\">(Contractor)<br>Overtime Rate</font></th>");
			
			buffer.println("<th bgcolor=\"midnightblue\"><font color=\"white\">Loaded Annual Cost</font></th>");
		}
		
		buffer.println("</tr>");
		buffer.println("</thead>");
	}
	
	private void report(PrintWriter buffer, B4TUsers users, boolean annualCost)
	{
		DecimalFormat rateFormat = new DecimalFormat("####.##");
		
		this.reportHeader(buffer, annualCost);
		
		int fixed = 0,
			broken = 0,
			fullTimers = 0,
			coops = 0;
		
		for (int index = 0; index < users.getIds().size() - 1; index++)
		{
			if (!"Active".equals(users.getStatuses().get(index)))
			    continue;
			
			boolean isCoOp = ("Limited User".equals(users.getUserTypes().get(index)));
			
			if (isCoOp)
			    coops++;
			else
			    fullTimers++;
			
			System.out.println(index + " " + users.getPayableRates().get(index));
			
			Double b4tHourlyRate = Double.valueOf(users.getBilligRates().get(index)),
					flatRate = (!"null".equals(users.getPayableRates().get(index))) ? 
									Double.valueOf(users.getPayableRates().get(index)) : (double)0,
					overtimeRate = (!"null".equals(users.getOvertimeRates().get(index))) ? 
									Double.valueOf(users.getOvertimeRates().get(index)) : (double)0,
					flatOvertimeRate = (!"null".equals(users.getPayableRateOvertime().get(index))) ? 
									Double.valueOf(users.getPayableRateOvertime().get(index)) : (double)0,
					stdHourlyRate = (isCoOp) ? (double)30 : Double.valueOf(rateFormat.format(flatRate * .4)),
					annualEDTCost = Double.valueOf(B4TUsers.getAnnualSalary(users.getPayableRates().get(index)));

			String fontStart = "",
					fontEnd = "",
					cellColor = "",
					position = ("},{".equals(users.getPosition().get(index))) ? "" : users.getPosition().get(index),
					department = ("\"\"".equals(users.getDepartments().get(index))) ? "" : users.getDepartments().get(index);
					
			if (!b4tHourlyRate.equals(stdHourlyRate))
			{
				System.out.println("User " + users.getLnames().get(index) + ": "
						+ b4tHourlyRate + " -> "
						+ stdHourlyRate
						+ " (" + flatRate + ") = "
						+ Double.valueOf(rateFormat.format(b4tHourlyRate * 1.6))
						);
			}
			
			if (b4tHourlyRate.equals(stdHourlyRate))
			{
				fixed++;
				
				buffer.println("<tr bgcolor=\"#1E7B1E\">");
				fontStart = "<font color=\"white\"><b>";
				fontEnd = "</b></font>";
				cellColor = "<td>";
			}
			else
			{
				buffer.println("<tr>");
				cellColor = "<td bgcolor=\"yellow\">";
				broken++;
			}
			
			String typeColumn = (isCoOp) ? "<font color=\"red\"><center>CoOp</center></font>"
										: "<font color=\"blue\"><center>FTE</center></font>";

			buffer.println("<td><center>" + fontStart + users.getIds().get(index) + fontEnd + "</center></td>");
			buffer.println("<td><center>" + fontStart + users.getFnames().get(index) + " " + users.getLnames().get(index) + fontEnd + "</center></td>");
			
			buffer.println(cellColor + "<center>" + fontStart + Str.dollarFormat.format(stdHourlyRate) + fontEnd + "</center></td>");
			
			buffer.println("<td><center>" + typeColumn + "</center></td>");

			buffer.println(cellColor + "<center>" + fontStart + Str.dollarFormat.format(b4tHourlyRate) + fontEnd + "</center></td>");
			
			buffer.println(cellColor + "<center>" + fontStart + position + fontEnd + "</center></td>");
			buffer.println(cellColor + "<center>" + fontStart + department + fontEnd + "</center></td>");
			
			if (annualCost)
			{
				buffer.println("<td><center>" + fontStart + Str.dollarFormat.format(overtimeRate) + fontEnd + "</center></td>");
				buffer.println("<td><center>" + fontStart + Str.dollarFormat.format(flatRate) + fontEnd + "</center></td>");
				buffer.println("<td><center>" + fontStart + Str.dollarFormat.format(flatOvertimeRate) + fontEnd + "</center></td>");
				
				buffer.println("<td><center>" + fontStart + Str.dollarFormat.format(annualEDTCost) + fontEnd + "</center></td>");
			}
			
			buffer.println("</tr>");
		}
		
		this.reportFooter(buffer, fixed, broken, annualCost);
		
		buffer.println("</table>");
		buffer.println("</center>");
		buffer.println("</body></html>");
		buffer.close();
		
		System.out.println("Co-Ops =\t" + coops
		                    + "\r\nFull Timers =\t" + fullTimers
		                    + "\r\nTotal \t" + (coops + fullTimers));
	}

	private void reportFooter(PrintWriter buffer, int fixed, int broken, boolean annualCost)
	{
		int cols = (annualCost) ? 7 : 3;
		
//		String fontColor = (0 < this.totalProfit) ? "white" : "black",
//				   rowColor = (0 < this.totalProfit) ? "1E7B1E" : "FF0000";
			
		buffer.println("<tr bgcolor=\"#1E7B1E\">");
//		buffer.println("<td colspan=\"2\"></td>");
		buffer.println("<td align=\"center\"><font color=\"yellow\"><b>Total</b></font></td>");
		buffer.println("<td align=\"center\"><font color=\"yellow\"><b>" + (fixed + broken) + "</b></font></td>");
		
		buffer.println("<td align=\"center\"><font color=\"white\"><b>" + fixed + " Fixed</b></font></td>");
//		buffer.println("<td align=\"center\"><font color=\"white\"><b>" + fixed + "</b></font></td>");
		
//		buffer.println("<td align=\"center\"><font color=\"red\"><b>Broken</b></font></td>");
		buffer.println("<td align=\"center\"></td>");
		buffer.println("<td align=\"center\" colspan=\"" + cols + "\"><font color=\"red\"><b>" + broken + " Broke</b></font></td>");
		buffer.println("</tr>");
	}

	public static double getAnnualSalary(String hourlyRate)
	{
		if ("null".equals(hourlyRate))
			return (0);
		
		double rate = Double.parseDouble(hourlyRate);
		
		rate = rate * B4TUsers.TOTAL_WORK_HOURS;
		rate = rate - B4TUsers.LOADED_ADMINISTATION;
		rate = rate / B4TUsers.LOADED_OVERHEAD;
		
		return rate;
	}

	public B4TUsers(String userPrefs)
	{
		String id = "id",
				fname = "fname",
				lname = "lname",
				email = "email",
				address1 = "address1",
				address2 = "address2",
				city = "city",
				state = "state",
				postalCode = "postalCode",
				country = "country",
				homePhone = "homePhone",
				officePhone = "officePhone",
				mobilePhone = "mobilePhone",
				userType = "userType",
				status = "status",
				department = "department",
				dateOfBirth = "dateOfBirth",
				billingRate = "billingRate",
				overtimeRate = "overtimeRate",
				doubleRate = "doubleRate",
				payableRate = "payableRate",
				payableRateOvertime = "payableRateOvertime",
				position = "position";

		try
		{
			this.url = this.url + userPrefs;
			
//			System.out.println(this.url);
			
			ParseOData data = new ParseOData(this.url);
		
			this.ids = data.getTagStrings(id);
			
			this.fnames = data.getTagStrings(fname);
			this.lnames = data.getTagStrings(lname);
			this.emails = data.getTagStrings(email);
			this.address1s = data.getTagStrings(address1);
			this.address2s = data.getTagStrings(address2);
			this.cities = data.getTagStrings(city);
			this.states = data.getTagStrings(state);
			this.postalCodes = data.getTagStrings(postalCode);
			this.countries = data.getTagStrings(country);
			this.homePhones = data.getTagStrings(homePhone);
			this.officePhones = data.getTagStrings(officePhone);
			this.mobilePhones = data.getTagStrings(mobilePhone);
			this.userTypes = data.getTagStrings(userType);
			this.statuses = data.getTagStrings(status);
			this.departments = data.getTagStrings(department);
			this.dateOfBirths = data.getTagStrings(dateOfBirth);
			this.billigRates = data.getTagStrings(billingRate);
			this.overtimeRates = data.getTagStrings(overtimeRate);
			this.doubleRates = data.getTagStrings(doubleRate);
			this.payableRates = data.getTagStrings(payableRate);
			this.payableRateOvertime = data.getTagStrings(payableRateOvertime);
			this.position = data.getTagStrings(position);
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

	public ArrayList<String> getIds()
	{
		return this.ids;
	}

	public ArrayList<String> getFnames()
	{
		return this.fnames;
	}

	public ArrayList<String> getLnames()
	{
		return this.lnames;
	}

	public ArrayList<String> getEmails()
	{
		return this.emails;
	}

	public ArrayList<String> getAddress1s()
	{
		return this.address1s;
	}

	public ArrayList<String> getAddress2s()
	{
		return this.address2s;
	}

	public ArrayList<String> getCities()
	{
		return this.cities;
	}

	public ArrayList<String> getStates()
	{
		return this.states;
	}

	public ArrayList<String> getPostalCodes()
	{
		return this.postalCodes;
	}

	public ArrayList<String> getCountries()
	{
		return this.countries;
	}

	public ArrayList<String> getHomePhones()
	{
		return this.homePhones;
	}

	public ArrayList<String> getOfficePhones()
	{
		return this.officePhones;
	}

	public ArrayList<String> getMobilePhones()
	{
		return this.mobilePhones;
	}

	public ArrayList<String> getUserTypes()
	{
		return this.userTypes;
	}

	public ArrayList<String> getStatuses()
	{
		return this.statuses;
	}

	public ArrayList<String> getDepartments()
	{
		return this.departments;
	}

	public ArrayList<String> getDateOfBirths()
	{
		return this.dateOfBirths;
	}

	public ArrayList<String> getBilligRates()
	{
		return this.billigRates;
	}

	public ArrayList<String> getOvertimeRates()
	{
		return this.overtimeRates;
	}

	public ArrayList<String> getDoubleRates()
	{
		return this.doubleRates;
	}

	public ArrayList<String> getPayableRates()
	{
		return this.payableRates;
	}

	public ArrayList<String> getPayableRateOvertime()
	{
		return this.payableRateOvertime;
	}

	public ArrayList<String> getPosition()
	{
		return this.position;
	}

	public String getUrl()
	{
		return this.url;
	}

	public static double getLOADED_OVERHEAD()
	{
		return B4TUsers.LOADED_OVERHEAD;
	}

	public static double getLOADED_ADMINISTATION()
	{
		return B4TUsers.LOADED_ADMINISTATION;
	}

	public static double getTOTAL_WORK_HOURS()
	{
		return B4TUsers.TOTAL_WORK_HOURS;
	}
}