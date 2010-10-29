package com.aillusions.dictionary.core;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.aillusions.dictionary.model.Pair;

public class CurrentPairManager {
	
	private Pair currentPair;
	private String currSample;
	
	private Manager manager;
	
	public CurrentPairManager(Manager manager){
		this.manager = manager;
	}

	public boolean setCurrentPairByKey(String paramString) {
		if (setCurrentPair(manager.getPairsManager().getPairByKey(paramString)) != null){
			return true;
		}
		return false;
	}
	
	public Pair setCurrentPair(Pair paramPair) {
		this.currentPair = paramPair;
		this.currSample = null;
		return currentPair;
	}
	
	
	public boolean aplyCurrWordChanged(String paramString1, String paramString2) {

		if (getCurrentPair() != null) {
			getCurrentPair().setRussian(paramString1);
			getCurrentPair().setTranscription(paramString2);
			return true;
		}
		return false;
	}
	
	public void removeCurrentPair() {
		
		if (getCurrentPair() == null){
			return;
		}
		manager.getAudioMan().removeAllTightAudio(getCurrentPair().getEnglish());
		Pair localPair = manager.getPairsManager().removePair(getCurrentPair());
		setCurrentPair(localPair);
		this.currSample = null;
	}

	public void renameCurrentPair(String paramString) {
		
		if ((paramString == null) || (paramString.trim().equals(""))){
			paramString = JOptionPane.showInputDialog(new JFrame("FrameDemo"),	"Input word please:", "Rename", 3);
		}
		
		if ((paramString != null) && (!(paramString.trim().equals("")))	&& (manager.getPairsManager().getPairByKey(paramString) == null)) {
			getCurrentPair().setEnglish(paramString);
			setCurrentPair(manager.getPairsManager().getPairByKey(paramString));
		} else {
			JOptionPane.showMessageDialog(new JFrame("Sorry"), "Such word already exists, or empty.");
		}
		this.currSample = null;
	}
	
	public void upCurrentSample() {
		
		if ((getCurrSample() == null) || (getCurrSample().trim().equals(""))){
			return;
		}
		getCurrentPair().upSample(getCurrSample());
	}


	public void removeCurrSample() {
		if (currSample != null)
			getCurrentPair().removeSample(currSample);
		currSample = null;
	}

	public void setCurrSample(String paramString) {
		if (paramString == null){
			currSample = null;
		}
		else{
			for (String str : getCurrentPair().getSamples()) {
				if (!(paramString.equals(str))){
					continue;
				}
				currSample = str;
			}
		}
	}
	
	public Pair getCurrentPair() {
		return this.currentPair;
	}
	
	public String getCurrSample() {
		return currSample;
	}
}
