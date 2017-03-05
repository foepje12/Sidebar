package handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import main.Constants;

public class CategoryHandler
{
	private static Map<String, Category> categoryMap;
	private static Map<String, BarItem> barItemMap;

	static
	{
		categoryMap = new HashMap<String, Category>();
		barItemMap = new HashMap<String, BarItem>();
	}

	public static Set<String> getCategoryNames()
	{
		Categories catgs = getCategories();
		Set<String> keySet = catgs.categoryMap.keySet();
		return keySet;
	}
	
	public static Set<String> getBarItemNames(String categoryName)
	{
		Categories catgs = getCategories();		
		Set<String> keySet = catgs.categoryMap.get(categoryName).barItemMap.keySet();
		return keySet;
	}

	public static void changeCategoryName(String oldName, String newName)
	{
		Categories catgs = getCategories();
		catgs.categoryMap.put(newName, catgs.categoryMap.get(oldName));
		catgs.categoryMap.remove(oldName);
		categoryMap = catgs.categoryMap;		
		WriteToFile();
	}

	public static Categories getCategories()
	{
		File file = new File(Constants.filesPath + "/" + Constants.categoriesFile);
		try
		{
			Gson gson = new Gson();
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

	public static void addCategory(String name)
	{
		Categories categories = getCategories();
		Category category = new Category();
		category.barItemMap = new HashMap<String, BarItem>();
		categories.categoryMap.put(name, category);
	}

	public static void addBarItem(String categoryName, BarItem item)
	{
		Categories categories = getCategories();
		categories.categoryMap.get(categoryName).barItemMap.put(item.name, item);
		WriteToFile();
	}

	public static void WriteToFile()
	{
		Gson gson = new Gson();

		Categories categories = getCategories();
		
		if(!categoryMap.isEmpty())
		{
			categories.categoryMap = categoryMap;
		}
		
		/*
		 * BarItem barItem = new BarItem(); barItem.name = "Github";
		 * barItem.webUrl = "https://github.com/"; barItem.iconPath =
		 * "assets/icons/github.png"; barItemMap.put(barItem.name, barItem);
		 * 
		 * barItem.name = "Trello"; barItem.webUrl = "https://trello.com/";
		 * barItem.iconPath = "assets/icons/trello.png";
		 * barItemMap.put(barItem.name, barItem);
		 * 
		 * Category category = new Category(); category.barItemMap = barItemMap;
		 * category.name = "Favourites";
		 * 
		 * categoryMap.put("Favourites", category);
		 * 
		 * categories.categoryMap = categoryMap;
		 */

		if (!(new File(Constants.filesPath).exists()))
		{
			new File(Constants.filesPath).mkdir();
		}

		PrintWriter writer;
		try
		{
			writer = new PrintWriter(Constants.filesPath + "/" + Constants.categoriesFile);
			writer.write(gson.toJson(categories));
			writer.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}

class Categories
{
	Map<String, Category> categoryMap;
}

class Category
{
	Map<String, BarItem> barItemMap;
}

class BarItem
{
	String name;
	String webUrl;
	String iconPath;

}