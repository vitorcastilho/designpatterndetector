package com.patterndetector.detector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Singleton {

	private static String remove = "/home/castilho/workspace/college/TCC-2023/ProjetosJavaParaAnalise/";

	public static void analyzeJavaFiles(List<String> javaFileList) {
		int count = 1;
		for (String filePath : javaFileList) {
			if (isSingleton(filePath)) {

				System.out.println(count + " - Singleton: " + filePath.replaceFirst(remove, ""));
				count++;
			}
		}
	}

	private static boolean isSingleton(String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			boolean hasPrivateConstructor = false;
			boolean hasGetInstanceMethod = false;
			while ((line = reader.readLine()) != null) {
				if (line.contains("private " + getClassName(filePath) + "()")) {
					hasPrivateConstructor = true;
				}
				if (line.contains("public static " + getClassName(filePath) + " getInstance()")) {
					hasGetInstanceMethod = true;
				}
			}
			return hasPrivateConstructor && hasGetInstanceMethod;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static String getClassName(String filePath) {
		String[] parts = filePath.split("/");
		String fileName = parts[parts.length - 1];
		return fileName.substring(0, fileName.lastIndexOf('.'));
	}
}