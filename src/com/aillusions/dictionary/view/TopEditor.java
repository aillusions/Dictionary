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
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.aillusions.dictionary.Manager;
import com.aillusions.dictionary.view.components.KeyBoardPanel;
import com.aillusions.dictionary.view.components.SpecialSymbolsPanel;
import com.aillusions.dictionary.view.components.TopMenuBar;
import com.aillusions.dictionary.view.listener.MainDictPanelListener;

public class TopEditor extends JFrame {
	public static final int WINDOW_WIDTH_EXPANDED = 790;
	public static final int WINDOW_WIDTH_SHORT = 570;
	public static final int WINDOW_HEIGHT = 343;
	private static final long serialVersionUID = 1L;
	
	public SpecialSymbolsPanel specialSymbolsPanel;
	public KeyBoardPanel keyBoardPanel;
	public MainDictPanelListener mainDictPanelListener;
	public Manager dictionary;
	public int prevSelectedIndex;
	public Random nextSelRandom;
	public JButton Load_Btn;
	public JButton Remove_Btn;
	public JButton Rename_Btn;
	public JButton Save_Btn;
	public JTextField Transcription_TextF;
	public JTextField Translate_TextF;
	public JTextField Word_TextF;

	public JButton jButton499;


	public JCheckBox jChBoxPlayOnSelections;
	public JButton jButtDeleteRecord;
	public JList WordsList_Lst;
	public JButton jButton10;
	public JButton jButtPlay;
	public JButton jButtRecordding;
	public JScrollPane jScrollPane2;
	public JCheckBox jTop;
	public JScrollPane jScrollPane1;
	public JScrollPane jScrollPane3;
	public JButton jButton50;
	public JButton jButton49;
	public JButton jButton48;
	public JButton jButton47;
	public JButton jButton46;
	public JButton jButton45;
	public JButton selectNextRandomBtn;
	public JButton dellFromRight_all;
	public JButton dellFromRight_one;
	public JButton addToRight_all;
	public JButton addToRight_one;
	public JList InUseJList;
	public JButton runTrainerBtn;
	public JButton word_start;
	public JTextField jTextSamplesSearch;
	public JTextField jTxtSearch;
	public JButton jButtRemSample;
	public JList jListSamples;

