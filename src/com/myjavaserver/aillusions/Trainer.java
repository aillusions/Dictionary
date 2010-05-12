package com.myjavaserver.aillusions;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.myjavaserver.aillusions.model.Pair;
import com.myjavaserver.aillusions.model.TrainerViewListener;
import com.myjavaserver.aillusions.view.TrainerView;

public class Trainer implements TrainerViewListener  {

	private Dictionary dictionary;
	
	private TrainerView view;	
	
	public Trainer(Dictionary dict){
		this.dictionary = dict;
	}
	
	private boolean isEngRus;
	
	Random generatorQuestion = new Random(0);	
	
	Random generatorVariant = new Random(0);	
	Random generatorRightPosition = new Random();
	Random generatorENG_RUS = new Random(0);
	
	public void startTraining(){
		
		final int viewWidth = 400;
		final int viewHeight = 200;
		
		view = new TrainerView(this);
		showQuestion();
		 
		final int scrH = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		final int scrW = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		
		//center
		view.setBounds(scrW/2-viewWidth/2 ,scrH/2-viewHeight/2 ,viewWidth, viewHeight);
		view.setAlwaysOnTop(true);
		
		view.setVisible(true);
	}
	
	public void stopTraining(){
		view.setVisible(false);
		view.dispose();
		view = null;
	}
	
	public Pair getMaxPriorityPair(){
		return getMaxPriorityPair(dictionary.getAllInUsePairs());
	}
	
	public Pair getMaxPriorityPair(Pair[] pairs){

		Pair result = null;
		int next = generatorQuestion.nextInt(pairs.length);
		result = pairs[next];
		
		/*double index = 0;
		double batchSize = pairs.length;
		Map<String, Double> piors = new HashMap<String, Double>();
		double maxPriority = -1;
		
		for(Pair p : pairs){
			
			index++;
			boolean inUse = p.isInuse(); 
			if(inUse){
				double question = p.getAsksQuantity();
				double rightAnsw = p.getRightAnswerQuantity();
				double attempts = p.getAttemttsQuantity();				
				double priority = (((attempts+1)/(rightAnsw+1)) + (index / batchSize))/(question+1) ;

				if(maxPriority < priority){
					maxPriority = priority;
					result = p;
					piors.put(p.getEnglish(), new Double(priority));					
				}	
			}
		}
		*/
		
		
		
		return result;
	}
	
	private Pair currentPair = null;
	
	
	private String[] getVariants(){
		
		int varQuantity = 6;
	
		int rightPosition = generatorRightPosition.nextInt(varQuantity-1);
		int maxIndex = dictionary.getAllPairs().length;
		int minIndex = 0;	
			
		List<String> variants = new ArrayList<String>();
		for(int i = 0; i < varQuantity; i++){
			if(i != rightPosition){
				int next = generatorVariant.nextInt(maxIndex);	
				if(isEngRus)
					variants.add(dictionary.getAllPairs()[next].getRussian());
				else
					variants.add(dictionary.getAllPairs()[next].getEnglish());
			}else{
				if(!currentPair.getRussian().trim().equals(""))
					if(isEngRus)
						variants.add(currentPair.getRussian());
					else
						variants.add(currentPair.getEnglish());
				else{
					variants.add("empty");
				}
			}	
		}

		return variants.toArray(new String[varQuantity]);
	}

	public boolean onChuseAnsversVariant(String str) {

		currentPair.incrAttemttsQuantity();
		if(isEngRus){
			if(currentPair.getRussian().equalsIgnoreCase(str) || str.equals("empty")){
				currentPair.incrRightAnswerQuantity();
				//showQuestion();
				return true;			
			}
		}else{
			if(currentPair.getEnglish().equalsIgnoreCase(str) || str.equals("empty")){
				currentPair.incrRightAnswerQuantity();
				//showQuestion();
				return true;			
			}
		}
				
		return false;
	}

	public void onCloseView() {
		//System.out.println("CCC");		
	}
	
	void showQuestion(){
		int eng_rus = generatorENG_RUS.nextInt(2);
		System.out.println(eng_rus);
		if(eng_rus == 1)
			isEngRus = true;
		else 
			isEngRus = false;
		currentPair = getMaxPriorityPair();
		if(currentPair == null){
			view.setVisible(false);			
		}else{
			currentPair.incrAsksQuantity();		
			if(isEngRus)
				view.setPairtForQuastion(currentPair.getEnglish(), currentPair.getTranscription(), getVariants());
			else
				view.setPairtForQuastion(currentPair.getRussian(), "", getVariants());
		}
	}
	
	public void clearResults(){
		for(Pair p : dictionary.getAllPairs()){
			p.clearStatus();
		}
	}

	public void onClearResults() {
		clearResults();
		showQuestion();
	}

	public void onNextReady() {
		new Thread( new Runnable() {
			public void run() {
				try {
					Thread.currentThread().sleep(1000);
					} catch (Exception e) {e.printStackTrace();	}
				showQuestion();
		    }
		}).start();		
	}	
}
