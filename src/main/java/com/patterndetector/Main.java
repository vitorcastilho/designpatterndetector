package com.patterndetector;

import java.time.LocalDateTime;
import java.util.List;

import com.patterndetector.detector.Singleton;
import com.patterndetector.detector.SingletonNLP;
import com.patterndetector.utils.DataManipulator;

public class Main {

	public static void main(String[] args) {
		String folderPath = "/home/castilho/workspace/college/TCC-2023/ProjetosJavaParaAnalise";
		List<String> javaFileList = DataManipulator.findJavaFile(folderPath);

		LocalDateTime inicio = LocalDateTime.now();
		System.out.println("Início: " + inicio);
		SingletonNLP.analyzeJavaFiles(javaFileList);
		LocalDateTime fim = LocalDateTime.now();
		System.out.println("Término: " + fim);

		inicio = LocalDateTime.now();
		System.out.println("Início: " + inicio);
		Singleton.analyzeJavaFiles(javaFileList);
		fim = LocalDateTime.now();
		System.out.println("Término: " + fim);

	}
}