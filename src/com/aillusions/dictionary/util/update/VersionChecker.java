package com.aillusions.dictionary.util.update;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

import com.aillusions.dictionary.util.Config;

public class VersionChecker {

	private String lastAvailableVersion;
	
	private static final Logger l = Logger.getLogger(VersionChecker.class);
	
	public static void checkVersionInSeparateThread(JFrame container, boolean showMessageIfUpToDate){
		AsynchronousVersionUpdater r = new AsynchronousVersionUpdater(container, showMessageIfUpToDate);
		new Thread(r).start();
		
	}

	public boolean isNewerVersionAvailable(){		
		return getLastAvailableVersion() != null && !getCurrentVersion().equals(getLastAvailableVersion());
	}
	
	public String getLastAvailableVersion(){
		
		if(lastAvailableVersion == null){
			lastAvailableVersion = UpdateRepository.getInstance().getLastAvailableVersion();
		}
		
		return lastAvailableVersion;
	}
	
	
	public String getCurrentVersion(){		
		return Config.getConfig().getCurrentAppVersion();
	}
}
