package com.aillusions.dictionary.util;

import java.awt.Cursor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.aillusions.dictionary.view.DownloadUpdateDialog;

public class UpdateRepository {
	
	public static final String CONTENT_LENGTH = "Content-Length";
	
	private static UpdateRepository instanse = new UpdateRepository();
	
	private UpdateRepository(){
		
	}
	
	public static UpdateRepository getInstance(){
		return instanse;
	}

	private static final Logger l = Logger.getLogger(UpdateRepository.class);
	
	public String getLastAvailableVersion(){
		
		String lastAvailableVersion = null;
		
		try {			
			HttpURLConnection ucn = (HttpURLConnection)new URL(Config.getConfig().getUpdateRepoURL() + "?param=" + System.currentTimeMillis()).openConnection();
			
			ucn.setDefaultUseCaches(false);
			ucn.setUseCaches(false);
			ucn.setRequestProperty("Cache-Control", "no-store, no-cache, must-revalidate, post-check=0, pre-check=0");
			ucn.setRequestProperty("Expires", "0");
			ucn.setRequestProperty("Pragma", "no-cache");
			ucn.setRequestProperty("Content-Type", "text/html");

			//URLConnection ucn = new URL("file:///c:/last_version.inf").openConnection();
			
			String content = IOTools.readStreamAsString(ucn.getInputStream());
			int indexOfLastDict = content.indexOf(".zip\">Dictionary-");
			int indexOfLastFileExt = content.indexOf(".zip", indexOfLastDict + 4);
			lastAvailableVersion = content.substring(indexOfLastDict + 17, indexOfLastFileExt);
			
		} catch (MalformedURLException e) {
			l.log(Priority.ERROR, e);
		} catch (IOException e) {
			l.log(Priority.ERROR, e);
		}
		
		return lastAvailableVersion;
	}
	
	
	public String getUpdateZipFileName(String lastAvailableVersion){
		return "Dictionary-" + lastAvailableVersion + ".zip";
	}
	
	public int getUpdateZipFileSize(URLConnection ucn){
		
		List value = ucn.getHeaderFields().get(CONTENT_LENGTH);

		int size = -1;
		if (value != null && !value.isEmpty()) {

			String sLength = (String) value.get(0);

			if (sLength != null) {
				size = Integer.parseInt(sLength);
			}
		}
		
		return size;
	}
	
	public URLConnection getUpdateULRConnection(String lastAvailableVersion){
		URLConnection ucn = null;

		try {
			ucn = new URL(Config.getConfig().getUpdateRepoURL() + "/" +getUpdateZipFileName(lastAvailableVersion)).openConnection();
		} catch (MalformedURLException e) {
			l.log(Priority.ERROR, e);
		} catch (IOException e) {
			l.log(Priority.ERROR, e);
		}
		if (ucn == null) {
			JOptionPane.showMessageDialog(null,
					"Update temporarily unavailable. Try latter.",
					"Sorry!", JOptionPane.INFORMATION_MESSAGE);
			l.log(Priority.WARN, getUpdateZipFileName(lastAvailableVersion) + "ucn == null");
			return null;
		}


		return ucn;

	}
	
	public void downloadUpdate(ProcProgressTracker dud, String lastAvailableVersion){
		
		URLConnection ucn = getUpdateULRConnection(lastAvailableVersion);
		
		try {
			
			prepareUpdate(lastAvailableVersion);		

			l.log(Priority.INFO, "Size of update: " + getUpdateZipFileSize(ucn));

			byte[] b = new byte[10000];
			int read = 0;
			int overalRead = 0;

			InputStream isr = ucn.getInputStream();
			FileOutputStream fos = new FileOutputStream("update/"	+ getUpdateZipFileName(lastAvailableVersion), false);
			
			while ((read = isr.read(b)) > 0 && !dud.isStoped()) {
				fos.write(b, 0, read);
				overalRead += read;
				if (getUpdateZipFileSize(ucn) > 0) {
					dud.setValue((overalRead * 100 / getUpdateZipFileSize(ucn)));
				} else {
					dud.setValue(50);
				}
			}

			isr.close();
			fos.close();
			fos = null;
			
		} catch (IOException e) {
			l.log(Priority.ERROR, e);
		}
	}
	
	private void prepareUpdate( String lastAvailableVersion){
		File updateDir = new File("./update");
		if (updateDir.exists()) {
			Unzip.deleteDirectory(updateDir);
		}

		updateDir.mkdir();

		File targetFile = new File("update/" + getUpdateZipFileName(lastAvailableVersion));
		if (targetFile.exists()) {
			targetFile.delete();
		}
	}
	
	public void unzipUpdate( String lastAvailableVersion){
		Unzip unz = new Unzip();
		unz.unzip("update/" + getUpdateZipFileName(lastAvailableVersion), "update/unzipped");
	}
	
	
}
