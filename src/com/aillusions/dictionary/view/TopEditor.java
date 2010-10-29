package com.aillusions.dictionary.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.aillusions.dictionary.core.Manager;
import com.aillusions.dictionary.model.Dictionary;
import com.aillusions.dictionary.view.components.AudioPanel;
import com.aillusions.dictionary.view.components.KeyBoardPanel;
import com.aillusions.dictionary.view.components.SpecialSymbolsPanel;
import com.aillusions.dictionary.view.components.TopMenuBar;
import com.aillusions.dictionary.view.listener.MainDictPanelListener;
import com.aillusions.dictionary.view.listener.CommandsListener;

public class TopEditor extends JFrame {

	public static final int WINDOW_WIDTH_EXPANDED = 790;
	public static final int WINDOW_WIDTH_SHORT = 570;
	public static final int WINDOW_HEIGHT = 343;
	private static final long serialVersionUID = 1L;

	CommandsListener cmdListener;

	public Manager manager;

	public MainDictPanelListener mainDictPanelListener;		
	
	public int prevSelectedIndex;
	public Random nextSelRandom;

	public SpecialSymbolsPanel specialSymbolsPanel;
	public KeyBoardPanel keyBoardPanel;
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

		manager = new Manager("words.xml");
		manager.Load();
		
		cmdListener = new CommandsListener(manager, this);
		mainDictPanelListener = new MainDictPanelListener(manager, this);
		prevSelectedIndex = 0;
		nextSelRandom = new Random(0L);
		initComponents();
		setJMenuBar(new TopMenuBar(cmdListener, manager));
		
