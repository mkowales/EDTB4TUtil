package com.edt.b4t;

import java.io.IOException;
import java.util.ArrayList;

import com.edt.b4t.util.ParseOData;
import com.edt.b4t.util.Str;

public class B4TPayments
{
	private ArrayList<String> ids = null,
			clientIds = null,
			clientNames = null,
			projectIds = null,
			projectNames = null,
			paymentAmounts = null,
			isBalanceAdjustments = null,
			paymentDates = null,
			createdDates = null,
			createdBys = null,
			paymentTypes = null,
			paymentMethods = null,
			checkNumbers = null,
			privateNotes = null,
			publicNotes = null;

	private String url = Str.BASE_URL + "/v1/payments";

	public static void main(String[] args)
	{
		B4TPayments sample = new B4TPayments("");
		
		double paymentAmount = 0;

		for (int index = 0; index < sample.getIds().size(); index++)
		    paymentAmount += Double.valueOf(sample.getPaymentAmounts().get(index));
		
	      System.out.println("Total Payment Amount: " + Str.dollarFormat.format(paymentAmount) + 
                  " @ " + sample.getIds().size());

	}

	public B4TPayments(String userPrefs)
	{
		String id = "id",
				clientId = "clientId",
				clientName = "clientName",
				projectName = "projectName",
				paymentAmount = "paymentAmount",
				isBalanceAdjustment = "isBalanceAdjustment",
				paymentDate = "paymentDate",
				createdDate = "createdDate",
				createdBy = "createdBy",
				paymentType = "paymentType",
				paymentMethod = "paymentMethod",
				checkNumber = "checkNumber",
				privateNote = "privateNote",
				publicNote = "publicNote";

		try
		{
			this.url = this.url + userPrefs;
			
//			System.out.println(this.url);
			
			ParseOData usersData = new ParseOData(this.url);
			
			this.ids = usersData.getTagStrings(id);
			this.clientIds = usersData.getTagStrings(clientId);
			this.clientNames = usersData.getTagStrings(clientName);
			this.projectNames = usersData.getTagStrings(projectName);
			this.paymentAmounts = usersData.getTagStrings(paymentAmount);
			this.isBalanceAdjustments = usersData.getTagStrings(isBalanceAdjustment);
			this.paymentDates = usersData.getTagStrings(paymentDate);
			this.createdDates = usersData.getTagStrings(createdDate);
			this.createdBys = usersData.getTagStrings(createdBy);
			this.paymentTypes = usersData.getTagStrings(paymentType);
			this.paymentMethods = usersData.getTagStrings(paymentMethod);
			this.checkNumbers = usersData.getTagStrings(checkNumber);
			this.privateNotes = usersData.getTagStrings(privateNote);
			this.publicNotes = usersData.getTagStrings(publicNote);
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

	public ArrayList<String> getIds()
	{
		return ids;
	}

	public ArrayList<String> getClientIds()
	{
		return clientIds;
	}

	public ArrayList<String> getClientNames()
	{
		return clientNames;
	}

	public ArrayList<String> getProjectIds()
	{
		return projectIds;
	}

	public ArrayList<String> getProjectNames()
	{
		return projectNames;
	}

	public ArrayList<String> getPaymentAmounts()
	{
		return paymentAmounts;
	}

	public ArrayList<String> getIsBalanceAdjustments()
	{
		return isBalanceAdjustments;
	}

	public ArrayList<String> getPaymentDates()
	{
		return paymentDates;
	}

	public ArrayList<String> getCreatedDates()
	{
		return createdDates;
	}

	public ArrayList<String> getCreatedBys()
	{
		return createdBys;
	}

	public ArrayList<String> getPaymentTypes()
	{
		return paymentTypes;
	}

	public ArrayList<String> getPaymentMethods()
	{
		return paymentMethods;
	}

	public ArrayList<String> getCheckNumbers()
	{
		return checkNumbers;
	}

	public ArrayList<String> getPrivateNotes()
	{
		return privateNotes;
	}

	public ArrayList<String> getPublicNotes()
	{
		return publicNotes;
	}
}
