package com.myjavaserver.aillusions.audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ConcurrentPlayer implements Runnable {

	private String fName = null;
	SourceDataLine line = null;
	private static final int EXTERNAL_BUFFER_SIZE = 128000;

	public String getFName() {
		return fName;
	}

	public void setFName(String name) {
		fName = name;
	}

	public static void playRecording(String itemName, boolean accync){
		String fName = itemName;
		
		System.out.print("playing: " + itemName + " Result: ");

		if (fName != null) {
			if (accync) {
				ConcurrentPlayer player = new ConcurrentPlayer();
				player.setFName(fName);
				new Thread(player).start();
				System.out.println(fName + " successfully played. ");
			} else {
				ConcurrentPlayer player = new ConcurrentPlayer();
				player.setFName(fName);
				player.run();
				// new Thread(player).start();
				System.out.println(fName + " successfully played. ");
			}
		} else {
			System.out.println(itemName + ".wav or " + itemName
					+ ".mp3 does not exists");
		}
		
	}
	public void run() {

		if (fName != null && !fName.equals("")) {
			if (fName.toLowerCase().indexOf(".wav".toLowerCase(),
					fName.length() - 5) != -1) {
				File soundFile = new File(fName);

				AudioInputStream audioInputStream = null;
				try {
					audioInputStream = AudioSystem
							.getAudioInputStream(soundFile);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
				AudioFormat audioFormat = audioInputStream.getFormat();

				DataLine.Info info = new DataLine.Info(SourceDataLine.class,
						audioFormat);
				try {
					line = (SourceDataLine) AudioSystem.getLine(info);
					line.open(audioFormat);
				} catch (LineUnavailableException e) {
					e.printStackTrace();
					System.exit(1);
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}

				line.start();

				int nBytesRead = 0;
				byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
				while (nBytesRead != -1) {
					try {
						nBytesRead = audioInputStream.read(abData, 0,
								abData.length);
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (nBytesRead >= 0) {
						line.write(abData, 0, nBytesRead);
					}
				}
				try {
					audioInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				line.drain();
				line.close();
				line = null;
			} else if (fName.toLowerCase().indexOf(".mp3".toLowerCase(),
					fName.length() - 5) != -1) {
				MP3Player mp3Player = new MP3Player(fName);
				mp3Player.play();
			} else {

				JOptionPane.showMessageDialog(new JFrame("FrameDemo"),
						"Unsupperted file format: "
								+ fName.substring(fName.lastIndexOf(".")));

			}

		}
	}

	public static void main(String args[]) {
		(new Thread(new ConcurrentPlayer())).start();
	}

}