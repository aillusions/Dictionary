package com.aillusions.dictionary;

import java.io.File;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.aillusions.dictionary.util.IOTools;
import com.aillusions.dictionary.util.update.VersionChecker;
import com.aillusions.dictionary.view.TopEditor;

public class Main {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				TopEditor localTopEditor = new TopEditor();
				localTopEditor.setLocationRelativeTo(null);
				localTopEditor.setVisible(true);
				localTopEditor.setSize(TopEditor.WINDOW_WIDTH_SHORT,
						TopEditor.WINDOW_HEIGHT);

				Logger l = Logger.getLogger(Main.class);
				l.log(Priority.INFO, "Started.");

				File updateDir = new File("update");
				if (updateDir.exists()) {
					IOTools.deleteDirectory(updateDir);
				}

				VersionChecker.checkVersionInSeparateThread(localTopEditor);

			}
		});
	}
}
