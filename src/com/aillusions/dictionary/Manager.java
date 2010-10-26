package com.aillusions.dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.aillusions.dictionary.audio.AudioManager;
import com.aillusions.dictionary.dao.PairDao;
import com.aillusions.dictionary.dao.PairXmlDao;
import com.aillusions.dictionary.model.Pair;

public class Manager {
	private Pair currentPair;
	private AudioManager audioMan = new AudioManager();
	private PairDao pdao = null;
	private String currSample;
	Trainer trainer;
	private static String ALL_WORDS_XSL = "com/aillusions/dictionary/TransSheet.xsl";
	private static String INUSE_WORDS_XSL = "com/aillusions/dictionary/TransSheetInUse.xsl";
	private static String ALL_SAMPLES_XSL = "com/aillusions/dictionary/TransSheetSamples.xsl";
	private static String w1 = "c:\\Program Files\\MSOffice\\OFFICE11\\WINWORD.EXE";
	private static String w2 = "c:\\Program Files\\Microsoft Office\\OFFICE11\\WINWORD.EXE";

	public void runTrainer() {
		this.trainer = new Trainer(this);
		this.trainer.startTraining();
	}

	public AudioManager getAudioMan() {
		return this.audioMan;
	}

	public Manager(String paramString) {
		this.pdao = new PairXmlDao(paramString);
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
		this.pdao.load();
		if ((getCurrentPair() == null)
				|| (this.pdao.getPair(getCurrentPair().getEnglish()) != null))
			return;
		setCurWord(null);
	}

	public void saveInFile() {
		this.pdao.save();
	}

	public boolean addNew(String paramString) {
		int i = 0;
		if ((paramString == null) || (paramString.trim().equals("")))
			paramString = JOptionPane.showInputDialog(new JFrame("FrameDemo"),
					"Input word please:", "Add new word", 3);
		if ((paramString != null) && (paramString.length() > 0)
				&& (this.pdao.getPair(paramString) == null)) {
			setCurWord(this.pdao.addNew(paramString));
			i = 1;
		}
		return getBool(i);
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
		if (setCurWord(this.pdao.getPair(paramString)) != null)
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
		return this.pdao.getAllEnglish();
	}

	public void removeCurrent() {
		if (getCurrentPair() == null)
			return;
		this.audioMan.removeAllTightAudio(getCurrentPair().getEnglish());
		Pair localPair = this.pdao.remove(getCurrentPair());
		setCurWord(localPair);
		this.currSample = null;
	}

	public void renameCurrent(String paramString) {
		if ((paramString == null) || (paramString.trim().equals("")))
			paramString = JOptionPane.showInputDialog(new JFrame("FrameDemo"),
					"Input word please:", "Rename", 3);
		if ((paramString != null) && (!(paramString.trim().equals("")))
				&& (this.pdao.getPair(paramString) == null)) {
			getCurrentPair().setEnglish(paramString);
			setCurWord(this.pdao.getPair(paramString));
		} else {
			JOptionPane.showMessageDialog(new JFrame("Sorry"),
					"Such word already exists, or empty.");
		}
		this.currSample = null;
	}

	public void remCurrSample() {
		if (this.currSample != null)
			getCurrentPair().removeSample(this.currSample);
		this.currSample = null;
	}

