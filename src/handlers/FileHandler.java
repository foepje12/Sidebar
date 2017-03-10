package handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

import main.Constants;

public class FileHandler
{

	public static void CreateNewFile(File file)
	{
		try
		{
			Files.createFile(file.toPath());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void WriteToConfig(String propertyName, String value)
	{
		File file = new File(Constants.filesPath + "/" + Constants.configFile);
		FileOutputStream output;
		try
		{
			output = new FileOutputStream(file);
			Properties properties = new Properties();
			properties.setProperty(propertyName, value);
			properties.store(output, "TestCommentOrSomething");
			output.close();
		}
		catch (FileNotFoundException e)
		{
			CreateNewFile(file);
			WriteToConfig(propertyName, value);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static String GetConfigProperty(String propertyName)
	{
		File file = new File(Constants.filesPath + "/" + Constants.configFile);
		FileReader input;
		try
		{
			input = new FileReader(file);
			Properties properties = new Properties();
			properties.load(input);
			String propertyValue = properties.getProperty(propertyName);
			input.close();
			return propertyValue;
		}
		catch (FileNotFoundException e)
		{
			return null;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
