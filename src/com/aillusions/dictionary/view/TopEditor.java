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
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.aillusions.dictionary.Manager;
import com.aillusions.dictionary.view.components.AudioPanel;
import com.aillusions.dictionary.view.components.KeyBoardPanel;
import com.aillusions.dictionary.view.components.SpecialSymbolsPanel;
import com.aillusions.dictionary.view.components.TopMenuBar;
import com.aillusions.dictionary.view.listener.MainDictPanelListener;
import com.aillusions.dictionary.view.listener.MenuListener;

public class TopEditor extends JFrame {

	public static final int WINDOW_WIDTH_EXPANDED = 790;
	public static final int WINDOW_WIDTH_SHORT = 570;
	public static final int WINDOW_HEIGHT = 343;
	private static final long serialVersionUID = 1L;

	MenuListener menuListener;

	public SpecialSymbolsPanel specialSymbolsPanel;
	public KeyBoardPanel keyBoardPanel;
	public MainDictPanelListener mainDictPanelListener;
	public Manager dictionary;
	public int prevSelectedIndex;
	public Random nextSelRandom;

	public JButton Remove_Btn;
	public JButton Rename_Btn;

	public JTextField Transcription_TextF;
	public JTextField Translate_TextF;
	public JTextField Word_TextF;

	public JList WordsList_Lst;
	public AudioPanel audioPanel;

