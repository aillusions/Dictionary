package com.aillusions.dictionary;

import java.util.Collections;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import com.aillusions.dictionary.audio.AudioManager;
import com.aillusions.dictionary.dao.XmlStorage;
import com.aillusions.dictionary.model.Dictionary;
import com.aillusions.dictionary.model.Pair;
import com.aillusions.dictionary.model.Workspace;
import com.aillusions.dictionary.xsl.DocConverter;

public class Manager {

	private Workspace workspace;
	private Dictionary currentDictionary;
	private Pair currentPair;
	private AudioManager audioMan = new AudioManager();
	private XmlStorage pdao = null;
	private String currSample;
	private Trainer trainer;
	private boolean playOnSelections;

	public Manager(String paramString) {
		this.pdao = new XmlStorage(paramString);
	}

	public void setCurrentDict(Dictionary dict) {
		if (dict == null) {
			throw new RuntimeException("Dictionary can not be null.");
		}
		if (workspace.getDictioanries().contains(dict)) {
			currentDictionary = dict;
		}
	}

	public Pair[] getAllPairs() {
		return currentDictionary.getPairs().toArray(
				new Pair[currentDictionary.getPairs().size()]);
	}

	public String[] getAllEnglish() {
		String[] res = new String[currentDictionary.getPairs().size()];
		int i = 0;
		for (Pair word : currentDictionary.getPairs()) {
			res[i] = word.getEnglish();
			i++;
		}

		return res;
	}

	public Pair getPair(String eng) {
		Pair res = null;
		for (Pair word : currentDictionary.getPairs()) {
			if (word.getEnglish().equals(eng)) {
				res = word;
			}
		}
		return res;
	}

	public Pair remove(Pair pair) {
		Pair res = null;
		int indexDel = currentDictionary.getPairs().indexOf(pair);
		int indexSibl = -1;
		currentDictionary.getPairs().remove(pair);
		currentDictionary.getTrash().add(pair);
		if (currentDictionary.getPairs().size() > indexDel) {
			indexSibl = indexDel;
		} else if (currentDictionary.getPairs().size() > 0
				&& currentDictionary.getPairs().size() <= indexDel) {
			indexSibl = currentDictionary.getPairs().size() - 1;
		}
		if (indexSibl != -1)
			res = currentDictionary.getPairs().get(indexSibl);
		return res;
	}

	public void shuffle() {
		Collections.shuffle(this.currentDictionary.getPairs());
	}

	public void runTrainer() {
		this.trainer = new Trainer(this);
		this.trainer.startTraining();
	}

	private String getCurentAudioItem() {
		String str = null;
		if (this.currSample != null)
			str = getCurrentPair().getEnglish() + "_"
					+ this.currSample.replaceAll("\\W", "-");
		else if (getCurrentPair() != null)
			str = getCurrentPair().getEnglish().replaceAll("\\W", "-");
		return str;
	}

	public void Load() {

		workspace = pdao.load();

		if (workspace.getDictioanries().size() > 0) {
			currentDictionary = workspace.getDictioanries().get(0);
		} else {
			while(!addNewDictionary()){
				//Have to specify at least one dictionary!
			}
		}		
		
		if ((getCurrentPair() == null)	|| (getPair(getCurrentPair().getEnglish()) != null)){
			return;
		}
		
		setCurWord(null);
	}

	public void saveInFile() {
		this.pdao.save(workspace);
	}

	public boolean addNew(String paramString) {
		int i = 0;
		if ((paramString == null) || (paramString.trim().equals("")))
			paramString = JOptionPane.showInputDialog(new JFrame("FrameDemo"),
					"Input word please:", "Add new word", 3);
		if ((paramString != null) && (paramString.length() > 0)
				&& (getPair(paramString) == null)) {

			Pair fw = null;
			if (paramString != null) {
				fw = new Pair();
				fw.setEnglish(paramString);
				fw.setTranscription("");
				fw.setTranscription("");
				currentDictionary.getPairs().add(fw);
			}
			setCurWord(fw);

			i = 1;
		}
		return getBool(i);
	}

	public void selectDictionary(String name) {
		setCurrentDict(getDictionaryByName(name));
	}

	public boolean addNewDictionary() {

		String paramString = null;
		
		if ((paramString == null) || (paramString.trim().equals(""))){
			paramString = JOptionPane.showInputDialog(new JFrame("FrameDemo"), "Input dictionary name:", "Create new dictionary", 3);
		}
		
		if ((paramString != null) && (paramString.length() > 0)	&& (getDictionaryByName(paramString) == null)) {
			Dictionary res = new Dictionary();
			res.setDisplayName(paramString);
			workspace.getDictioanries().add(res);
			currentDictionary = res;
			return true;
		}else{
			return false;
		}
	}

