package main;

public class Constants {

	//ArcConstants
	public static int arcPieceAmount = 8;
	public static int arcPrecision = 10;
	public static int arcPointAmount = 2 * arcPrecision;
	public static int arcRadius = 150;
	public static int arcPieceWidth = 40;
	
	//SettingConstants
	public static int settingsWidth = 600;
	public static int settingsHeight = 400;
	public static int settingsMenuWidth = settingsWidth;
	public static int settingsMenuHeight = 50;
	public static int settingsMainWidth = settingsWidth - 100;
	public static int settingsMainHeight = 400 - settingsMenuHeight;
	public static int leftScrollPaneWidth = settingsWidth - settingsMainWidth;
	public static int LeftScrollPaneHeight = settingsHeight -settingsMenuHeight;
	
	public static int maxBarAmounts = 5;
	
	public static String filesPath = "files";
	public static String categoriesFile = "categories.json";
	public static String profileFile = "profiles.json";
	public static String configFile = "config.properties";
	public static String buttonIconsPath = "assets/icons/button";
	
	public static String baseFilePath = System.getProperty("user.home");
	
	
}
