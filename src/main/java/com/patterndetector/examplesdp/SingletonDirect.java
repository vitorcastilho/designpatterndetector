package com.patterndetector.examplesdp;

public class SingletonDirect {
	private static SingletonDirect uniqueInstance = new SingletonDirect();

	private SingletonDirect() {
	}

	public static synchronized SingletonDirect getInstance() {
		return uniqueInstance;
	}
}
