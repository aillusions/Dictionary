package com.myjavaserver.aillusions;

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.myjavaserver.aillusions.audio.AudioManager;

import com.myjavaserver.aillusions.model.Pair;
import com.myjavaserver.aillusions.model.PairDao;
import com.myjavaserver.aillusions.model.PairXmlDao;

public class Dictionary {

	private Pair currentPair;
	private AudioManager audioMan;
	private PairDao pdao = null;
	private String currSample;
	Trainer trainer;

	private static String ALL_WORDS_XSL = "com/myjavaserver/aillusions/TransSheet.xsl";
	private static String INUSE_WORDS_XSL = "com/myjavaserver/aillusions/TransSheetInUse.xsl";
	private static String ALL_SAMPLES_XSL = "com/myjavaserver/aillusions/TransSheetSamples.xsl";
	
	private static String w1 = "c:\\Program Files\\MSOffice\\OFFICE11\\WINWORD.EXE";
	private static String w2 = "c:\\Program Files\\Microsoft Office\\OFFICE11\\WINWORD.EXE";
	
	public void runTrainer() {
		trainer = new Trainer(this);
		trainer.startTraining();
	}

	public AudioManager getAudioMan() {
		return audioMan;
	}

	public Dictionary(String fName) {
		super();
		audioMan = new AudioManager();
		pdao = new PairXmlDao(fName);
	}

	private String getCurentAudioItem() {
		String res = null;
		if (currSample != null) {
			res = (getCurrentPair().getEnglish() + "_" + currSample)
					.replaceAll("\\W", "-");
		} else if (getCurrentPair() != null) {
			res = getCurrentPair().getEnglish().replaceAll("\\W", "-");
		}
		return res;
	}

	public void Load() {
		pdao.load();
		if (getCurrentPair() != null
				&& pdao.getPair(getCurrentPair().getEnglish()) == null) {
			setCurWord(null);
		}
	}

	public void saveInFile() {
		pdao.save();
	}

	public boolean addNew(String s) {
		boolean res = false;
		if (s == null || s.trim().equals("")) {
			s = (String) JOptionPane.showInputDialog(new JFrame("FrameDemo"),
					"Input word please:", "Add new word", 3);
		}
		if ((s != null) && (s.length() > 0)) {
			if (pdao.getPair(s) == null) {
				setCurWord(pdao.addNew(s));
				res = true;
			}
		}
		return res;
	}

	public boolean addNewSample(String s) {
		boolean res = false;
		if (getCurrentPair() == null) {
			JOptionPane.showMessageDialog(null, "Select word before!");
			res = false;
		} else {
			if (s == null || s.trim().equals("")) {
				s = (String) JOptionPane.showInputDialog(
						new JFrame("FrameDemo"), "Input word please:",
						"Add new word", 3);
			}
			if ((s != null) && (s.length() > 0)) {
				// if(getCurrentPair().getSamples() == null)
				// getCurrentPair().setSamples(new LinkedList<String>());
				getCurrentPair().addSample(s);
				res = true;
				currSample = s;
			}
		}
		upCurrentSample();
		return res;
	}

	public boolean setSelected(String engWord) {
		boolean res = false;
		if ((setCurWord(pdao.getPair(engWord))) != null) {
			res = true;
		}
		return res;
	}

	private Pair setCurWord(Pair corr) {
		currentPair = corr;
		currSample = null;
		return currentPair;
	}

	public Pair getCurrentPair() {
		return currentPair;
	}

	public boolean aplyCurrWordChanged(String translate, String transcription) {
		boolean res = false;
		if (getCurrentPair() != null) {
			getCurrentPair().setRussian(translate);
			getCurrentPair().setTranscription(transcription);
			res = true;
		}
		return res;
	}

	public String[] getAllWords() {
		return pdao.getAllEnglish();
	}

	/**
	 * deleting current selected word and all audio samples
	 */
	public void removeCurrent() {
		if (getCurrentPair() != null) {
			audioMan.removeAllTightAudio(getCurrentPair().getEnglish());

			// delete correspondence itself
			Pair near = pdao.remove(getCurrentPair());
			setCurWord(near);
			currSample = null;
		}
	}

	public void renameCurrent(String newName) {
		if (newName == null || newName.trim().equals("")) {
			newName = (String) JOptionPane.showInputDialog(new JFrame(
					"FrameDemo"), "Input word please:", "Rename", 3);
		}

		if ((newName != null) && !(newName.trim().equals(""))) {
			getCurrentPair().setEnglish(newName);
		}
		setCurWord(null);
		currSample = null;
	}

	public void remCurrSample() {
		if (currSample != null) {
			// deleteRecording(getCurAudioItem());
			getCurrentPair().removeSample(currSample);// .getSamples().remove(currSample);
		}
		currSample = null;
	}

