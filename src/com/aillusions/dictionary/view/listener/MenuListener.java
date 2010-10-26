package com.aillusions.dictionary.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;

import com.aillusions.dictionary.Manager;
import com.aillusions.dictionary.view.About;
import com.aillusions.dictionary.view.TopEditor;

public class MenuListener implements ActionListener {
	
	private Manager dictionary;
	private TopEditor topEditor;

	public static final String OPEN_ALL_WORDS_IN_WORD = "1";
	public static final String OPEN_SELECTED_WORDS_IN_WORD = "2";
	public static final String OPEN_SAMPLES_IN_WORD = "3";
	public static final String SET_ALWAYS_ON_TOP = "4";
	public static final String RUN_TRAINER = "5";
	public static final String PLAY_ON_SELECTION = "6";
	public static final String MIX_WORDS = "7";
	public static final String SAVE = "8";
	public static final String LOAD = "9";
	public static final String ABOUT = "10";
	public static final String EXPAND = "11";
	public static final String MOVE_SAMPLE_UP = "12";
	public static final String EDIT_CURRENT = "13";
	public static final String REMOVE_CURRENT = "14";
	
	public MenuListener(Manager dictionary, TopEditor topEditor){
		this.dictionary = dictionary;
		this.topEditor = topEditor;
	}
	
	public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(OPEN_ALL_WORDS_IN_WORD))	{
				dictionary.runWord(false, false);
			}else if(e.getActionCommand().equals(OPEN_SELECTED_WORDS_IN_WORD))	{
				dictionary.runWord(true, false);;
			}else if(e.getActionCommand().equals(OPEN_SAMPLES_IN_WORD))	{
				dictionary.runWord(true, true);
			}else if(e.getActionCommand().equals(SET_ALWAYS_ON_TOP)){				
				topEditor.setAlwaysOnTop(((JCheckBoxMenuItem)e.getSource()).isSelected());
			}else if(e.getActionCommand().equals(RUN_TRAINER)){				
				dictionary.runTrainer();
			}else if(e.getActionCommand().equals(PLAY_ON_SELECTION)){				
				dictionary.setPlayOnSelections(((JCheckBoxMenuItem)e.getSource()).isSelected());
			}else if(e.getActionCommand().equals(MIX_WORDS)){				
				dictionary.shuffle();
				topEditor.refresh(true, true);
			}else if(e.getActionCommand().equals(SAVE)){				
				dictionary.saveInFile();
			}else if(e.getActionCommand().equals(LOAD)){				
				dictionary.Load();
				topEditor.refresh(true, true);
			}else if(e.getActionCommand().equals(ABOUT)){				
				About localAbout = new About();
				localAbout.frameConstructor();
				localAbout.setVisible(true);
			}else if(e.getActionCommand().equals(EXPAND)){		
				JMenuItem munuItem = (JMenuItem)e.getSource();
				if (munuItem.getText().equals("Reduce <")) {
					munuItem.setText("Expand >");
					topEditor.setSize(TopEditor.WINDOW_WIDTH_SHORT, TopEditor.WINDOW_HEIGHT);
				} else {
					munuItem.setText("Reduce <");
					topEditor.setSize(TopEditor.WINDOW_WIDTH_EXPANDED, TopEditor.WINDOW_HEIGHT);
				}
			}else if(e.getActionCommand().equals(MOVE_SAMPLE_UP)){				
				dictionary.upCurrentSample();
				topEditor.refresh(false, true);
			}else if(e.getActionCommand().equals(EDIT_CURRENT)){				
				if (dictionary.getCurrentPair() == null)
					return;
				dictionary.renameCurrent(null);
				topEditor.refresh(true, true);
			}else if(e.getActionCommand().equals(REMOVE_CURRENT)){				
				if (dictionary.getCurrentPair() == null)
					topEditor.Alert("You have to select one before!");
				dictionary.removeCurrent();
				topEditor.refresh(true, true);
			}
	}

}
