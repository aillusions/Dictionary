package com.aillusions.dictionary.util;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Frame;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.aillusions.dictionary.view.DownloadUpdateDialog;

public class AsynchronousVersionUpdater implements Runnable {

	private static final Logger l = Logger
			.getLogger(AsynchronousVersionUpdater.class);
	public Frame currentContainer;

	public Frame getCurrentContainer() {
		return currentContainer;
	}

	public void setCurrentContainer(Frame currentContainer) {
		this.currentContainer = currentContainer;
	}

	public void run() {

		VersionChecker vc = new VersionChecker();
		boolean continueAscting = vc.isNewerVersionAvailable();

		l.log(Priority.INFO, "Newer version available: " + continueAscting);

		if (continueAscting) {

			Object[] options = { "Yes", "No", "Never ask me" };
			int n = JOptionPane.showOptionDialog(null,
					"Would you like to update Dictionary now?",
					"Newer version is available!",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

			if (n == 0) {

				URLConnection ucn = null;
				String zipName = "Dictionary" + vc.getLastAvailableVersion()
						+ ".zip";

				try {
					ucn = new URL(
							"http://github.com/downloads/aillusions/Dictionary/"
									+ zipName).openConnection();
				} catch (MalformedURLException e) {
					l.log(Priority.ERROR, e);
				} catch (IOException e) {
					l.log(Priority.ERROR, e);
				}
				if (ucn == null) {
					JOptionPane.showMessageDialog(null,
							"Update temporarily unavailable. Try latter.",
							"Sorry!", JOptionPane.INFORMATION_MESSAGE);
					l.log(Priority.WARN, zipName + "ucn == null");
					return;
				}

				try {
					InputStream isr = ucn.getInputStream();

					boolean success = (new File("./update")).mkdir();
					if (success) {
						System.out.println("Directory: created");
					} else {
						System.out.println("fuck");
					}

					FileOutputStream fos = new FileOutputStream("update/"
							+ zipName, false);

					currentContainer.setCursor(Cursor
							.getPredefinedCursor(Cursor.WAIT_CURSOR));

					DownloadUpdateDialog dud = new DownloadUpdateDialog();
					dud.setVisible(true);

					byte[] b = new byte[10000];
					int read = 0;
					while ((read = isr.read(b)) != 0) {
						fos.write(b);
					}
					isr.close();
					fos.close();
					fos = null;

				} catch (IOException e) {
					l.log(Priority.ERROR, e);
				}
			}
		}

		Object[] options1 = { "Yes", "No" };
		int n1 = JOptionPane.showOptionDialog(null, "Restart Dictionary now?",
				"Latest version was installed!",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options1, options1[0]);

		JOptionPane.showMessageDialog(null,
				"You have latest version of Dictionary", "Congratulations!",
				JOptionPane.INFORMATION_MESSAGE);

	}
}
