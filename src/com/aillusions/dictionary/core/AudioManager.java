package com.aillusions.dictionary.core;

import java.io.File;
import java.io.FilenameFilter;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

import com.aillusions.dictionary.audio.ConcurrentPlayer;
import com.aillusions.dictionary.audio.SimpleAudioRecorder;

public class AudioManager {
	
	public SimpleAudioRecorder recorder = null;
	SourceDataLine line = null;
	
	private boolean playOnSelections;

	private Manager manager;
	
	public static final String AUDIO_DIR = "audio/";
	public static final String AUDIO_ONLINE_TEMP_DIR = "audio/online/";
	public static final String[] AUDIO_EXTs = new String[]{".wav" , ".mp3"};
	
	public AudioManager(Manager manager){
		this.manager = manager;
	}
	
	public boolean isPlayOnSelections() {
		return playOnSelections;
	}
	
	public void setPlayOnSelections(boolean playOnSelections) {
		this.playOnSelections = playOnSelections;
	}
	
	private String getCurentAudioItem() {
		String str = null;
		CurrentStateManager cpm = manager.getCurrentStateManager();
		if (cpm.getCurrSample() != null)
			str = cpm.getCurrentPair().getEnglish() + "_"
					+ cpm.getCurrSample().replaceAll("\\W", "-");
		else if (cpm.getCurrentPair() != null)
			str = cpm.getCurrentPair().getEnglish().replaceAll("\\W",
					"-");
		return str;
	}
	
	public void playCurrentAudioRecord() {

		String str1;
		str1 = null;
		if (getCurentAudioItem() != null) {
			str1 = getCurentAudioItem();
			playRecording("audio/" + str1,
					AudioManager.AUDIO_EXTs, true);
		}
	}

	public boolean isCurrentItemAlreadyRecorded() {
		return isRecordingExist("audio/" + getCurentAudioItem(),
				AudioManager.AUDIO_EXTs);
	}

	public void deleteCurrentAudioRecord() {
		String str = null;
		if (getCurentAudioItem() != null)
			str = getCurentAudioItem();
		deleteRecording("audio/" + str, AudioManager.AUDIO_EXTs);
	}

	public void startRecording(String paramString) {
		String str = null;
		if (getCurentAudioItem() != null) {
			if ((paramString == null) || (paramString.equals(""))) {
				str = getCurentAudioItem() + ".wav";
			} else {
				str = paramString + ".wav";
			}
		}
		startRecording_("audio/" + str);
	}

	
	public void removeAllTightAudio(final String eng) {
		// deleting audio files
		new File(AUDIO_DIR).list(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				if (name.length() > eng.length()
						&& name.substring(0, eng.length()).equals(eng))
					new File(name).delete();
				return false;
			}
		});
	}

	
	public boolean isRecordingExist(String audioItem, String[] exts) {
		boolean res = false;
		if (audioItem != null && exts != null) {
			for(String s : exts){
				String strFilename = audioItem + s;
				if (new File(strFilename).exists()) 
					res = true;
			}			
		}
		return res;
	}

	/**
	 * 
	 * @param fName name of file without extension
	 * @param extensions - array of possible extensions
	 */
	public void deleteRecording(String cutfName, String[] extensions) {
		String fName = null;
		if (cutfName != null && !cutfName.equals("") && extensions!=null && extensions.length > 0  ) {
			if(cutfName.indexOf(".")!= -1){
				throw new IllegalArgumentException("File name should be without extension.");
			}
			for(String ext : extensions){
				fName = cutfName + ext;
				if (new File(fName).exists())
					new File(fName).delete();
			}
		}
	}

	/**
	 * 
	 * @param fName name of file
	 */
	public void deleteRecording(String fName) {
		if (fName != null && !fName.equals("")) {
			System.out.print("Deleting: " + fName  );
			if (new File(fName).exists()) {
				System.out.println(" Result: " + new File(fName).delete());
			}
		}
	}
	
	public boolean startRecording_(String fWavName) {
		boolean res = false;
		if (fWavName != null && !fWavName.equals("")) {

			System.out.print("recording: " + fWavName + " Result: ");
			if (new File(fWavName).exists()) {
				System.out.println(" alredy exists. ");
			} else {
				System.out.print(" pause 1 sec ..");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

				File outputFile = new File(fWavName);

				AudioFormat audioFormat = new AudioFormat(
						AudioFormat.Encoding.PCM_SIGNED, 44100.0F, 16, 2, 4,
						44100.0F, false);

				DataLine.Info info = new DataLine.Info(TargetDataLine.class,
						audioFormat);
				TargetDataLine targetDataLine = null;
				try {
					targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
					targetDataLine.open(audioFormat);
				} catch (LineUnavailableException e) {
					System.out.println("unable to get a recording line");
					e.printStackTrace();
					System.exit(1);
				}

				AudioFileFormat.Type targetType = AudioFileFormat.Type.WAVE;

				recorder = new SimpleAudioRecorder(targetDataLine, targetType,
						outputFile);

				System.out.print(" recording.. ");
				recorder.start();

				res = true;
			}
		}
		return res;
	}

	public void stopRecording() {
		recorder.stopRecording();
		recorder = null;
		System.out.println(" stopped successfull");
	}

	public void playRecording(String path, String[] extensions, boolean accync) {
		String fName = null;
		if (path != null && line == null && recorder == null && extensions!=null && extensions.length > 0  ) {
			for(String ext : extensions){
				fName = path + ext;
				if (new File(fName).exists())
					ConcurrentPlayer.playRecording(fName, accync);
			}
		}
	}	
	
	public void playRecording(String path) {
		if (path != null && line == null && recorder == null   ) {
			if (new File(path).exists())
				ConcurrentPlayer.playRecording(path, true);
		}
	}
	
}
