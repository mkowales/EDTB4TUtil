package com.edt.b4t;

import java.io.IOException;
import java.util.ArrayList;

import com.edt.b4t.util.ParseOData;
import com.edt.b4t.util.Str;

public class B4TTimesheets
{
	private ArrayList<String> Ids = null,
			clientIds = null,
			clientNames = null,
			projectIds = null,
			projectNames = null,
			userIds = null,
			userNames = null,
			billableAmounts = null,
			billableTimes = null,
			laborTimes = null,
			travelTimes = null,
			entryDates = null,
			createdDates = null,
			invoiceIds = null,
			descriptionPrivates = null,
			descriptionPublics = null,
			activityTypes = null,
			litigationCodes = null,
			litigationCodeDescs = null,
			isBillables = null,
			billingStatuses = null,
			hourlyRates = null,
			payableRates = null;
	
	private String url = Str.BASE_URL + "/v1/timeEntries";
	
	public static void main(String[] args)
	{
		B4TTimesheets sample = new B4TTimesheets("?$filter=userId%20eq%201&$select=descriptionPrivate,laborTime,activityType&$orderby=descriptionPrivate");
		
		System.out.println("Total Timesheets: " +  sample.getIds().size());
	}
	
	public static B4TTimesheets getTimesheetsByClient(String client)
	{
		B4TTimesheets timesheets = new B4TTimesheets(Str.convertToURL("?$filter=clientName eq " + client));;
		
		return timesheets;
	}

	public B4TTimesheets(String prefs)
	{
	    System.out.println("mkowales " + prefs);
	    
		String id = "id",
				clientId = "clientId",
				clientName = "clientName",
				projectId = "projectId",
				projectName = "projectName",
				userId = "userId",
				userName = "userName",
				billableAmount = "billableAmount",
				billableTime = "billableTime",
				laborTime = "laborTime",
				travelTime = "travelTime",
				entryDate = "entryDate",
				createdDate = "createdDate",
				invoiceId = "invoiceId",
				descriptionPrivate = "descriptionPrivate",
				descriptionPublic = "descriptionPublic",
				activityType = "activityType",
				litigationCode = "litigationCode",
				litigationCodeDesc = "litigationCodeDesc",
				isBillable = "isBillable",
				billingStatus = "billingStatus",
				hourlyRate = "hourlyRate",
				payableRate = "payableRate";

		try
		{
			this.url = this.url + prefs;
			
//			System.out.println(this.url);
			
			ParseOData usersData = new ParseOData(this.url);
			
			this.Ids = usersData.getTagStrings(id);
			this.clientIds = usersData.getTagStrings(clientId);
			this.clientNames = usersData.getTagStrings(clientName);
			this.projectIds = usersData.getTagStrings(projectId);
			this.projectNames = usersData.getTagStrings(projectName);
			this.userIds = usersData.getTagStrings(userId);
			this.userNames = usersData.getTagStrings(userName);
			this.billableAmounts = usersData.getTagStrings(billableAmount);
			this.billableTimes = usersData.getTagStrings(billableTime);
			this.laborTimes = usersData.getTagStrings(laborTime);
			this.travelTimes = usersData.getTagStrings(travelTime);
			this.entryDates = usersData.getTagStrings(entryDate);
			this.createdDates = usersData.getTagStrings(createdDate);
			this.invoiceIds = usersData.getTagStrings(invoiceId);
			this.descriptionPrivates = usersData.getTagStrings(descriptionPrivate);
			this.descriptionPublics = usersData.getTagStrings(descriptionPublic);
			this.activityTypes = usersData.getTagStrings(activityType);
			System.out.println("activityTypes = " + this.activityTypes.size());
			
			this.litigationCodes = usersData.getTagStrings(litigationCode);
			this.litigationCodeDescs = usersData.getTagStrings(litigationCodeDesc);
			this.isBillables = usersData.getTagStrings(isBillable);
			this.billingStatuses = usersData.getTagStrings(billingStatus);
			this.hourlyRates = usersData.getTagStrings(hourlyRate);
			this.payableRates = usersData.getTagStrings(payableRate);
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

	public ArrayList<String> getIds()
	{
		return this.Ids;
	}

	public ArrayList<String> getClientIds()
	{
		return this.clientIds;
	}

	public ArrayList<String> getClientNames()
	{
		return this.clientNames;
	}

	public ArrayList<String> getProjectIds()
	{
		return this.projectIds;
	}

	public ArrayList<String> getProjectNames()
	{
		return this.projectNames;
	}

	public ArrayList<String> getUserIds()
	{
		return this.userIds;
	}

	public ArrayList<String> getUserNames()
	{
		return this.userNames;
	}

	public ArrayList<String> getBillableAmounts()
	{
		return this.billableAmounts;
	}

	public ArrayList<String> getBillableTimes()
	{
		return this.billableTimes;
	}

	public ArrayList<String> getLaborTimes()
	{
		return this.laborTimes;
	}

	public ArrayList<String> getTravelTimes()
	{
		return this.travelTimes;
	}

	public ArrayList<String> getEntryDates()
	{
		return this.entryDates;
	}

	public ArrayList<String> getCreatedDates()
	{
		return this.createdDates;
	}

	public ArrayList<String> getInvoiceIds()
	{
		return this.invoiceIds;
	}

	public ArrayList<String> getDescriptionPrivates()
	{
		return this.descriptionPrivates;
	}

	public ArrayList<String> getDescriptionPublics()
	{
		return this.descriptionPublics;
	}

	public ArrayList<String> getActivityTypes()
	{
		return this.activityTypes;
	}

	public ArrayList<String> getLitigationCodes()
	{
		return this.litigationCodes;
	}

	public ArrayList<String> getLitigationCodeDescs()
	{
		return this.litigationCodeDescs;
	}

	public ArrayList<String> getIsBillables()
	{
		return this.isBillables;
	}

	public ArrayList<String> getBillingStatuses()
	{
		return this.billingStatuses;
	}

	public ArrayList<String> getHourlyRates()
	{
		return this.hourlyRates;
	}

	public ArrayList<String> getPayableRates()
	{
		return this.payableRates;
	}
}