		WordsList_Lst.setListData(manager.getPairsManager().getAllKeys());
		jListSamples.setListData(new String[0]);
		refresh(true, true);
		WordsList_Lst.setSelectedIndex(0);
	}

	public void Alert(String paramString) {
		JOptionPane.showMessageDialog(new JFrame("FrameDemo"), paramString);
	}

	private void initComponents() {

		getContentPane().setLayout(null);
		
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
		jTextSamplesSearch = new JTextField();
		Rename_Btn = new JButton();

		add(specialSymbolsPanel);
		add(keyBoardPanel);
		add(audioPanel);
		add(Remove_Btn);
		add(Rename_Btn);
		add(Transcription_TextF);
		add(Translate_TextF);
		add(Word_TextF);
		add(jButtRemSample);
		add(jScrollPane3);
		add(selectNextRandomBtn);
		add(jTextSamplesSearch);
		add(jTxtSearch);

		specialSymbolsPanel.setBounds(5, 92, 350, 28);
		keyBoardPanel.setBounds(5, 120, 350, 85);
		audioPanel.setBounds(240, 10, 150, 25);		
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

		selectNextRandomBtn.setText("Restore");
		selectNextRandomBtn.setMargin(new Insets(0, 0, 0, 0));
		selectNextRandomBtn.setFont(new Font("Arial", 0, 11));
		selectNextRandomBtn.setBounds(608, 267, 162, 20);
		selectNextRandomBtn.setActionCommand(CommandsListener.RESTORE_SELECTED_REMOVED);
		selectNextRandomBtn.addActionListener(cmdListener);
		
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

		ListSelectionListener localObject = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent paramListSelectionEvent) {
				mainDictPanelListener.InUseJListMouseReleased(paramListSelectionEvent);
			}
		};
		
		InUseJList.addListSelectionListener(localObject);

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

		localObject  = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent paramListSelectionEvent) {
				if (paramListSelectionEvent.getValueIsAdjusting())
					return;
				if ((manager.getCurrentStateManager().getCurrentPair() != null)
						&& (!(manager.getCurrentStateManager().aplyCurrWordChanged(Translate_TextF
								.getText(), Transcription_TextF.getText()))))
					Alert("Your input was not aplyed!");
				if (manager.getCurrentStateManager().setCurrentPairByKey((String) WordsList_Lst
						.getSelectedValue())) {
					refresh(false, true);
					if (manager.getAudioMan().isPlayOnSelections())
						manager.getAudioMan().playCurrentAudioRecord();
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
					manager.getCurrentStateManager().setCurrSample(null);
				else
					manager.getCurrentStateManager().setCurrSample(jListSamples.getSelectedValue()
							.toString());
				if ((!(manager.getAudioMan().isPlayOnSelections()))
						|| (jListSamples.getSelectedIndex() == -1))
					return;
				manager.getAudioMan().playCurrentAudioRecord();
			}
		};

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

		setTitle("Top Dictionary");
		pack();
		setSize(WINDOW_WIDTH_EXPANDED, WINDOW_HEIGHT);
		setIconImage(new ImageIcon(super.getClass().getResource("iconA16.GIF"))
				.getImage());
	}

	public void inUseListRefresh() {
		int i = InUseJList.getSelectedIndex();
		String[] keys =  manager.getTrashManager().getAllKeys();
		InUseJList.setListData(keys);
		if (i == -1)
			return;
		if (i < keys.length){
			InUseJList.setSelectedIndex(i);
		}
		else{
			InUseJList.setSelectedIndex(keys.length - 1);
		}
	}

	public void refresh(boolean paramBoolean1, boolean paramBoolean2) {
		if (manager.getAudioMan().recorder == null) {
			audioPanel.jButtRecordding.setText("start");
			audioPanel.jButtPlay.setEnabled(true);
			if (manager.getAudioMan().isCurrentItemAlreadyRecorded()) {
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
		if (manager.getCurrentStateManager().getCurrentPair() == null) {
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
					+ new Integer(manager.getPairsManager().getAllKeys().length).toString());
			Word_TextF.setText(manager.getCurrentStateManager().getCurrentPair().getEnglish());
			Translate_TextF.setText(manager.getCurrentStateManager().getCurrentPair().getRussian());
			Transcription_TextF.setText(manager.getCurrentStateManager().getCurrentPair()
					.getTranscription());
			if (paramBoolean2) {
				str = null;
				if (manager.getCurrentStateManager().getCurrSample() != null)
					str = manager.getCurrentStateManager().getCurrSample();
				if (manager.getCurrentStateManager().getCurrentPair().getSamples() != null)
					jListSamples.setListData(manager.getCurrentStateManager().getCurrentPair()
							.getSamples());
				else
					jListSamples.setListData(new String[0]);
				if (manager.getCurrentStateManager().getCurrentPair().getSamples() != null)
					jListSamples.setSelectedValue(str, true);
			}
		}
		if (paramBoolean1) {
			str = null;
			if (manager.getCurrentStateManager().getCurrentPair() != null)
				str = manager.getCurrentStateManager().getCurrentPair().getEnglish();
			WordsList_Lst.setListData(manager.getPairsManager().getAllKeys());
			if ((manager.getCurrentStateManager().setCurrentPairByKey(str))
					&& (manager.getCurrentStateManager().getCurrentPair() != null))
				WordsList_Lst.setSelectedValue(manager.getCurrentStateManager().getCurrentPair()
						.getEnglish(), true);
		}
		if (manager.getCurrentStateManager().getCurrentPair() == null)
			if (manager.getPairsManager().getAllKeys().length > 0)
				WordsList_Lst.setSelectedValue(manager.getPairsManager().getAllKeys()[0],
						true);
			else
				WordsList_Lst.setSelectedValue(null, true);
		inUseListRefresh();
	}

	public void regenDictList() {
		
		JMenu submenu;
		JMenuItem menuItem;
	
		submenu = (JMenu)getJMenuBar().getMenu(0).getItem(3);
		submenu.removeAll();	
		
		List<Dictionary> dictioanries = manager.getWorkspaceManager().getWorkspace().getDictioanries();
		ButtonGroup group = new ButtonGroup();	
		
		for(Dictionary d : dictioanries){
			menuItem = new JRadioButtonMenuItem(d.getDisplayName());
			group.add(menuItem);
			menuItem.addActionListener(cmdListener);
			menuItem.setActionCommand(CommandsListener.SELECT_DICT);
			if(manager.getCurrentStateManager().getCurrentDictionary().getDisplayName().equals(d.getDisplayName())){
				menuItem.setSelected(true);
			}
			submenu.add(menuItem);
		}
	}
}
