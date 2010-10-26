package com.aillusions.dictionary.view.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.aillusions.dictionary.view.listener.MainDictPanelListener;

public class AudioPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public JButton jButtPause;
	public JButton jButtPlay;
	public JButton jButtDeleteRecord;
	public JButton jButtRecordding;
	
	public AudioPanel(final MainDictPanelListener mainDictPanelListener){
		
		setLayout(null);
		jButtDeleteRecord = new JButton();
		jButtPause = new JButton();
		jButtPlay = new JButton();
		jButtRecordding = new JButton();
		
		add(jButtDeleteRecord);
		add(jButtPause);
		add(jButtRecordding);
		add(jButtPlay);
		
		
		jButtPlay.setText(">");
		jButtPlay.setBounds(3, 1, 24, 21);
		jButtPlay.setForeground(new Color(0, 0, 0));
		jButtPlay.setToolTipText("Play");
		jButtPlay.setMargin(new Insets(0, 0, 0, 0));
		jButtPlay.setFont(new Font("Arial", 0, 11));
		jButtPlay.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.jButtPlayMouseReleased(paramMouseEvent);
			}
		});
		

		jButtRecordding.setText("start");
		jButtRecordding.setForeground(new Color(255, 0, 0));
		jButtRecordding.setBounds(63, 1, 53, 21);
		jButtRecordding.setToolTipText("Recording pronunciation");
		jButtRecordding.setMargin(new Insets(0, 0, 0, 0));
		jButtRecordding.setFont(new Font("Arial", 0, 11));
		jButtRecordding.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.jButtRecordMouseReleased(paramMouseEvent);
			}
		});
		
		jButtPause.setText("||");
		jButtPause.setMargin(new Insets(0, 0, 0, 0));
		jButtPause.setForeground(new Color(255, 0, 0));
		jButtPause.setToolTipText("Pause");
		jButtPause.setBounds(33, 1, 24, 21);
		jButtPause.setEnabled(false);
		jButtPause.setFont(new Font("Arial", 0, 11));


		jButtDeleteRecord.setText("X");
		jButtDeleteRecord.setMargin(new Insets(0, 0, 0, 0));
		jButtDeleteRecord.setForeground(new Color(0, 0, 0));
		jButtDeleteRecord.setToolTipText("Delete record");
		jButtDeleteRecord.setBounds(121, 1, 24, 21);
		jButtDeleteRecord.setFont(new Font("Arial", 0, 11));
		jButtDeleteRecord.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.jButtDeleteRecordMouseReleased(paramMouseEvent);
			}
		});
	}

}
