package com.patterndetector.detector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.patterndetector.utils.Pipeline;
import com.patterndetector.utils.ReadFile;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class SingletonNLP {

	private static String remove = "/home/castilho/workspace/college/TCC-2023/ProjetosJavaParaAnalise/";

	public static void analyzeJavaFiles(List<String> javaFileList) {
		int count = 1;
		StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
		for (String filePath : javaFileList) {
			try {
				String text = ReadFile.read(filePath);
				CoreDocument coreDocument = new CoreDocument(text);
				stanfordCoreNLP.annotate(coreDocument);
				List<CoreLabel> coreLabelList = coreDocument.tokens();
				List<String> words = new ArrayList<String>();

				for (CoreLabel coreLabel : coreLabelList) {
					words.add(coreLabel.originalText());
				}

				String nameOfClass = findClassName(words);

				if (!nameOfClass.isEmpty()) {
					if (!findConstructor(words, nameOfClass).isEmpty()) {
						if (!findAttributeStatic(words, nameOfClass).isEmpty()) {
							if (!findGetInstance(words, nameOfClass).isEmpty()) {
								if (!findNewInstanceOfTheClass(words, nameOfClass).isEmpty()) {
									System.out.println(count + " - Singleton: " + filePath.replaceFirst(remove, ""));
									count++;
								}
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String findClassName(List<String> words) {
		int i = 0;
		String nameOfClass = "";
		for (String word : words) {
			if (word.contains("class")) {
				nameOfClass = words.get(i + 1);
				break;
			}
			i++;
		}
		return nameOfClass;
	}

	public static String findConstructor(List<String> words, String nameOfClass) {
		String typeOfConstructor = "";
		for (int i = 0; i < words.size(); i++) {
			if (i > 0 && i < words.size() - 1) { // Verifica se o índice i é válido
				String word = words.get(i);
				String previousWord = words.get(i - 1);
				String nextWord = words.get(i + 1);

				if (word.contains(nameOfClass) && !previousWord.contains("class") && nextWord.contains("(")
						&& previousWord.contains("private")) {
					typeOfConstructor = previousWord + " " + word + nextWord + words.get(i + 2);
					break;
				}
			}
		}
		return typeOfConstructor;
	}

	public static String findAttributeStatic(List<String> words, String nameOfClass) {
		int i = 0;
		String attributeStatic = "";
		for (String word : words) {
			if (word.contains(nameOfClass) && !words.get(i - 1).contains("class") && words.get(i - 1).contains("static")
					&& words.get(i - 2).contains("private")) {
				attributeStatic = words.get(i - 2) + " " + words.get(i - 1) + " " + words.get(i) + " "
						+ words.get(i + 1);
				break;
			}
			i++;
		}
		return attributeStatic;
	}

	public static String findGetInstance(List<String> words, String nameOfClass) {
		int i = 0;
		String verifyInstance = "";
		for (String word : words) {
			if (word.contains(nameOfClass) && words.get(i + 1).contains("get") && words.get(i + 2).contains("(")) {
				verifyInstance = words.get(i) + " " + words.get(i + 1) + words.get(i + 2) + words.get(i + 3);
				int j = 0;
				if (j != 0) {
					verifyInstance = "Existe mais de um método principal na classe analisada";
				}
				j++;
			}
			i++;
		}
		return verifyInstance;
	}

	public static String findNewInstanceOfTheClass(List<String> words, String nameOfClass) {
		int i = 0;
		String newStance = "";
		for (String word : words) {
			if (word.contains("new") && words.get(i + 1).contains(nameOfClass)) {
				newStance = words.get(i) + " " + words.get(i + 1) + words.get(i + 2) + words.get(i + 3)
						+ words.get(i + 4);
			}
			i++;
		}
		return newStance;
	}
}
