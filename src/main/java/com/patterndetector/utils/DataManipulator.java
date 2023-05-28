package com.patterndetector.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataManipulator {

	public static List<String> findJavaFile(String folderPath) {
		List<String> javaFilesPaths = new ArrayList<String>();
		File folder = new File(folderPath);

		File[] files = folder.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					javaFilesPaths.addAll(findJavaFile(file.getAbsolutePath())); // Chamada recursiva para percorrer
																					// subpastas
				} else if (file.isFile() && file.getName().endsWith(".java")) {
					javaFilesPaths.add(file.getAbsolutePath());
				}
			}
		}

		return javaFilesPaths;
	}
}
