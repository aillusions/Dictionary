package com.aillusions.dictionary.audio;


import java.io.IOException;
import java.io.File;

import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.AudioFileFormat;

import com.aillusions.dictionary.audio.SimpleAudioRecorder;

public class SimpleAudioRecorder extends Thread
{
	private TargetDataLine		m_line;
	private AudioFileFormat.Type	m_targetType;
	private AudioInputStream	m_audioInputStream;
	private File			m_outputFile;


	public SimpleAudioRecorder(TargetDataLine line,
				     AudioFileFormat.Type targetType,
				     File file)
	{
		m_line = line;
		m_audioInputStream = new AudioInputStream(line);
		m_targetType = targetType;
		m_outputFile = file;
	}


	public void start()
	{

		m_line.start();


		super.start();
	}



	public void stopRecording()
	{
		m_line.stop();
		m_line.close();
	}



	public void run()
	{
			try
			{
				AudioSystem.write(
					m_audioInputStream,
					m_targetType,
					m_outputFile);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
	}

	public static void main(String[] args)
	{
		if (args.length != 1 || args[0].equals("-h"))
		{
			printUsageAndExit();
		}

		String	strFilename = args[0];
		File	outputFile = new File(strFilename);

		AudioFormat	audioFormat = new AudioFormat(
			AudioFormat.Encoding.PCM_SIGNED,
			44100.0F, 16, 2, 4, 44100.0F, false);

		DataLine.Info	info = new DataLine.Info(TargetDataLine.class, audioFormat);
		TargetDataLine	targetDataLine = null;
		try
		{
			targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
			targetDataLine.open(audioFormat);
		}
		catch (LineUnavailableException e)
		{
			out("unable to get a recording line");
			e.printStackTrace();
			System.exit(1);
		}

		AudioFileFormat.Type	targetType = AudioFileFormat.Type.WAVE;

		SimpleAudioRecorder	recorder = new SimpleAudioRecorder(
			targetDataLine,
			targetType,
			outputFile);

		out("Press ENTER to start the recording.");
		try
		{
			System.in.read();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		recorder.start();
		out("Recording...");

		out("Press ENTER to stop the recording.");
		try
		{
			System.in.read();
			System.in.read();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		recorder.stopRecording();
		out("Recording stopped.");
	}



	private static void printUsageAndExit()
	{
		out("SimpleAudioRecorder: usage:");
		out("\tjava SimpleAudioRecorder -h");
		out("\tjava SimpleAudioRecorder <audiofile>");
		System.exit(0);
	}



	private static void out(String strMessage)
	{
		System.out.println(strMessage);
	}
}
