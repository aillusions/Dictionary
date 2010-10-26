package com.aillusions.dictionary.view.listener;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;

import com.aillusions.dictionary.Manager;
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
		if (this.dictionary.getCurrentPair() != null) {
			topEditor.Transcription_TextF.setText(this.dictionary.getCurrentPair()
					.getTranscription()
					+ ((JButton) paramMouseEvent.getSource()).getText());
			if (this.dictionary.aplyCurrWordChanged(topEditor.Translate_TextF
					.getText(), topEditor.Transcription_TextF.getText()))
				return;
			Alert("Your input was not aplyed!");
		} else {
			Alert("You mast select one before!");
		}
	}
	
	public void Transcription_TextFKeyReleased(KeyEvent paramKeyEvent) {
		if (this.dictionary.aplyCurrWordChanged(topEditor.Translate_TextF.getText(),
				topEditor.Transcription_TextF.getText()))
			return;
		Alert("Your input was not aplyed!");
	}

	public void Translate_TextFKeyReleased(KeyEvent paramKeyEvent) {
		if (dictionary.aplyCurrWordChanged(topEditor.Translate_TextF.getText(),
				topEditor.Transcription_TextF.getText()))
			return;
		Alert("Your input was not aplyed!");
	}

	public void Word_TextFKeyReleased(KeyEvent paramKeyEvent) {
		if (dictionary.aplyCurrWordChanged(topEditor.Translate_TextF.getText(),
				topEditor.Transcription_TextF.getText()))
			return;
		Alert("Your input was not aplyed!");
	}



	public void refresh(boolean paramBoolean1, boolean paramBoolean2) {
		if (dictionary.getAudioMan().recorder == null) {
			topEditor.jButtRecordding.setText("start");
			topEditor.jButtPlay.setEnabled(true);
			if (dictionary.isCurrentItemAlreadyRecorded()) {
				topEditor.jButtPlay.setEnabled(true);
				topEditor.jButtDeleteRecord.setEnabled(true);
				topEditor.jButtRecordding.setEnabled(false);
			} else {
				topEditor.jButtPlay.setEnabled(false);
				topEditor.jButtDeleteRecord.setEnabled(false);
				topEditor.jButtRecordding.setEnabled(true);
			}
		} else {
			topEditor.jButtRecordding.setText("stop");
			topEditor.jButtPlay.setEnabled(false);
		}
		String str;
		if (dictionary.getCurrentPair() == null) {
			topEditor.setTitle("Top Dictionary - none : ");
			topEditor.Word_TextF.setText("");
			topEditor.Translate_TextF.setText("");
			topEditor.Transcription_TextF.setText("");
			topEditor.jListSamples.setListData(new String[0]);
			topEditor.jButtPlay.setEnabled(false);
			topEditor.jButtDeleteRecord.setEnabled(false);
			topEditor.jButtRecordding.setEnabled(false);
		} else {
			topEditor.setTitle("Top Dictionary - "
					+ (topEditor.WordsList_Lst.getSelectedIndex() + 1)
					+ " : "
					+ new Integer(dictionary.getAllWords().length)
							.toString());
			topEditor.Word_TextF.setText(dictionary.getCurrentPair()
					.getEnglish());
			topEditor.Translate_TextF.setText(dictionary.getCurrentPair()
					.getRussian());
			topEditor.Transcription_TextF.setText(dictionary.getCurrentPair()
					.getTranscription());
			if (paramBoolean2) {
				str = null;
				if (dictionary.getCurrSample() != null)
					str = dictionary.getCurrSample();
				if (dictionary.getCurrentPair().getSamples() != null)
					topEditor.jListSamples.setListData(dictionary
							.getCurrentPair().getSamples());
				else
					topEditor.jListSamples.setListData(new String[0]);
				if (dictionary.getCurrentPair().getSamples() != null)
					topEditor.jListSamples.setSelectedValue(str, true);
			}
		}
		if (paramBoolean1) {
			str = null;
			if (dictionary.getCurrentPair() != null)
				str = dictionary.getCurrentPair().getEnglish();
			topEditor.WordsList_Lst.setListData(dictionary.getAllWords());
			if ((dictionary.setSelected(str))
					&& (dictionary.getCurrentPair() != null))
				topEditor.WordsList_Lst.setSelectedValue(dictionary
						.getCurrentPair().getEnglish(), true);
		}
		if (dictionary.getCurrentPair() == null)
			if (dictionary.getAllWords().length > 0)
				topEditor.WordsList_Lst.setSelectedValue(dictionary
						.getAllWords()[0], true);
			else
				topEditor.WordsList_Lst.setSelectedValue(null, true);
		inUseListRefresh();
	}

	public void Rename_BtnMouseReleased(MouseEvent paramMouseEvent) {
		if (dictionary.getCurrentPair() == null)
			return;
		dictionary.renameCurrent(null);
		refresh(true, true);
	}

	public void Remove_BtnMouseReleased(MouseEvent paramMouseEvent) {
		if (dictionary.getCurrentPair() == null)
			Alert("You have to select one before!");
		dictionary.removeCurrent();
		refresh(true, true);
	}

	public void Add_btnMouseReleased(MouseEvent paramMouseEvent) {
		boolean bool = false;
		if (topEditor.jTxtSearch.getText().equals(""))
			bool = dictionary.addNew(null);
		else
			bool = dictionary.addNew(topEditor.jTxtSearch.getText());
		if (bool)
			topEditor.jTxtSearch.setText("");
		refresh(true, true);
	}

	public void jTxtSearchKeyReleased(KeyEvent paramKeyEvent) {
		String str = topEditor.jTxtSearch.getText().trim();
		if (paramKeyEvent.getKeyCode() == 10) {
			boolean bool = false;
			if (str.equals(""))
				bool = dictionary.addNew(null);
			else
				bool = dictionary.addNew(str);
			if (bool)
				topEditor.jTxtSearch.setText("");
			refresh(true, true);
		} else {
			if (str.equals(""))
				return;
			for (int i = topEditor.prevSelectedIndex + 1; i < dictionary
					.getAllWords().length; ++i) {
				if (dictionary.getAllWords()[i].indexOf(str) == 0) {
					topEditor.WordsList_Lst.setSelectedValue(dictionary
							.getAllWords()[i], true);
					topEditor.prevSelectedIndex = i;
					return;
				}
				if (i != dictionary.getAllWords().length - 1)
					continue;
				topEditor.prevSelectedIndex = 0;
			}
		}
	}

	public void Load_BtnMouseReleased(MouseEvent paramMouseEvent) {
		dictionary.Load();
		System.out.println("1");
		refresh(true, true);
	}

	public void Save_BtnMouseReleased(MouseEvent paramMouseEvent) {
		dictionary.saveInFile();
	}

	public void jButtAddSampleMouseReleased(MouseEvent paramMouseEvent) {
		if (topEditor.jTextSamplesSearch.getText().equals(""))
			dictionary.addNewSample(null);
		else if (dictionary
				.addNewSample(topEditor.jTextSamplesSearch.getText()))
			topEditor.jTextSamplesSearch.setText("");
		refresh(false, true);
	}

	public void jTextSamplesSearchKeyReleased(KeyEvent paramKeyEvent) {
		if (paramKeyEvent.getKeyCode() != 10)
			return;
		if ((!(topEditor.jTextSamplesSearch.getText().equals("")))
				&& (dictionary.addNewSample(topEditor.jTextSamplesSearch
						.getText())))
			topEditor.jTextSamplesSearch.setText("");
		refresh(false, true);
	}

	public void jButtRemSampleMouseReleased(MouseEvent paramMouseEvent) {
		dictionary.remCurrSample();
		refresh(false, true);
	}

	public void jListSamplesMouseReleased(MouseEvent paramMouseEvent) {
		dictionary.setCurrSample((String) topEditor.jListSamples
				.getSelectedValue());
		refresh(false, false);
	}

	public void jButtRecordMouseReleased(MouseEvent paramMouseEvent) {
		if (dictionary.getAudioMan().recorder == null)
			dictionary.startRecording(null);
		else
			dictionary.stopRecording();
		refresh(false, false);
	}

	public void jButtPlayMouseReleased(MouseEvent paramMouseEvent) {
		dictionary.playCurrentAudioRecord(true, false);
	}

	public void jButtDeleteRecordMouseReleased(MouseEvent paramMouseEvent) {
		if (!(dictionary.isCurrentItemAlreadyRecorded()))
			return;
		dictionary.deleteCurrentAudioRecord();
		refresh(false, false);
	}

	public void WordsList_LstKeyReleased(KeyEvent paramKeyEvent) {
		if ((paramKeyEvent.getKeyCode() == 155)
				&& (topEditor.WordsList_Lst.getSelectedIndex() < dictionary
						.getAllWords().length)) {
			dictionary.getCurrentPair().setInuse(true);
			topEditor.WordsList_Lst.setSelectedIndex(topEditor.WordsList_Lst
					.getSelectedIndex() + 1);
		}
		if ((paramKeyEvent.getKeyCode() != 127)
				|| (topEditor.WordsList_Lst.getSelectedIndex() >= dictionary
						.getAllWords().length))
			return;
		dictionary.getCurrentPair().setInuse(false);
		topEditor.WordsList_Lst.setSelectedIndex(topEditor.WordsList_Lst
				.getSelectedIndex() + 1);
	}

	public void WordsList_LstMouseReleased(MouseEvent paramMouseEvent) {
	}

	public void word_startMouseReleased(MouseEvent paramMouseEvent) {
		dictionary.runWord(false, false);
	}

	public void runTrainerBtnMouseReleased(MouseEvent paramMouseEvent) {
		dictionary.runTrainer();
	}

	public void InUseJListMouseReleased(MouseEvent paramMouseEvent) {
		topEditor.WordsList_Lst.setSelectedValue(topEditor.InUseJList.getSelectedValue(),
				true);
	}

	public void InUseJListKeyReleased(KeyEvent paramKeyEvent) {
		if ((paramKeyEvent.getKeyCode() == 127)
				&& (topEditor.WordsList_Lst.getSelectedIndex() < dictionary
						.getAllWords().length)) {
			dictionary.getCurrentPair().setInuse(false);
			topEditor.WordsList_Lst.setSelectedIndex(topEditor.WordsList_Lst
					.getSelectedIndex() + 1);
		}
		inUseListRefresh();
	}

	public void inUseListRefresh() {
		int i = topEditor.InUseJList.getSelectedIndex();
		Object localObject = topEditor.InUseJList.getSelectedValue();
		topEditor.InUseJList.setListData(dictionary.getAllInUseWords());
		if (i == -1)
			return;
		if (i < dictionary.getAllInUseWords().length)
			topEditor.InUseJList.setSelectedIndex(i);
		else
			topEditor.InUseJList
					.setSelectedIndex(dictionary.getAllInUseWords().length - 1);
	}

	public void addToRight_oneMouseReleased(MouseEvent paramMouseEvent) {
		dictionary.getCurrentPair().setInuse(true);
		inUseListRefresh();
	}

	public void addToRight_allMouseReleased(MouseEvent paramMouseEvent) {
		for (Pair localPair : dictionary.getAllPairs())
			localPair.setInuse(true);
		inUseListRefresh();
	}

	public void dellFromRight_oneMouseReleased(MouseEvent paramMouseEvent) {
		dictionary.getCurrentPair().setInuse(false);
		inUseListRefresh();
	}

	public void dellFromRight_allMouseReleased(MouseEvent paramMouseEvent) {
		for (Pair localPair : dictionary.getAllPairs())
			localPair.setInuse(false);
		inUseListRefresh();
	}

	public void selectNextRandomBtnMouseReleased(MouseEvent paramMouseEvent) {
		String str = dictionary.getAllInUseWords()[topEditor.nextSelRandom
				.nextInt(dictionary.getAllInUsePairs().length)];
		topEditor.InUseJList.setSelectedValue(str, true);
	}

	public void thisMouseReleased(MouseEvent paramMouseEvent) {
	
	}

	public void jButton45MouseReleased(MouseEvent paramMouseEvent) {
		dictionary.runWord(false, false);
	}

	public void inUseOnlyWordsShowInWord(MouseEvent paramMouseEvent) {
		dictionary.runWord(true, false);
	}

	public void jButton47MouseReleased(MouseEvent paramMouseEvent) {
		dictionary.runWord(true, true);
	}

	public void jButton48MouseReleased(MouseEvent paramMouseEvent) {
		dictionary.upCurrentSample();
		refresh(false, true);
	}

	public void jButton49MouseReleased(MouseEvent paramMouseEvent) {
		if (topEditor.jButton49.getText().equals("<")) {
			topEditor.jButton49.setText(">");
			topEditor.setSize(TopEditor.WINDOW_WIDTH_SHORT, TopEditor.WINDOW_HEIGHT);
		} else {
			topEditor.jButton49.setText("<");
			topEditor.setSize(TopEditor.WINDOW_WIDTH_EXPANDED, TopEditor.WINDOW_HEIGHT);
		}
	}

	public void jButton499MouseReleased(MouseEvent paramMouseEvent) {
		dictionary.shuffle();
		refresh(true, true);
	}

	public void jTopStateChanged(ChangeEvent paramChangeEvent) {
		topEditor.setAlwaysOnTop(topEditor.jTop.isSelected());
	}
	
	public void Alert(String paramString) {
		JOptionPane.showMessageDialog(new JFrame("FrameDemo"), paramString);
	}
}
