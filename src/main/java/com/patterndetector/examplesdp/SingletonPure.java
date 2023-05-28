package com.patterndetector.examplesdp;

public class SingletonPure {
	private static SingletonPure uniqueInstance;

	private SingletonPure() {
	}

	public static synchronized SingletonPure getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new SingletonPure();
		}
		return uniqueInstance;
	}
}
