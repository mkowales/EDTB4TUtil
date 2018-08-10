package com.edt.b4t.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import net.maritimecloud.internal.core.javax.json.Json;
import net.maritimecloud.internal.core.javax.json.JsonArray;
import net.maritimecloud.internal.core.javax.json.JsonObject;
import net.maritimecloud.internal.core.javax.json.JsonReader;

public class ParseOData
{
    private JsonArray jsonArray = null;
    
	public ParseOData(String url)
			throws IOException
	{
        System.out.println(url);
        
        InputStream input = new URL(url).openStream();

// Create JsonReader object
        JsonReader jsonReader = Json.createReader(input);
    
// Get JsonObject (root object).
        JsonObject rootJSON = jsonReader.readObject();
  
// Close resources
        jsonReader.close();
        input.close();
  
     // Reading production_companies array from json
        this.jsonArray = rootJSON.getJsonArray("data");
	}
	
	public ArrayList <String> getTagStrings(String tag)
			throws IOException
	{
		ArrayList <String> values = new ArrayList<String>();
		
		if (null != this.jsonArray)
		{
//		    System.out.println(tag + " " + jsonArray.size());
		    
		    for (int index = 0; index < jsonArray.size(); index++)
		    {
                JsonObject object = (JsonObject)jsonArray.get(index);
                
//                if (0 == index)
//                    System.out.println(jsobj.getValueType());
                
                try
                {
//                    System.out.println(tag + " " + object.getJsonString(tag));
                    
                    if (null != object.getJsonString(tag))
                    {
                        String val = object.getJsonString(tag).toString();
                        val = val.replaceFirst("\"", "");
                        val = val.substring(0, val.length() - 1);
                        values.add(val);
                    }
                }
                catch (ClassCastException cce1)
                {
                    try
                    {
                        values.add(object.getJsonNumber(tag).toString());
                    }
                    catch (ClassCastException cce2)
                    {
                        values.add("0");
                    }
                }
            }
		}
			
		return values;
	}
}