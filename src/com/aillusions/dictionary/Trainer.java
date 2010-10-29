package com.aillusions.dictionary;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.aillusions.dictionary.core.Manager;
import com.aillusions.dictionary.model.Pair;
import com.aillusions.dictionary.view.TrainerView;
import com.aillusions.dictionary.view.TrainerViewListener;

public class Trainer implements TrainerViewListener {
	
	private Manager dictionary;
	
	private TrainerView view;
	private boolean isEngRus;
	Random generatorQuestion = new Random(0L);
	Random generatorVariant = new Random(0L);
	Random generatorRightPosition = new Random();
	Random generatorENG_RUS = new Random(0L);
	private Pair currentPair = null;

	public Trainer(Manager paramDictionary) {
		this.dictionary = paramDictionary;
	}

	public void startTraining() {
		this.view = new TrainerView(this);
		showQuestion();
		int i = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int j = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		this.view.setBounds(j / 2 - 200, i / 2 - 100, 400, 200);
		this.view.setAlwaysOnTop(true);
		this.view.setVisible(true);
	}

	public void stopTraining() {
		this.view.setVisible(false);
		this.view.dispose();
		this.view = null;
	}

	public Pair getMaxPriorityPair() {
		return getMaxPriorityPair(this.dictionary.getPairsManager().getAllInUsePairs());
	}

	public Pair getMaxPriorityPair(List<Pair> paramArrayOfPair) {
		if (paramArrayOfPair.size() < 1) {
			return null;
		}
		Pair localPair = null;
		int i = this.generatorQuestion.nextInt(paramArrayOfPair.size());
		localPair = paramArrayOfPair.get(i);
		return localPair;
	}

	private String[] getVariants() {
		int i = 6;
		int j = this.generatorRightPosition.nextInt(i - 1);
		int k = this.dictionary.getPairsManager().getAllPairs().size();
		ArrayList<String> localArrayList = new ArrayList<String>();
		
		for (int i1 = 0; i1 < i; ++i1){
			if (i1 != j) {
				int i2 = this.generatorVariant.nextInt(k);
				if (this.isEngRus){
					localArrayList.add(this.dictionary.getPairsManager().getAllPairs().get(i2).getRussian());
				}
				else{
					localArrayList.add(this.dictionary.getPairsManager().getAllPairs().get(i2).getEnglish());
				}
			} else if (!(this.currentPair.getRussian().trim().equals(""))) {
				if (this.isEngRus){
					localArrayList.add(this.currentPair.getRussian());
				}
				else{
					localArrayList.add(this.currentPair.getEnglish());
				}
			} else {
				localArrayList.add("empty");
			}
		}
		return ((String[]) localArrayList.toArray(new String[i]));
	}

	public boolean onChuseAnsversVariant(String paramString) {
		this.currentPair.incrAttemttsQuantity();
		if (this.isEngRus) {
			if ((this.currentPair.getRussian().equalsIgnoreCase(paramString))
					|| (paramString.equals("empty"))) {
				this.currentPair.incrRightAnswerQuantity();
				return true;
			}
		} else if ((this.currentPair.getEnglish().equalsIgnoreCase(paramString))
				|| (paramString.equals("empty"))) {
			this.currentPair.incrRightAnswerQuantity();
			return true;
		}
		return false;
	}

	public void onCloseView() {
	}

	void showQuestion() {
		int i = this.generatorENG_RUS.nextInt(2);
		System.out.println(i);
		if (i == 1)
			this.isEngRus = true;
		else
			this.isEngRus = false;
		this.currentPair = getMaxPriorityPair();
		if (this.currentPair == null) {
			this.view.setVisible(false);
		} else {
			this.currentPair.incrAsksQuantity();
			if (this.isEngRus)
				this.view.setPairtForQuastion(this.currentPair.getEnglish(),
						this.currentPair.getTranscription(), getVariants());
			else
				this.view.setPairtForQuastion(this.currentPair.getRussian(),
						"", getVariants());
		}
	}

	public void clearResults() {
		for (Pair localPair : this.dictionary.getPairsManager().getAllPairs())
			localPair.clearStatus();
	}

	public void onClearResults() {
		clearResults();
		showQuestion();
	}

	public void onNextReady() {
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.currentThread();
					Thread.sleep(1000L);
				} catch (Exception localException) {
					localException.printStackTrace();
				}
				Trainer.this.showQuestion();
			}
		}).start();
	}
}

/*
 * Location: E:\ATG\workspace\Dictionary.jar Qualified Name:
 * com.myjavaserver.aillusions.Trainer Java Class Version: 5 (49.0) JD-Core
 * Version: 0.5.3
 */