package com.aillusions.dictionary.manager;

import com.aillusions.dictionary.AudioManager;
import com.aillusions.dictionary.Trainer;
import com.aillusions.dictionary.xsl.DocConverter;

public class Manager {

	private AudioManager audioMan;
	private WorkspaceManager workspaceManager;
	private PairsManager pairsManager;

	private Trainer trainer;

	public Manager(String fName) {
		workspaceManager = new WorkspaceManager(fName);	
		audioMan = new AudioManager(this);
		pairsManager = new PairsManager(this);		
	}

	public void runTrainer() {
		this.trainer = new Trainer(this);
		this.trainer.startTraining();
	}
	
	public void Load() {

		workspaceManager.load();
		if ((pairsManager.getCurrentPair() == null)	|| (pairsManager.getPairByKey(pairsManager.getCurrentPair().getEnglish()) != null)) {
			return;
		}
		pairsManager.setCurWord(null);
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
	
}
