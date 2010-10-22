package com.aillusions.dictionary.model;

public interface TrainerViewListener {

	boolean onChuseAnsversVariant(String str);
	void onCloseView();
	void onClearResults();
	void onNextReady();
}
