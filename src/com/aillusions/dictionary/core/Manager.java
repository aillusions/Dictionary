package com.aillusions.dictionary.core;

import com.aillusions.dictionary.xsl.DocConverter;

public class Manager {

	private AudioManager audioMan;
	private WorkspaceManager workspaceManager;
	private PairsManager pairsManager;
	private CurrentPairManager currentPairManager;

	private TrainerManager trainer;

	public Manager(String fName) {
		workspaceManager = new WorkspaceManager(fName);	
		audioMan = new AudioManager(this);
		pairsManager = new PairsManager(this);	
		currentPairManager = new CurrentPairManager(this);
	}

	public void runTrainer() {
		this.trainer = new TrainerManager(this);
		this.trainer.startTraining();
	}
	
	public void Load() {

		workspaceManager.load();
		if ((currentPairManager.getCurrentPair() == null)	|| (pairsManager.getPairByKey(currentPairManager.getCurrentPair().getEnglish()) != null)) {
			return;
		}
		currentPairManager.setCurrentPair(null);
	}

	public void runWord(boolean paramBoolean1, boolean paramBoolean2) {
		workspaceManager.saveInFile();
		new DocConverter().runWord(paramBoolean1, paramBoolean2);
	}

	public AudioManager getAudioMan() {
		return audioMan;
	}

	public PairsManager getPairsManager() {
		return pairsManager;
	}

	public WorkspaceManager getWorkspaceManager() {
		return workspaceManager;
	}

	public CurrentPairManager getCurrentPairManager() {
		return currentPairManager;
	}
	
	
	
}
