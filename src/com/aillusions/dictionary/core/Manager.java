package com.aillusions.dictionary.core;

import org.apache.log4j.Logger;

import com.aillusions.dictionary.Trainer;
import com.aillusions.dictionary.util.Config;
import com.aillusions.dictionary.xsl.DocConverter;
import com.aillusions.dictionary.xsl.DocConverter.DocViewMode;

public class Manager {
	private static final Logger l = Logger.getLogger(Config.class);

	private AudioManager audioManager;
	private WorkspaceManager workspaceManager;
	private PairsManager pairsManager;
	private CurrentStateManager currentStateManager;
	private TrashManager trashManager;

	public Manager() {
		l.info("Constructor");
	}

	public void runTrainer() {
		new Trainer(this).startTraining();
	}

	public void runWord(final DocViewMode docViewMode) {
		this.workspaceManager.saveInFile();
		new DocConverter().runWord(docViewMode);
	}

	public AudioManager getAudioManager() {
		return audioManager;
	}

	public void setAudioManager(AudioManager audioManager) {
		this.audioManager = audioManager;
	}

	public WorkspaceManager getWorkspaceManager() {
		return workspaceManager;
	}

	public void setWorkspaceManager(WorkspaceManager workspaceManager) {
		this.workspaceManager = workspaceManager;
	}

	public PairsManager getPairsManager() {
		return pairsManager;
	}

	public void setPairsManager(PairsManager pairsManager) {
		this.pairsManager = pairsManager;
	}

	public CurrentStateManager getCurrentStateManager() {
		return currentStateManager;
	}

	public void setCurrentStateManager(CurrentStateManager currentStateManager) {
		this.currentStateManager = currentStateManager;
	}

	public TrashManager getTrashManager() {
		return trashManager;
	}

	public void setTrashManager(TrashManager trashManager) {
		this.trashManager = trashManager;
	}

}