	public void setCurrSample(String paramString) {
		if (paramString == null)
			this.currSample = null;
		else
			for (String str : getCurrentPair().getSamples()) {
				if (!(paramString.equals(str)))
					continue;
				this.currSample = str;
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

	public String getCurrSample() {
		return this.currSample;
	}

	public void playCurrentAudioRecord(boolean paramBoolean1,
			boolean paramBoolean2) {
		String str1;
		if (!(paramBoolean2)) {
			str1 = null;
			if (getCurentAudioItem() != null) {
				str1 = getCurentAudioItem();
				this.audioMan.playRecording("audio/" + str1,
						AudioManager.AUDIO_EXTs, paramBoolean1);
			}
		} else {
			try {
				str1 = getCurrentPair().getEnglish();
				String str2 = str1 + ".mp3";
				String str3 = "audio/online/" + str2;
				if (!(new File(str3).exists())) {
					URL localURL = new URL(
							"http://www.english-easy.info/talker/words/" + str2);
					URLConnection localURLConnection = localURL
							.openConnection();
					InputStream localInputStream = localURLConnection
							.getInputStream();
					FileOutputStream localFileOutputStream = new FileOutputStream(
							str3);
					byte[] arrayOfByte = new byte[10000];
					int i;
					while ((i = localInputStream.read(arrayOfByte)) > 0) {
						localFileOutputStream.write(arrayOfByte, 0, i);
					}
					localInputStream.close();
					localFileOutputStream.flush();
					localFileOutputStream.close();
				}
				this.audioMan.playRecording(str3);
			} catch (IOException localIOException) {
			}
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
		if (getCurentAudioItem() != null)
			if ((paramString == null) || (paramString.equals("")))
				str = getCurentAudioItem() + ".wav";
			else
				str = paramString + ".wav";
		this.audioMan.startRecording("audio/" + str);
	}

	public void stopRecording() {
		this.audioMan.stopRecording();
	}

	public void runWord(boolean paramBoolean1, boolean paramBoolean2) {
		saveInFile();
		File localFile = new File("Words.xml");
		TransformerFactory localTransformerFactory = TransformerFactory
				.newInstance();
		Transformer localTransformer = null;
		try {
			InputStream localInputStream = null;
			if (!(paramBoolean1))
				localInputStream = super.getClass().getClassLoader().getResourceAsStream(ALL_WORDS_XSL);
			else if (!(paramBoolean2))
				localInputStream = super.getClass().getClassLoader().getResourceAsStream(INUSE_WORDS_XSL);
			else
				localInputStream = super.getClass().getClassLoader().getResourceAsStream(ALL_SAMPLES_XSL);
			if (localInputStream != null)
				localTransformer = localTransformerFactory.newTransformer(new StreamSource(localInputStream));
			else
				System.out.println(localInputStream);
		} catch (TransformerConfigurationException localTransformerConfigurationException) {
			localTransformerConfigurationException.printStackTrace();
		}
		FileOutputStream localFileOutputStream = null;
		try {
			localFileOutputStream = new FileOutputStream("print.doc");
		} catch (FileNotFoundException localFileNotFoundException) {
			localFileNotFoundException.printStackTrace();
		}
		try {
			localTransformer.transform(new StreamSource(localFile),	new StreamResult(localFileOutputStream));
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		try {
			localFileOutputStream.close();
		} catch (IOException localIOException1) {
			localIOException1.printStackTrace();
		}
		try {
			if (new File(w1).exists())
				Runtime.getRuntime().exec(w1 + " print.doc");
			else if (new File(w2).exists())
				Runtime.getRuntime().exec(w2 + " print.doc");
			else
				Runtime.getRuntime().exec("cmd /C print.doc");
		} catch (IOException localIOException2) {
			localIOException2.printStackTrace();
		}
	}

	public Pair[] getAllInUsePairs() {
		LinkedList localLinkedList = new LinkedList();
		for (Pair localPair : this.pdao.getAllPairs()) {
			if (!(localPair.isInuse()))
				continue;
			localLinkedList.add(localPair);
		}
		return ((Pair[]) localLinkedList.toArray(new Pair[localLinkedList
				.size()]));
	}

	public String[] getAllInUseWords() {
		LinkedList localLinkedList = new LinkedList();
		for (Pair localPair : this.pdao.getAllPairs()) {
			if (!(localPair.isInuse()))
				continue;
			localLinkedList.add(localPair.getEnglish());
		}
		return ((String[]) localLinkedList.toArray(new String[localLinkedList
				.size()]));
	}

	public Pair[] getAllPairs() {
		return this.pdao.getAllPairs();
	}

	public void shuffle() {
		this.pdao.shuffle();
	}
}

/*
 * Location: E:\ATG\workspace\Dictionary.jar Qualified Name:
 * com.myjavaserver.aillusions.Dictionary Java Class Version: 5 (49.0) JD-Core
 * Version: 0.5.3
 */