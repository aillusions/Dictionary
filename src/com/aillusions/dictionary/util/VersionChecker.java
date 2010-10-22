package com.aillusions.dictionary.util;

import java.awt.Component;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class VersionChecker {

	private String lastAvailableVersion;
	private String currentVersion;
	
	
	private static final Logger l = Logger.getLogger(VersionChecker.class);
	
	public static void checkVersionInSeparateThread(Frame container){

		AsynchronousVersionUpdater r = new AsynchronousVersionUpdater();
		r.setCurrentContainer(container);
		
		new Thread(r).start();
		
	}

	public boolean isNewerVersionAvailable(){
		
		return getLastAvailableVersion() != null && !getCurrentVersion().equals(getLastAvailableVersion());
	}
	
	public String getLastAvailableVersion(){
		
		if(lastAvailableVersion != null){
			return lastAvailableVersion;
		}
		
		String lastAvailableVersion = null;
		
		try {			
			URLConnection ucn = new URL("http://github.com/downloads/aillusions/Dictionary/last_version.inf").openConnection();
			BufferedReader bis = new BufferedReader(new InputStreamReader(ucn.getInputStream()));
			lastAvailableVersion = bis.readLine();
			
		} catch (MalformedURLException e) {
			l.log(Priority.ERROR, e);
		} catch (IOException e) {
			l.log(Priority.ERROR, e);
		}
		
		return lastAvailableVersion;
	}
	
	public String getCurrentVersion(){
		
		if(currentVersion != null){
			return currentVersion;
		}
		
		Properties props = new Properties();
		try {
			InputStream is = new FileInputStream(new File("config.properties"));
			if (is != null) {
				props.load(is);
			} else {
		    	l.log(Priority.ERROR, "config.properties was not found.");
			}

		} catch (IOException e) {
	    	l.log(Priority.ERROR, e);
		}
		
		return props.getProperty("version");
	}
}
