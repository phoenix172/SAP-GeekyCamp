package StudentsDB;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Configuration {
	public static DBSettings LoadDBSettings(String configFileName) throws IOException
	{
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		builder.serializeNulls();
		Gson gson = builder.create();
		File configFile = new File(configFileName);
		if(!configFile.exists())
		{
			DBSettings settings = new DBSettings();
			configFile.createNewFile();
			String content = gson.toJson(settings);
			writeFile(configFile, content);
			System.out.printf("Warning: remember to set configuration in %s\n", configFileName);
			return settings;
		}
		String configContent = readFile(configFile);
		return gson.fromJson(configContent, DBSettings.class);
	}

	private static void writeFile(File configFile, String content) throws IOException {
		Writer writer = new FileWriter(configFile);
		writer.write(content);
		writer.close();
	}

	private static String readFile(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file)
				.useDelimiter("\\Z");
		String configContent = scanner.next();
		scanner.close();
		return configContent;
	}
}
