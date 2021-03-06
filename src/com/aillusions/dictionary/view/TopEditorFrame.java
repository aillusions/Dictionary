package com.aillusions.dictionary.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

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

import org.apache.log4j.Logger;

import com.aillusions.dictionary.core.Manager;
import com.aillusions.dictionary.model.Dictionary;
import com.aillusions.dictionary.view.components.AudioPanel;
import com.aillusions.dictionary.view.components.KeyBoardPanel;
import com.aillusions.dictionary.view.components.SpecialSymbolsPanel;
import com.aillusions.dictionary.view.components.TopMenuBar;
import com.aillusions.dictionary.view.listener.CommandsListener;
import com.aillusions.dictionary.view.listener.MainDictPanelListener;

public class TopEditorFrame extends JFrame {

	protected final static Logger l = Logger.getLogger(TopEditorFrame.class);

	public static final int WINDOW_WIDTH_EXPANDED = 790;
	public static final int WINDOW_WIDTH_SHORT = 570;
	public static final int WINDOW_HEIGHT = 343;
	private static final long serialVersionUID = 1L;

	private CommandsListener cmdListener;
	private Manager manager;
	private MainDictPanelListener mainDictPanelListener;

	private int prevSelectedIndex;

	public SpecialSymbolsPanel specialSymbolsPanel;
	public KeyBoardPanel keyBoardPanel;
	public JButton Remove_Btn;
	public JButton Rename_Btn;
	public JTextField Transcription_TextF;
	public JTextField Translate_TextF;
	public JTextField Word_TextF;
	public JList wordsLst;
	public AudioPanel audioPanel;
	public JScrollPane jScrollPane2;
	public JScrollPane jScrollPane1;
	public JScrollPane jScrollPane3;
	public JButton selectNextRandomBtn;
	public JList InUseJList;
	public JTextField jTextSamplesSearch;
	public JTextField jTxtSearch;
	public JButton jButtRemSample;
	public JList samplesLst;

	public TopEditorFrame(MainDictPanelListener mainDictPanelListener, Manager manager, CommandsListener cmdListener) {

		this.manager = manager;
		this.mainDictPanelListener = mainDictPanelListener;
		this.cmdListener = cmdListener;

		prevSelectedIndex = 0;
		initComponents();

		setLocationRelativeTo(null);
		setVisible(true);
		setSize(TopEditorFrame.WINDOW_WIDTH_SHORT, TopEditorFrame.WINDOW_HEIGHT);
	}

	public void Alert(final String paramString) {
		JOptionPane.showMessageDialog(new JFrame("FrameDemo"), paramString);
	}

	private void initComponents() {

		setJMenuBar(new TopMenuBar(cmdListener, manager));

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception localException) {
			l.error(localException);
		}

		getContentPane().setLayout(null);

		this.specialSymbolsPanel = new SpecialSymbolsPanel(this.mainDictPanelListener);
		this.keyBoardPanel = new KeyBoardPanel(this.mainDictPanelListener);
		this.audioPanel = new AudioPanel(this.mainDictPanelListener);
		this.Word_TextF = new JTextField();
		this.Translate_TextF = new JTextField();
		this.Transcription_TextF = new JTextField();
		this.Remove_Btn = new JButton();
		this.jButtRemSample = new JButton();
		this.jTxtSearch = new JTextField();
		this.jScrollPane3 = new JScrollPane();
		this.selectNextRandomBtn = new JButton();
		this.jTextSamplesSearch = new JTextField();
		this.Rename_Btn = new JButton();

		add(this.specialSymbolsPanel);
		add(this.keyBoardPanel);
		add(this.audioPanel);
		add(this.Remove_Btn);
		add(this.Rename_Btn);
		add(this.Transcription_TextF);
		add(this.Translate_TextF);
		add(this.Word_TextF);
		add(this.jButtRemSample);
		add(this.jScrollPane3);
		add(this.selectNextRandomBtn);
		add(this.jTextSamplesSearch);
		add(this.jTxtSearch);

		this.specialSymbolsPanel.setBounds(5, 92, 350, 28);
		this.keyBoardPanel.setBounds(5, 120, 350, 85);
		this.audioPanel.setBounds(240, 10, 150, 25);
		this.Translate_TextF.setBounds(6, 38, 230, 21);

