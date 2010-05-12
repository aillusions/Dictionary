package com.myjavaserver.aillusions.model;

public interface TrainerViewListener {

	boolean onChuseAnsversVariant(String str);
	void onCloseView();
	void onClearResults();
	void onNextReady();
}
