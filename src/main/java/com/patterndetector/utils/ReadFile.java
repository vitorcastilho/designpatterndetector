package com.patterndetector.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	public static String read(String path) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String line = "";
		String text = "";
		while (true) {
			if (line != null) {
				text = text + line;
			} else
				break;
			line = buffRead.readLine();
		}
		buffRead.close();
		
		return text;
	}
}
