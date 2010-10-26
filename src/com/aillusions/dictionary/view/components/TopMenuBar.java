package com.aillusions.dictionary.view.components;

import java.awt.Font;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class TopMenuBar extends JMenuBar{
	
	private static final long serialVersionUID = 1L;

	public TopMenuBar() {
		
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
		menu.add(menuItem);
		menuItem = new JMenuItem("Re load");
		menu.add(menuItem);
		menu.addSeparator();		
		submenu = new JMenu("Select current");
		menuItem = new JMenuItem("English");
		submenu.add(menuItem);
		menuItem = new JMenuItem("Italian");
		submenu.add(menuItem);
		menu.add(submenu);
		menuItem = new JMenuItem("Use current as default");
		menu.add(menuItem);		
		menuItem = new JMenuItem("Create new");
		menu.add(menuItem);			
		menu.addSeparator();		
		menuItem = new JMenuItem("Save and exit");
		menu.add(menuItem);
		
		//---------------
		menu = new JMenu("View");
		menu.setMargin(new Insets(0, 0, 0, 0));
		menu.setFont(new Font("Arial", 0, 11));
		add(menu);		
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
		menu = new JMenu("Words");
		menu.setMargin(new Insets(0, 0, 0, 0));
		menu.setFont(new Font("Arial", 0, 11));
		add(menu);		
		menuItem = new JMenuItem("Mix words");
		menu.add(menuItem);
		menuItem = new JMenuItem("View removed words");
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
		menu.add(menuItem);		

	}
}