	public JScrollPane jScrollPane2;
	public JScrollPane jScrollPane1;
	public JScrollPane jScrollPane3;
	public JButton selectNextRandomBtn;
	public JButton dellFromRight_all;
	public JButton dellFromRight_one;
	public JButton addToRight_all;
	public JButton addToRight_one;
	public JList InUseJList;
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
		menuListener = new MenuListener(dictionary, this);
		mainDictPanelListener = new MainDictPanelListener(dictionary, this);
		prevSelectedIndex = 0;
		nextSelRandom = new Random(0L);
		initComponents();
		setJMenuBar(new TopMenuBar(menuListener));
		dictionary.Load();
		WordsList_Lst.setListData(dictionary.getAllWords());
		jListSamples.setListData(new String[0]);
		refresh(true, true);
		WordsList_Lst.setSelectedIndex(0);
	}

	public void Alert(String paramString) {
		JOptionPane.showMessageDialog(new JFrame("FrameDemo"), paramString);
	}

	private void initComponents() {

		getContentPane().setLayout(null);

		Rename_Btn = new JButton();
		specialSymbolsPanel = new SpecialSymbolsPanel(mainDictPanelListener);
		keyBoardPanel = new KeyBoardPanel(mainDictPanelListener);
		audioPanel = new AudioPanel(mainDictPanelListener);

		Word_TextF = new JTextField();
		Translate_TextF = new JTextField();
		Transcription_TextF = new JTextField();
		Remove_Btn = new JButton();
		jButtRemSample = new JButton();

		jTxtSearch = new JTextField();

		jScrollPane3 = new JScrollPane();
		selectNextRandomBtn = new JButton();
		dellFromRight_all = new JButton();
		dellFromRight_one = new JButton();
		addToRight_all = new JButton();
		addToRight_one = new JButton();
		jTextSamplesSearch = new JTextField();

		specialSymbolsPanel.setBounds(5, 92, 350, 28);
		add(specialSymbolsPanel);

		keyBoardPanel.setBounds(5, 120, 350, 85);
		add(keyBoardPanel);

		audioPanel.setBounds(240, 10, 150, 25);
		add(audioPanel);

		add(Remove_Btn);
		add(Rename_Btn);
		add(Transcription_TextF);
		add(Translate_TextF);
		add(Word_TextF);
		add(jButtRemSample);
		add(jScrollPane3);
		add(selectNextRandomBtn);
		add(dellFromRight_all);
		add(dellFromRight_one);
		add(addToRight_all);
		add(addToRight_one);
		add(jTextSamplesSearch);
		add(jTxtSearch);

		Translate_TextF.setBounds(6, 38, 230, 21);

		jButtRemSample.setText("del");
		jButtRemSample.setMargin(new Insets(0, 0, 0, 0));
		jButtRemSample.setBounds(356, 271, 29, 19);
		jButtRemSample.setFont(new Font("Arial", 0, 11));
		jButtRemSample.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener
						.jButtRemSampleMouseReleased(paramMouseEvent);
			}
		});

		jTxtSearch.setBounds(393, 269, 102, 21);
		jTxtSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent paramKeyEvent) {
				mainDictPanelListener.jTxtSearchKeyReleased(paramKeyEvent);
			}
		});

		jTextSamplesSearch.setBounds(6, 270, 348, 21);
		jTextSamplesSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent paramKeyEvent) {
				mainDictPanelListener
						.jTextSamplesSearchKeyReleased(paramKeyEvent);
			}
		});

		addToRight_one.setText("->");
		addToRight_one.setMargin(new Insets(1, 1, 1, 1));
		addToRight_one.setFont(new Font("Lucida Sans Unicode", 0, 11));
		addToRight_one.setBounds(565, 74, 31, 20);
		addToRight_one.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener
						.addToRight_oneMouseReleased(paramMouseEvent);
			}
		});

		addToRight_all.setText(">>");
		addToRight_all.setMargin(new Insets(1, 1, 1, 1));
		addToRight_all.setFont(new Font("Lucida Sans Unicode", 0, 11));
		addToRight_all.setBounds(565, 100, 31, 20);
		addToRight_all.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener
						.addToRight_allMouseReleased(paramMouseEvent);
			}
		});

		dellFromRight_one.setText("<-");
		dellFromRight_one.setMargin(new Insets(1, 1, 1, 1));
		dellFromRight_one.setFont(new Font("Lucida Sans Unicode", 0, 11));
		dellFromRight_one.setBounds(565, 153, 31, 20);
		dellFromRight_one.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener
						.dellFromRight_oneMouseReleased(paramMouseEvent);
			}
		});

		dellFromRight_all.setText("<<");
		dellFromRight_all.setMargin(new Insets(1, 1, 1, 1));
		dellFromRight_all.setFont(new Font("Lucida Sans Unicode", 0, 11));
		dellFromRight_all.setBounds(565, 179, 31, 20);
		dellFromRight_all.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener
						.dellFromRight_allMouseReleased(paramMouseEvent);
			}
		});

		selectNextRandomBtn.setText("> go next random >");
		selectNextRandomBtn.setMargin(new Insets(0, 0, 0, 0));
		selectNextRandomBtn.setFont(new Font("Arial", 0, 11));
		selectNextRandomBtn.setBounds(608, 267, 162, 20);
		selectNextRandomBtn.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener
						.selectNextRandomBtnMouseReleased(paramMouseEvent);
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
				WordsList_Lst.setSelectedValue(InUseJList.getSelectedValue(),
						true);
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
		WordsList_Lst.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				new Color(255, 255, 255)));
		WordsList_Lst.setForeground(new Color(0, 0, 0));
		WordsList_Lst.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener
						.WordsList_LstMouseReleased(paramMouseEvent);
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
						&& (!(dictionary.aplyCurrWordChanged(Translate_TextF
								.getText(), Transcription_TextF.getText()))))
					Alert("Your input was not aplyed!");
				if (dictionary.setSelected((String) WordsList_Lst
						.getSelectedValue())) {
					refresh(false, true);
					if (dictionary.isPlayOnSelections())
						dictionary.playCurrentAudioRecord(true, false);
				}
				WordsList_Lst.setSelectedValue(
						WordsList_Lst.getSelectedValue(), true);
			}
		};
		WordsList_Lst
				.addListSelectionListener((ListSelectionListener) localObject);

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
				mainDictPanelListener
						.jListSamplesMouseReleased(paramMouseEvent);
			}
		});
		localObject = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent paramListSelectionEvent) {
				if (paramListSelectionEvent.getValueIsAdjusting())
					return;
				if (jListSamples.getSelectedIndex() == -1)
					dictionary.setCurrSample(null);
				else
					dictionary.setCurrSample(jListSamples.getSelectedValue()
							.toString());
				if ((!(dictionary.isPlayOnSelections()))
						|| (jListSamples.getSelectedIndex() == -1))
					return;
				dictionary.playCurrentAudioRecord(true, false);
			}
		};
		jListSamples
				.addListSelectionListener((ListSelectionListener) localObject);

		Word_TextF.setEditable(false);
		Word_TextF.setFont(new Font("Tahoma", 1, 11));
		Word_TextF.setBounds(6, 11, 230, 21);
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
				mainDictPanelListener
						.Transcription_TextFKeyReleased(paramKeyEvent);
			}
		});

		WordsList_Lst.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4",
					"Item 5" };

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int paramInt) {
				return strings[paramInt];
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
		setIconImage(new ImageIcon(super.getClass().getResource("iconA16.GIF"))
				.getImage());
	}

	public void inUseListRefresh() {
		int i = InUseJList.getSelectedIndex();
		Object localObject = InUseJList.getSelectedValue();
		InUseJList.setListData(dictionary.getAllInUseWords());
		if (i == -1)
			return;
		if (i < dictionary.getAllInUseWords().length)
			InUseJList.setSelectedIndex(i);
		else
			InUseJList
					.setSelectedIndex(dictionary.getAllInUseWords().length - 1);
	}

	public void refresh(boolean paramBoolean1, boolean paramBoolean2) {
		if (dictionary.getAudioMan().recorder == null) {
			audioPanel.jButtRecordding.setText("start");
			audioPanel.jButtPlay.setEnabled(true);
			if (dictionary.isCurrentItemAlreadyRecorded()) {
				audioPanel.jButtPlay.setEnabled(true);
				audioPanel.jButtDeleteRecord.setEnabled(true);
				audioPanel.jButtRecordding.setEnabled(false);
			} else {
				audioPanel.jButtPlay.setEnabled(false);
				audioPanel.jButtDeleteRecord.setEnabled(false);
				audioPanel.jButtRecordding.setEnabled(true);
			}
		} else {
			audioPanel.jButtRecordding.setText("stop");
			audioPanel.jButtPlay.setEnabled(false);
		}
		String str;
		if (dictionary.getCurrentPair() == null) {
			setTitle("Top Dictionary - none : ");
			Word_TextF.setText("");
			Translate_TextF.setText("");
			Transcription_TextF.setText("");
			jListSamples.setListData(new String[0]);
			audioPanel.jButtPlay.setEnabled(false);
			audioPanel.jButtDeleteRecord.setEnabled(false);
			audioPanel.jButtRecordding.setEnabled(false);
		} else {
			setTitle("Top Dictionary - "
					+ (WordsList_Lst.getSelectedIndex() + 1) + " : "
					+ new Integer(dictionary.getAllWords().length).toString());
			Word_TextF.setText(dictionary.getCurrentPair().getEnglish());
			Translate_TextF.setText(dictionary.getCurrentPair().getRussian());
			Transcription_TextF.setText(dictionary.getCurrentPair()
					.getTranscription());
			if (paramBoolean2) {
				str = null;
				if (dictionary.getCurrSample() != null)
					str = dictionary.getCurrSample();
				if (dictionary.getCurrentPair().getSamples() != null)
					jListSamples.setListData(dictionary.getCurrentPair()
							.getSamples());
				else
					jListSamples.setListData(new String[0]);
				if (dictionary.getCurrentPair().getSamples() != null)
					jListSamples.setSelectedValue(str, true);
			}
		}
		if (paramBoolean1) {
			str = null;
			if (dictionary.getCurrentPair() != null)
				str = dictionary.getCurrentPair().getEnglish();
			WordsList_Lst.setListData(dictionary.getAllWords());
			if ((dictionary.setSelected(str))
					&& (dictionary.getCurrentPair() != null))
				WordsList_Lst.setSelectedValue(dictionary.getCurrentPair()
						.getEnglish(), true);
		}
		if (dictionary.getCurrentPair() == null)
			if (dictionary.getAllWords().length > 0)
				WordsList_Lst.setSelectedValue(dictionary.getAllWords()[0],
						true);
			else
				WordsList_Lst.setSelectedValue(null, true);
		inUseListRefresh();
	}

}
