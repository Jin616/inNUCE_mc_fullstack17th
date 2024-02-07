package com.mc.innuce.global.config;

public class Config {

	public final static String CURRENT_OS; 
	
	static {
		String osName = System.getProperty("os.name").toLowerCase();
		
		if (osName.contains("win")) {
			CURRENT_OS = "windows";
		} else if (osName.contains("nix") || osName.contains("nux") || osName.contains("mac")) {
			CURRENT_OS = "linux";
		} else {
			CURRENT_OS = "unknown";
		}
	}
	
}
