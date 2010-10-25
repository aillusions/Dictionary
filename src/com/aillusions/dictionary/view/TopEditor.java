package com.aillusions.dictionary.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.aillusions.dictionary.Manager;
import com.aillusions.dictionary.model.Pair;

public class TopEditor extends JFrame {
	public static final int WINDOW_HEIGHT = 343;
	private static final long serialVersionUID = 1L;
	Manager dictionary;
	int prevSelectedIndex;
	Random nextSelRandom;
	private JButton Load_Btn;
	private JButton Remove_Btn;
	private JButton Rename_Btn;
	private JButton Save_Btn;
	private JTextField Transcription_TextF;
	private JTextField Translate_TextF;
	private JTextField Word_TextF;
	private JButton jButton1;
	private JButton jButton11;
	private JButton jButton12;
	private JButton jButton13;
	private JButton jButton14;
	private JButton jButton15;
	private JButton jButton16;
	private JButton jButton17;
	private JButton jButton18;
	private JButton jButton19;
	private JButton jButton2;
	private JButton jButton20;
	private JButton jButton21;
	private JButton jButton22;
	private JButton jButton23;
	private JButton jButton24;
	private JButton jButton25;
	private JButton jButton26;
	private JButton jButton27;
	private JButton jButton28;
	private JButton jButton29;
	private JButton jButton3;
	private JButton jButton30;
	private JButton jButton31;
	private JButton jButton32;
	private JButton jButton33;
	private JButton jButton34;
	private JButton jButton35;
	private JButton jButton36;
	private JButton jButton37;
	private JButton jButton38;
	private JButton jButton39;
	private JButton jButton4;
	private JButton jButton40;
	private JButton jButton499;
	private JButton jButton41;
	private JButton jButton42;
	private JButton jButton43;
	private JButton jButton44;
	private JButton jButton5;
	private JButton jButton6;
	private JButton jButton7;
	private JButton jButton8;
	private JButton jButton9;
	private JCheckBox jChBoxPlayOnSelections;
	private JButton jButtDeleteRecord;
	private JList WordsList_Lst;
	private JButton jButton10;
	private JButton jButtPlay;
	private JButton jButtRecordding;
	private JScrollPane jScrollPane2;
	private JCheckBox jTop;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane3;
	private JButton jButton50;
	private JButton jButton49;
	private JButton jButton48;
	private JButton jButton47;
	private JButton jButton46;
	private JButton jButton45;
	private JButton selectNextRandomBtn;
	private JButton dellFromRight_all;
	private JButton dellFromRight_one;
	private JButton addToRight_all;
	private JButton addToRight_one;
	private JList InUseJList;
	private JButton runTrainerBtn;
	private JButton word_start;
	private JTextField jTextSamplesSearch;
	private JTextField jTxtSearch;
	private JButton jButtRemSample;
	private JList jListSamples;