		this.jButtRemSample.setText("del");
		this.jButtRemSample.setMargin(new Insets(0, 0, 0, 0));
		this.jButtRemSample.setBounds(356, 271, 29, 19);
		this.jButtRemSample.setFont(new Font("Arial", 0, 11));
		this.jButtRemSample.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(final MouseEvent paramMouseEvent) {
				TopEditorFrame.this.mainDictPanelListener.jButtRemSampleMouseReleased(paramMouseEvent);
			}
		});

		this.jTxtSearch.setBounds(393, 269, 102, 21);
		this.jTxtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent paramKeyEvent) {
				TopEditorFrame.this.mainDictPanelListener.jTxtSearchKeyReleased(paramKeyEvent);
			}
		});

		this.jTextSamplesSearch.setBounds(6, 270, 348, 21);
		this.jTextSamplesSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent paramKeyEvent) {
				TopEditorFrame.this.mainDictPanelListener.jTextSamplesSearchKeyReleased(paramKeyEvent);
			}
		});

		this.selectNextRandomBtn.setText("Restore");
		this.selectNextRandomBtn.setMargin(new Insets(0, 0, 0, 0));
		this.selectNextRandomBtn.setFont(new Font("Arial", 0, 11));
		this.selectNextRandomBtn.setBounds(608, 267, 162, 20);
		this.selectNextRandomBtn.setActionCommand(CommandsListener.RESTORE_SELECTED_REMOVED);
		this.selectNextRandomBtn.addActionListener(this.cmdListener);

		this.jScrollPane3.setBounds(608, 12, 159, 249);
		DefaultComboBoxModel localDefaultComboBoxModel = new DefaultComboBoxModel(new String[] { "Item One", "Item Two" });
		this.InUseJList = new JList();
		this.jScrollPane3.setViewportView(this.InUseJList);
		this.InUseJList.setModel(localDefaultComboBoxModel);
		this.InUseJList.setSelectedIndex(0);
		this.InUseJList.setFont(new Font("Arial", 0, 11));
		this.InUseJList.setBounds(608, 12, 159, 249);
		this.InUseJList.setBorder(BorderFactory.createEtchedBorder(1));

		ListSelectionListener localObject = new ListSelectionListener() {
			public void valueChanged(final ListSelectionEvent paramListSelectionEvent) {
				TopEditorFrame.this.mainDictPanelListener.InUseJListMouseReleased(paramListSelectionEvent);
			}
		};

		this.InUseJList.addListSelectionListener(localObject);

		this.jScrollPane1 = new JScrollPane();
		add(this.jScrollPane1);
		this.jScrollPane1.setBounds(393, 12, 164, 251);
		localDefaultComboBoxModel = new DefaultComboBoxModel(new String[] { "Item One", "Item Two" });
		this.wordsLst = new JList();
		this.jScrollPane1.setViewportView(this.wordsLst);
		this.wordsLst.setModel(localDefaultComboBoxModel);
		this.wordsLst.setBounds(393, 12, 164, 251);
		this.wordsLst.setFont(new Font("Arial", 0, 11));
		this.wordsLst.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(255, 255, 255)));
		this.wordsLst.setForeground(new Color(0, 0, 0));

		localObject = new ListSelectionListener() {
			public void valueChanged(final ListSelectionEvent paramListSelectionEvent) {
				if (paramListSelectionEvent.getValueIsAdjusting())
					return;
				if ((TopEditorFrame.this.manager.getCurrentStateManager().getCurrentPair() != null)
						&& (!(TopEditorFrame.this.manager.getCurrentStateManager().aplyCurrWordChanged(TopEditorFrame.this.Translate_TextF
								.getText(), TopEditorFrame.this.Transcription_TextF.getText()))))
					Alert("Your input was not aplyed!");
				if (TopEditorFrame.this.manager.getCurrentStateManager().setCurrentPairByKey(
						(String) TopEditorFrame.this.wordsLst.getSelectedValue())) {
					refresh(false, true);
					if (TopEditorFrame.this.manager.getAudioManager().isPlayOnSelections())
						TopEditorFrame.this.manager.getAudioManager().playCurrentAudioRecord();
				}
				TopEditorFrame.this.wordsLst.setSelectedValue(TopEditorFrame.this.wordsLst.getSelectedValue(), true);
			}
		};
		this.wordsLst.addListSelectionListener(localObject);

		this.jScrollPane2 = new JScrollPane();
		add(this.jScrollPane2);
		this.jScrollPane2.setBounds(6, 210, 379, 55);
		localDefaultComboBoxModel = new DefaultComboBoxModel(new String[] { "Item One", "Item Two" });
		this.samplesLst = new JList();
		this.jScrollPane2.setViewportView(this.samplesLst);
		this.samplesLst.setModel(localDefaultComboBoxModel);
		this.samplesLst.setBounds(6, 210, 379, 55);
		this.samplesLst.setFont(new Font("Arial", 0, 13));
		this.samplesLst.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(final MouseEvent paramMouseEvent) {
				TopEditorFrame.this.mainDictPanelListener.jListSamplesMouseReleased(paramMouseEvent);
			}
		});
		localObject = new ListSelectionListener() {
			public void valueChanged(final ListSelectionEvent paramListSelectionEvent) {
				if (paramListSelectionEvent.getValueIsAdjusting())
					return;
				if (TopEditorFrame.this.samplesLst.getSelectedIndex() == -1)
					TopEditorFrame.this.manager.getCurrentStateManager().setCurrSample(null);
				else
					TopEditorFrame.this.manager.getCurrentStateManager().setCurrSample(
							TopEditorFrame.this.samplesLst.getSelectedValue().toString());
				if ((!(TopEditorFrame.this.manager.getAudioManager().isPlayOnSelections()))
						|| (TopEditorFrame.this.samplesLst.getSelectedIndex() == -1))
					return;
				TopEditorFrame.this.manager.getAudioManager().playCurrentAudioRecord();
			}
		};

		this.Word_TextF.setEditable(false);
		this.Word_TextF.setFont(new Font("Tahoma", 1, 11));
		this.Word_TextF.setBounds(6, 11, 230, 21);
		this.Word_TextF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent paramKeyEvent) {
				TopEditorFrame.this.mainDictPanelListener.Word_TextFKeyReleased(paramKeyEvent);
			}
		});
		this.Translate_TextF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent paramKeyEvent) {
				TopEditorFrame.this.mainDictPanelListener.Translate_TextFKeyReleased(paramKeyEvent);
			}
		});
		this.Transcription_TextF.setFont(new Font("Lucida Sans Unicode", 0, 14));
		this.Transcription_TextF.setBounds(6, 65, 230, 25);
		this.Transcription_TextF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(final KeyEvent paramKeyEvent) {
				TopEditorFrame.this.mainDictPanelListener.Transcription_TextFKeyReleased(paramKeyEvent);
			}
		});

		this.wordsLst.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };

			public int getSize() {
				return this.strings.length;
			}

			public Object getElementAt(final int paramInt) {
				return this.strings[paramInt];
			}
		});

		this.Remove_Btn.setText("del");
		this.Remove_Btn.setMargin(new Insets(0, 0, 0, 0));
		this.Remove_Btn.setBounds(527, 270, 29, 19);
		this.Remove_Btn.setFont(new Font("Arial", 0, 11));
		this.Remove_Btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(final MouseEvent paramMouseEvent) {
				TopEditorFrame.this.mainDictPanelListener.Remove_BtnMouseReleased(paramMouseEvent);
			}
		});

		this.Rename_Btn.setText("edit");
		this.Rename_Btn.setMargin(new Insets(0, 0, 0, 0));
		this.Rename_Btn.setBounds(496, 270, 29, 19);
		this.Rename_Btn.setFont(new Font("Arial", 0, 11));
		this.Rename_Btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(final MouseEvent paramMouseEvent) {
				TopEditorFrame.this.mainDictPanelListener.Rename_BtnMouseReleased(paramMouseEvent);
			}
		});

		setDefaultCloseOperation(3);
		setLocationByPlatform(true);
		setResizable(false);

		setTitle("Top Dictionary");
		pack();
		setSize(WINDOW_WIDTH_EXPANDED, WINDOW_HEIGHT);
		setIconImage(new ImageIcon(super.getClass().getResource("iconA16.GIF")).getImage());
	}

	public void inUseListRefresh() {
		int i = this.InUseJList.getSelectedIndex();
		String[] keys = this.manager.getTrashManager().getAllKeys();
		this.InUseJList.setListData(keys);
		if (i == -1)
			return;
		if (i < keys.length) {
			this.InUseJList.setSelectedIndex(i);
		} else {
			this.InUseJList.setSelectedIndex(keys.length - 1);
		}
	}

	public void refresh(final boolean paramBoolean1, final boolean paramBoolean2) {
		if (this.manager.getAudioManager().recorder == null) {
			this.audioPanel.jButtRecordding.setText("start");
			this.audioPanel.jButtPlay.setEnabled(true);
			if (this.manager.getAudioManager().isCurrentItemAlreadyRecorded()) {
				this.audioPanel.jButtPlay.setEnabled(true);
				this.audioPanel.jButtDeleteRecord.setEnabled(true);
				this.audioPanel.jButtRecordding.setEnabled(false);
			} else {
				this.audioPanel.jButtPlay.setEnabled(false);
				this.audioPanel.jButtDeleteRecord.setEnabled(false);
				this.audioPanel.jButtRecordding.setEnabled(true);
			}
		} else {
			this.audioPanel.jButtRecordding.setText("stop");
			this.audioPanel.jButtPlay.setEnabled(false);
		}
		String str;
		if (this.manager.getCurrentStateManager().getCurrentPair() == null) {
			setTitle("Top Dictionary - none : ");
			this.Word_TextF.setText("");
			this.Translate_TextF.setText("");
			this.Transcription_TextF.setText("");
			this.samplesLst.setListData(new String[0]);
			this.audioPanel.jButtPlay.setEnabled(false);
			this.audioPanel.jButtDeleteRecord.setEnabled(false);
			this.audioPanel.jButtRecordding.setEnabled(false);
		} else {
			setTitle("Top Dictionary - " + (this.wordsLst.getSelectedIndex() + 1) + " : "
					+ new Integer(this.manager.getPairsManager().getAllKeys().length).toString());
			this.Word_TextF.setText(this.manager.getCurrentStateManager().getCurrentPair().getWord());
			this.Translate_TextF.setText(this.manager.getCurrentStateManager().getCurrentPair().getTranslation());
			this.Transcription_TextF.setText(this.manager.getCurrentStateManager().getCurrentPair().getTranscription());
			if (paramBoolean2) {
				str = null;
				if (this.manager.getCurrentStateManager().getCurrSample() != null)
					str = this.manager.getCurrentStateManager().getCurrSample();
				if (this.manager.getCurrentStateManager().getCurrentPair().getSamples() != null)
					this.samplesLst.setListData(this.manager.getCurrentStateManager().getCurrentPair().getSamples());
				else
					this.samplesLst.setListData(new String[0]);
				if (this.manager.getCurrentStateManager().getCurrentPair().getSamples() != null)
					this.samplesLst.setSelectedValue(str, true);
			}
		}
		if (paramBoolean1) {
			str = null;
			if (this.manager.getCurrentStateManager().getCurrentPair() != null)
				str = this.manager.getCurrentStateManager().getCurrentPair().getWord();
			this.wordsLst.setListData(this.manager.getPairsManager().getAllKeys());
			if ((this.manager.getCurrentStateManager().setCurrentPairByKey(str))
					&& (this.manager.getCurrentStateManager().getCurrentPair() != null))
				this.wordsLst.setSelectedValue(this.manager.getCurrentStateManager().getCurrentPair().getWord(), true);
		}
		if (this.manager.getCurrentStateManager().getCurrentPair() == null)
			if (this.manager.getPairsManager().getAllKeys().length > 0)
				this.wordsLst.setSelectedValue(this.manager.getPairsManager().getAllKeys()[0], true);
			else
				this.wordsLst.setSelectedValue(null, true);
		inUseListRefresh();
	}

	public void regenDictList() {

		JMenu submenu;
		JMenuItem menuItem;

		submenu = (JMenu) getJMenuBar().getMenu(0).getItem(3);
		submenu.removeAll();

		List<Dictionary> dictioanries = this.manager.getWorkspaceManager().getWorkspace().getDictioanries();
		ButtonGroup group = new ButtonGroup();

		for (Dictionary d : dictioanries) {
			menuItem = new JRadioButtonMenuItem(d.getDisplayName());
			group.add(menuItem);
			menuItem.addActionListener(this.cmdListener);
			menuItem.setActionCommand(CommandsListener.SELECT_DICT);
			if (this.manager.getCurrentStateManager().getCurrentDictionary().getDisplayName().equals(d.getDisplayName())) {
				menuItem.setSelected(true);
			}
			submenu.add(menuItem);
		}
	}

	public MainDictPanelListener getMainDictPanelListener() {
		return mainDictPanelListener;
	}

	public void setMainDictPanelListener(MainDictPanelListener mainDictPanelListener) {
		this.mainDictPanelListener = mainDictPanelListener;
	}

	public int getPrevSelectedIndex() {
		return prevSelectedIndex;
	}

	public void setPrevSelectedIndex(int prevSelectedIndex) {
		this.prevSelectedIndex = prevSelectedIndex;
	}

}
