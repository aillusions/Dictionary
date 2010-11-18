package com.aillusions.dictionary.core;

import com.aillusions.dictionary.Trainer;
import com.aillusions.dictionary.xsl.DocConverter;
import com.aillusions.dictionary.xsl.DocConverter.DocViewMode;

public class Manager {

	private AudioManager audioMan;
	private WorkspaceManager workspaceManager;
	private PairsManager pairsManager;
	private CurrentStateManager currentStateManager;
	private TrashManager trashManager;

	public Manager(String fName) {
		workspaceManager = new WorkspaceManager(this, fName);	
		audioMan = new AudioManager(this);
		pairsManager = new PairsManager(this);	
		currentStateManager = new CurrentStateManager(this);
		trashManager = new TrashManager(this);
	}
	
	public void Load() {

		workspaceManager.load();
		if ((currentStateManager.getCurrentPair() == null)	|| (pairsManager.getPairByKey(currentStateManager.getCurrentPair().getEnglish()) != null)) {
			return;
		}
		currentStateManager.setCurrentPair(null);
	}

	public void runTrainer() {
		 new Trainer(this).startTraining();
	}

	public void runWord(DocViewMode docViewMode) {
		workspaceManager.saveInFile();
		new DocConverter().runWord(docViewMode);
	}
	
	//
	//Getters and setters
	//
	
	public AudioManager getAudioMan() {
		return audioMan;
	}

	public PairsManager getPairsManager() {
		return pairsManager;
	}

	public WorkspaceManager getWorkspaceManager() {
		return workspaceManager;
	}

	public CurrentStateManager getCurrentStateManager() {
		return currentStateManager;
	}

	public TrashManager getTrashManager() {
		return trashManager;
	}	
	
	
}
