package com.edt.b4t.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DemoFile
{
	public static String newLine = "\r\n";
	
	private BufferedWriter buffer = null;
	
	private FileWriter fname = null;
	
	private String DEMO_FILE = "demo.txt";
	
	public void writeLine(String line)
	{
		try
		{
			if (null != line)
			{
				this.buffer.write(line);
				this.buffer.newLine();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void close()
	{
		try
		{
			if (buffer != null)
				buffer.close();
			
			if (fname != null)
				fname.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public DemoFile(String demoFile)
	{
		if (null != demoFile)
		{
			try
			{
				File file = new File(demoFile);
				
				if (file.exists())
					file.delete();
				
				fname = new FileWriter(demoFile);
				
				this.buffer = new BufferedWriter(fname);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public DemoFile()
	{
		try
		{
			File file = new File(DEMO_FILE);
			
			if (file.exists())
				file.delete();
			
			fname = new FileWriter(DEMO_FILE);
			
			this.buffer = new BufferedWriter(fname);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
