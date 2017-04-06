package handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Map;

import com.google.gson.Gson;

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
		File file = new File(Constants.filesPath + "/" + Constants.categoriesFile);
		try
		{
			Gson gson = new Gson();
			FileReader reader = new FileReader(file);
			Categories catgs = gson.fromJson(reader, Categories.class);
			return catgs;
		}
		catch (FileNotFoundException e)
		{
			FileHandler.CreateNewFile(file);
			getCategories();
		}
		return null;
	}

	public static Profiles getProfiles()
	{
		File file = new File(Constants.filesPath + "/" + Constants.profileFile);

		try
		{
			Gson gson = new Gson();
			FileReader reader = new FileReader(file);
			Profiles profs = gson.fromJson(reader, Profiles.class);
			return profs;
		}
		catch (FileNotFoundException e)
		{
			FileHandler.CreateNewFile(file);
		}
		return null;
	}
}

class Profiles
{
	Map<String, Profile> profileMap;
}

class Profile
{
	public Map<String, Category> categories;
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