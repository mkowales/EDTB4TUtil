package com.edt.b4t;

import java.io.IOException;
import java.util.ArrayList;

import com.edt.b4t.util.ParseOData;
import com.edt.b4t.util.Str;

public class B4TProjects
{
	private ArrayList<String> ids = null,
						customIds = null,
						projectNames = null,
						descriptions = null,
						clientIds = null,
						clientNames = null,
						projectIds = null,
						projectTypes = null,
						statuses = null,
						assignedTos = null,
						billingMethods = null,
						createdDates = null,
						emails = null;
	
	private String url = Str.BASE_URL + "/v1/projects";
	
	public final static String B4T_PROJECT_URL = "https://secure.bill4time.com/B4T2/Matters/view.aspx?tid=";
	
	public static final int HOURLY = 1;
	public static final int FIXED_FEE = 2;
	public static final int PERCENTAGE = 3;
	
	public static void main(String[] args)
	{
		B4TProjects sample = new B4TProjects("");
		
        System.out.println("Total Projects: " +  sample.getIds().size());
	}

	public static String convertProjectName(String name)
	{
		String convertedName;
		
		convertedName = "%27" + name + "%27";
		convertedName = convertedName.replaceAll(" ", "%20");
		convertedName = convertedName.replaceAll("#", "%23");
		convertedName = convertedName.replaceAll("&", "%38");

		return (convertedName);
	}

	public static String getBillingMethod(int type)
	{
		String urlType;
		
		switch (type)
		{
			case B4TProjects.HOURLY:
				urlType = "%27Hourly%27";
				break;
		
			case B4TProjects.FIXED_FEE:
				urlType = "%27Flat%20Fee%27";
				break;
				
			case B4TProjects.PERCENTAGE:
			default:
				urlType = "%27Percentage%27";
				break;
		}
		
		return (urlType);
	}
	
	public B4TProjects(String prefs)
	{
		String id = "id",
				customId = "customId",
				clientName = "clientName",
				projectName = "projectName",
				description = "description",
				clientId = "clientId",
				projectType = "projectType",
				projectId = "projectId",
				status = "status",
				assignedTo = "assignedTo",
				billingMethod = "billingMethod",
				createdDate = "createdDate",
				email = "email";

		ArrayList <String> values = new ArrayList<String>();

		try
		{
			this.url = this.url + prefs;
			
			ParseOData data = new ParseOData(this.url);
			
			if (null != (values = data.getTagStrings(id)))
				this.ids = values;
			
			if (null != (values = data.getTagStrings(customId)))
				this.customIds = values;
			
			if (null != (values = data.getTagStrings(projectName)))
				this.projectNames = values;
			
			if (null != (values = data.getTagStrings(description)))
				this.descriptions = values;
			
			if (null != (values = data.getTagStrings(clientId)))
				this.clientIds = values;
			
			if (null != (values = data.getTagStrings(clientName)))
				this.clientNames = values;
			
			if (null != (values = data.getTagStrings(projectType)))
				this.projectTypes = values;
			
			if (null != (values = data.getTagStrings(status)))
				this.statuses = values;
			
			if (null != (values = data.getTagStrings(assignedTo)))
				this.assignedTos = values;
			
			if (null != (values = data.getTagStrings(billingMethod)))
				this.billingMethods = values;
			
			if (null != (values = data.getTagStrings(createdDate)))
				this.createdDates = values;
			
			if (null != (values = data.getTagStrings(email)))
				this.emails = values;
			
			if (null != (values = data.getTagStrings(projectId)))
				this.projectIds = values;
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

	public ArrayList<String> getCustomIds()
	{
		return this.customIds;
	}

	public ArrayList<String> getProjectNames()
	{
		return this.projectNames;
	}

	public ArrayList<String> getDescriptions()
	{
		return this.descriptions;
	}

	public ArrayList<String> getClientIds()
	{
		return this.clientIds;
	}

	public ArrayList<String> getClientNames()
	{
		return this.clientNames;
	}

	public ArrayList<String> getProjectTypes()
	{
		return this.projectTypes;
	}

	public ArrayList<String> getStatuses()
	{
		return this.statuses;
	}

	public ArrayList<String> getAssignedTos()
	{
		return this.assignedTos;
	}

	public ArrayList<String> getBillingMethods()
	{
		return this.billingMethods;
	}

	public ArrayList<String> getCreatedDates()
	{
		return this.createdDates;
	}

	public ArrayList<String> getEmails()
	{
		return this.emails;
	}

	public ArrayList<String> getProjectIds()
	{
		return this.projectIds;
	}
}
