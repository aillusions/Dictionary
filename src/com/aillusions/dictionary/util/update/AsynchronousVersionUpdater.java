package com.aillusions.dictionary.util.update;

import java.awt.Cursor;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.aillusions.dictionary.view.DownloadUpdateDialog;

public class AsynchronousVersionUpdater implements Runnable {

	
	public static final String COPY_OVERRITE_UPDATE = "cmd.exe /c xcopy .\\" + "update\\"	+ "unzipped\\*.* /a /e /k /Y /n";
	public static final String CMD_RUN_DICT = "cmd.exe /c java -jar Dictionary.jar /n";
	
	private static final Logger l = Logger.getLogger(AsynchronousVersionUpdater.class);
	public JFrame currentContainer;
	
	private UpdateRepository updateRepo = UpdateRepository.getInstance();
	
	private boolean showMessageIfUpToDate;
	
	public AsynchronousVersionUpdater(JFrame currentContainer, boolean showMessageIfUpToDate){
		this.currentContainer = currentContainer;
		this.showMessageIfUpToDate =showMessageIfUpToDate;
	}

	public void run() {

		VersionChecker vc = new VersionChecker();
		boolean continueAscting = vc.isNewerVersionAvailable();

		if(showMessageIfUpToDate && !continueAscting){
			JOptionPane.showMessageDialog(currentContainer, "You have up to date version!");
		}
		
		l.log(Priority.INFO, "Newer version available: " + continueAscting + "; current: " + vc.getCurrentVersion() + "; avail: " + vc.getLastAvailableVersion() );


		if (continueAscting) {

			Object[] options = { "Yes", "No", "Never ask me" };
			int n = JOptionPane.showOptionDialog(currentContainer,
					"Would you like to update Dictionary now?",
					"Newer version is available!",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

			if (n == 0) {

				DownloadUpdateDialog dud = new DownloadUpdateDialog(currentContainer);
				dud.setLocationRelativeTo(currentContainer);
				dud.setVisible(true);				
				currentContainer.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));				
				updateRepo.downloadUpdate(dud, vc.getLastAvailableVersion());				
				currentContainer.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				continueAscting = !dud.isStoped();
				dud.setVisible(false);
				dud.dispose();
				dud = null;
				updateRepo.unzipUpdate(vc.getLastAvailableVersion());
		
			} else {
				continueAscting = false;
			}
		}

		if (continueAscting) {

			Object[] options1 = { "Yes", "No" };
			int n1 = JOptionPane.showOptionDialog(currentContainer,
					"Can we restart Dictionary now?",
					"Latest version will be installed!",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options1, options1[0]);

			if (n1 == 0) {

				Runnable rnb = new Runnable() {

					public void run() {
						try {
							Runtime.getRuntime().exec(COPY_OVERRITE_UPDATE);
							Runtime.getRuntime().exec(CMD_RUN_DICT);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				};

				new Thread(rnb).run();
				System.exit(0);
			}
		}
	}

	public boolean isShowMessageIfUpToDate() {
		return showMessageIfUpToDate;
	}

	public void setShowMessageIfUpToDate(boolean showMessageIfUpToDate) {
		this.showMessageIfUpToDate = showMessageIfUpToDate;
	}
	
	
}
