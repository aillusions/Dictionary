package com.aillusions.dictionary.manager;

import java.util.Collections;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.aillusions.dictionary.model.Pair;

public class PairsManager {
	
	private Manager manager;
	
	private Pair currentPair;
	private String currSample;
	
	public PairsManager(Manager manager){
		this.manager = manager;
	}
	
	public Pair[] getAllPairs() {
		return manager.getWorkspaceManager().getCurrentDictionary().getPairs().toArray(
				new Pair[manager.getWorkspaceManager().getCurrentDictionary().getPairs().size()]);
	}

	public String[] getAllKeys() {
		String[] res = new String[manager.getWorkspaceManager().getCurrentDictionary().getPairs().size()];
		int i = 0;
		for (Pair word : manager.getWorkspaceManager().getCurrentDictionary().getPairs()) {
			res[i] = word.getEnglish();
			i++;
		}

		return res;
	}

	public Pair getPairByKey(String eng) {
		Pair res = null;
		for (Pair word : manager.getWorkspaceManager().getCurrentDictionary().getPairs()) {
			if (word.getEnglish().equals(eng)) {
				res = word;
			}
		}
		return res;
	}

	public boolean aplyCurrWordChanged(String paramString1, String paramString2) {

		if (getCurrentPair() != null) {
			getCurrentPair().setRussian(paramString1);
			getCurrentPair().setTranscription(paramString2);
			return true;
		}
		return false;
	}
	
	public Pair remove(Pair pair) {
		Pair res = null;
		int indexDel = manager.getWorkspaceManager().getCurrentDictionary().getPairs().indexOf(pair);
		int indexSibl = -1;
		manager.getWorkspaceManager().getCurrentDictionary().getPairs().remove(pair);
		manager.getWorkspaceManager().getCurrentDictionary().getTrash().add(pair);
		if (manager.getWorkspaceManager().getCurrentDictionary().getPairs().size() > indexDel) {
			indexSibl = indexDel;
		} else if (manager.getWorkspaceManager().getCurrentDictionary().getPairs().size() > 0
				&& manager.getWorkspaceManager().getCurrentDictionary().getPairs().size() <= indexDel) {
			indexSibl = manager.getWorkspaceManager().getCurrentDictionary().getPairs().size() - 1;
		}
		if (indexSibl != -1)
			res = manager.getWorkspaceManager().getCurrentDictionary().getPairs().get(indexSibl);
		return res;
	}

	public void shuffle() {
		Collections.shuffle(this.manager.getWorkspaceManager().getCurrentDictionary().getPairs());
	}
	
	public boolean addNew(String paramString) {

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
				manager.getWorkspaceManager().getCurrentDictionary().getPairs().add(fw);
			}
			setCurWord(fw);

			return true;
		}
		return false;
	}

	public boolean addNewSample(String paramString) {
		boolean res = false;
		if (getCurrentPair() == null) {
			JOptionPane.showMessageDialog(null, "Select word before!");
			res = false;
		} else {
			if ((paramString == null) || (paramString.trim().equals("")))
				paramString = JOptionPane.showInputDialog(new JFrame(
						"FrameDemo"), "Input word please:", "Add new word", 3);
			if ((paramString != null) && (paramString.length() > 0)) {
				getCurrentPair().addSample(paramString);
				res = true;
				this.currSample = paramString;
			}
		}
		upCurrentSample();
		return res;
	}

	public boolean setSelected(String paramString) {

		if (setCurWord(getPairByKey(paramString)) != null){
			return true;
		}
		return false;
	}
	
	public void removeCurrent() {
		
		if (getCurrentPair() == null){
			return;
		}
		manager.getAudioMan().removeAllTightAudio(getCurrentPair().getEnglish());
		Pair localPair = remove(getCurrentPair());
		setCurWord(localPair);
		this.currSample = null;
	}

	public void renameCurrent(String paramString) {
		
		if ((paramString == null) || (paramString.trim().equals(""))){
			paramString = JOptionPane.showInputDialog(new JFrame("FrameDemo"),	"Input word please:", "Rename", 3);
		}
		
		if ((paramString != null) && (!(paramString.trim().equals("")))	&& (getPairByKey(paramString) == null)) {
			getCurrentPair().setEnglish(paramString);
			setCurWord(getPairByKey(paramString));
		} else {
			JOptionPane.showMessageDialog(new JFrame("Sorry"), "Such word already exists, or empty.");
		}
		this.currSample = null;
	}
	
	public void upCurrentSample() {
		
		if ((getCurrSample() == null) || (getCurrSample().trim().equals(""))){
			return;
		}
		getCurrentPair().upSample(getCurrSample());
	}

	public Pair setCurWord(Pair paramPair) {
		this.currentPair = paramPair;
		this.currSample = null;
		return currentPair;
	}
	
	public void remCurrSample() {
		if (currSample != null)
			getCurrentPair().removeSample(currSample);
		currSample = null;
	}

	public void setCurrSample(String paramString) {
		if (paramString == null)
			currSample = null;
		else
			for (String str : getCurrentPair().getSamples()) {
				if (!(paramString.equals(str))){
					continue;
				}
				currSample = str;
			}
	}
	
	public Pair[] getAllInUsePairs() {
		LinkedList<Pair> localLinkedList = new LinkedList<Pair>();
		for (Pair localPair : getAllPairs()) {
			if (!(localPair.isInuse()))
				continue;
			localLinkedList.add(localPair);
		}
		return ((Pair[]) localLinkedList.toArray(new Pair[localLinkedList
				.size()]));
	}

	public String[] getAllInUseWords() {
		LinkedList<String> localLinkedList = new LinkedList<String>();
		for (Pair localPair : getAllPairs()) {
			if (!(localPair.isInuse()))
				continue;
			localLinkedList.add(localPair.getEnglish());
		}
		return ((String[]) localLinkedList.toArray(new String[localLinkedList
				.size()]));
	}
	
	public Pair getCurrentPair() {
		return this.currentPair;
	}
	
	public String getCurrSample() {
		return currSample;
	}
}
