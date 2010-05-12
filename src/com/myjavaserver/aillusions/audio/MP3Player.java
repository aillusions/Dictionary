package com.myjavaserver.aillusions.audio;

import javazoom.jl.player.*;

import java.io.*;

public class MP3Player {
	


	private Player player;
	private InputStream is;

	/** Creates a new instance of MP3Player */
	public MP3Player(String filename) {
		try {
			// Create an InputStream to the file
			is = new FileInputStream(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void play() {
		try {
			player = new Player(is);
			PlayerThread pt = new PlayerThread();
			pt.start();
			while (!player.isComplete()) {
				int position = player.getPosition();
				//System.out.println("Position: " + position);
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class PlayerThread extends Thread {
		public void run() {
			try {
				player.play();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader r = null;
		if (args.length == 0) {
			//System.out.println("Usage: MP3Player <filename>");

			r = new BufferedReader(new InputStreamReader(System.in));
		}

		MP3Player mp3Player = new MP3Player(r.readLine());
		mp3Player.play();
	}
}