package com.aillusions.dictionary.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class Unzip {
	
	private static final Logger l = Logger.getLogger(Unzip.class);	

	public void copyInputStream(InputStream in, OutputStream out)
			throws IOException {
		byte[] buffer = new byte[1024];
		int len;

		while ((len = in.read(buffer)) >= 0)
			out.write(buffer, 0, len);

		in.close();
		out.close();
	}
	
	public void unzip(String args, String subDir) {
		Enumeration entries;
		ZipFile zipFile;

		File f = new File(subDir);
		if (f.exists()) {
			IOTools.deleteDirectory(f);
		}
		f.mkdir();

		try {
			zipFile = new ZipFile(args);

			entries = zipFile.entries();

			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();

				if (entry.isDirectory()) {
					// Assume directories are stored parents first then
					// children.
					l.log(Priority.INFO, "Extracting directory: " + subDir + "/" + entry.getName());
					// This is not robust, just for demonstration purposes.
					(new File(subDir + "/" + entry.getName())).mkdir();
					continue;
				}
				l.log(Priority.INFO, "Extracting file: " + subDir + "/" + entry.getName());
				copyInputStream(zipFile.getInputStream(entry),
						new BufferedOutputStream(new FileOutputStream(subDir
								+ "/" + entry.getName())));
			}

			zipFile.close();
		} catch (IOException ioe) {
			l.log(Priority.ERROR, ioe);
		}
	}

}
