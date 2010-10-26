package com.aillusions.dictionary.view.components;

import java.awt.Font;

import javax.swing.JPanel;

import com.aillusions.dictionary.view.listener.MainDictPanelListener;

public class SpecialSymbolsPanel  extends JPanel{

	public static final int BTN_HEIGHT = 25;

	public static final int BTN_WIDTH = 24;

	private static final long serialVersionUID = 1L;
	
	private KeyBoardButton jButton1;
	private KeyBoardButton jButton2;
	private KeyBoardButton jButton3;	
	private KeyBoardButton jButton4;	
	private KeyBoardButton jButton5;
	private KeyBoardButton jButton6;
	private KeyBoardButton jButton7;
	private KeyBoardButton jButton8;
	private KeyBoardButton jButton9;
	private KeyBoardButton jButton10;
	private KeyBoardButton jButton11;
	private KeyBoardButton jButton12;
	
	public SpecialSymbolsPanel(final MainDictPanelListener mainDictPanelListener){

		setLayout(null);
		
		jButton1 = new KeyBoardButton("æ",mainDictPanelListener);
		jButton2 = new KeyBoardButton("ɔ",mainDictPanelListener);
		jButton3 = new KeyBoardButton("∫",mainDictPanelListener);
		jButton4 = new KeyBoardButton("ŋ",mainDictPanelListener);
		jButton5 = new KeyBoardButton("θ",mainDictPanelListener);
		jButton6 = new KeyBoardButton("ı",mainDictPanelListener);
		jButton7 = new KeyBoardButton("ɛ",mainDictPanelListener);
		jButton8 = new KeyBoardButton("ə",mainDictPanelListener);
		jButton9 = new KeyBoardButton("ʌ",mainDictPanelListener);
		jButton10 = new KeyBoardButton("Ʒ",mainDictPanelListener);
		jButton11 = new KeyBoardButton("ð",mainDictPanelListener);
		jButton12 = new KeyBoardButton("-",mainDictPanelListener);

		setFont(jButton1);
		jButton1.setBounds(4, 1, BTN_WIDTH, BTN_HEIGHT);

		setFont(jButton2);
		jButton2.setBounds(33, 1, BTN_WIDTH, BTN_HEIGHT);

		setFont(jButton3);
		jButton3.setBounds(62, 1, BTN_WIDTH, BTN_HEIGHT);

		setFont(jButton4);
		jButton4.setBounds(91, 1, BTN_WIDTH, BTN_HEIGHT);

		setFont(jButton5);
		jButton5.setBounds(120, 1, BTN_WIDTH, BTN_HEIGHT);

		setFont(jButton6);
		jButton6.setBounds(149, 1, BTN_WIDTH, BTN_HEIGHT);

		setFont(jButton7);
		jButton7.setBounds(178, 1, BTN_WIDTH, BTN_HEIGHT);

		setFont(jButton8);
		jButton8.setBounds(207, 1, BTN_WIDTH, BTN_HEIGHT);

		setFont(jButton9);
		jButton9.setBounds(235, 1, BTN_WIDTH, BTN_HEIGHT);
		
		setFont(jButton10);
		jButton10.setBounds(264, 1, BTN_WIDTH, BTN_HEIGHT);

		setFont(jButton11);
		jButton11.setBounds(293, 1, BTN_WIDTH, BTN_HEIGHT);

		setFont(jButton12);
		jButton12.setBounds(322, 1, BTN_WIDTH, BTN_HEIGHT);	

		add(jButton1);
		add(jButton2);
		add(jButton3);		
		add(jButton4);
		add(jButton5);		
		add(jButton6);
		add(jButton7);	
		add(jButton8);
		add(jButton9);
		add(jButton10);
		add(jButton11);
		add(jButton12);
	}
	
	private void setFont(KeyBoardButton btn){
		btn.setFont(new Font("Lucida Sans Unicode", 0, 11));
	}
}
