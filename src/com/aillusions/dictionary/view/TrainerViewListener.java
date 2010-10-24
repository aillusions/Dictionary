package com.aillusions.dictionary.view;

public interface TrainerViewListener {

	boolean onChuseAnsversVariant(String str);
	void onCloseView();
	void onClearResults();
	void onNextReady();
}
