package com.aillusions.dictionary.view.components;

import java.awt.Font;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import com.aillusions.dictionary.view.listener.MenuListener;

public class TopMenuBar extends JMenuBar{
	
	private static final long serialVersionUID = 1L;

	public TopMenuBar(MenuListener menuListener) {
		
		//JMenuBar menuBar;
		JMenu menu, submenu;
		JMenuItem menuItem;
		JCheckBoxMenuItem cbMenuItem;

		//menuBar = new JMenuBar();
		
		//---------------
		menu = new JMenu("Dictionary");
		menu.setMargin(new Insets(0, 0, 0, 0));
		menu.setFont(new Font("Arial", 0, 11));
		add(menu);	
		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(menuListener);
		menuItem.setActionCommand(MenuListener.SAVE);
		menu.add(menuItem);
		menuItem = new JMenuItem("Re load (undo all changes)");
		menuItem.addActionListener(menuListener);
		menuItem.setActionCommand(MenuListener.LOAD);
		menu.add(menuItem);
		menu.addSeparator();		
		submenu = new JMenu("Select current");
		menuItem = new JMenuItem("English");
		menuItem.addActionListener(menuListener);
		submenu.add(menuItem);
		menuItem = new JMenuItem("Italian");
		menuItem.addActionListener(menuListener);
		submenu.add(menuItem);
		menu.add(submenu);
		menuItem = new JMenuItem("Use current as default");
		menuItem.addActionListener(menuListener);
		menu.add(menuItem);		
		menuItem = new JMenuItem("Create new");
		menuItem.addActionListener(menuListener);
		menu.add(menuItem);			
		menu.addSeparator();	
		menuItem = new JMenuItem("Convert all and open Word");
		menuItem.addActionListener(menuListener);
		menuItem.setActionCommand(MenuListener.OPEN_ALL_WORDS_IN_WORD);
		menu.add(menuItem);
		menuItem = new JMenuItem("Convert selected and open Word");
		menuItem.addActionListener(menuListener);
		menuItem.setActionCommand(MenuListener.OPEN_SELECTED_WORDS_IN_WORD);
		menu.add(menuItem);
		menuItem = new JMenuItem("Convert samples and open Word");
		menuItem.addActionListener(menuListener);
		menuItem.setActionCommand(MenuListener.OPEN_SAMPLES_IN_WORD);
		menu.add(menuItem);
		menu.addSeparator();	
		menuItem = new JMenuItem("Save and exit");
		menuItem.addActionListener(menuListener);
		menu.add(menuItem);
		
		//---------------
		menu = new JMenu("View");
		menu.setMargin(new Insets(0, 0, 0, 0));
		menu.setFont(new Font("Arial", 0, 11));
		add(menu);		
		menuItem = new JMenuItem("Expand >");	
		menuItem.addActionListener(menuListener);
		menuItem.setActionCommand(MenuListener.EXPAND);
		menu.add(menuItem);

		ButtonGroup group = new ButtonGroup();	
		
		submenu = new JMenu("Language");
		menuItem = new JRadioButtonMenuItem("English");
		menuItem.addActionListener(menuListener);
		submenu.add(menuItem);
		group.add(menuItem);
		menuItem = new JRadioButtonMenuItem("Italian");
		menuItem.addActionListener(menuListener);
		submenu.add(menuItem);
		group.add(menuItem);
		menuItem = new JRadioButtonMenuItem("Russian");
		menuItem.addActionListener(menuListener);
		submenu.add(menuItem);
		group.add(menuItem);
		menu.add(submenu);	
		
		menuItem = new JCheckBoxMenuItem("Always on top");	
		menuItem.addActionListener(menuListener);
		menuItem.setActionCommand(MenuListener.SET_ALWAYS_ON_TOP);
		menu.add(menuItem);	
		
		//---------------
		menu = new JMenu("Words");
		menu.setMargin(new Insets(0, 0, 0, 0));
		menu.setFont(new Font("Arial", 0, 11));
		add(menu);		
		menuItem = new JMenuItem("Mix words");
		menuItem.addActionListener(menuListener);
		menuItem.setActionCommand(MenuListener.MIX_WORDS);
		menu.add(menuItem);
		menuItem = new JMenuItem("View removed words");
		menu.add(menuItem);
		menu.addSeparator();
		menuItem = new JMenuItem("Edit current");
		menuItem.addActionListener(menuListener);
		menuItem.setActionCommand(MenuListener.EDIT_CURRENT);
		menu.add(menuItem);
		menuItem = new JMenuItem("Remove current");
		menuItem.addActionListener(menuListener);
		menuItem.setActionCommand(MenuListener.REMOVE_CURRENT);
		menu.add(menuItem);
		menu.addSeparator();
		menuItem = new JMenuItem("Mov sample up");
		menuItem.addActionListener(menuListener);
		menuItem.setActionCommand(MenuListener.MOVE_SAMPLE_UP);
		menu.add(menuItem);
		
		//---------------
		menu = new JMenu("Audio");
		menu.setMargin(new Insets(0, 0, 0, 0));
		menu.setFont(new Font("Arial", 0, 11));
		add(menu);		
		menuItem = new JMenuItem("Paly");
		menu.add(menuItem);
		menuItem = new JMenuItem("Record");
		menu.add(menuItem);
		menuItem = new JMenuItem("Remove");
		menu.add(menuItem);		
		menu.addSeparator();
		cbMenuItem = new JCheckBoxMenuItem("Play on select");
		cbMenuItem.setToolTipText("Play record by selection in navigator");
		cbMenuItem.addActionListener(menuListener);
		cbMenuItem.setActionCommand(MenuListener.PLAY_ON_SELECTION);
		menu.add(cbMenuItem);
		
		//---------------
		menu = new JMenu("Update");
		menu.setMargin(new Insets(0, 0, 0, 0));
		menu.setFont(new Font("Arial", 0, 11));
		add(menu);		
		menuItem = new JMenuItem("Check for update");
		menu.add(menuItem);
		menuItem = new JCheckBoxMenuItem("Do not ask");
		menu.add(menuItem);
		
		//---------------
		menu = new JMenu("Trainer");
		menu.setMargin(new Insets(0, 0, 0, 0));
		menu.setFont(new Font("Arial", 0, 11));
		add(menu);		
		menuItem = new JMenuItem("Run trainer");
		menuItem.addActionListener(menuListener);
		menuItem.setActionCommand(MenuListener.RUN_TRAINER);
		menu.add(menuItem);
		menuItem = new JMenuItem("View statistic");
		menu.add(menuItem);
		menuItem = new JMenuItem("Clear statistic");
		menu.add(menuItem);
		
		//---------------
		menu = new JMenu("About");
		menu.setMargin(new Insets(0, 0, 0, 0));
		menu.setFont(new Font("Arial", 0, 11));
		add(menu);		
		menuItem = new JMenuItem("About Dictionary");
		menuItem.addActionListener(menuListener);
		menuItem.setActionCommand(MenuListener.ABOUT);
		menu.add(menuItem);		

	}
}
