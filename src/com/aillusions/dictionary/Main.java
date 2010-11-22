package com.aillusions.dictionary;

import java.io.File;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.aillusions.dictionary.core.AudioManager;
import com.aillusions.dictionary.core.CurrentStateManager;
import com.aillusions.dictionary.core.Manager;
import com.aillusions.dictionary.core.PairsManager;
import com.aillusions.dictionary.core.TrashManager;
import com.aillusions.dictionary.core.WorkspaceManager;
import com.aillusions.dictionary.core.XmlFileManager;
import com.aillusions.dictionary.core.WorkspaceManager.DictionaryHasToBeCreated;
import com.aillusions.dictionary.util.IOTools;
import com.aillusions.dictionary.util.update.VersionChecker;
import com.aillusions.dictionary.view.TopEditorFrame;
import com.aillusions.dictionary.view.listener.CommandsListener;
import com.aillusions.dictionary.view.listener.MainDictPanelListener;

public class Main {

	protected final static Logger l = Logger.getLogger(Main.class);

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				MainDictPanelListener mainDictPanelListener = new MainDictPanelListener();
				CommandsListener cmdListener = new CommandsListener();
				Manager manager = new Manager();

				XmlFileManager xmlFileManager = new XmlFileManager("words.xml");

				WorkspaceManager workspaceManager = new WorkspaceManager();
				workspaceManager.setManager(manager);
				workspaceManager.setXmlFileManager(xmlFileManager);

				manager.setWorkspaceManager(workspaceManager);
				manager.setAudioManager(new AudioManager(manager));
				manager.setPairsManager(new PairsManager(manager));
				manager.setCurrentStateManager(new CurrentStateManager(manager));
				manager.setTrashManager(new TrashManager(manager));

				try {
					manager.getWorkspaceManager().load();
				} catch (DictionaryHasToBeCreated e) {
					while (!manager.getWorkspaceManager().addNewDictionary()) {
						// Have to specify at least one dictionary!
					}
				}

				TopEditorFrame localTopEditor = new TopEditorFrame(mainDictPanelListener, manager, cmdListener);

				mainDictPanelListener.setManager(manager);
				mainDictPanelListener.setTopEditor(localTopEditor);
				cmdListener.setManager(manager);
				cmdListener.setTopEditor(localTopEditor);

				localTopEditor.refresh(true, true);

				l.log(Priority.INFO, "Started.");

				File updateDir = new File("update");
				if (updateDir.exists()) {
					IOTools.deleteDirectory(updateDir);
				}

				VersionChecker.checkVersionInSeparateThread(localTopEditor, false);

			}
		});
	}
}
