package handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import main.Constants;

public class JSonInfoHandler
{
	public static void WriteToFile(String whereToWrite, String whatToWrite)
	{
		PrintWriter writer;
		try
		{
			writer = new PrintWriter(whereToWrite);
			writer.write(whatToWrite);
			writer.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public static Categories getCategories()
	{
		try
		{
			Gson gson = new Gson();
			File file = new File(Constants.filesPath + "/" + Constants.categoriesFile);

			if (file.exists())
			{
				FileReader reader = new FileReader(file);

				Categories catgs = gson.fromJson(reader, Categories.class);

				return catgs;
			}
		}
		catch (JsonSyntaxException | JsonIOException | FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}

class Categories
{
	public Map<String, Category> categoryMap;
}

class Category
{
	public Map<String, BarItem> barItemMap;
	public String iconPath;
}

class BarItem
{
	String webUrl;
	String iconPath;

}