package handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.Constants;

public class CategoryHandler extends JSonInfoHandler
{
	private static Map<String, Category> categoryMap;

	static
	{
		categoryMap = new HashMap<String, Category>();
	}

	public static Set<String> getCategoryNames()
	{
		Categories catgs = getCategories();
		Set<String> keySet = catgs.categoryMap.keySet();
		return keySet;
	}

	public static String getCurrentCategoryName()
	{
		File file = new File(Constants.filesPath + "/" + Constants.configFile);
		try
		{
			FileReader reader;
			reader = new FileReader(file);
			Properties prop = new Properties();
			prop.load(reader);
			String currentCategoryName = prop.getProperty("currentCategory");
			reader.close();

			return currentCategoryName;
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return "";
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

	public static void renameCategory(String oldName, String newName)
	{
		Categories catgs = getCategories();
		catgs.categoryMap.put(newName, catgs.categoryMap.get(oldName));
		catgs.categoryMap.remove(oldName);
		categoryMap = catgs.categoryMap;
		WriteCategoryToFile();
	}

	public static void changeCategoryIcon(String catgName, String iconPath)
	{
		Categories catgs = getCategories();
		catgs.categoryMap.get(catgName).iconPath = iconPath;
		categoryMap = catgs.categoryMap;
		WriteCategoryToFile();
	}

	public static String getCategoryInfo(String catgName)
	{
		Categories catgs = getCategories();

		if (catgs.categoryMap.get(catgName) != null)
		{
			return catgs.categoryMap.get(catgName).iconPath;
		}
		return null;
	}

	public static int getCategoryAmount()
	{
		Categories categories = getCategories();
		return categories.categoryMap.size();
	}

	public static void deleteCategory(String name)
	{
		Categories categories = getCategories();
		categories.categoryMap.remove(name);
		categoryMap = categories.categoryMap;
		WriteCategoryToFile();
	}
}
