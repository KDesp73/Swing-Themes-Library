/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.themeLib;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author konstantinos
 */
public class Utils {

	static Color hexToColor(String hex) {
		hex = "#" + hex;
		return Color.decode(hex);
	}

	static String ColorToHex(Color c) {
		return String.format("%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
	}

	static String getJsonValue(String JsonString, String tag) {
		tag = "\"" + tag + "\"";
		int tagIndex = JsonString.indexOf(tag);

		if (tagIndex == -1) {
			return null;
		}

		int begin = tagIndex + tag.length() + 2; // "+2" For space and comma
		int end = JsonString.indexOf(',', begin);

		if (end == -1) {
			end = JsonString.indexOf('}', begin);
		}

		String value = JsonString.substring(begin, end);
		return value.replaceAll("\\]", "").replaceAll("\\[", "").replaceAll("\\}", "")
				.replaceAll("\n", "").strip();
	}

	public static String pair(String key, String value) {
		return "" + key + ": " + value + "\n";
	}

	public static String readFile(String path) {
		String content = "";
		try {
			// Create a FileReader to read the file
			FileReader fileReader = new FileReader(path);

			// Create a BufferedReader to efficiently read the file line by line
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				content += line + "\n";
			}

			// Close the BufferedReader and FileReader when done
			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return content;
	}
}
