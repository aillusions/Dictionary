package com.aillusions.dictionary.core;

import java.util.Collections;
import java.util.LinkedList;
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
		return manager.getWorkspaceManager().getCurrentDictionary().getPairs();
	}

	public String[] getAllKeys() {
		String[] res = new String[getAllPairs().size()];
		int i = 0;
		for (Pair word : getAllPairs()) {
			res[i] = word.getEnglish();
			i++;
		}

		return res;
	}

	public Pair getPairByKey(String eng) {
		Pair res = null;
		for (Pair word : getAllPairs()) {
			if (word.getEnglish().equals(eng)) {
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

		if ((paramString == null) || (paramString.trim().equals("")))
			paramString = JOptionPane.showInputDialog(new JFrame("FrameDemo"),
					"Input word please:", "Add new word", 3);
		if ((paramString != null) && (paramString.length() > 0)
				&& (getPairByKey(paramString) == null)) {

			Pair fw = null;
			if (paramString != null) {
				fw = new Pair();
				fw.setEnglish(paramString);
				fw.setTranscription("");
				fw.setTranscription("");
				addNew(fw);
			}
			manager.getCurrentPairManager().setCurrentPair(fw);

			return true;
		}
		return false;
	}

	public boolean addNew(Pair pair){
		return getAllPairs().add(pair);
	}
	
	public boolean addNewSample(String paramString) {
		
		boolean res = false;
		if (manager.getCurrentPairManager().getCurrentPair() == null) {
			JOptionPane.showMessageDialog(null, "Select word before!");
			res = false;
		} else {
			if ((paramString == null) || (paramString.trim().equals("")))
				paramString = JOptionPane.showInputDialog(new JFrame(
						"FrameDemo"), "Input word please:", "Add new word", 3);
			if ((paramString != null) && (paramString.length() > 0)) {
				manager.getCurrentPairManager().getCurrentPair().addSample(paramString);
				res = true;
				manager.getCurrentPairManager().setCurrSample(paramString);
			}
		}
		manager.getCurrentPairManager().upCurrentSample();
		return res;
	}

	public LinkedList<Pair> getAllInUsePairs() {
		
		LinkedList<Pair> localLinkedList = new LinkedList<Pair>();
		for (Pair localPair : getAllPairs()) {
			if (!(localPair.isInuse())){
				continue;
			}
			localLinkedList.add(localPair);
		}
		return localLinkedList;
	}

	public String[] getAllInUseKeys() {
		
		LinkedList<String> localLinkedList = new LinkedList<String>();
		
		for (Pair localPair : getAllInUsePairs()) {
			localLinkedList.add(localPair.getEnglish());
		}
		
		return ((String[]) localLinkedList.toArray(new String[localLinkedList.size()]));
	}
	

}
