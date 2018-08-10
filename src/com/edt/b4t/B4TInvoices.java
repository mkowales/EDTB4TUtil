package com.edt.b4t;

import java.io.IOException;
import java.util.ArrayList;

import com.edt.b4t.util.ParseOData;
import com.edt.b4t.util.Str;

public class B4TInvoices
{
	private ArrayList<String> ids = null,
						customIds = null,
						clientIds = null,
						clientNames = null,
						projectIds = null,
						projectNames = null,
						descriptions = null,
						invoiceDates = null,
						createdDates = null,
						statuses = null,
						notes = null,
						invoiceTermTypes = null,
						invoiceAmounts = null,
						expenseAmounts = null,
						lateFeeAmounts = null,
						paidStatuses = null;
			
	private String url = Str.BASE_URL + "/v1/invoices";
	
	public static void main(String[] args)
	{
		double invoiceAmount = 0;
		
		B4TInvoices sample = new B4TInvoices("");
		
		for (int index = 0; index < sample.getIds().size(); index++)
		{
/*
			System.out.println("Id: " + sample.getIds().get(index) +
					" Custom Id: " + sample.getCustomIds().get(index) +
					" Client Name: " + sample.getClientNames().get(index) +
					" Project Name: " + sample.getProjectNames().get(index) +
					" Invoice Amount: " + sample.getInvoiceAmouts().get(index) +
					" Invoice Date: " + sample.getInvoiceDates().get(index) +
					" Status: " + sample.getStatuses().get(index));
*/			
			invoiceAmount += Double.parseDouble(sample.getInvoiceAmouts().get(index));
		}

		System.out.println("Total Invoice Amount: " + Str.dollarFormat.format(invoiceAmount) + 
		                    " @ " + sample.getIds().size());
	}
	
	public B4TInvoices(String prefs)
	{
		String id = "id",
				customId = "customId",
				clientId = "clientId",
				clientName = "clientName",
				projectId = "projectId",
				projectName = "projectName",
				description = "description",
				invoiceDate = "invoiceDate",
				createdDate = "createdDate",
				status = "status",
				notes = "notes",
				invoiceTermType = "invoiceTermType",
				invoiceAmount = "invoiceAmount",
				expenseAmount = "expenseAmount",
				lateFeeAmount = "lateFeeAmount",
				paidStatus = "paidStatus";

		try
		{
			this.url = this.url + prefs;
			
//			System.out.println(this.url);
			
			ParseOData data = new ParseOData(this.url);
			
		    this.ids = data.getTagStrings(id);
			this.customIds = data.getTagStrings(customId);
			this.clientIds = data.getTagStrings(clientId);
			this.clientNames = data.getTagStrings(clientName);
			this.projectIds = data.getTagStrings(projectId);
			this.projectNames = data.getTagStrings(projectName);
			this.descriptions = data.getTagStrings(description);
			this.invoiceDates = data.getTagStrings(invoiceDate);
			this.createdDates = data.getTagStrings(createdDate);
			this.statuses = data.getTagStrings(status);
			this.notes = data.getTagStrings(notes);
			this.invoiceTermTypes = data.getTagStrings(invoiceTermType);
			this.invoiceAmounts = data.getTagStrings(invoiceAmount);
			this.expenseAmounts = data.getTagStrings(expenseAmount);
			this.lateFeeAmounts = data.getTagStrings(lateFeeAmount);
			this.paidStatuses = data.getTagStrings(paidStatus);
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

	public ArrayList<String> getCustomIds()
	{
		return customIds;
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

	public ArrayList<String> getDescriptions()
	{
		return descriptions;
	}

	public ArrayList<String> getInvoiceDates()
	{
		return invoiceDates;
	}

	public ArrayList<String> getCreatedDates()
	{
		return createdDates;
	}

	public ArrayList<String> getStatuses()
	{
		return statuses;
	}

	public ArrayList<String> getNotes()
	{
		return notes;
	}

	public ArrayList<String> getInvoiceTermTypes()
	{
		return invoiceTermTypes;
	}

	public ArrayList<String> getInvoiceAmouts()
	{
		return invoiceAmounts;
	}

	public ArrayList<String> getExpenseAmounts()
	{
		return expenseAmounts;
	}

	public ArrayList<String> getLateFeeAmounts()
	{
		return lateFeeAmounts;
	}

	public ArrayList<String> getPaidStatuses()
	{
		return paidStatuses;
	}
}
