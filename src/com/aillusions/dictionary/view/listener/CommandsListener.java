package com.aillusions.dictionary.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;

import com.aillusions.dictionary.core.Manager;
import com.aillusions.dictionary.core.WorkspaceManager.DictionaryHasToBeCreated;
import com.aillusions.dictionary.model.Pair;
import com.aillusions.dictionary.util.IOTools;
import com.aillusions.dictionary.util.update.VersionChecker;
import com.aillusions.dictionary.view.About;
import com.aillusions.dictionary.view.Statistics;
import com.aillusions.dictionary.view.TopEditorFrame;
import com.aillusions.dictionary.view.components.TopMenuBar;
import com.aillusions.dictionary.xsl.DocConverter.DocViewMode;

public class CommandsListener implements ActionListener {

	private Manager manager;
	private TopEditorFrame topEditor;

	public static final String OPEN_ALL_WORDS_IN_WORD = "1";
	public static final String OPEN_FULL_WORDS_DETAILS_IN_WORD = "2";
	public static final String OPEN_SAMPLES_IN_WORD = "3";
	public static final String SET_ALWAYS_ON_TOP = "4";
	public static final String RUN_TRAINER = "5";
	public static final String PLAY_ON_SELECTION = "6";
	public static final String MIX_WORDS = "7";
	public static final String SAVE = "8";
	public static final String LOAD = "9";
	public static final String ABOUT = "10";
	public static final String EXPAND = "11";
	public static final String MOVE_SAMPLE_UP = "12";
	public static final String EDIT_CURRENT = "13";
	public static final String REMOVE_CURRENT_WORD = "14";
	public static final String ADD_NEW_DICT = "15";
	public static final String SELECT_DICT = "16";
	public static final String SAVE_EXIT = "17";
	public static final String RESTORE_SELECTED_REMOVED = "18";
	public static final String VIEW_STATISTIC = "19";
	public static final String CHECK_FOR_YPDATE = "20";

	public CommandsListener() {

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(OPEN_ALL_WORDS_IN_WORD)) {
			manager.runWord(DocViewMode.ALL_WORDS_XSL);
		} else if (e.getActionCommand().equals(OPEN_FULL_WORDS_DETAILS_IN_WORD)) {
			manager.runWord(DocViewMode.WORD_TRANSL_SAMPLE);
		} else if (e.getActionCommand().equals(OPEN_SAMPLES_IN_WORD)) {
			manager.runWord(DocViewMode.ALL_SAMPLES_XSL);
		} else if (e.getActionCommand().equals(SET_ALWAYS_ON_TOP)) {
			topEditor.setAlwaysOnTop(((JCheckBoxMenuItem) e.getSource()).isSelected());
		} else if (e.getActionCommand().equals(RUN_TRAINER)) {
			manager.runTrainer();
		} else if (e.getActionCommand().equals(PLAY_ON_SELECTION)) {
			manager.getAudioManager().setPlayOnSelections(((JCheckBoxMenuItem) e.getSource()).isSelected());
		} else if (e.getActionCommand().equals(MIX_WORDS)) {
			manager.getPairsManager().shuffle();
			topEditor.refresh(true, true);
		} else if (e.getActionCommand().equals(SAVE)) {
			manager.getWorkspaceManager().saveInFile();
		} else if (e.getActionCommand().equals(LOAD)) {
			try {
				manager.getWorkspaceManager().load();
			} catch (DictionaryHasToBeCreated e1) {
				while (!manager.getWorkspaceManager().addNewDictionary()) {
					// Have to specify at least one dictionary!
				}
			}
			topEditor.refresh(true, true);
		} else if (e.getActionCommand().equals(ABOUT)) {
			About localAbout = new About();
			localAbout.setLocationRelativeTo(topEditor);
			localAbout.setModal(true);
			localAbout.setAlwaysOnTop(topEditor.isAlwaysOnTop());
			localAbout.setVisible(true);
		} else if (e.getActionCommand().equals(EXPAND)) {
			JMenuItem munuItem = (JMenuItem) e.getSource();
			if (munuItem.getText().equals(TopMenuBar.COLLAPSE_MENU_ITEM_NAME)) {
				munuItem.setText(TopMenuBar.EXPAND_MENU_ITEM_NAME);
				topEditor.setSize(TopEditorFrame.WINDOW_WIDTH_SHORT, TopEditorFrame.WINDOW_HEIGHT);
			} else {
				munuItem.setText(TopMenuBar.COLLAPSE_MENU_ITEM_NAME);
				topEditor.setSize(TopEditorFrame.WINDOW_WIDTH_EXPANDED, TopEditorFrame.WINDOW_HEIGHT);
			}
		} else if (e.getActionCommand().equals(MOVE_SAMPLE_UP)) {
			manager.getCurrentStateManager().upCurrentSample();
			topEditor.refresh(false, true);
		} else if (e.getActionCommand().equals(EDIT_CURRENT)) {
			if (manager.getCurrentStateManager().getCurrentPair() == null)
				return;
			manager.getCurrentStateManager().renameCurrentPair(null);
			topEditor.refresh(true, true);
		} else if (e.getActionCommand().equals(REMOVE_CURRENT_WORD)) {
			if (manager.getCurrentStateManager().getCurrentPair() == null)
				topEditor.Alert("You have to select one before!");
			manager.getCurrentStateManager().removeCurrentPair();
			topEditor.refresh(true, true);
		} else if (e.getActionCommand().equals(ADD_NEW_DICT)) {
			manager.getWorkspaceManager().addNewDictionary();
			topEditor.refresh(true, true);
			topEditor.regenDictList();
		} else if (e.getActionCommand().equals(SELECT_DICT)) {
			manager.getWorkspaceManager().selectDictionary(((JMenuItem) e.getSource()).getText());
			topEditor.refresh(true, true);
		} else if (e.getActionCommand().equals(SAVE_EXIT)) {
			manager.getWorkspaceManager().saveInFile();
			System.exit(1);
		} else if (e.getActionCommand().equals(RESTORE_SELECTED_REMOVED)) {
			Pair currPair = manager.getCurrentStateManager().getCurrentTrashPair();
			if (currPair != null) {
				manager.getTrashManager().restorePair(currPair);
				topEditor.refresh(true, true);
			}
		} else if (e.getActionCommand().equals(VIEW_STATISTIC)) {
			Statistics localAbout = new Statistics(manager);
			localAbout.setLocationRelativeTo(topEditor);
			localAbout.setModal(true);
			localAbout.setAlwaysOnTop(topEditor.isAlwaysOnTop());
			localAbout.setVisible(true);
		} else if (e.getActionCommand().equals(CHECK_FOR_YPDATE)) {
			File updateDir = new File("update");
			if (updateDir.exists()) {
				IOTools.deleteDirectory(updateDir);
			}
			VersionChecker.checkVersionInSeparateThread(topEditor, true);
		}
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public TopEditorFrame getTopEditor() {
		return topEditor;
	}

	public void setTopEditor(TopEditorFrame topEditor) {
		this.topEditor = topEditor;
	}

}
