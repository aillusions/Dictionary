package com.aillusions.dictionary.view.components;

import java.awt.Font;
import java.awt.Insets;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import com.aillusions.dictionary.core.Manager;
import com.aillusions.dictionary.model.Dictionary;
import com.aillusions.dictionary.view.listener.CommandsListener;

public class TopMenuBar extends JMenuBar{
	
	private static final long serialVersionUID = 1L;
	public final static String EXPAND_MENU_ITEM_NAME = "Show trash >";
	public final static String COLLAPSE_MENU_ITEM_NAME = "< Hide trash";

	public TopMenuBar(CommandsListener cmdListener, Manager namager) {
		
		//JMenuBar menuBar;
		JMenu menu, submenu;
		JMenuItem menuItem;
		JCheckBoxMenuItem cbMenuItem;
		
		//---------------
		menu = new JMenu("Dictionary");
		menu.setMargin(new Insets(0, 0, 0, 0));
		menu.setFont(new Font("Arial", 0, 11));
		add(menu);	
		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(cmdListener);
		menuItem.setActionCommand(CommandsListener.SAVE);
		menu.add(menuItem);
		menuItem = new JMenuItem("Re load (undo all changes)");
		menuItem.addActionListener(cmdListener);
		menuItem.setActionCommand(CommandsListener.LOAD);
		menu.add(menuItem);
		menu.addSeparator();		
		submenu = new JMenu("Select dictionary");
		
		List<Dictionary> dictioanries = namager.getWorkspaceManager().getWorkspace().getDictioanries();
		
		ButtonGroup group = new ButtonGroup();	
		
		for(Dictionary d : dictioanries){
			JRadioButtonMenuItem menuItem1 = new JRadioButtonMenuItem(d.getDisplayName());
			group.add(menuItem1);
			menuItem1.addActionListener(cmdListener);
			menuItem1.setActionCommand(CommandsListener.SELECT_DICT);
			if(namager.getCurrentStateManager().getCurrentDictionary().getDisplayName().equals(d.getDisplayName())){
				menuItem1.setSelected(true);
			}
			submenu.add(menuItem1);
		}
		menu.add(submenu);
		
		menuItem = new JMenuItem("Use current as default");
		menuItem.setEnabled(false);
		menuItem.addActionListener(cmdListener);
		menu.add(menuItem);		
		menuItem = new JMenuItem("Create new");
		menuItem.addActionListener(cmdListener);
		menuItem.setActionCommand(CommandsListener.ADD_NEW_DICT);
		menu.add(menuItem);			
		menu.addSeparator();	
		menuItem = new JMenuItem("Open all in Word");
		menuItem.addActionListener(cmdListener);
		menuItem.setActionCommand(CommandsListener.OPEN_ALL_WORDS_IN_WORD);
		menu.add(menuItem);
		menuItem = new JMenuItem("Open selected in Word");
		menuItem.addActionListener(cmdListener);
		menuItem.setActionCommand(CommandsListener.OPEN_SELECTED_WORDS_IN_WORD);
		menu.add(menuItem);
		menuItem = new JMenuItem("Open examples in Word");
		menuItem.addActionListener(cmdListener);
		menuItem.setActionCommand(CommandsListener.OPEN_SAMPLES_IN_WORD);
		menu.add(menuItem);
		menu.addSeparator();	
		menuItem = new JMenuItem("Save and exit");
		menuItem.addActionListener(cmdListener);
		menuItem.setActionCommand(CommandsListener.SAVE_EXIT);
		menu.add(menuItem);
		
		//---------------
		menu = new JMenu("View");
		menu.setMargin(new Insets(0, 0, 0, 0));
		menu.setFont(new Font("Arial", 0, 11));
		add(menu);		
		menuItem = new JMenuItem(EXPAND_MENU_ITEM_NAME);	
		menuItem.addActionListener(cmdListener);
		menuItem.setActionCommand(CommandsListener.EXPAND);
		menu.add(menuItem);

		group = new ButtonGroup();	
		
		submenu = new JMenu("Language");
		submenu.setEnabled(false);
		menuItem = new JRadioButtonMenuItem("English");
		menuItem.addActionListener(cmdListener);
		submenu.add(menuItem);
		group.add(menuItem);
		menuItem = new JRadioButtonMenuItem("Italian");
		menuItem.addActionListener(cmdListener);
		submenu.add(menuItem);
		group.add(menuItem);
		menuItem = new JRadioButtonMenuItem("Russian");
		menuItem.addActionListener(cmdListener);
		submenu.add(menuItem);
		group.add(menuItem);
		menu.add(submenu);	
		
		menuItem = new JCheckBoxMenuItem("Always on top");	
		menuItem.addActionListener(cmdListener);
		menuItem.setActionCommand(CommandsListener.SET_ALWAYS_ON_TOP);
		menu.add(menuItem);	
		
		//---------------
		menu = new JMenu("Words");
		menu.setMargin(new Insets(0, 0, 0, 0));
		menu.setFont(new Font("Arial", 0, 11));
		add(menu);		
		menuItem = new JMenuItem("Mix words");
		menuItem.addActionListener(cmdListener);
		menuItem.setActionCommand(CommandsListener.MIX_WORDS);
		menu.add(menuItem);
		menu.addSeparator();
		menuItem = new JMenuItem("Edit word");
		menuItem.addActionListener(cmdListener);
		menuItem.setActionCommand(CommandsListener.EDIT_CURRENT);
		menu.add(menuItem);
		menuItem = new JMenuItem("Remove word");
		menuItem.addActionListener(cmdListener);
		menuItem.setActionCommand(CommandsListener.REMOVE_CURRENT_WORD);
		menu.add(menuItem);
		menu.addSeparator();
		menuItem = new JMenuItem("Move sample up");
		menuItem.addActionListener(cmdListener);
		menuItem.setActionCommand(CommandsListener.MOVE_SAMPLE_UP);
		menu.add(menuItem);
		
		//---------------
		menu = new JMenu("Audio");
		menu.setMargin(new Insets(0, 0, 0, 0));
		menu.setFont(new Font("Arial", 0, 11));
		add(menu);		
		menuItem = new JMenuItem("Paly");
		menuItem.setEnabled(false);
		menu.add(menuItem);
		menuItem = new JMenuItem("Record");
		menuItem.setEnabled(false);
		menu.add(menuItem);
		menuItem = new JMenuItem("Remove");
		menuItem.setEnabled(false);
		menu.add(menuItem);		
		menu.addSeparator();
		cbMenuItem = new JCheckBoxMenuItem("Play on select");
		cbMenuItem.setToolTipText("Play record by selection in navigator");
		cbMenuItem.addActionListener(cmdListener);
		cbMenuItem.setActionCommand(CommandsListener.PLAY_ON_SELECTION);
		menu.add(cbMenuItem);
		
		//---------------
		menu = new JMenu("Update");
		menu.setMargin(new Insets(0, 0, 0, 0));
		menu.setFont(new Font("Arial", 0, 11));
		add(menu);		
		menuItem = new JMenuItem("Check for update");
		menuItem.setEnabled(false);
		menu.add(menuItem);
		menuItem = new JCheckBoxMenuItem("Do not ask");
		menuItem.setEnabled(false);
		menu.add(menuItem);
		
		//---------------
		menu = new JMenu("Trainer");
		menu.setMargin(new Insets(0, 0, 0, 0));
		menu.setFont(new Font("Arial", 0, 11));
		add(menu);		
		menuItem = new JMenuItem("Run trainer");
		menuItem.addActionListener(cmdListener);
		menuItem.setActionCommand(CommandsListener.RUN_TRAINER);
		menu.add(menuItem);
		menuItem = new JMenuItem("View statistic");
		menuItem.setEnabled(false);
		menu.add(menuItem);
		menuItem = new JMenuItem("Clear statistic");
		menuItem.setEnabled(false);
		menu.add(menuItem);
		
		//---------------
		menu = new JMenu("About");
		menu.setMargin(new Insets(0, 0, 0, 0));
		menu.setFont(new Font("Arial", 0, 11));
		add(menu);		
		menuItem = new JMenuItem("About Dictionary");
		menuItem.addActionListener(cmdListener);
		menuItem.setActionCommand(CommandsListener.ABOUT);
		menu.add(menuItem);		

	}
}
