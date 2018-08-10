package com.edt.b4t;

import java.io.IOException;
import java.util.ArrayList;

import com.edt.b4t.util.ParseOData;
import com.edt.b4t.util.Str;

public class B4TClients
{
	private ArrayList<String> ids = null,
						customIds = null,
						clientNames = null,
						billingNames = null,
						address1s = null,
						address2s = null,
						address3s = null,
						cities = null,
						states = null,
						countries = null,
						postalCodes = null,
						phones = null,
						emails = null,
						statuses = null,
						creationDates = null;
			
	private String url = Str.BASE_URL + "/v1/clients";
	
	public static void main(String[] args)
	{
//		String name = "AAR Corp";

//		B4TClients sample = new B4TClients(Str.convertToURL("?$filter=clientName eq " + name));
		B4TClients sample = new B4TClients(Str.convertToURL(""));
		
		for (int index = 0; (index < 1) && (index < sample.getIds().size()); index++)
		{
			System.out.println("Id: " + sample.getIds().get(index) +
					" Custom Id: " + sample.getCustomIds().get(index) +
					" Client Name: " + sample.getClientNames().get(index) +
					" Invoice Date: " + sample.getEmails().get(index) +
					" Status: " + sample.getStatuses().get(index));
		}
	}

	public B4TClients(String userPrefs)
	{
		String id = "id",
				customId = "customId",
				clientName = "clientName",
				billingName = "billingName",
				address1 = "address1",
				address2 = "address2",
				address3 = "address3",
				city = "city",
				state = "state",
				country = "country",
				postalCode = "postalCode",
				phone = "phone",
				email = "email",
				status = "status",
				creationDate = "creationDate";

		try
		{
			this.url = this.url + userPrefs;
			
//			System.out.println(this.url);
			
			ParseOData usersData = new ParseOData(this.url);
			
			this.ids = usersData.getTagStrings(id);
			this.customIds = usersData.getTagStrings(customId);
			this.clientNames = usersData.getTagStrings(clientName);
			this.billingNames = usersData.getTagStrings(billingName);
			this.address1s = usersData.getTagStrings(address1);
			this.address2s = usersData.getTagStrings(address2);
			this.address3s = usersData.getTagStrings(address3);
			this.cities = usersData.getTagStrings(city);
			this.states = usersData.getTagStrings(state);
			this.countries = usersData.getTagStrings(country);
			this.postalCodes = usersData.getTagStrings(postalCode);
			this.phones = usersData.getTagStrings(phone);
			this.emails = usersData.getTagStrings(email);
			this.statuses = usersData.getTagStrings(status);
			this.creationDates = usersData.getTagStrings(creationDate);
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

	public ArrayList<String> getClientNames()
	{
		return clientNames;
	}

	public ArrayList<String> getBillingNames()
	{
		return billingNames;
	}

	public ArrayList<String> getAddress1s()
	{
		return address1s;
	}

	public ArrayList<String> getAddress2s()
	{
		return address2s;
	}

	public ArrayList<String> getAddress3s()
	{
		return address3s;
	}

	public ArrayList<String> getCities()
	{
		return cities;
	}

	public ArrayList<String> getStates()
	{
		return states;
	}

	public ArrayList<String> getCountries()
	{
		return countries;
	}

	public ArrayList<String> getPostalCodes()
	{
		return postalCodes;
	}

	public ArrayList<String> getPhones()
	{
		return phones;
	}

	public ArrayList<String> getEmails()
	{
		return emails;
	}

	public ArrayList<String> getStatuses()
	{
		return statuses;
	}

	public ArrayList<String> getCreationDates()
	{
		return creationDates;
	}
}