	public TopEditor() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception localException) {
			localException.printStackTrace();
		}		
		
		dictionary = new Manager("words.xml");
		mainDictPanelListener = new MainDictPanelListener(dictionary, this);
		prevSelectedIndex = 0;
		nextSelRandom = new Random(0L);
		initComponents();
		setJMenuBar(new TopMenuBar());
		dictionary.Load();
		WordsList_Lst.setListData(dictionary.getAllWords());
		jListSamples.setListData(new String[0]);
		mainDictPanelListener.refresh(true, true);
		WordsList_Lst.setSelectedIndex(0);
	}

	private void Alert(String paramString) {
		JOptionPane.showMessageDialog(new JFrame("FrameDemo"), paramString);
	}

	private void initComponents() {

		getContentPane().setLayout(null);
		
		Rename_Btn = new JButton();
		specialSymbolsPanel = new SpecialSymbolsPanel(mainDictPanelListener);
		keyBoardPanel = new KeyBoardPanel(mainDictPanelListener);
		Word_TextF = new JTextField();
		Translate_TextF = new JTextField();
		Transcription_TextF = new JTextField();
		Save_Btn = new JButton();
		Load_Btn = new JButton();		
		Remove_Btn = new JButton();
		jButtRemSample = new JButton();
		jButtPlay = new JButton();
		jButtRecordding = new JButton();
		jTxtSearch = new JTextField();
		jButtDeleteRecord = new JButton();
		jButton10 = new JButton();
		jChBoxPlayOnSelections = new JCheckBox();
		jScrollPane3 = new JScrollPane();
		jButton50 = new JButton();
		jButton499 = new JButton();
		jButton49 = new JButton();
		jButton48 = new JButton();
		jButton47 = new JButton();
		jButton46 = new JButton();
		jButton45 = new JButton();
		selectNextRandomBtn = new JButton();
		dellFromRight_all = new JButton();
		dellFromRight_one = new JButton();
		addToRight_all = new JButton();
		addToRight_one = new JButton();
		jTextSamplesSearch = new JTextField();
		word_start = new JButton();
		runTrainerBtn = new JButton();
		
		
		
		
		
		specialSymbolsPanel.setBounds(5, 92, 350, 28);
		add(specialSymbolsPanel);		
		keyBoardPanel.setBounds(5, 120, 350, 85);
		add(keyBoardPanel);
		add(Remove_Btn);
		add(Save_Btn);
		add(Load_Btn);
		add(Rename_Btn);
		add(Transcription_TextF);
		add(Translate_TextF);
		add(Word_TextF);
		add(jButtRemSample);
		add(jScrollPane3);
		add(jButton50);
		add(jButton499);
		add(jButton49);
		add(jButton48);
		add(jButton47);
		add(jButton46);
		add(jButton45);
		add(selectNextRandomBtn);
		add(dellFromRight_all);
		add(dellFromRight_one);
		add(addToRight_all);
		add(addToRight_one);
		add(runTrainerBtn);
		add(word_start);
		add(jTextSamplesSearch);
		add(jTxtSearch);
		add(jChBoxPlayOnSelections);
		add(jButtDeleteRecord);
		add(jButton10);
		add(jButtRecordding);
		add(jButtPlay);

		Translate_TextF.setBounds(6, 38, 230, 21);
		
		
		jButtRemSample.setText("del");
		jButtRemSample.setMargin(new Insets(0, 0, 0, 0));
		jButtRemSample.setBounds(356, 271, 29, 19);
		jButtRemSample.setFont(new Font("Arial", 0, 11));
		jButtRemSample.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.jButtRemSampleMouseReleased(paramMouseEvent);
			}
		});
		
		
		jButtPlay.setText(">");
		jButtPlay.setBounds(243, 64, 24, 21);
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
		jButtRecordding.setBounds(303, 64, 53, 21);
		jButtRecordding.setToolTipText("Recording pronunciation");
		jButtRecordding.setMargin(new Insets(0, 0, 0, 0));
		jButtRecordding.setFont(new Font("Arial", 0, 11));
		jButtRecordding.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.jButtRecordMouseReleased(paramMouseEvent);
			}
		});
		

		jButton10.setText("||");
		jButton10.setMargin(new Insets(0, 0, 0, 0));
		jButton10.setForeground(new Color(255, 0, 0));
		jButton10.setToolTipText("Pause");
		jButton10.setBounds(273, 64, 24, 21);
		jButton10.setEnabled(false);
		jButton10.setFont(new Font("Arial", 0, 11));


		jButtDeleteRecord.setText("X");
		jButtDeleteRecord.setMargin(new Insets(0, 0, 0, 0));
		jButtDeleteRecord.setForeground(new Color(0, 0, 0));
		jButtDeleteRecord.setToolTipText("Delete record");
		jButtDeleteRecord.setBounds(361, 64, 24, 21);
		jButtDeleteRecord.setFont(new Font("Arial", 0, 11));
		jButtDeleteRecord.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.jButtDeleteRecordMouseReleased(paramMouseEvent);
			}
		});


		jChBoxPlayOnSelections.setText("Pronunciation");
		jChBoxPlayOnSelections.setBounds(243, 38, 85, 18);
		jChBoxPlayOnSelections
				.setToolTipText("Play record by selection in navigator");
		jChBoxPlayOnSelections.setFont(new Font("Arial", 0, 11));


		jTxtSearch.setBounds(393, 269, 102, 21);
		jTxtSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent paramKeyEvent) {
				mainDictPanelListener.jTxtSearchKeyReleased(paramKeyEvent);
			}
		});
		

		jTextSamplesSearch.setBounds(6, 270, 348, 21);
		jTextSamplesSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent paramKeyEvent) {
				mainDictPanelListener.jTextSamplesSearchKeyReleased(paramKeyEvent);
			}
		});
		

		word_start.setMargin(new Insets(1, 1, 1, 1));
		word_start.setFont(new Font("Lucida Sans Unicode", 0, 11));

		word_start.setText("W");
		word_start.setBounds(359, 38, 25, 20);
		word_start.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.word_startMouseReleased(paramMouseEvent);
			}
		});


		runTrainerBtn.setText("T");
		runTrainerBtn.setMargin(new Insets(1, 1, 1, 1));
		runTrainerBtn.setFont(new Font("Lucida Sans Unicode", 0, 11));
		runTrainerBtn.setBounds(332, 38, 25, 20);
		runTrainerBtn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.runTrainerBtnMouseReleased(paramMouseEvent);
			}
		});


		addToRight_one.setText("->");
		addToRight_one.setMargin(new Insets(1, 1, 1, 1));
		addToRight_one.setFont(new Font("Lucida Sans Unicode", 0, 11));
		addToRight_one.setBounds(565, 74, 31, 20);
		addToRight_one.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.addToRight_oneMouseReleased(paramMouseEvent);
			}
		});


		addToRight_all.setText(">>");
		addToRight_all.setMargin(new Insets(1, 1, 1, 1));
		addToRight_all.setFont(new Font("Lucida Sans Unicode", 0, 11));
		addToRight_all.setBounds(565, 100, 31, 20);
		addToRight_all.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.addToRight_allMouseReleased(paramMouseEvent);
			}
		});


		dellFromRight_one.setText("<-");
		dellFromRight_one.setMargin(new Insets(1, 1, 1, 1));
		dellFromRight_one.setFont(new Font("Lucida Sans Unicode", 0, 11));
		dellFromRight_one.setBounds(565, 153, 31, 20);
		dellFromRight_one.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.dellFromRight_oneMouseReleased(paramMouseEvent);
			}
		});


		dellFromRight_all.setText("<<");
		dellFromRight_all.setMargin(new Insets(1, 1, 1, 1));
		dellFromRight_all.setFont(new Font("Lucida Sans Unicode", 0, 11));
		dellFromRight_all.setBounds(565, 179, 31, 20);
		dellFromRight_all.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.dellFromRight_allMouseReleased(paramMouseEvent);
			}
		});


		selectNextRandomBtn.setText("> go next random >");
		selectNextRandomBtn.setMargin(new Insets(0, 0, 0, 0));
		selectNextRandomBtn.setFont(new Font("Arial", 0, 11));
		selectNextRandomBtn.setBounds(608, 267, 162, 20);
		selectNextRandomBtn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.selectNextRandomBtnMouseReleased(paramMouseEvent);
			}
		});


		jButton45.setText("W");
		jButton45.setMargin(new Insets(1, 1, 1, 1));
		jButton45.setFont(new Font("Lucida Sans Unicode", 0, 11));
		jButton45.setBounds(359, 38, 25, 20);
		jButton45.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.jButton45MouseReleased(paramMouseEvent);
			}
		});


		jButton46.setText("W");
		jButton46.setMargin(new Insets(1, 1, 1, 1));
		jButton46.setFont(new Font("Lucida Sans Unicode", 0, 11));
		jButton46.setBounds(565, 36, 25, 21);
		jButton46.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.inUseOnlyWordsShowInWord(paramMouseEvent);
			}
		});


		jButton47.setText("W");
		jButton47.setMargin(new Insets(1, 1, 1, 1));
		jButton47.setFont(new Font("Lucida Sans Unicode", 0, 11));
		jButton47.setBounds(365, 179, 21, 21);
		jButton47.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.jButton47MouseReleased(paramMouseEvent);
			}
		});


		jButton48.setText("▲");
		jButton48.setMargin(new Insets(1, 1, 1, 1));
		jButton48.setFont(new Font("Lucida Sans Unicode", 0, 11));
		jButton48.setBounds(340, 179, 23, 21);
		jButton48.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.jButton48MouseReleased(paramMouseEvent);
			}
		});


		jButton49.setText(">");
		jButton49.setMargin(new Insets(1, 1, 1, 1));
		jButton49.setFont(new Font("Lucida Sans Unicode", 0, 11));
		jButton49.setBounds(363, 98, 22, 23);
		jButton49.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.jButton49MouseReleased(paramMouseEvent);
			}
		});


		jButton499.setText("M");
		jButton499.setMargin(new Insets(0, 0, 0, 0));
		jButton499.setFont(new Font("Lucida Sans Unicode", 0, 11));
		jButton499.setBounds(363, 125, 22, 23);
		jButton499.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.jButton499MouseReleased(paramMouseEvent);
			}
		});


		jButton50.setText("About");
		jButton50.setBounds(217, 11, 50, 20);
		jButton50.setMargin(new Insets(0, 0, 0, 0));
		jButton50.setFont(new Font("Arial", 0, 11));
		jButton50.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				About localAbout = new About();
				localAbout.frameConstructor();
				localAbout.setVisible(true);
			}
		});


		jScrollPane3.setBounds(608, 12, 159, 249);
		DefaultComboBoxModel localDefaultComboBoxModel = new DefaultComboBoxModel(
				new String[] { "Item One", "Item Two" });
		InUseJList = new JList();
		jScrollPane3.setViewportView(InUseJList);
		InUseJList.setModel(localDefaultComboBoxModel);
		InUseJList.setSelectedIndex(0);
		InUseJList.setFont(new Font("Arial", 0, 11));
		InUseJList.setBounds(608, 12, 159, 249);
		InUseJList.setBorder(BorderFactory.createEtchedBorder(1));
		InUseJList.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent paramKeyEvent) {
				mainDictPanelListener.InUseJListKeyReleased(paramKeyEvent);
			}
		});
		InUseJList.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.InUseJListMouseReleased(paramMouseEvent);
			}
		});
		Object localObject = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent paramListSelectionEvent) {
				if ((paramListSelectionEvent.getValueIsAdjusting())
						|| ((!(InUseJList.isFocusOwner())) && (!(selectNextRandomBtn
								.isFocusOwner()))))
					return;
				WordsList_Lst.setSelectedValue(
						InUseJList.getSelectedValue(), true);
			}
		};
		InUseJList
				.addListSelectionListener((ListSelectionListener) localObject);
		jScrollPane1 = new JScrollPane();
		add(jScrollPane1);
		jScrollPane1.setBounds(393, 12, 164, 251);
		localDefaultComboBoxModel = new DefaultComboBoxModel(new String[] {
				"Item One", "Item Two" });
		WordsList_Lst = new JList();
		jScrollPane1.setViewportView(WordsList_Lst);
		WordsList_Lst.setModel(localDefaultComboBoxModel);
		WordsList_Lst.setBounds(393, 12, 164, 251);
		WordsList_Lst.setFont(new Font("Arial", 0, 11));
		WordsList_Lst.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
				1, new Color(255, 255, 255)));
		WordsList_Lst.setForeground(new Color(0, 0, 0));
		WordsList_Lst.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.WordsList_LstMouseReleased(paramMouseEvent);
			}
		});
		WordsList_Lst.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent paramKeyEvent) {
				mainDictPanelListener.WordsList_LstKeyReleased(paramKeyEvent);
			}
		});
		localObject = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent paramListSelectionEvent) {
				if (paramListSelectionEvent.getValueIsAdjusting())
					return;
				if ((dictionary.getCurrentPair() != null)
						&& (!(dictionary.aplyCurrWordChanged(
								Translate_TextF.getText(),
								Transcription_TextF.getText()))))
					Alert("Your input was not aplyed!");
				if (dictionary
						.setSelected((String) WordsList_Lst
								.getSelectedValue())) {
					mainDictPanelListener.refresh(false, true);
					if (jChBoxPlayOnSelections.isSelected())
						dictionary.playCurrentAudioRecord(true,
								false);
				}
				WordsList_Lst.setSelectedValue(
						WordsList_Lst.getSelectedValue(), true);
			}
		};
		WordsList_Lst
				.addListSelectionListener((ListSelectionListener) localObject);
		jTop = new JCheckBox();
		add(jTop);
		jTop.setText("top");
		jTop.setBounds(351, 154, 36, 18);
		jTop.setMargin(new Insets(0, 0, 0, 0));
		jTop.setFont(new Font("Arial", 0, 11));
		jTop.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent paramChangeEvent) {
				mainDictPanelListener.jTopStateChanged(paramChangeEvent);
			}
		});
		jScrollPane2 = new JScrollPane();
		add(jScrollPane2);
		jScrollPane2.setBounds(6, 210, 379, 55);
		localDefaultComboBoxModel = new DefaultComboBoxModel(new String[] {
				"Item One", "Item Two" });
		jListSamples = new JList();
		jScrollPane2.setViewportView(jListSamples);
		jListSamples.setModel(localDefaultComboBoxModel);
		jListSamples.setBounds(6, 210, 379, 55);
		jListSamples.setFont(new Font("Arial", 0, 13));
		jListSamples.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.jListSamplesMouseReleased(paramMouseEvent);
			}
		});
		localObject = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent paramListSelectionEvent) {
				if (paramListSelectionEvent.getValueIsAdjusting())
					return;
				if (jListSamples.getSelectedIndex() == -1)
					dictionary.setCurrSample(null);
				else
					dictionary
							.setCurrSample(jListSamples
									.getSelectedValue().toString());
				if ((!(jChBoxPlayOnSelections.isSelected()))
						|| (jListSamples.getSelectedIndex() == -1))
					return;
				dictionary.playCurrentAudioRecord(true, false);
			}
		};
		jListSamples.addListSelectionListener((ListSelectionListener) localObject);


		Word_TextF.setEditable(false);
		Word_TextF.setFont(new Font("Tahoma", 1, 11));
		Word_TextF.setBounds(6, 11, 205, 21);
		Word_TextF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent paramKeyEvent) {
				mainDictPanelListener.Word_TextFKeyReleased(paramKeyEvent);
			}
		});
		Translate_TextF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent paramKeyEvent) {
				mainDictPanelListener.Translate_TextFKeyReleased(paramKeyEvent);
			}
		});
		Transcription_TextF.setFont(new Font("Lucida Sans Unicode", 0, 14));
		Transcription_TextF.setBounds(6, 65, 230, 25);
		Transcription_TextF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent paramKeyEvent) {
				mainDictPanelListener.Transcription_TextFKeyReleased(paramKeyEvent);
			}
		});

		WordsList_Lst.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int paramInt) {
				return strings[paramInt];
			}
		});
		Save_Btn.setText("save");
		Save_Btn.setMargin(new Insets(0, 0, 0, 0));
		Save_Btn.setBounds(328, 11, 56, 20);
		Save_Btn.setFont(new Font("Arial", 0, 11));
		Save_Btn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.Save_BtnMouseReleased(paramMouseEvent);
			}
		});
		Load_Btn.setText("load");
		Load_Btn.setMargin(new Insets(0, 0, 0, 0));
		Load_Btn.setBounds(270, 11, 54, 20);
		Load_Btn.setFont(new Font("Arial", 0, 11));
		Load_Btn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.Load_BtnMouseReleased(paramMouseEvent);
			}
		});

		Remove_Btn.setText("del");
		Remove_Btn.setMargin(new Insets(0, 0, 0, 0));
		Remove_Btn.setBounds(527, 270, 29, 19);
		Remove_Btn.setFont(new Font("Arial", 0, 11));
		Remove_Btn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.Remove_BtnMouseReleased(paramMouseEvent);
			}
		});

		Rename_Btn.setText("edit");
		Rename_Btn.setMargin(new Insets(0, 0, 0, 0));
		Rename_Btn.setBounds(496, 270, 29, 19);
		Rename_Btn.setFont(new Font("Arial", 0, 11));
		Rename_Btn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.Rename_BtnMouseReleased(paramMouseEvent);
			}
		});
		
		setDefaultCloseOperation(3);
		setLocationByPlatform(true);
		setResizable(false);
		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.thisMouseReleased(paramMouseEvent);
			}
		});
		setTitle("Top Dictionary");
		pack();
		setSize(WINDOW_WIDTH_EXPANDED, WINDOW_HEIGHT);
		setIconImage(new ImageIcon(super.getClass().getResource("iconA16.GIF")).getImage());
	}


}
