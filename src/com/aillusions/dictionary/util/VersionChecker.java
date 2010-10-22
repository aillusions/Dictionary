package com.aillusions.dictionary.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class VersionChecker {

	public static final String LAST_VERSION_FILE_URL = "http://github.com/aillusions/Dictionary/downloads";
	private String lastAvailableVersion;
	private String currentVersion;
	
	
	private static final Logger l = Logger.getLogger(VersionChecker.class);
	
	public static void checkVersionInSeparateThread(JFrame container){

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
			HttpURLConnection ucn = (HttpURLConnection)new URL(LAST_VERSION_FILE_URL + "?param=" + System.currentTimeMillis()).openConnection();
			
			ucn.setDefaultUseCaches(false);
			ucn.setUseCaches(false);
			ucn.setRequestProperty("Cache-Control", "no-store, no-cache, must-revalidate, post-check=0, pre-check=0");
			ucn.setRequestProperty("Expires", "0");
			ucn.setRequestProperty("Pragma", "no-cache");
			ucn.setRequestProperty("Content-Type", "text/html");

			//URLConnection ucn = new URL("file:///c:/last_version.inf").openConnection();
			
			String content = readFileAsString(ucn.getInputStream());
			int indexOfLastDict = content.indexOf(".zip\">Dictionary");
			int indexOfLastFileExt = content.indexOf(".zip", indexOfLastDict + 4);
			lastAvailableVersion = content.substring(indexOfLastDict + 16, indexOfLastFileExt);
			
		} catch (MalformedURLException e) {
			l.log(Priority.ERROR, e);
		} catch (IOException e) {
			l.log(Priority.ERROR, e);
		}
		
		return lastAvailableVersion;
	}
	
    private static String readFileAsString(InputStream is)
    throws java.io.IOException{
        StringBuffer fileData = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        return fileData.toString();
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
