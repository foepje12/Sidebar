package main;

import java.io.IOException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class Main
{

	public static void main(String[] args)
	{
		try
		{
			// Saving.Test();
			Saving.TestOut();
		}
		catch (JsonSyntaxException | JsonIOException | IOException e)
		{
			e.printStackTrace();
		}

		// new Sidebar();
	}

}