	public TopEditor() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		this.dictionary = new Manager("words.xml");
		this.prevSelectedIndex = 0;
		this.nextSelRandom = new Random(0L);
		this.initComponents();
		this.createMenu();
		this.dictionary.Load();
		this.WordsList_Lst.setListData(this.dictionary.getAllWords());
		this.jListSamples.setListData(new String[0]);
		refresh(true, true);
		this.WordsList_Lst.setSelectedIndex(0);
	}

	private void Alert(String paramString) {
		JOptionPane.showMessageDialog(new JFrame("FrameDemo"), paramString);
	}

	private void createMenu() {
		JMenuBar menuBar;
		JMenu menu, submenu;
		JMenuItem menuItem;
		JCheckBoxMenuItem cbMenuItem;

		menuBar = new JMenuBar();
		
		//---------------
		menu = new JMenu("View");
		menu.setMargin(new Insets(0, 0, 0, 0));
		menu.setFont(new Font("Arial", 0, 11));
		menuBar.add(menu);		
		menuItem = new JMenuItem("Expand");		
		menu.add(menuItem);

		ButtonGroup group = new ButtonGroup();	
		
		submenu = new JMenu("Language");
		menuItem = new JRadioButtonMenuItem("English");
		submenu.add(menuItem);
		group.add(menuItem);
		menuItem = new JRadioButtonMenuItem("Italian");
		submenu.add(menuItem);
		group.add(menuItem);
		menuItem = new JRadioButtonMenuItem("Russian");
		submenu.add(menuItem);
		group.add(menuItem);
		menu.add(submenu);	
		
		menuItem = new JCheckBoxMenuItem("Always on top");		
		menu.add(menuItem);		

		//---------------
		menu = new JMenu("Dictionary");
		menu.setMargin(new Insets(0, 0, 0, 0));
		menu.setFont(new Font("Arial", 0, 11));
		menuBar.add(menu);
		submenu = new JMenu("Select current");
		menuItem = new JMenuItem("English");
		submenu.add(menuItem);
		menuItem = new JMenuItem("Italian");
		submenu.add(menuItem);
		menu.add(submenu);

		menuItem = new JMenuItem("Use current as default");
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Create new");
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Load");
		menu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menu.add(menuItem);
		
		//---------------
		menu = new JMenu("Words");
		menu.setMargin(new Insets(0, 0, 0, 0));
		menu.setFont(new Font("Arial", 0, 11));
		menuBar.add(menu);		
		menuItem = new JMenuItem("Mix words");
		menu.add(menuItem);
		menuItem = new JMenuItem("View removed words");
		menu.add(menuItem);

		
		//---------------
		menu = new JMenu("Audio");
		menu.setMargin(new Insets(0, 0, 0, 0));
		menu.setFont(new Font("Arial", 0, 11));
		menuBar.add(menu);		
		menuItem = new JMenuItem("Paly");
		menu.add(menuItem);
		menuItem = new JMenuItem("Record");
		menu.add(menuItem);
		menuItem = new JMenuItem("Remove");
		menu.add(menuItem);		
		menu.addSeparator();
		cbMenuItem = new JCheckBoxMenuItem("Play on select");
		menu.add(cbMenuItem);
		
		//---------------
		menu = new JMenu("Update");
		menu.setMargin(new Insets(0, 0, 0, 0));
		menu.setFont(new Font("Arial", 0, 11));
		menuBar.add(menu);		
		menuItem = new JMenuItem("Check for update");
		menu.add(menuItem);
		menuItem = new JCheckBoxMenuItem("Do not ask");
		menu.add(menuItem);
		
		//---------------
		menu = new JMenu("Trainer");
		menu.setMargin(new Insets(0, 0, 0, 0));
		menu.setFont(new Font("Arial", 0, 11));
		menuBar.add(menu);		
		menuItem = new JMenuItem("Run trainer");
		menu.add(menuItem);
		menuItem = new JMenuItem("View statistic");
		menu.add(menuItem);
		menuItem = new JMenuItem("Clear statistic");
		menu.add(menuItem);
		
		//---------------
		menu = new JMenu("About");
		menu.setMargin(new Insets(0, 0, 0, 0));
		menu.setFont(new Font("Arial", 0, 11));
		menuBar.add(menu);		
		menuItem = new JMenuItem("About Dictionary");
		menu.add(menuItem);
		
		this.setJMenuBar(menuBar);

	}

	private void initComponents() {

		this.jButton1 = new JButton();
		this.Word_TextF = new JTextField();
		this.Translate_TextF = new JTextField();
		this.Transcription_TextF = new JTextField();
		this.jButton2 = new JButton();
		this.jButton3 = new JButton();
		this.jButton4 = new JButton();
		this.jButton5 = new JButton();
		this.jButton6 = new JButton();
		this.jButton7 = new JButton();
		this.jButton8 = new JButton();
		this.jButton9 = new JButton();
		this.Save_Btn = new JButton();
		this.Load_Btn = new JButton();
		this.jButton14 = new JButton();
		this.jButton15 = new JButton();
		this.jButton16 = new JButton();
		this.jButton17 = new JButton();
		this.jButton18 = new JButton();
		this.jButton19 = new JButton();
		this.jButton20 = new JButton();
		this.jButton21 = new JButton();
		this.jButton22 = new JButton();
		this.jButton23 = new JButton();
		this.jButton24 = new JButton();
		this.jButton25 = new JButton();
		getContentPane().setLayout(null);
		getContentPane().add(this.jButton25);
		this.jButton26 = new JButton();
		this.jButton27 = new JButton();
		this.jButton28 = new JButton();
		this.jButton29 = new JButton();
		this.jButton30 = new JButton();
		this.jButton31 = new JButton();
		this.jButton32 = new JButton();
		this.jButton33 = new JButton();
		this.jButton34 = new JButton();
		this.jButton35 = new JButton();
		this.jButton36 = new JButton();
		this.jButton37 = new JButton();
		this.jButton38 = new JButton();
		this.jButton39 = new JButton();
		this.jButton40 = new JButton();
		getContentPane().add(this.jButton40);
		this.jButton41 = new JButton();
		getContentPane().add(this.jButton41);
		this.jButton42 = new JButton();
		this.jButton43 = new JButton();
		this.jButton44 = new JButton();
		this.Remove_Btn = new JButton();
		getContentPane().add(this.Remove_Btn);
		getContentPane().add(this.Save_Btn);
		getContentPane().add(this.Load_Btn);
		this.jButton11 = new JButton();
		this.jButton12 = new JButton();
		this.jButton13 = new JButton();
		this.Rename_Btn = new JButton();
		getContentPane().add(this.Rename_Btn);
		getContentPane().add(this.jButton14);
		getContentPane().add(this.jButton32);
		getContentPane().add(this.jButton17);
		getContentPane().add(this.jButton19);
		getContentPane().add(this.jButton20);
		getContentPane().add(this.jButton21);
		getContentPane().add(this.jButton23);
		getContentPane().add(this.jButton24);
		getContentPane().add(this.jButton3);
		getContentPane().add(this.jButton11);
		getContentPane().add(this.jButton12);
		getContentPane().add(this.jButton13);
		getContentPane().add(this.jButton1);
		getContentPane().add(this.jButton5);
		getContentPane().add(this.jButton9);
		getContentPane().add(this.jButton6);
		getContentPane().add(this.jButton4);
		getContentPane().add(this.jButton8);
		getContentPane().add(this.jButton7);
		getContentPane().add(this.jButton2);
		getContentPane().add(this.Transcription_TextF);
		getContentPane().add(this.Translate_TextF);
		getContentPane().add(this.Word_TextF);
		getContentPane().add(this.jButton37);
		getContentPane().add(this.jButton16);
		getContentPane().add(this.jButton35);
		getContentPane().add(this.jButton15);
		getContentPane().add(this.jButton27);
		getContentPane().add(this.jButton26);
		getContentPane().add(this.jButton42);
		getContentPane().add(this.jButton39);
		getContentPane().add(this.jButton30);
		getContentPane().add(this.jButton36);
		getContentPane().add(this.jButton18);
		getContentPane().add(this.jButton31);
		getContentPane().add(this.jButton33);
		getContentPane().add(this.jButton38);
		getContentPane().add(this.jButton34);
		getContentPane().add(this.jButton22);
		getContentPane().add(this.jButton28);
		getContentPane().add(this.jButton29);
		getContentPane().add(this.jButton43);
		getContentPane().add(this.jButton44);
		this.Translate_TextF.setBounds(6, 38, 230, 21);
		this.jButtRemSample = new JButton();
		getContentPane().add(this.jButtRemSample);
		this.jButtRemSample.setText("del");
		this.jButtRemSample.setMargin(new Insets(0, 0, 0, 0));
		this.jButtRemSample.setBounds(356, 271, 29, 19);
		this.jButtRemSample.setFont(new Font("Arial", 0, 11));
		this.jButtRemSample.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.jButtRemSampleMouseReleased(paramMouseEvent);
			}
		});
		this.jButtPlay = new JButton();
		getContentPane().add(this.jButtPlay);
		this.jButtPlay.setText(">");
		this.jButtPlay.setBounds(243, 64, 24, 21);
		this.jButtPlay.setForeground(new Color(0, 0, 0));
		this.jButtPlay.setToolTipText("Play");
		this.jButtPlay.setMargin(new Insets(0, 0, 0, 0));
		this.jButtPlay.setFont(new Font("Arial", 0, 11));
		this.jButtPlay.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.jButtPlayMouseReleased(paramMouseEvent);
			}
		});
		this.jButtRecordding = new JButton();
		getContentPane().add(this.jButtRecordding);
		this.jButtRecordding.setText("start");
		this.jButtRecordding.setForeground(new Color(255, 0, 0));
		this.jButtRecordding.setBounds(303, 64, 53, 21);
		this.jButtRecordding.setToolTipText("Recording pronunciation");
		this.jButtRecordding.setMargin(new Insets(0, 0, 0, 0));
		this.jButtRecordding.setFont(new Font("Arial", 0, 11));
		this.jButtRecordding.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.jButtRecordMouseReleased(paramMouseEvent);
			}
		});
		this.jButton10 = new JButton();
		getContentPane().add(this.jButton10);
		this.jButton10.setText("||");
		this.jButton10.setMargin(new Insets(0, 0, 0, 0));
		this.jButton10.setForeground(new Color(255, 0, 0));
		this.jButton10.setToolTipText("Pause");
		this.jButton10.setBounds(273, 64, 24, 21);
		this.jButton10.setEnabled(false);
		this.jButton10.setFont(new Font("Arial", 0, 11));
		this.jButtDeleteRecord = new JButton();
		getContentPane().add(this.jButtDeleteRecord);
		this.jButtDeleteRecord.setText("X");
		this.jButtDeleteRecord.setMargin(new Insets(0, 0, 0, 0));
		this.jButtDeleteRecord.setForeground(new Color(0, 0, 0));
		this.jButtDeleteRecord.setToolTipText("Delete record");
		this.jButtDeleteRecord.setBounds(361, 64, 24, 21);
		this.jButtDeleteRecord.setFont(new Font("Arial", 0, 11));
		this.jButtDeleteRecord.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.jButtDeleteRecordMouseReleased(paramMouseEvent);
			}
		});
		this.jChBoxPlayOnSelections = new JCheckBox();
		getContentPane().add(this.jChBoxPlayOnSelections);
		this.jChBoxPlayOnSelections.setText("Pronunciation");
		this.jChBoxPlayOnSelections.setBounds(243, 38, 85, 18);
		this.jChBoxPlayOnSelections
				.setToolTipText("Play record by selection in navigator");
		this.jChBoxPlayOnSelections.setFont(new Font("Arial", 0, 11));
		this.jTxtSearch = new JTextField();
		getContentPane().add(this.jTxtSearch);
		this.jTxtSearch.setBounds(393, 269, 102, 21);
		this.jTxtSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent paramKeyEvent) {
				TopEditor.this.jTxtSearchKeyReleased(paramKeyEvent);
			}
		});
		this.jTextSamplesSearch = new JTextField();
		getContentPane().add(this.jTextSamplesSearch);
		this.jTextSamplesSearch.setBounds(6, 270, 348, 21);
		this.jTextSamplesSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent paramKeyEvent) {
				TopEditor.this.jTextSamplesSearchKeyReleased(paramKeyEvent);
			}
		});
		this.word_start = new JButton();
		this.word_start.setMargin(new Insets(1, 1, 1, 1));
		this.word_start.setFont(new Font("Lucida Sans Unicode", 0, 11));
		getContentPane().add(this.word_start);
		this.word_start.setText("W");
		this.word_start.setBounds(359, 38, 25, 20);
		this.word_start.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.word_startMouseReleased(paramMouseEvent);
			}
		});
		this.runTrainerBtn = new JButton();
		getContentPane().add(this.runTrainerBtn);
		this.runTrainerBtn.setText("T");
		this.runTrainerBtn.setMargin(new Insets(1, 1, 1, 1));
		this.runTrainerBtn.setFont(new Font("Lucida Sans Unicode", 0, 11));
		this.runTrainerBtn.setBounds(332, 38, 25, 20);
		this.runTrainerBtn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.runTrainerBtnMouseReleased(paramMouseEvent);
			}
		});
		this.addToRight_one = new JButton();
		getContentPane().add(this.addToRight_one);
		this.addToRight_one.setText("->");
		this.addToRight_one.setMargin(new Insets(1, 1, 1, 1));
		this.addToRight_one.setFont(new Font("Lucida Sans Unicode", 0, 11));
		this.addToRight_one.setBounds(565, 74, 31, 20);
		this.addToRight_one.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.addToRight_oneMouseReleased(paramMouseEvent);
			}
		});
		this.addToRight_all = new JButton();
		getContentPane().add(this.addToRight_all);
		this.addToRight_all.setText(">>");
		this.addToRight_all.setMargin(new Insets(1, 1, 1, 1));
		this.addToRight_all.setFont(new Font("Lucida Sans Unicode", 0, 11));
		this.addToRight_all.setBounds(565, 100, 31, 20);
		this.addToRight_all.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.addToRight_allMouseReleased(paramMouseEvent);
			}
		});
		this.dellFromRight_one = new JButton();
		getContentPane().add(this.dellFromRight_one);
		this.dellFromRight_one.setText("<-");
		this.dellFromRight_one.setMargin(new Insets(1, 1, 1, 1));
		this.dellFromRight_one.setFont(new Font("Lucida Sans Unicode", 0, 11));
		this.dellFromRight_one.setBounds(565, 153, 31, 20);
		this.dellFromRight_one.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.dellFromRight_oneMouseReleased(paramMouseEvent);
			}
		});
		this.dellFromRight_all = new JButton();
		getContentPane().add(this.dellFromRight_all);
		this.dellFromRight_all.setText("<<");
		this.dellFromRight_all.setMargin(new Insets(1, 1, 1, 1));
		this.dellFromRight_all.setFont(new Font("Lucida Sans Unicode", 0, 11));
		this.dellFromRight_all.setBounds(565, 179, 31, 20);
		this.dellFromRight_all.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.dellFromRight_allMouseReleased(paramMouseEvent);
			}
		});
		this.selectNextRandomBtn = new JButton();
		getContentPane().add(this.selectNextRandomBtn);
		this.selectNextRandomBtn.setText("> go next random >");
		this.selectNextRandomBtn.setMargin(new Insets(0, 0, 0, 0));
		this.selectNextRandomBtn.setFont(new Font("Arial", 0, 11));
		this.selectNextRandomBtn.setBounds(608, 267, 162, 20);
		this.selectNextRandomBtn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this
						.selectNextRandomBtnMouseReleased(paramMouseEvent);
			}
		});
		this.jButton45 = new JButton();
		getContentPane().add(this.jButton45);
		this.jButton45.setText("W");
		this.jButton45.setMargin(new Insets(1, 1, 1, 1));
		this.jButton45.setFont(new Font("Lucida Sans Unicode", 0, 11));
		this.jButton45.setBounds(359, 38, 25, 20);
		this.jButton45.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.jButton45MouseReleased(paramMouseEvent);
			}
		});
		this.jButton46 = new JButton();
		getContentPane().add(this.jButton46);
		this.jButton46.setText("W");
		this.jButton46.setMargin(new Insets(1, 1, 1, 1));
		this.jButton46.setFont(new Font("Lucida Sans Unicode", 0, 11));
		this.jButton46.setBounds(565, 36, 25, 21);
		this.jButton46.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.inUseOnlyWordsShowInWord(paramMouseEvent);
			}
		});
		this.jButton47 = new JButton();
		getContentPane().add(this.jButton47);
		this.jButton47.setText("W");
		this.jButton47.setMargin(new Insets(1, 1, 1, 1));
		this.jButton47.setFont(new Font("Lucida Sans Unicode", 0, 11));
		this.jButton47.setBounds(365, 179, 21, 21);
		this.jButton47.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.jButton47MouseReleased(paramMouseEvent);
			}
		});
		this.jButton48 = new JButton();
		getContentPane().add(this.jButton48);
		this.jButton48.setText("▲");
		this.jButton48.setMargin(new Insets(1, 1, 1, 1));
		this.jButton48.setFont(new Font("Lucida Sans Unicode", 0, 11));
		this.jButton48.setBounds(340, 179, 23, 21);
		this.jButton48.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.jButton48MouseReleased(paramMouseEvent);
			}
		});
		this.jButton49 = new JButton();
		getContentPane().add(this.jButton49);
		this.jButton49.setText(">");
		this.jButton49.setMargin(new Insets(1, 1, 1, 1));
		this.jButton49.setFont(new Font("Lucida Sans Unicode", 0, 11));
		this.jButton49.setBounds(363, 98, 22, 23);
		this.jButton49.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.jButton49MouseReleased(paramMouseEvent);
			}
		});
		this.jButton499 = new JButton();
		getContentPane().add(this.jButton499);
		this.jButton499.setText("M");
		this.jButton499.setMargin(new Insets(0, 0, 0, 0));
		this.jButton499.setFont(new Font("Lucida Sans Unicode", 0, 11));
		this.jButton499.setBounds(363, 125, 22, 23);
		this.jButton499.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.jButton499MouseReleased(paramMouseEvent);
			}
		});
		this.jButton50 = new JButton();
		getContentPane().add(this.jButton50);
		this.jButton50.setText("About");
		this.jButton50.setBounds(217, 11, 50, 20);
		this.jButton50.setMargin(new Insets(0, 0, 0, 0));
		this.jButton50.setFont(new Font("Arial", 0, 11));
		this.jButton50.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				About localAbout = new About();
				localAbout.frameConstructor();
				localAbout.setVisible(true);
			}
		});
		this.jScrollPane3 = new JScrollPane();
		getContentPane().add(this.jScrollPane3);
		this.jScrollPane3.setBounds(608, 12, 159, 249);
		DefaultComboBoxModel localDefaultComboBoxModel = new DefaultComboBoxModel(
				new String[] { "Item One", "Item Two" });
		this.InUseJList = new JList();
		this.jScrollPane3.setViewportView(this.InUseJList);
		this.InUseJList.setModel(localDefaultComboBoxModel);
		this.InUseJList.setSelectedIndex(0);
		this.InUseJList.setFont(new Font("Arial", 0, 11));
		this.InUseJList.setBounds(608, 12, 159, 249);
		this.InUseJList.setBorder(BorderFactory.createEtchedBorder(1));
		this.InUseJList.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent paramKeyEvent) {
				TopEditor.this.InUseJListKeyReleased(paramKeyEvent);
			}
		});
		this.InUseJList.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.InUseJListMouseReleased(paramMouseEvent);
			}
		});
		Object localObject = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent paramListSelectionEvent) {
				if ((paramListSelectionEvent.getValueIsAdjusting())
						|| ((!(TopEditor.this.InUseJList.isFocusOwner())) && (!(TopEditor.this.selectNextRandomBtn
								.isFocusOwner()))))
					return;
				TopEditor.this.WordsList_Lst.setSelectedValue(
						TopEditor.this.InUseJList.getSelectedValue(), true);
			}
		};
		this.InUseJList
				.addListSelectionListener((ListSelectionListener) localObject);
		this.jScrollPane1 = new JScrollPane();
		getContentPane().add(this.jScrollPane1);
		this.jScrollPane1.setBounds(393, 12, 164, 251);
		localDefaultComboBoxModel = new DefaultComboBoxModel(new String[] {
				"Item One", "Item Two" });
		this.WordsList_Lst = new JList();
		this.jScrollPane1.setViewportView(this.WordsList_Lst);
		this.WordsList_Lst.setModel(localDefaultComboBoxModel);
		this.WordsList_Lst.setBounds(393, 12, 164, 251);
		this.WordsList_Lst.setFont(new Font("Arial", 0, 11));
		this.WordsList_Lst.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
				1, new Color(255, 255, 255)));
		this.WordsList_Lst.setForeground(new Color(0, 0, 0));
		this.WordsList_Lst.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.WordsList_LstMouseReleased(paramMouseEvent);
			}
		});
		this.WordsList_Lst.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent paramKeyEvent) {
				TopEditor.this.WordsList_LstKeyReleased(paramKeyEvent);
			}
		});
		localObject = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent paramListSelectionEvent) {
				if (paramListSelectionEvent.getValueIsAdjusting())
					return;
				if ((TopEditor.this.dictionary.getCurrentPair() != null)
						&& (!(TopEditor.this.dictionary.aplyCurrWordChanged(
								TopEditor.this.Translate_TextF.getText(),
								TopEditor.this.Transcription_TextF.getText()))))
					TopEditor.this.Alert("Your input was not aplyed!");
				if (TopEditor.this.dictionary
						.setSelected((String) TopEditor.this.WordsList_Lst
								.getSelectedValue())) {
					TopEditor.this.refresh(false, true);
					if (TopEditor.this.jChBoxPlayOnSelections.isSelected())
						TopEditor.this.dictionary.playCurrentAudioRecord(true,
								false);
				}
				TopEditor.this.WordsList_Lst.setSelectedValue(
						TopEditor.this.WordsList_Lst.getSelectedValue(), true);
			}
		};
		this.WordsList_Lst
				.addListSelectionListener((ListSelectionListener) localObject);
		this.jTop = new JCheckBox();
		getContentPane().add(this.jTop);
		this.jTop.setText("top");
		this.jTop.setBounds(351, 154, 36, 18);
		this.jTop.setMargin(new Insets(0, 0, 0, 0));
		this.jTop.setFont(new Font("Arial", 0, 11));
		this.jTop.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent paramChangeEvent) {
				TopEditor.this.jTopStateChanged(paramChangeEvent);
			}
		});
		this.jScrollPane2 = new JScrollPane();
		getContentPane().add(this.jScrollPane2);
		this.jScrollPane2.setBounds(6, 210, 379, 55);
		localDefaultComboBoxModel = new DefaultComboBoxModel(new String[] {
				"Item One", "Item Two" });
		this.jListSamples = new JList();
		this.jScrollPane2.setViewportView(this.jListSamples);
		this.jListSamples.setModel(localDefaultComboBoxModel);
		this.jListSamples.setBounds(6, 210, 379, 55);
		this.jListSamples.setFont(new Font("Arial", 0, 13));
		this.jListSamples.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.jListSamplesMouseReleased(paramMouseEvent);
			}
		});
		localObject = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent paramListSelectionEvent) {
				if (paramListSelectionEvent.getValueIsAdjusting())
					return;
				if (TopEditor.this.jListSamples.getSelectedIndex() == -1)
					TopEditor.this.dictionary.setCurrSample(null);
				else
					TopEditor.this.dictionary
							.setCurrSample(TopEditor.this.jListSamples
									.getSelectedValue().toString());
				if ((!(TopEditor.this.jChBoxPlayOnSelections.isSelected()))
						|| (TopEditor.this.jListSamples.getSelectedIndex() == -1))
					return;
				TopEditor.this.dictionary.playCurrentAudioRecord(true, false);
			}
		};
		this.jListSamples
				.addListSelectionListener((ListSelectionListener) localObject);
		setDefaultCloseOperation(3);
		setTitle("Top Dictionary");
		setLocationByPlatform(true);
		setResizable(false);
		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.thisMouseReleased(paramMouseEvent);
			}
		});
		this.jButton1.setFont(new Font("Lucida Sans Unicode", 0, 11));
		this.jButton1.setText("æ");
		this.jButton1.setHorizontalTextPosition(0);
		this.jButton1.setMargin(new Insets(1, 1, 1, 1));
		this.jButton1.setBounds(14, 97, 24, 25);
		this.jButton1.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.Word_TextF.setEditable(false);
		this.Word_TextF.setFont(new Font("Tahoma", 1, 11));
		this.Word_TextF.setBounds(6, 11, 205, 21);
		this.Word_TextF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent paramKeyEvent) {
				TopEditor.this.Word_TextFKeyReleased(paramKeyEvent);
			}
		});
		this.Translate_TextF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent paramKeyEvent) {
				TopEditor.this.Translate_TextFKeyReleased(paramKeyEvent);
			}
		});
		this.Transcription_TextF
				.setFont(new Font("Lucida Sans Unicode", 0, 14));
		this.Transcription_TextF.setBounds(6, 65, 230, 25);
		this.Transcription_TextF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent paramKeyEvent) {
				TopEditor.this.Transcription_TextFKeyReleased(paramKeyEvent);
			}
		});
		this.jButton2.setFont(new Font("Lucida Sans Unicode", 0, 11));
		this.jButton2.setText("ə");
		this.jButton2.setHorizontalTextPosition(0);
		this.jButton2.setMargin(new Insets(1, 1, 1, 1));
		this.jButton2.setBounds(217, 97, 24, 25);
		this.jButton2.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton3.setFont(new Font("Lucida Sans Unicode", 0, 11));
		this.jButton3.setText("ʌ");
		this.jButton3.setHorizontalTextPosition(0);
		this.jButton3.setMargin(new Insets(1, 1, 1, 1));
		this.jButton3.setBounds(246, 97, 24, 25);
		this.jButton3.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton4.setFont(new Font("Lucida Sans Unicode", 0, 11));
		this.jButton4.setText("θ");
		this.jButton4.setHorizontalTextPosition(0);
		this.jButton4.setMargin(new Insets(1, 1, 1, 1));
		this.jButton4.setBounds(130, 97, 24, 25);
		this.jButton4.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton5.setFont(new Font("Lucida Sans Unicode", 0, 11));
		this.jButton5.setText("ɔ");
		this.jButton5.setHorizontalTextPosition(0);
		this.jButton5.setMargin(new Insets(1, 1, 1, 1));
		this.jButton5.setBounds(43, 97, 24, 25);
		this.jButton5.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton6.setFont(new Font("Lucida Sans Unicode", 0, 11));
		this.jButton6.setText("ŋ");
		this.jButton6.setHorizontalTextPosition(0);
		this.jButton6.setMargin(new Insets(1, 1, 1, 1));
		this.jButton6.setBounds(101, 97, 24, 25);
		this.jButton6.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton7.setFont(new Font("Lucida Sans Unicode", 0, 11));
		this.jButton7.setText("ɛ");
		this.jButton7.setHorizontalTextPosition(0);
		this.jButton7.setMargin(new Insets(1, 1, 1, 1));
		this.jButton7.setBounds(188, 97, 24, 25);
		this.jButton7.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton8.setFont(new Font("Lucida Sans Unicode", 0, 11));
		this.jButton8.setText("ı");
		this.jButton8.setHorizontalTextPosition(0);
		this.jButton8.setMargin(new Insets(1, 1, 1, 1));
		this.jButton8.setBounds(159, 97, 24, 25);
		this.jButton8.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton9.setFont(new Font("Lucida Sans Unicode", 0, 11));
		this.jButton9.setText("∫");
		this.jButton9.setHorizontalTextPosition(0);
		this.jButton9.setMargin(new Insets(1, 1, 1, 1));
		this.jButton9.setBounds(72, 97, 24, 25);
		this.jButton9.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.WordsList_Lst.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4",
					"Item 5" };

			public int getSize() {
				return this.strings.length;
			}

			public Object getElementAt(int paramInt) {
				return this.strings[paramInt];
			}
		});
		this.Save_Btn.setText("save");
		this.Save_Btn.setMargin(new Insets(0, 0, 0, 0));
		this.Save_Btn.setBounds(328, 11, 56, 20);
		this.Save_Btn.setFont(new Font("Arial", 0, 11));
		this.Save_Btn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.Save_BtnMouseReleased(paramMouseEvent);
			}
		});
		this.Load_Btn.setText("load");
		this.Load_Btn.setMargin(new Insets(0, 0, 0, 0));
		this.Load_Btn.setBounds(270, 11, 54, 20);
		this.Load_Btn.setFont(new Font("Arial", 0, 11));
		this.Load_Btn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.Load_BtnMouseReleased(paramMouseEvent);
			}
		});
		this.jButton14.setText("a");
		this.jButton14.setHorizontalTextPosition(0);
		this.jButton14.setMargin(new Insets(1, 1, 1, 1));
		this.jButton14.setBounds(43, 153, 24, 21);
		this.jButton14.setFont(new Font("Arial", 0, 11));
		this.jButton14.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton15.setText("b");
		this.jButton15.setHorizontalTextPosition(0);
		this.jButton15.setMargin(new Insets(1, 1, 1, 1));
		this.jButton15.setBounds(164, 179, 24, 21);
		this.jButton15.setFont(new Font("Arial", 0, 11));
		this.jButton15.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton16.setText("c");
		this.jButton16.setHorizontalTextPosition(0);
		this.jButton16.setMargin(new Insets(1, 1, 1, 1));
		this.jButton16.setBounds(108, 179, 24, 21);
		this.jButton16.setFont(new Font("Arial", 0, 11));
		this.jButton17.setText("d");
		this.jButton17.setHorizontalTextPosition(0);
		this.jButton17.setMargin(new Insets(1, 1, 1, 1));
		this.jButton17.setBounds(98, 153, 24, 21);
		this.jButton17.setFont(new Font("Arial", 0, 11));
		this.jButton17.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton18.setText("e");
		this.jButton18.setHorizontalTextPosition(0);
		this.jButton18.setMargin(new Insets(1, 1, 1, 1));
		this.jButton18.setBounds(77, 127, 24, 21);
		this.jButton18.setFont(new Font("Arial", 0, 11));
		this.jButton18.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton19.setText("f");
		this.jButton19.setHorizontalTextPosition(0);
		this.jButton19.setMargin(new Insets(1, 1, 1, 1));
		this.jButton19.setBounds(126, 153, 24, 21);
		this.jButton19.setFont(new Font("Arial", 0, 11));
		this.jButton19.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton20.setText("g");
		this.jButton20.setHorizontalTextPosition(0);
		this.jButton20.setMargin(new Insets(1, 1, 1, 1));
		this.jButton20.setBounds(153, 153, 24, 21);
		this.jButton20.setFont(new Font("Arial", 0, 11));
		this.jButton20.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton21.setText("h");
		this.jButton21.setHorizontalTextPosition(0);
		this.jButton21.setMargin(new Insets(1, 1, 1, 1));
		this.jButton21.setBounds(181, 153, 24, 21);
		this.jButton21.setFont(new Font("Arial", 0, 11));
		this.jButton21.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton22.setText("i");
		this.jButton22.setHorizontalTextPosition(0);
		this.jButton22.setMargin(new Insets(1, 1, 1, 1));
		this.jButton22.setBounds(220, 127, 24, 21);
		this.jButton22.setFont(new Font("Arial", 0, 11));
		this.jButton22.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton23.setText("j");
		this.jButton23.setHorizontalTextPosition(0);
		this.jButton23.setMargin(new Insets(1, 1, 1, 1));
		this.jButton23.setBounds(209, 153, 24, 21);
		this.jButton23.setFont(new Font("Arial", 0, 11));
		this.jButton23.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton24.setText("k");
		this.jButton24.setHorizontalTextPosition(0);
		this.jButton24.setMargin(new Insets(1, 1, 1, 1));
		this.jButton24.setBounds(236, 153, 24, 21);
		this.jButton24.setFont(new Font("Arial", 0, 11));
		this.jButton24.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton25.setText("l");
		this.jButton25.setHorizontalTextPosition(0);
		this.jButton25.setMargin(new Insets(1, 1, 1, 1));
		this.jButton25.setBounds(264, 154, 24, 21);
		this.jButton25.setFont(new Font("Arial", 0, 11));
		this.jButton25.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton26.setText("m");
		this.jButton26.setHorizontalTextPosition(0);
		this.jButton26.setMargin(new Insets(1, 1, 1, 1));
		this.jButton26.setBounds(220, 179, 24, 21);
		this.jButton26.setFont(new Font("Arial", 0, 11));
		this.jButton26.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton27.setText("n");
		this.jButton27.setHorizontalTextPosition(0);
		this.jButton27.setMargin(new Insets(1, 1, 1, 1));
		this.jButton27.setBounds(192, 179, 24, 21);
		this.jButton27.setFont(new Font("Arial", 0, 11));
		this.jButton27.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton28.setText("o");
		this.jButton28.setHorizontalTextPosition(0);
		this.jButton28.setMargin(new Insets(1, 1, 1, 1));
		this.jButton28.setBounds(248, 127, 24, 21);
		this.jButton28.setFont(new Font("Arial", 0, 11));
		this.jButton28.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton29.setText("p");
		this.jButton29.setHorizontalTextPosition(0);
		this.jButton29.setMargin(new Insets(1, 1, 1, 1));
		this.jButton29.setBounds(277, 127, 24, 21);
		this.jButton29.setFont(new Font("Arial", 0, 11));
		this.jButton29.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton30.setText("q");
		this.jButton30.setHorizontalTextPosition(0);
		this.jButton30.setMargin(new Insets(1, 1, 1, 1));
		this.jButton30.setBounds(21, 127, 24, 21);
		this.jButton30.setFont(new Font("Arial", 0, 11));
		this.jButton30.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton31.setText("r");
		this.jButton31.setHorizontalTextPosition(0);
		this.jButton31.setMargin(new Insets(1, 1, 1, 1));
		this.jButton31.setBounds(106, 127, 24, 21);
		this.jButton31.setFont(new Font("Arial", 0, 11));
		this.jButton31.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton32.setText("s");
		this.jButton32.setHorizontalTextPosition(0);
		this.jButton32.setMargin(new Insets(1, 1, 1, 1));
		this.jButton32.setBounds(70, 153, 24, 21);
		this.jButton32.setFont(new Font("Arial", 0, 11));
		this.jButton32.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton33.setText("t");
		this.jButton33.setHorizontalTextPosition(0);
		this.jButton33.setMargin(new Insets(1, 1, 1, 1));
		this.jButton33.setBounds(134, 127, 24, 21);
		this.jButton33.setFont(new Font("Arial", 0, 11));
		this.jButton33.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton34.setText("u");
		this.jButton34.setHorizontalTextPosition(0);
		this.jButton34.setMargin(new Insets(1, 1, 1, 1));
		this.jButton34.setBounds(191, 127, 24, 21);
		this.jButton34.setFont(new Font("Arial", 0, 11));
		this.jButton34.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton35.setText("v");
		this.jButton35.setHorizontalTextPosition(0);
		this.jButton35.setMargin(new Insets(1, 1, 1, 1));
		this.jButton35.setBounds(136, 179, 24, 21);
		this.jButton35.setFont(new Font("Arial", 0, 11));
		this.jButton35.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton36.setText("w");
		this.jButton36.setHorizontalTextPosition(0);
		this.jButton36.setMargin(new Insets(1, 1, 1, 1));
		this.jButton36.setBounds(49, 127, 24, 21);
		this.jButton36.setFont(new Font("Arial", 0, 11));
		this.jButton36.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton37.setText("x");
		this.jButton37.setHorizontalTextPosition(0);
		this.jButton37.setMargin(new Insets(1, 1, 1, 1));
		this.jButton37.setBounds(80, 179, 24, 21);
		this.jButton37.setFont(new Font("Arial", 0, 11));
		this.jButton37.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton38.setText("y");
		this.jButton38.setHorizontalTextPosition(0);
		this.jButton38.setMargin(new Insets(1, 1, 1, 1));
		this.jButton38.setBounds(163, 127, 24, 21);
		this.jButton38.setFont(new Font("Arial", 0, 11));
		this.jButton38.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton39.setText("z");
		this.jButton39.setHorizontalTextPosition(0);
		this.jButton39.setMargin(new Insets(1, 1, 1, 1));
		this.jButton39.setBounds(53, 179, 24, 21);
		this.jButton39.setFont(new Font("Arial", 0, 11));
		this.jButton39.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton40.setText(":");
		this.jButton40.setHorizontalTextPosition(0);
		this.jButton40.setMargin(new Insets(1, 1, 1, 1));
		this.jButton40.setBounds(292, 153, 24, 21);
		this.jButton40.setFont(new Font("Arial", 0, 11));
		this.jButton40.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton41.setText("'");
		this.jButton41.setHorizontalTextPosition(0);
		this.jButton41.setMargin(new Insets(1, 1, 1, 1));
		this.jButton41.setBounds(320, 153, 24, 21);
		this.jButton41.setFont(new Font("Arial", 0, 11));
		this.jButton41.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton42.setText(" ");
		this.jButton42.setHorizontalTextPosition(0);
		this.jButton42.setMargin(new Insets(1, 1, 1, 1));
		this.jButton42.setBounds(250, 179, 84, 21);
		this.jButton42.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton43.setText("(");
		this.jButton43.setHorizontalTextPosition(0);
		this.jButton43.setMargin(new Insets(1, 1, 1, 1));
		this.jButton43.setBounds(305, 127, 24, 21);
		this.jButton43.setFont(new Font("Arial", 0, 11));
		this.jButton43.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton44.setText(")");
		this.jButton44.setHorizontalTextPosition(0);
		this.jButton44.setMargin(new Insets(1, 1, 1, 1));
		this.jButton44.setBounds(334, 127, 24, 21);
		this.jButton44.setFont(new Font("Arial", 0, 11));
		this.jButton44.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.Remove_Btn.setText("del");
		this.Remove_Btn.setMargin(new Insets(0, 0, 0, 0));
		this.Remove_Btn.setBounds(527, 270, 29, 19);
		this.Remove_Btn.setFont(new Font("Arial", 0, 11));
		this.Remove_Btn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.Remove_BtnMouseReleased(paramMouseEvent);
			}
		});
		this.jButton11.setFont(new Font("Lucida Sans Unicode", 0, 11));
		this.jButton11.setText("Ʒ");
		this.jButton11.setHorizontalTextPosition(0);
		this.jButton11.setMargin(new Insets(1, 1, 1, 1));
		this.jButton11.setBounds(275, 97, 24, 25);
		this.jButton11.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton12.setFont(new Font("Lucida Sans Unicode", 0, 11));
		this.jButton12.setText("ð");
		this.jButton12.setHorizontalTextPosition(0);
		this.jButton12.setMargin(new Insets(1, 1, 1, 1));
		this.jButton12.setBounds(304, 97, 24, 25);
		this.jButton12.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.jButton13.setFont(new Font("Lucida Sans Unicode", 0, 11));
		this.jButton13.setText("-");
		this.jButton13.setHorizontalTextPosition(0);
		this.jButton13.setMargin(new Insets(1, 1, 1, 1));
		this.jButton13.setBounds(333, 97, 24, 25);
		this.jButton13.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
		this.Rename_Btn.setText("edit");
		this.Rename_Btn.setMargin(new Insets(0, 0, 0, 0));
		this.Rename_Btn.setBounds(496, 270, 29, 19);
		this.Rename_Btn.setFont(new Font("Arial", 0, 11));
		this.Rename_Btn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				TopEditor.this.Rename_BtnMouseReleased(paramMouseEvent);
			}
		});
		setTitle("Top Dictionary");
		pack();
		setSize(790, WINDOW_HEIGHT);
		setIconImage(new ImageIcon(super.getClass().getResource("iconA16.GIF"))
				.getImage());
	}

	private void Transcription_TextFKeyReleased(KeyEvent paramKeyEvent) {
		if (this.dictionary.aplyCurrWordChanged(this.Translate_TextF.getText(),
				this.Transcription_TextF.getText()))
			return;
		Alert("Your input was not aplyed!");
	}

	private void Translate_TextFKeyReleased(KeyEvent paramKeyEvent) {
		if (this.dictionary.aplyCurrWordChanged(this.Translate_TextF.getText(),
				this.Transcription_TextF.getText()))
			return;
		Alert("Your input was not aplyed!");
	}

	private void Word_TextFKeyReleased(KeyEvent paramKeyEvent) {
		if (this.dictionary.aplyCurrWordChanged(this.Translate_TextF.getText(),
				this.Transcription_TextF.getText()))
			return;
		Alert("Your input was not aplyed!");
	}

	private void literalButtonsMouseReleased(MouseEvent paramMouseEvent) {
		if (this.dictionary.getCurrentPair() != null) {
			this.Transcription_TextF.setText(this.dictionary.getCurrentPair()
					.getTranscription()
					+ ((JButton) paramMouseEvent.getSource()).getText());
			if (this.dictionary.aplyCurrWordChanged(this.Translate_TextF
					.getText(), this.Transcription_TextF.getText()))
				return;
			Alert("Your input was not aplyed!");
		} else {
			Alert("You mast select one before!");
		}
	}

	private void refresh(boolean paramBoolean1, boolean paramBoolean2) {
		if (this.dictionary.getAudioMan().recorder == null) {
			this.jButtRecordding.setText("start");
			this.jButtPlay.setEnabled(true);
			if (this.dictionary.isCurrentItemAlreadyRecorded()) {
				this.jButtPlay.setEnabled(true);
				this.jButtDeleteRecord.setEnabled(true);
				this.jButtRecordding.setEnabled(false);
			} else {
				this.jButtPlay.setEnabled(false);
				this.jButtDeleteRecord.setEnabled(false);
				this.jButtRecordding.setEnabled(true);
			}
		} else {
			this.jButtRecordding.setText("stop");
			this.jButtPlay.setEnabled(false);
		}
		String str;
		if (this.dictionary.getCurrentPair() == null) {
			setTitle("Top Dictionary - none : ");
			this.Word_TextF.setText("");
			this.Translate_TextF.setText("");
			this.Transcription_TextF.setText("");
			this.jListSamples.setListData(new String[0]);
			this.jButtPlay.setEnabled(false);
			this.jButtDeleteRecord.setEnabled(false);
			this.jButtRecordding.setEnabled(false);
		} else {
			setTitle("Top Dictionary - "
					+ (this.WordsList_Lst.getSelectedIndex() + 1)
					+ " : "
					+ new Integer(this.dictionary.getAllWords().length)
							.toString());
			this.Word_TextF.setText(this.dictionary.getCurrentPair()
					.getEnglish());
			this.Translate_TextF.setText(this.dictionary.getCurrentPair()
					.getRussian());
			this.Transcription_TextF.setText(this.dictionary.getCurrentPair()
					.getTranscription());
			if (paramBoolean2) {
				str = null;
				if (this.dictionary.getCurrSample() != null)
					str = this.dictionary.getCurrSample();
				if (this.dictionary.getCurrentPair().getSamples() != null)
					this.jListSamples.setListData(this.dictionary
							.getCurrentPair().getSamples());
				else
					this.jListSamples.setListData(new String[0]);
				if (this.dictionary.getCurrentPair().getSamples() != null)
					this.jListSamples.setSelectedValue(str, true);
			}
		}
		if (paramBoolean1) {
			str = null;
			if (this.dictionary.getCurrentPair() != null)
				str = this.dictionary.getCurrentPair().getEnglish();
			this.WordsList_Lst.setListData(this.dictionary.getAllWords());
			if ((this.dictionary.setSelected(str))
					&& (this.dictionary.getCurrentPair() != null))
				this.WordsList_Lst.setSelectedValue(this.dictionary
						.getCurrentPair().getEnglish(), true);
		}
		if (this.dictionary.getCurrentPair() == null)
			if (this.dictionary.getAllWords().length > 0)
				this.WordsList_Lst.setSelectedValue(this.dictionary
						.getAllWords()[0], true);
			else
				this.WordsList_Lst.setSelectedValue(null, true);
		inUseListRefresh();
	}

	private void Rename_BtnMouseReleased(MouseEvent paramMouseEvent) {
		if (this.dictionary.getCurrentPair() == null)
			return;
		this.dictionary.renameCurrent(null);
		refresh(true, true);
	}

	private void Remove_BtnMouseReleased(MouseEvent paramMouseEvent) {
		if (this.dictionary.getCurrentPair() == null)
			Alert("You have to select one before!");
		this.dictionary.removeCurrent();
		refresh(true, true);
	}

	private void Add_btnMouseReleased(MouseEvent paramMouseEvent) {
		boolean bool = false;
		if (this.jTxtSearch.getText().equals(""))
			bool = this.dictionary.addNew(null);
		else
			bool = this.dictionary.addNew(this.jTxtSearch.getText());
		if (bool)
			this.jTxtSearch.setText("");
		refresh(true, true);
	}

	private void jTxtSearchKeyReleased(KeyEvent paramKeyEvent) {
		String str = this.jTxtSearch.getText().trim();
		if (paramKeyEvent.getKeyCode() == 10) {
			boolean bool = false;
			if (str.equals(""))
				bool = this.dictionary.addNew(null);
			else
				bool = this.dictionary.addNew(str);
			if (bool)
				this.jTxtSearch.setText("");
			refresh(true, true);
		} else {
			if (str.equals(""))
				return;
			for (int i = this.prevSelectedIndex + 1; i < this.dictionary
					.getAllWords().length; ++i) {
				if (this.dictionary.getAllWords()[i].indexOf(str) == 0) {
					this.WordsList_Lst.setSelectedValue(this.dictionary
							.getAllWords()[i], true);
					this.prevSelectedIndex = i;
					return;
				}
				if (i != this.dictionary.getAllWords().length - 1)
					continue;
				this.prevSelectedIndex = 0;
			}
		}
	}

	private void Load_BtnMouseReleased(MouseEvent paramMouseEvent) {
		this.dictionary.Load();
		System.out.println("1");
		refresh(true, true);
	}

	private void Save_BtnMouseReleased(MouseEvent paramMouseEvent) {
		this.dictionary.saveInFile();
	}

	private void jButtAddSampleMouseReleased(MouseEvent paramMouseEvent) {
		if (this.jTextSamplesSearch.getText().equals(""))
			this.dictionary.addNewSample(null);
		else if (this.dictionary
				.addNewSample(this.jTextSamplesSearch.getText()))
			this.jTextSamplesSearch.setText("");
		refresh(false, true);
	}

	private void jTextSamplesSearchKeyReleased(KeyEvent paramKeyEvent) {
		if (paramKeyEvent.getKeyCode() != 10)
			return;
		if ((!(this.jTextSamplesSearch.getText().equals("")))
				&& (this.dictionary.addNewSample(this.jTextSamplesSearch
						.getText())))
			this.jTextSamplesSearch.setText("");
		refresh(false, true);
	}

	private void jButtRemSampleMouseReleased(MouseEvent paramMouseEvent) {
		this.dictionary.remCurrSample();
		refresh(false, true);
	}

	private void jListSamplesMouseReleased(MouseEvent paramMouseEvent) {
		this.dictionary.setCurrSample((String) this.jListSamples
				.getSelectedValue());
		refresh(false, false);
	}

	private void jButtRecordMouseReleased(MouseEvent paramMouseEvent) {
		if (this.dictionary.getAudioMan().recorder == null)
			this.dictionary.startRecording(null);
		else
			this.dictionary.stopRecording();
		refresh(false, false);
	}

	private void jButtPlayMouseReleased(MouseEvent paramMouseEvent) {
		this.dictionary.playCurrentAudioRecord(true, false);
	}

	private void jButtDeleteRecordMouseReleased(MouseEvent paramMouseEvent) {
		if (!(this.dictionary.isCurrentItemAlreadyRecorded()))
			return;
		this.dictionary.deleteCurrentAudioRecord();
		refresh(false, false);
	}

	private void WordsList_LstKeyReleased(KeyEvent paramKeyEvent) {
		if ((paramKeyEvent.getKeyCode() == 155)
				&& (this.WordsList_Lst.getSelectedIndex() < this.dictionary
						.getAllWords().length)) {
			this.dictionary.getCurrentPair().setInuse(true);
			this.WordsList_Lst.setSelectedIndex(this.WordsList_Lst
					.getSelectedIndex() + 1);
		}
		if ((paramKeyEvent.getKeyCode() != 127)
				|| (this.WordsList_Lst.getSelectedIndex() >= this.dictionary
						.getAllWords().length))
			return;
		this.dictionary.getCurrentPair().setInuse(false);
		this.WordsList_Lst.setSelectedIndex(this.WordsList_Lst
				.getSelectedIndex() + 1);
	}

	private void WordsList_LstMouseReleased(MouseEvent paramMouseEvent) {
	}

	private void word_startMouseReleased(MouseEvent paramMouseEvent) {
		this.dictionary.runWord(false, false);
	}

	private void runTrainerBtnMouseReleased(MouseEvent paramMouseEvent) {
		this.dictionary.runTrainer();
	}

	private void InUseJListMouseReleased(MouseEvent paramMouseEvent) {
		this.WordsList_Lst.setSelectedValue(this.InUseJList.getSelectedValue(),
				true);
	}

	private void InUseJListKeyReleased(KeyEvent paramKeyEvent) {
		if ((paramKeyEvent.getKeyCode() == 127)
				&& (this.WordsList_Lst.getSelectedIndex() < this.dictionary
						.getAllWords().length)) {
			this.dictionary.getCurrentPair().setInuse(false);
			this.WordsList_Lst.setSelectedIndex(this.WordsList_Lst
					.getSelectedIndex() + 1);
		}
		inUseListRefresh();
	}

	private void inUseListRefresh() {
		int i = this.InUseJList.getSelectedIndex();
		Object localObject = this.InUseJList.getSelectedValue();
		this.InUseJList.setListData(this.dictionary.getAllInUseWords());
		if (i == -1)
			return;
		if (i < this.dictionary.getAllInUseWords().length)
			this.InUseJList.setSelectedIndex(i);
		else
			this.InUseJList
					.setSelectedIndex(this.dictionary.getAllInUseWords().length - 1);
	}

	private void addToRight_oneMouseReleased(MouseEvent paramMouseEvent) {
		this.dictionary.getCurrentPair().setInuse(true);
		inUseListRefresh();
	}

	private void addToRight_allMouseReleased(MouseEvent paramMouseEvent) {
		for (Pair localPair : this.dictionary.getAllPairs())
			localPair.setInuse(true);
		inUseListRefresh();
	}

	private void dellFromRight_oneMouseReleased(MouseEvent paramMouseEvent) {
		this.dictionary.getCurrentPair().setInuse(false);
		inUseListRefresh();
	}

	private void dellFromRight_allMouseReleased(MouseEvent paramMouseEvent) {
		for (Pair localPair : this.dictionary.getAllPairs())
			localPair.setInuse(false);
		inUseListRefresh();
	}

	private void selectNextRandomBtnMouseReleased(MouseEvent paramMouseEvent) {
		String str = this.dictionary.getAllInUseWords()[this.nextSelRandom
				.nextInt(this.dictionary.getAllInUsePairs().length)];
		this.InUseJList.setSelectedValue(str, true);
	}

	private void thisMouseReleased(MouseEvent paramMouseEvent) {
	}

	private void jButton45MouseReleased(MouseEvent paramMouseEvent) {
		System.out.println("jButton45.mouseReleased, event=" + paramMouseEvent);
	}

	private void inUseOnlyWordsShowInWord(MouseEvent paramMouseEvent) {
		this.dictionary.runWord(true, false);
	}

	private void jButton47MouseReleased(MouseEvent paramMouseEvent) {
		this.dictionary.runWord(true, true);
	}

	private void jButton48MouseReleased(MouseEvent paramMouseEvent) {
		this.dictionary.upCurrentSample();
		refresh(false, true);
	}

	private void jButton49MouseReleased(MouseEvent paramMouseEvent) {
		if (this.jButton49.getText().equals("<")) {
			this.jButton49.setText(">");
			setSize(570, WINDOW_HEIGHT);
		} else {
			this.jButton49.setText("<");
			setSize(790, WINDOW_HEIGHT);
		}
	}

	private void jButton499MouseReleased(MouseEvent paramMouseEvent) {
		this.dictionary.shuffle();
		refresh(true, true);
	}

	private void jTopStateChanged(ChangeEvent paramChangeEvent) {
		setAlwaysOnTop(this.jTop.isSelected());
	}
}

/*
 * Location: E:\ATG\workspace\Dictionary.jar Qualified Name:
 * com.myjavaserver.aillusions.view.TopEditor Java Class Version: 5 (49.0)
 * JD-Core Version: 0.5.3
 */