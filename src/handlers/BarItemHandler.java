package handlers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.Constants;

public class BarItemHandler extends JSonInfoHandler
{
	private static Map<String, BarItem> barItemMap;
	
	static
	{
		barItemMap = new HashMap<String, BarItem>();
	}
	
	public static Set<String> getBarItemNames(String categoryName)
	{
		Categories catgs = getCategories();
		try
		{
			Set<String> keySet = catgs.categoryMap.get(categoryName).barItemMap.keySet();
			return keySet;
		}
		catch (NullPointerException ex)
		{
			return null;
		}
	}
	
	public static void addBarItem(String catgName)
	{
		Categories categories = getCategories();
		Category category = categories.categoryMap.get(catgName);
		category.barItemMap.put("newBarItem", new BarItem());

		barItemMap = category.barItemMap;
		WriteBarItemToFile(catgName);
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
	
	public static void changeBarItemWebUrl(String catgName, String name, String webUrl)
	{
		Categories catgs = getCategories();
		catgs.categoryMap.get(catgName).barItemMap.get(name).webUrl = webUrl;
		barItemMap = catgs.categoryMap.get(catgName).barItemMap;
		WriteBarItemToFile(catgName);
	}
	
	public static void changeBarItemIcon(String catgName, String name, String iconPath)
	{
		Categories catgs = getCategories();
		catgs.categoryMap.get(catgName).barItemMap.get(name).iconPath = iconPath;
		barItemMap = catgs.categoryMap.get(catgName).barItemMap;
		WriteBarItemToFile(catgName);
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
	
	public static String[] getBarItemInfo(String catgName, String name)
	{
		Categories catgs = getCategories();
		String[] infoArray = new String[2];

		try
		{
			BarItem barItem = catgs.categoryMap.get(catgName).barItemMap.get(name);
			infoArray[0] = barItem.iconPath;
			infoArray[1] = barItem.webUrl;
			return infoArray;
		}
		catch (NullPointerException ex)
		{

		}
		return null;
	}
}
