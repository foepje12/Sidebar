package handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

	public static void renameCategory(String oldName, String newName)
	{
		Categories catgs = getCategories();
		catgs.categoryMap.put(newName, catgs.categoryMap.get(oldName));
		catgs.categoryMap.remove(oldName);
		categoryMap = catgs.categoryMap;
		WriteCategoryToFile();
	}

	public static void renameBarItem(String catgName, String oldName, String newName)
	{
		Categories catgs = getCategories();
		Category catg = catgs.categoryMap.get(catgName);

		catg.barItemMap.put(newName, catg.barItemMap.get(oldName));
		catg.barItemMap.remove(oldName);
		barItemMap = catg.barItemMap;
		WriteBarItemToFile(catgName);
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

	public static void addCategory()
	{
		Categories categories = getCategories();
		Category category = new Category();
		category.barItemMap = new HashMap<String, BarItem>();
		categories.categoryMap.put("newCategory", category);
		categoryMap = categories.categoryMap;
		WriteCategoryToFile();
	}

	public static void addBarItem(String catgName)
	{
		Categories categories = getCategories();
		Category category = categories.categoryMap.get(catgName);
		category.barItemMap.put("newBarItem", new BarItem());

		barItemMap = category.barItemMap;
		WriteBarItemToFile(catgName);
	}

	public static void WriteCategoryToFile()
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		Categories categories = getCategories();

		if (!categoryMap.isEmpty())
		{
			categories.categoryMap = categoryMap;
		}

		if (!(new File(Constants.filesPath).exists()))
		{
			new File(Constants.filesPath).mkdir();
		}

		WriteToFile(Constants.filesPath + "/" + Constants.categoriesFile, gson.toJson(categories));
	}

	public static void WriteBarItemToFile(String catgName)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		Categories categories = getCategories();

		if (!barItemMap.isEmpty())
		{
			categories.categoryMap.get(catgName).barItemMap = barItemMap;
		}

		if (!(new File(Constants.filesPath).exists()))
		{
			new File(Constants.filesPath).mkdir();
		}

		WriteToFile(Constants.filesPath + "/" + Constants.categoriesFile, gson.toJson(categories));
	}

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
	String webUrl;
	String iconPath;

}