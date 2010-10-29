package com.aillusions.dictionary.view.listener;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;

import com.aillusions.dictionary.core.Manager;
import com.aillusions.dictionary.view.TopEditor;

public class MainDictPanelListener {
	
	private Manager dictionary;
	private TopEditor topEditor;
	
	public MainDictPanelListener(Manager dictionary, TopEditor topEditor){
		this.dictionary = dictionary;
		this.topEditor = topEditor;
	}
	
	public void literalButtonsMouseReleased(MouseEvent paramMouseEvent) {
		if (this.dictionary.getCurrentStateManager().getCurrentPair() != null) {
			topEditor.Transcription_TextF.setText(this.dictionary.getCurrentStateManager().getCurrentPair()
					.getTranscription()
					+ ((JButton) paramMouseEvent.getSource()).getText());
			if (this.dictionary.getCurrentStateManager().aplyCurrWordChanged(topEditor.Translate_TextF
					.getText(), topEditor.Transcription_TextF.getText()))
				return;
			Alert("Your input was not aplyed!");
		} else {
			Alert("You mast select one before!");
		}
	}
	
	public void Transcription_TextFKeyReleased(KeyEvent paramKeyEvent) {
		if (dictionary.getCurrentStateManager().aplyCurrWordChanged(topEditor.Translate_TextF.getText(),
				topEditor.Transcription_TextF.getText()))
			return;
		Alert("Your input was not aplyed!");
	}

	public void Translate_TextFKeyReleased(KeyEvent paramKeyEvent) {
		if (dictionary.getCurrentStateManager().aplyCurrWordChanged(topEditor.Translate_TextF.getText(),
				topEditor.Transcription_TextF.getText()))
			return;
		Alert("Your input was not aplyed!");
	}

	public void Word_TextFKeyReleased(KeyEvent paramKeyEvent) {
		if (dictionary.getCurrentStateManager().aplyCurrWordChanged(topEditor.Translate_TextF.getText(),
				topEditor.Transcription_TextF.getText()))
			return;
		Alert("Your input was not aplyed!");
	}




	public void Rename_BtnMouseReleased(MouseEvent paramMouseEvent) {
		if (dictionary.getCurrentStateManager().getCurrentPair() == null)
			return;
		dictionary.getCurrentStateManager().renameCurrentPair(null);
		topEditor.refresh(true, true);
	}

	public void Remove_BtnMouseReleased(MouseEvent paramMouseEvent) {
		if (dictionary.getCurrentStateManager().getCurrentPair() == null)
			Alert("You have to select one before!");
		dictionary.getCurrentStateManager().removeCurrentPair();
		topEditor.refresh(true, true);
	}

	public void Add_btnMouseReleased(MouseEvent paramMouseEvent) {
		boolean bool = false;
		if (topEditor.jTxtSearch.getText().equals(""))
			bool = dictionary.getPairsManager().addNewKey(null);
		else
			bool = dictionary.getPairsManager().addNewKey(topEditor.jTxtSearch.getText());
		if (bool)
			topEditor.jTxtSearch.setText("");
		topEditor.refresh(true, true);
	}

	public void jTxtSearchKeyReleased(KeyEvent paramKeyEvent) {
		String str = topEditor.jTxtSearch.getText().trim();
		if (paramKeyEvent.getKeyCode() == 10) {
			boolean bool = false;
			if (str.equals(""))
				bool = dictionary.getPairsManager().addNewKey(null);
			else
				bool = dictionary.getPairsManager().addNewKey(str);
			if (bool)
				topEditor.jTxtSearch.setText("");
			topEditor.refresh(true, true);
		} else {
			if (str.equals(""))
				return;
			for (int i = topEditor.prevSelectedIndex + 1; i < dictionary.getPairsManager()
					.getAllKeys().length; ++i) {
				if (dictionary.getPairsManager().getAllKeys()[i].indexOf(str) == 0) {
					topEditor.WordsList_Lst.setSelectedValue(dictionary.getPairsManager()
							.getAllKeys()[i], true);
					topEditor.prevSelectedIndex = i;
					return;
				}
				if (i != dictionary.getPairsManager().getAllKeys().length - 1)
					continue;
				topEditor.prevSelectedIndex = 0;
			}
		}
	}


	public void jButtAddSampleMouseReleased(MouseEvent paramMouseEvent) {
		if (topEditor.jTextSamplesSearch.getText().equals(""))
			dictionary.getPairsManager().addNewSample(null);
		else if (dictionary.getPairsManager()
				.addNewSample(topEditor.jTextSamplesSearch.getText()))
			topEditor.jTextSamplesSearch.setText("");
		topEditor.refresh(false, true);
	}

	public void jTextSamplesSearchKeyReleased(KeyEvent paramKeyEvent) {
		if (paramKeyEvent.getKeyCode() != 10)
			return;
		if ((!(topEditor.jTextSamplesSearch.getText().equals("")))
				&& (dictionary.getPairsManager().addNewSample(topEditor.jTextSamplesSearch
						.getText())))
			topEditor.jTextSamplesSearch.setText("");
		topEditor.refresh(false, true);
	}

	public void jButtRemSampleMouseReleased(MouseEvent paramMouseEvent) {
		dictionary.getCurrentStateManager().removeCurrSample();
		topEditor.refresh(false, true);
	}

	public void jListSamplesMouseReleased(MouseEvent paramMouseEvent) {
		dictionary.getCurrentStateManager().setCurrSample((String) topEditor.jListSamples
				.getSelectedValue());
		topEditor.refresh(false, false);
	}

	public void jButtRecordMouseReleased(MouseEvent paramMouseEvent) {
		if (dictionary.getAudioMan().recorder == null)
			dictionary.getAudioMan().startRecording(null);
		else
			dictionary.getAudioMan().stopRecording();
		topEditor.refresh(false, false);
	}

	public void jButtPlayMouseReleased(MouseEvent paramMouseEvent) {
		dictionary.getAudioMan().playCurrentAudioRecord();
	}

	public void jButtDeleteRecordMouseReleased(MouseEvent paramMouseEvent) {
		if (!(dictionary.getAudioMan().isCurrentItemAlreadyRecorded()))
			return;
		dictionary.getAudioMan().deleteCurrentAudioRecord();
		topEditor.refresh(false, false);
	}

	public void Alert(String paramString) {
		JOptionPane.showMessageDialog(new JFrame("FrameDemo"), paramString);
	}
	
	public void InUseJListMouseReleased(ListSelectionEvent paramListSelectionEvent) {
		if( topEditor.InUseJList.getSelectedValue() != null){
			String selectedValue = topEditor.InUseJList.getSelectedValue().toString();
			dictionary.getCurrentStateManager().setCurrentTrashPairByKey(selectedValue);
		}else{
			dictionary.getCurrentStateManager().setCurrentTrashPair(null);
		}
		
	}
}
