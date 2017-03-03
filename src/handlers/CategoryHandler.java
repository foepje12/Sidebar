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
	private static final Map<String, Category> categoryMap;
	private static final Map<String, BarItem> barItemMap;

	static
	{
		categoryMap = new HashMap<String, Category>();
		barItemMap = new HashMap<String, BarItem>();
	}

	public static void Test()
	{
		Categories cats = getCategories();

		Set<String> keySet = cats.categoryMap.get("Favourites").barItemMap.keySet();

		for (String set : keySet)
		{
			System.out.println(set);
		}
	}

	public static Categories getCategories()
	{
		try
		{
			Gson gson = new Gson();
			FileReader reader = new FileReader(Constants.filesPath + "/" + Constants.categoriesFile);

			Categories catgs = gson.fromJson(reader, Categories.class);

			return catgs;
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
		category.name = name;
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

		Categories categories =  getCategories();

		BarItem barItem = new BarItem();
		barItem.name = "Github";
		barItem.webUrl = "https://github.com/";
		barItem.iconPath = "assets/icons/github.png";
		barItemMap.put(barItem.name, barItem);

		barItem.name = "Trello";
		barItem.webUrl = "https://trello.com/";
		barItem.iconPath = "assets/icons/trello.png";
		barItemMap.put(barItem.name, barItem);

		Category category = new Category();
		category.barItemMap = barItemMap;
		category.name = "Favourites";

		categoryMap.put("Favourites", category);

		categories.categoryMap = categoryMap;

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
	String name;
	Map<String, BarItem> barItemMap;
}

class BarItem
{
	String name;
	String webUrl;
	String iconPath;

}