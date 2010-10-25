package com.aillusions.dictionary.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class Config {
	
	private static final Logger l = Logger.getLogger(Config.class);
	private static Config instance = new Config();
	
	Properties props;
	
	public static Config getConfig(){
		return instance;
	}
	
	private Config(){		

		props = new Properties();
		
		try {
			InputStream is = new FileInputStream(new File("config.properties"));
			if (is != null) {
				props.load(is);
				is.close();
			} else {
		    	l.log(Priority.ERROR, "config.properties was not found.");
			}

		} catch (IOException e) {
	    	l.log(Priority.ERROR, e);
		}	
	}
	
	private String getPropertyValue(String name){
		return props.getProperty(name);
	}
	
	public String getCurrentAppVersion(){
		return getPropertyValue("version");
	}
	
	public String getUpdateRepoURL(){
		return getPropertyValue("updateRepoURL");
	}
}
