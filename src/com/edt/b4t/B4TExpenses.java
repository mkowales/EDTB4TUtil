package com.edt.b4t;

import java.io.IOException;
import java.util.ArrayList;

import com.edt.b4t.util.ParseOData;
import com.edt.b4t.util.Str;

public class B4TExpenses
{
	private ArrayList<String> ids = null,
			clientIds = null,
			clientNames = null,
			projectIds = null,
			projectNames = null,
			userIds = null,
			userNames = null,
			costPrices = null,
			sellPrices = null,
			expenseDates = null,
			createdDates = null,
			quantitys = null,
			invoiceIds = null,
			billingStatuses = null,
			descriptionPrivates = null,
			descriptionPublics = null,
			expenseTypes = null;

	private String url = Str.BASE_URL + "/v1/expenses";

	public static void main(String[] args)
	{
	    B4TExpenses sample = new B4TExpenses("");
	    
	    System.out.println(sample.getIds().size());
	}
	
	public B4TExpenses(String prefs)
	{
		String id = "id",
				clientId = "clientId",
				clientName = "clientName",
				projectId = "projectId",
				projectName = "projectName",
				userId = "userId",
				userName = "userName",
				costPrice = "costPrice",
				sellPrice = "sellPrice",
				expenseDate = "expenseDate",
				createdDate = "createdDate",
				quantity = "quantity",
				invoiceId = "invoiceId",
				billingStatus = "billingStatus",
				descriptionPrivate = "descriptionPrivate",
				descriptionPublic = "descriptionPublic",
				expenseType = "expenseType";

		try
		{
			this.url = this.url + prefs;
			
//			System.out.println(this.url);
			
			ParseOData data = new ParseOData(this.url);
			
			this.ids = data.getTagStrings(id);
			this.clientIds = data.getTagStrings(clientId);
			this.clientNames = data.getTagStrings(clientName);
			this.projectIds = data.getTagStrings(projectId);
			this.projectNames = data.getTagStrings(projectName);
			this.userIds = data.getTagStrings(userId);
			this.userNames = data.getTagStrings(userName);
			this.costPrices = data.getTagStrings(costPrice);
			this.sellPrices = data.getTagStrings(sellPrice);
			this.expenseDates = data.getTagStrings(expenseDate);
			this.createdDates = data.getTagStrings(createdDate);
			this.quantitys = data.getTagStrings(quantity);
			this.invoiceIds = data.getTagStrings(invoiceId);
			this.billingStatuses = data.getTagStrings(billingStatus);
			this.descriptionPrivates = data.getTagStrings(descriptionPrivate);
			this.descriptionPublics = data.getTagStrings(descriptionPublic);
			this.expenseTypes = data.getTagStrings(expenseType);
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

	public ArrayList<String> getCostPrices()
	{
		return this.costPrices;
	}

	public ArrayList<String> getSellPrices()
	{
		return this.sellPrices;
	}

	public ArrayList<String> getExpenseDates()
	{
		return this.expenseDates;
	}

	public ArrayList<String> getCreatedDates()
	{
		return this.createdDates;
	}

	public ArrayList<String> getQuantitys()
	{
		return this.quantitys;
	}

	public ArrayList<String> getInvoiceIds()
	{
		return this.invoiceIds;
	}

	public ArrayList<String> getBillingStatuses()
	{
		return this.billingStatuses;
	}

	public ArrayList<String> getDescriptionPrivates()
	{
		return this.descriptionPrivates;
	}

	public ArrayList<String> getDescriptionPublics()
	{
		return this.descriptionPublics;
	}

	public ArrayList<String> getExpenseTypes()
	{
		return this.expenseTypes;
	}
}
