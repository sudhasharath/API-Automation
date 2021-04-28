package com.api.framework.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private final String configFilePath = "./configs//Configuration.properties";
	private Properties properties;

	public ConfigReader() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(configFilePath));
			properties = new Properties();
			try {
				properties.load(br);
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public String getExtentConfigPath() {	return properties.getProperty("extentConfig").toString(); }
	public String getHeaderAcceptForRepoSearch() {	return properties.getProperty("headerAcceptRepoSearch").toString(); }
	public String getHeaderAcceptForCommitSearch() {	return properties.getProperty("headerAcceptCommitSearch").toString(); }
	
	
}