	private boolean getBool(int i) {
		return i > 0 ? true : false;
	}

	public boolean addNewSample(String paramString) {
		int i = 0;
		if (getCurrentPair() == null) {
			JOptionPane.showMessageDialog(null, "Select word before!");
			i = 0;
		} else {
			if ((paramString == null) || (paramString.trim().equals("")))
				paramString = JOptionPane.showInputDialog(new JFrame(
						"FrameDemo"), "Input word please:", "Add new word", 3);
			if ((paramString != null) && (paramString.length() > 0)) {
				getCurrentPair().addSample(paramString);
				i = 1;
				this.currSample = paramString;
			}
		}
		upCurrentSample();
		return getBool(i);
	}

	public boolean setSelected(String paramString) {
		int i = 0;
		if (setCurWord(getPair(paramString)) != null)
			i = 1;
		return getBool(i);
	}

	private Pair setCurWord(Pair paramPair) {
		this.currentPair = paramPair;
		this.currSample = null;
		return this.currentPair;
	}

	public Pair getCurrentPair() {
		return this.currentPair;
	}

	public boolean aplyCurrWordChanged(String paramString1, String paramString2) {
		int i = 0;
		if (getCurrentPair() != null) {
			getCurrentPair().setRussian(paramString1);
			getCurrentPair().setTranscription(paramString2);
			i = 1;
		}
		return getBool(i);
	}

	public String[] getAllWords() {
		return getAllEnglish();
	}

	public void removeCurrent() {
		if (getCurrentPair() == null)
			return;
		this.audioMan.removeAllTightAudio(getCurrentPair().getEnglish());
		Pair localPair = remove(getCurrentPair());
		setCurWord(localPair);
		this.currSample = null;
	}

	public void renameCurrent(String paramString) {
		if ((paramString == null) || (paramString.trim().equals("")))
			paramString = JOptionPane.showInputDialog(new JFrame("FrameDemo"),
					"Input word please:", "Rename", 3);
		if ((paramString != null) && (!(paramString.trim().equals("")))
				&& (getPair(paramString) == null)) {
			getCurrentPair().setEnglish(paramString);
			setCurWord(getPair(paramString));
		} else {
			JOptionPane.showMessageDialog(new JFrame("Sorry"),
					"Such word already exists, or empty.");
		}
		this.currSample = null;
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

	public void upCurrentSample() {
		if ((getCurrSample() == null) || (getCurrSample().trim().equals("")))
			return;
		getCurrentPair().upSample(getCurrSample());
	}

	public void downCurrentSample() {
		getCurrentPair();
	}

	public void playCurrentAudioRecord() {

		String str1;
		str1 = null;
		if (getCurentAudioItem() != null) {
			str1 = getCurentAudioItem();
			this.audioMan.playRecording("audio/" + str1,
					AudioManager.AUDIO_EXTs, true);
		}
	}

	public boolean isCurrentItemAlreadyRecorded() {
		return this.audioMan.isRecordingExist("audio/" + getCurentAudioItem(),
				AudioManager.AUDIO_EXTs);
	}

	public void deleteCurrentAudioRecord() {
		String str = null;
		if (getCurentAudioItem() != null)
			str = getCurentAudioItem();
		this.audioMan.deleteRecording("audio/" + str, AudioManager.AUDIO_EXTs);
	}

	public void startRecording(String paramString) {
		String str = null;
		if (getCurentAudioItem() != null){
			if ((paramString == null) || (paramString.equals(""))){
				str = getCurentAudioItem() + ".wav";
			}
			else{
				str = paramString + ".wav";
			}
		}
		this.audioMan.startRecording("audio/" + str);
	}

	public void stopRecording() {
		this.audioMan.stopRecording();
	}

	public void runWord(boolean paramBoolean1, boolean paramBoolean2) {
		saveInFile();
		new DocConverter().runWord(paramBoolean1, paramBoolean2);
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
	
	private Dictionary getDictionaryByName(String dictName) {
		Dictionary res = null;
		for (Dictionary dict : workspace.getDictioanries()) {
			if (dict.getDisplayName().equals(dictName)) {
				res = dict;
			}
		}
		return res;
	}
	
	public boolean isPlayOnSelections() {
		return playOnSelections;
	}

	public void setPlayOnSelections(boolean playOnSelections) {
		this.playOnSelections = playOnSelections;
	}

	public AudioManager getAudioMan() {
		return audioMan;
	}
	
	public Workspace getWorkspace() {
		return workspace;
	}

	public Dictionary getCurrentDictionary() {
		return currentDictionary;
	}
	
	public String getCurrSample() {
		return currSample;
	}

}
