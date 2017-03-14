package handlers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.Constants;

public class ProfileHandler extends JSonInfoHandler
{

	private static Map<String, Profile> profileMap;

	public static Set<String> getProfileNames()
	{
		Profiles profs = getProfiles();
		Set<String> keySet = profs.profileMap.keySet();
		return keySet;
	}

	public static void addProfile()
	{
		Profiles profs = getProfiles();
		Profile profile = new Profile();
		profile.categories = new HashMap<String, Categories>();
		profs.profileMap.put("newProfile", profile);
		profileMap = profs.profileMap;
		WriteProfileToFile();
	}

	public static void WriteProfileToFile()
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		Profiles profs = getProfiles();

		if (!profileMap.isEmpty())
		{
			profs.profileMap = profileMap;
		}
		if (!(new File(Constants.filesPath).exists()))
		{
			new File(Constants.filesPath).mkdir();
		}
		WriteToFile(Constants.filesPath + "/" + Constants.categoriesFile, gson.toJson(profs));
	}

}