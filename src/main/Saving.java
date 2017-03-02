package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class Saving
{
	private static final Map<String, Category> categoryMap;
	private static final Map<String, BarItem> barItemMap;

	static
	{
		categoryMap = new HashMap<String, Category>();
		barItemMap = new HashMap<String, BarItem>();
	}

	public static void Test() throws JsonSyntaxException, JsonIOException, FileNotFoundException
	{
		Gson gson = new Gson();
		Categories[] myTypes = gson.fromJson(new FileReader("files/test.json"), Categories[].class);
		System.out.println(gson.toJson(myTypes));
	}

	public static void TestOut() throws IOException
	{
		File file = new File("files/test.json");
		Gson gson = new Gson();

		Categories[] myTypes = new Categories[1];
		myTypes[0] = new Categories();
		
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
		
		myTypes[0].categoryMap = categoryMap;

		FileWriter writer = new FileWriter(file);
		writer.write(gson.toJson(myTypes));
		writer.close();
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
