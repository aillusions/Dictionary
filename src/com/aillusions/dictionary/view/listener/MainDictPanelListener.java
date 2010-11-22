package com.aillusions.dictionary.view.listener;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;

import com.aillusions.dictionary.core.Manager;
import com.aillusions.dictionary.view.TopEditorFrame;

public class MainDictPanelListener {

	private Manager manager;
	private TopEditorFrame topEditor;

	public MainDictPanelListener() {

	}

	public void literalButtonsMouseReleased(MouseEvent paramMouseEvent) {
		if (this.manager.getCurrentStateManager().getCurrentPair() != null) {
			topEditor.Transcription_TextF.setText(this.manager.getCurrentStateManager().getCurrentPair().getTranscription()
					+ ((JButton) paramMouseEvent.getSource()).getText());
			if (this.manager.getCurrentStateManager().aplyCurrWordChanged(topEditor.Translate_TextF.getText(),
					topEditor.Transcription_TextF.getText()))
				return;
			Alert("Your input was not aplyed!");
		} else {
			Alert("You mast select one before!");
		}
	}

	public void Transcription_TextFKeyReleased(KeyEvent paramKeyEvent) {
		if (manager.getCurrentStateManager().aplyCurrWordChanged(topEditor.Translate_TextF.getText(),
				topEditor.Transcription_TextF.getText()))
			return;
		Alert("Your input was not aplyed!");
	}

	public void Translate_TextFKeyReleased(KeyEvent paramKeyEvent) {
		if (manager.getCurrentStateManager().aplyCurrWordChanged(topEditor.Translate_TextF.getText(),
				topEditor.Transcription_TextF.getText()))
			return;
		Alert("Your input was not aplyed!");
	}

	public void Word_TextFKeyReleased(KeyEvent paramKeyEvent) {
		if (manager.getCurrentStateManager().aplyCurrWordChanged(topEditor.Translate_TextF.getText(),
				topEditor.Transcription_TextF.getText()))
			return;
		Alert("Your input was not aplyed!");
	}

	public void Rename_BtnMouseReleased(MouseEvent paramMouseEvent) {
		if (manager.getCurrentStateManager().getCurrentPair() == null)
			return;
		manager.getCurrentStateManager().renameCurrentPair(null);
		topEditor.refresh(true, true);
	}

	public void Remove_BtnMouseReleased(MouseEvent paramMouseEvent) {
		if (manager.getCurrentStateManager().getCurrentPair() == null)
			Alert("You have to select one before!");
		manager.getCurrentStateManager().removeCurrentPair();
		topEditor.refresh(true, true);
	}

	public void Add_btnMouseReleased(MouseEvent paramMouseEvent) {
		boolean bool = false;
		if (topEditor.jTxtSearch.getText().equals(""))
			bool = manager.getPairsManager().addNewKey(null);
		else
			bool = manager.getPairsManager().addNewKey(topEditor.jTxtSearch.getText());
		if (bool)
			topEditor.jTxtSearch.setText("");
		topEditor.refresh(true, true);
	}

	public void jTxtSearchKeyReleased(KeyEvent paramKeyEvent) {
		String str = topEditor.jTxtSearch.getText().trim();
		if (paramKeyEvent.getKeyCode() == 10) {
			boolean bool = false;
			if (str.equals(""))
				bool = manager.getPairsManager().addNewKey(null);
			else
				bool = manager.getPairsManager().addNewKey(str);
			if (bool)
				topEditor.jTxtSearch.setText("");
			topEditor.refresh(true, true);
		} else {
			if (str.equals(""))
				return;
			for (int i = topEditor.getPrevSelectedIndex() + 1; i < manager.getPairsManager().getAllKeys().length; ++i) {
				if (manager.getPairsManager().getAllKeys()[i].indexOf(str) == 0) {
					topEditor.wordsLst.setSelectedValue(manager.getPairsManager().getAllKeys()[i], true);
					topEditor.setPrevSelectedIndex(i);
					return;
				}
				if (i != manager.getPairsManager().getAllKeys().length - 1)
					continue;
				topEditor.setPrevSelectedIndex(0);
			}
		}
	}

	public void jButtAddSampleMouseReleased(MouseEvent paramMouseEvent) {
		if (topEditor.jTextSamplesSearch.getText().equals(""))
			manager.getPairsManager().addNewSample(null);
		else if (manager.getPairsManager().addNewSample(topEditor.jTextSamplesSearch.getText()))
			topEditor.jTextSamplesSearch.setText("");
		topEditor.refresh(false, true);
	}

	public void jTextSamplesSearchKeyReleased(KeyEvent paramKeyEvent) {
		if (paramKeyEvent.getKeyCode() != 10)
			return;
		if ((!(topEditor.jTextSamplesSearch.getText().equals("")))
				&& (manager.getPairsManager().addNewSample(topEditor.jTextSamplesSearch.getText())))
			topEditor.jTextSamplesSearch.setText("");
		topEditor.refresh(false, true);
	}

	public void jButtRemSampleMouseReleased(MouseEvent paramMouseEvent) {
		manager.getCurrentStateManager().removeCurrSample();
		topEditor.refresh(false, true);
	}

	public void jListSamplesMouseReleased(MouseEvent paramMouseEvent) {
		manager.getCurrentStateManager().setCurrSample((String) topEditor.samplesLst.getSelectedValue());
		topEditor.refresh(false, false);
	}

	public void jButtRecordMouseReleased(MouseEvent paramMouseEvent) {
		if (manager.getAudioManager().recorder == null)
			manager.getAudioManager().startRecording(null);
		else
			manager.getAudioManager().stopRecording();
		topEditor.refresh(false, false);
	}

	public void jButtPlayMouseReleased(MouseEvent paramMouseEvent) {
		manager.getAudioManager().playCurrentAudioRecord();
	}

	public void jButtDeleteRecordMouseReleased(MouseEvent paramMouseEvent) {
		if (!(manager.getAudioManager().isCurrentItemAlreadyRecorded()))
			return;
		manager.getAudioManager().deleteCurrentAudioRecord();
		topEditor.refresh(false, false);
	}

	public void Alert(String paramString) {
		JOptionPane.showMessageDialog(new JFrame("FrameDemo"), paramString);
	}

	public void InUseJListMouseReleased(ListSelectionEvent paramListSelectionEvent) {
		if (topEditor.InUseJList.getSelectedValue() != null) {
			String selectedValue = topEditor.InUseJList.getSelectedValue().toString();
			manager.getCurrentStateManager().setCurrentTrashPairByKey(selectedValue);
		} else {
			manager.getCurrentStateManager().setCurrentTrashPair(null);
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
