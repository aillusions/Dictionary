package com.aillusions.dictionary.view.listener;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.aillusions.dictionary.core.Manager;
import com.aillusions.dictionary.model.Pair;
import com.aillusions.dictionary.view.TopEditor;

public class MainDictPanelListener {
	
	private Manager dictionary;
	private TopEditor topEditor;
	
	public MainDictPanelListener(Manager dictionary, TopEditor topEditor){
		this.dictionary = dictionary;
		this.topEditor = topEditor;
	}
	
	public void literalButtonsMouseReleased(MouseEvent paramMouseEvent) {
		if (this.dictionary.getCurrentPairManager().getCurrentPair() != null) {
			topEditor.Transcription_TextF.setText(this.dictionary.getCurrentPairManager().getCurrentPair()
					.getTranscription()
					+ ((JButton) paramMouseEvent.getSource()).getText());
			if (this.dictionary.getCurrentPairManager().aplyCurrWordChanged(topEditor.Translate_TextF
					.getText(), topEditor.Transcription_TextF.getText()))
				return;
			Alert("Your input was not aplyed!");
		} else {
			Alert("You mast select one before!");
		}
	}
	
	public void Transcription_TextFKeyReleased(KeyEvent paramKeyEvent) {
		if (dictionary.getCurrentPairManager().aplyCurrWordChanged(topEditor.Translate_TextF.getText(),
				topEditor.Transcription_TextF.getText()))
			return;
		Alert("Your input was not aplyed!");
	}

	public void Translate_TextFKeyReleased(KeyEvent paramKeyEvent) {
		if (dictionary.getCurrentPairManager().aplyCurrWordChanged(topEditor.Translate_TextF.getText(),
				topEditor.Transcription_TextF.getText()))
			return;
		Alert("Your input was not aplyed!");
	}

	public void Word_TextFKeyReleased(KeyEvent paramKeyEvent) {
		if (dictionary.getCurrentPairManager().aplyCurrWordChanged(topEditor.Translate_TextF.getText(),
				topEditor.Transcription_TextF.getText()))
			return;
		Alert("Your input was not aplyed!");
	}




	public void Rename_BtnMouseReleased(MouseEvent paramMouseEvent) {
		if (dictionary.getCurrentPairManager().getCurrentPair() == null)
			return;
		dictionary.getCurrentPairManager().renameCurrentPair(null);
		topEditor.refresh(true, true);
	}

	public void Remove_BtnMouseReleased(MouseEvent paramMouseEvent) {
		if (dictionary.getCurrentPairManager().getCurrentPair() == null)
			Alert("You have to select one before!");
		dictionary.getCurrentPairManager().removeCurrentPair();
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
		dictionary.getCurrentPairManager().removeCurrSample();
		topEditor.refresh(false, true);
	}

	public void jListSamplesMouseReleased(MouseEvent paramMouseEvent) {
		dictionary.getCurrentPairManager().setCurrSample((String) topEditor.jListSamples
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

	public void WordsList_LstKeyReleased(KeyEvent paramKeyEvent) {
		if ((paramKeyEvent.getKeyCode() == 155)
				&& (topEditor.WordsList_Lst.getSelectedIndex() < dictionary.getPairsManager()
						.getAllKeys().length)) {
			dictionary.getCurrentPairManager().getCurrentPair().setInuse(true);
			topEditor.WordsList_Lst.setSelectedIndex(topEditor.WordsList_Lst
					.getSelectedIndex() + 1);
		}
		if ((paramKeyEvent.getKeyCode() != 127)
				|| (topEditor.WordsList_Lst.getSelectedIndex() >= dictionary.getPairsManager()
						.getAllKeys().length))
			return;
		dictionary.getCurrentPairManager().getCurrentPair().setInuse(false);
		topEditor.WordsList_Lst.setSelectedIndex(topEditor.WordsList_Lst
				.getSelectedIndex() + 1);
	}

	public void WordsList_LstMouseReleased(MouseEvent paramMouseEvent) {
	}



	public void InUseJListMouseReleased(MouseEvent paramMouseEvent) {
		topEditor.WordsList_Lst.setSelectedValue(topEditor.InUseJList.getSelectedValue(),
				true);
	}

	public void InUseJListKeyReleased(KeyEvent paramKeyEvent) {
		if ((paramKeyEvent.getKeyCode() == 127)
				&& (topEditor.WordsList_Lst.getSelectedIndex() < dictionary.getPairsManager()
						.getAllKeys().length)) {
			dictionary.getCurrentPairManager().getCurrentPair().setInuse(false);
			topEditor.WordsList_Lst.setSelectedIndex(topEditor.WordsList_Lst
					.getSelectedIndex() + 1);
		}
		topEditor.inUseListRefresh();
	}



	public void addToRight_oneMouseReleased(MouseEvent paramMouseEvent) {
		dictionary.getCurrentPairManager().getCurrentPair().setInuse(true);
		topEditor.inUseListRefresh();
	}

	public void addToRight_allMouseReleased(MouseEvent paramMouseEvent) {
		for (Pair localPair : dictionary.getPairsManager().getAllPairs())
			localPair.setInuse(true);
		topEditor.inUseListRefresh();
	}

	public void dellFromRight_oneMouseReleased(MouseEvent paramMouseEvent) {
		dictionary.getCurrentPairManager().getCurrentPair().setInuse(false);
		topEditor.inUseListRefresh();
	}

	public void dellFromRight_allMouseReleased(MouseEvent paramMouseEvent) {
		for (Pair localPair : dictionary.getPairsManager().getAllPairs())
			localPair.setInuse(false);
		topEditor.inUseListRefresh();
	}

	public void selectNextRandomBtnMouseReleased(MouseEvent paramMouseEvent) {
		String str = dictionary.getPairsManager().getAllInUseKeys()[topEditor.nextSelRandom
				.nextInt(dictionary.getPairsManager().getAllInUsePairs().size())];
		topEditor.InUseJList.setSelectedValue(str, true);
	}

	public void thisMouseReleased(MouseEvent paramMouseEvent) {
	
	}

	public void Alert(String paramString) {
		JOptionPane.showMessageDialog(new JFrame("FrameDemo"), paramString);
	}
}
