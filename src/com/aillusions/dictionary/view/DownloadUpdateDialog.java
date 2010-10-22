package com.aillusions.dictionary.view;

import java.awt.Color;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class DownloadUpdateDialog extends JDialog  {

	private static final long serialVersionUID = 1L;

	private JLabel quastionLable;
	private JProgressBar progressBar;
	private boolean stoped;

	public DownloadUpdateDialog(JFrame parent) {	
		
		super(parent, "About Dialog", false);
		
		quastionLable = new JLabel();
		quastionLable.setBounds(20, 10, 400, 50);
		
		quastionLable.setText("Downloading operation in progress, this can take some time.");
		progressBar = new JProgressBar();				
		
		this.setTitle("Update downloading in progress..");

		this.setSize(400, 100);
		this.setBackground(Color.gray);
		this.getContentPane().add(quastionLable);
		this.getContentPane().add(progressBar);
	}
	
	public void setValue(int val){
		progressBar.setValue(val);
	}
	
    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
        	stoped = true;
        }
    }

	public boolean isStoped() {
		return stoped;
	}

	public void setStoped(boolean stoped) {
		this.stoped = stoped;
	}
    
}
