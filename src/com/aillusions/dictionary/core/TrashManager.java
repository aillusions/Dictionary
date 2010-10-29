package com.aillusions.dictionary.core;

import java.util.List;

import com.aillusions.dictionary.model.Pair;

public class TrashManager {

	private Manager manager;
	
	public TrashManager(Manager manager){
		this.manager = manager;
	}
	
	public boolean addToTrash(Pair pair){
		return getAllTrashPairs().add(pair);
	}
	
	public boolean removeFromTrash(Pair pair){
		return getAllTrashPairs().remove(pair);
	}
	
	public List<Pair> getAllTrashPairs(){
		return manager.getWorkspaceManager().getCurrentDictionary().getTrash();
	}
	
	public String[] getAllKeys() {
		String[] res = new String[getAllTrashPairs().size()];
		int i = 0;
		for (Pair word : getAllTrashPairs()) {
			res[i] = word.getEnglish();
			i++;
		}

		return res;
	}
	
	public boolean restorePair(Pair pair){
		getAllTrashPairs().remove(pair);
		manager.getPairsManager().addNew(pair);
		return true;
	}

}
