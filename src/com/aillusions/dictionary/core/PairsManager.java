package com.aillusions.dictionary.core;

import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.aillusions.dictionary.model.Pair;

public class PairsManager {
	
	private Manager manager;
	
	public PairsManager(Manager manager){
		this.manager = manager;
	}
	
	public  List<Pair> getAllPairs(){
		return manager.getCurrentStateManager().getCurrentDictionary().getPairs();
	}

	public String[] getAllKeys() {
		String[] res = new String[getAllPairs().size()];
		int i = 0;
		for (Pair word : getAllPairs()) {
			res[i] = word.getWord();
			i++;
		}

		return res;
	}

	public Pair getPairByKey(String eng) {
		Pair res = null;
		for (Pair word : getAllPairs()) {
			if (word.getWord().equals(eng)) {
				res = word;
			}
		}
		return res;
	}
	
	public Pair removePair(Pair pair) {
		Pair res = null;
		int indexDel = getAllPairs().indexOf(pair);
		int indexSibl = -1;
		getAllPairs().remove(pair);
		manager.getTrashManager().addToTrash(pair);
		if (getAllPairs().size() > indexDel) {
			indexSibl = indexDel;
		} else if (getAllPairs().size() > 0
				&& getAllPairs().size() <= indexDel) {
			indexSibl = getAllPairs().size() - 1;
		}
		if (indexSibl != -1)
			res = getAllPairs().get(indexSibl);
		return res;
	}

	public void shuffle() {
		Collections.shuffle(getAllPairs());
	}
	
	public boolean addNewKey(String paramString) {

		if ((paramString == null) || (paramString.trim().equals(""))){
			
			paramString = JOptionPane.showInputDialog(new JFrame("FrameDemo"), "Input word please:", "Add new word", 3);
		}
		
		if ((paramString != null) && (paramString.length() > 0)) {

			Pair fw = null;
			if (paramString != null) {
				fw = new Pair();
				fw.setWord(paramString);
				fw.setTranscription("");
				fw.setTranscription("");
				return addNew(fw);
			}
	
			return true;
		}
		return false;
	}

	public boolean addNew(Pair pair){
		
		if(getPairByKey(pair.getWord()) == null){
			
			if(manager.getTrashManager().getPairByKey(pair.getWord()) == null){
				boolean res = getAllPairs().add(pair);
				manager.getCurrentStateManager().setCurrentPair(pair);
				return res;
			}else{
				Pair pairTemp = manager.getTrashManager().getPairByKey(pair.getWord());
				boolean res = manager.getTrashManager().restorePair(pairTemp);
				manager.getCurrentStateManager().setCurrentPair(pairTemp);
				return res;
			}
		}else{
			manager.getCurrentStateManager().setCurrentPair(pair);
		}
		return true;
	}
	
	public boolean addNewSample(String paramString) {
		
		boolean res = false;
		if (manager.getCurrentStateManager().getCurrentPair() == null) {
			JOptionPane.showMessageDialog(null, "Select word before!");
			res = false;
		} else {
			if ((paramString == null) || (paramString.trim().equals("")))
				paramString = JOptionPane.showInputDialog(new JFrame(
						"FrameDemo"), "Input word please:", "Add new word", 3);
			if ((paramString != null) && (paramString.length() > 0)) {
				manager.getCurrentStateManager().getCurrentPair().addSample(paramString);
				res = true;
				manager.getCurrentStateManager().setCurrSample(paramString);
			}
		}
		manager.getCurrentStateManager().upCurrentSample();
		return res;
	}



}
