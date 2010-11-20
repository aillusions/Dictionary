package com.aillusions.dictionary.core;

import org.apache.log4j.Logger;

import com.aillusions.dictionary.Trainer;
import com.aillusions.dictionary.util.Config;
import com.aillusions.dictionary.xsl.DocConverter;
import com.aillusions.dictionary.xsl.DocConverter.DocViewMode;

public class Manager {
	private static final Logger l = Logger.getLogger(Config.class);

	private final AudioManager audioMan;
	private final WorkspaceManager workspaceManager;
	private final PairsManager pairsManager;
	private final CurrentStateManager currentStateManager;
	private final TrashManager trashManager;

	public Manager(final String fName) {
		l.info("Constructor");

		this.workspaceManager = new WorkspaceManager(this, fName.trim());
		this.audioMan = new AudioManager(this);
		this.pairsManager = new PairsManager(this);
		this.currentStateManager = new CurrentStateManager(this);
		this.trashManager = new TrashManager(this);
		Load();
	}

	public void Load() {

		this.workspaceManager.load();
		if ((this.currentStateManager.getCurrentPair() == null)
				|| (this.pairsManager.getPairByKey(this.currentStateManager.getCurrentPair().getEnglish()) != null)) {
			return;
		}
		this.currentStateManager.setCurrentPair(null);
	}

	public void runTrainer() {
		new Trainer(this).startTraining();
	}

	public void runWord(final DocViewMode docViewMode) {
		this.workspaceManager.saveInFile();
		new DocConverter().runWord(docViewMode);
	}

	//
	// Getters and setters
	//

	public AudioManager getAudioMan() {
		return this.audioMan;
	}

	public PairsManager getPairsManager() {
		return this.pairsManager;
	}

	public WorkspaceManager getWorkspaceManager() {
		return this.workspaceManager;
	}

	public CurrentStateManager getCurrentStateManager() {
		return this.currentStateManager;
	}

	public TrashManager getTrashManager() {
		return this.trashManager;
	}

}
