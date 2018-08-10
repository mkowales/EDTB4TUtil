package com.edt.b4t.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AppProperties
{
	private static String fname = "myProperties";
	
	public static void main(String[] args)
	{
		AppProperties.init();
	}
	
	public static void displayFiles(String dir)
	        throws IOException
	{
        File props = new File(dir);
        
        File[] files = props.listFiles();
        
        if (0 < files.length)
        {
            for (int index = 0; index < files.length; index++)
            {
                System.out.println("file #" + (index + 1) + " = "
                        + "getAbsolutePath: " + files[index].getAbsolutePath()
                        + Str.CR + "getCanonicalPath: " + files[index].getCanonicalPath()
                        + Str.CR + "getName: " + files[index].getName()
                        + Str.CR + "getParent: " + files[index].getParent()
                        + Str.CR + "getPath: " + files[index].getPath()
                        );
            }
        }
	}
	
	public static void dir(String dir)
	{
        File propertiesFile = new File(dir);
        
        File[] files = propertiesFile.listFiles();
        
        System.out.println(files.length);
        
        for (int index = 0; index < files.length; index++)
        {
            File file = files[index];
            System.out.println(file.getName());
        }
        
//        propFile = new FileInputStream(AppProperties.fname);
//        p = new Properties(System.getProperties());
	}
	
	public static void setFname(String fname)
	{
		AppProperties.fname = fname;
	}
	
	public static void update(String key, String value)
	{
		FileInputStream propFile = null;
		Properties p = null;
		
        try
		{
        	File propertiesFile = new File(AppProperties.fname);
        	
        	if (propertiesFile.exists())
        	{
        		propFile = new FileInputStream(AppProperties.fname);
        		p = new Properties(System.getProperties());
        		
        		p.load(propFile);
        		System.out.println("Updating " + key + " to " + value);
        		p.setProperty(key, value);
        		p.store(new FileOutputStream(AppProperties.fname), "// comments");
        	}
        	else
        		System.out.println(AppProperties.fname + " does NOT exist");
		}
        catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void init()
	{
		FileInputStream propFile = null;
		Properties p = null;
		
        try
		{
        	File propertiesFile = new File(AppProperties.fname);
        	
        	System.out.println("Looking for " + AppProperties.fname);
        	if (propertiesFile.exists())
        	{
 // 			set up new properties object from file "myProperties"
        		propFile = new FileInputStream(AppProperties.fname);
        		p = new Properties(System.getProperties());
        		
        		p.load(propFile);
        	}
        	else
        		System.out.println(AppProperties.fname + " does NOT exist");
		}
        catch (IOException e)
		{
			e.printStackTrace();
		}

// 		set the system properties
        System.setProperties(p);
        
//		display new properties
//        System.getProperties().list(System.out);
	}
}