	public void setCurrSample(String selectedValue) {
		if (selectedValue == null) {
			currSample = null;
		} else {
			for (String s : getCurrentPair().getSamples()) {
				if (selectedValue.equals(s)) {
					currSample = s;
				}
			}
		}
	}
	
	public void upCurrentSample(){
		if(getCurrSample() != null && !getCurrSample().trim().equals(""))
			getCurrentPair().upSample(getCurrSample());
	}
	
	public void downCurrentSample(){
		getCurrentPair();
	}

	public String getCurrSample() {
		return currSample;
	}

	public void playCurrentAudioRecord(boolean b, boolean onLine){
		if(!onLine){
			String strFilename = null;
			if (getCurentAudioItem() != null) {
				strFilename = getCurentAudioItem();
				audioMan.playRecording(AudioManager.AUDIO_DIR + strFilename,
						AudioManager.AUDIO_EXTs, b);
			}
		}else{
			try{
				
				String thWord= getCurrentPair().getEnglish(); 
				String strFilename = thWord + ".mp3";
				String fPath = AudioManager.AUDIO_ONLINE_TEMP_DIR + strFilename;
		        if(!new File(fPath).exists()){
					URL yahoo = new URL("http://www.english-easy.info/talker/words/" + strFilename);
			        URLConnection yc = yahoo.openConnection();
			        InputStream is = yc.getInputStream();
		
			        FileOutputStream fos = new FileOutputStream(fPath);
			        
					byte[] readBytes = new byte[10000];
					int readQuantity;
					while( (readQuantity = is.read(readBytes)) > 0){
						fos.write(readBytes , 0, readQuantity);					
					}
					is.close();
					fos.flush();
					fos.close();
		        }				
				audioMan.playRecording(fPath);	
				
			}catch(IOException q)
			{}
		}
	}

	public boolean isCurrentItemAlreadyRecorded() {
		return audioMan.isRecordingExist(AudioManager.AUDIO_DIR
				+ getCurentAudioItem(), AudioManager.AUDIO_EXTs);
	}

	public void deleteCurrentAudioRecord() {
		String strFilename = null;
		if (getCurentAudioItem() != null)
			strFilename = getCurentAudioItem();
		audioMan.deleteRecording(AudioManager.AUDIO_DIR + strFilename,
				AudioManager.AUDIO_EXTs);
	}

	public void startRecording(String fWavName) {
		String strFilename = null;
		if (getCurentAudioItem() != null) {
			if (fWavName == null || fWavName.equals(""))
				strFilename = getCurentAudioItem() + ".wav";
			else
				strFilename = fWavName + ".wav";
		}
		audioMan.startRecording(AudioManager.AUDIO_DIR + strFilename);
	}

	public void stopRecording() {
		audioMan.stopRecording();
	}

	public void runWord(boolean onlyInUse, boolean onlySamples) {
		saveInFile();
		File xmlFile = new File("Words.xml");
		//File xsltFile = new File("TransSheet.xsl");
		TransformerFactory transFact = TransformerFactory.newInstance();

		Transformer trans = null;
		try {
			InputStream isr = null;
			if(!onlyInUse){
				isr = this.getClass().getClassLoader().getResourceAsStream(ALL_WORDS_XSL);
			}
			else{
				if(!onlySamples)
					isr = this.getClass().getClassLoader().getResourceAsStream(INUSE_WORDS_XSL);
				else
					isr = this.getClass().getClassLoader().getResourceAsStream(ALL_SAMPLES_XSL);
			}
			if(isr != null)
				trans = transFact.newTransformer(new StreamSource(isr));
			else
				System.out.println(isr);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		FileOutputStream fis = null;
		try {
			fis = new FileOutputStream("print.doc");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			trans.transform(new StreamSource(xmlFile),
					new StreamResult(fis/* System.out */));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			fis.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			if (new File(w1).exists()) {
				Runtime.getRuntime().exec(w1 + " print.doc");
			} else if (new File(w2).exists()) {
				Runtime.getRuntime().exec(w2 + " print.doc");
			} else {
				Runtime.getRuntime().exec("cmd /C print.doc");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public Pair[] getAllInUsePairs(){
		List<Pair> result = new LinkedList<Pair>();
		for(Pair p : pdao.getAllPairs()){
			if(p.isInuse())
				result.add(p);
		}
		return result.toArray(new Pair[result.size()]);
	}
	
	public String[] getAllInUseWords(){
		List<String> result = new LinkedList<String>();
		for(Pair p : pdao.getAllPairs()){
			if(p.isInuse())
				result.add(p.getEnglish());
		}
		return result.toArray(new String[result.size()]);
	}


	public Pair[] getAllPairs() {
		return pdao.getAllPairs();
	}
}
