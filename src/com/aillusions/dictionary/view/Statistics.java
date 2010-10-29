package com.aillusions.dictionary.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import com.aillusions.dictionary.core.Manager;
import com.aillusions.dictionary.model.Dictionary;
import com.aillusions.dictionary.view.layout.AnchorConstraint;
import com.aillusions.dictionary.view.layout.AnchorLayout;

public class Statistics extends JDialog {
	public static final String NEW_LINE = "\r\n";
	private static final long serialVersionUID = 1L;

	private JScrollPane jScrollPane1;
	private JTextArea jTextArea1;
	private JPanel topPanel;

	Manager manager;
	
	public Statistics(Manager manager) {
		
		this.manager = manager;
		setTitle("Statistic");
		setSize(648, 277);
		//move(300, 300);
		setBackground(Color.gray);
		AnchorLayout localAnchorLayout = new AnchorLayout();
		getContentPane().setLayout(localAnchorLayout);
		this.topPanel = new JPanel();
		this.topPanel.setLayout(new BorderLayout());
		getContentPane().add(this.topPanel,
				new AnchorConstraint(329, 146, 329, 146, 1, 1, 1, 1));
		this.topPanel.setPreferredSize(new Dimension(0, 0));
		this.jScrollPane1 = new JScrollPane();
		getContentPane().add(this.jScrollPane1,
				new AnchorConstraint(15, 991, 972, 8, 1, 1, 1, 1));
		this.jScrollPane1.setPreferredSize(new Dimension(546, 157));
		this.jTextArea1 = new JTextArea();
		this.jScrollPane1.setViewportView(this.jTextArea1);
		
		
		List<Dictionary> dicts = manager.getWorkspaceManager().getWorkspace().getDictioanries();
		StringBuffer sb = new StringBuffer();
		
		sb.append("Your currently have: ");
		
		if(dicts.size() > 1){
			sb.append(dicts.size() +  " dictionaries.");
			sb.append(NEW_LINE);
		}else{
			sb.append("one dictionary.");
			sb.append(NEW_LINE);
		}
		
		for(Dictionary d : dicts){
			sb.append(NEW_LINE).append("- ");
			sb.append(d.getDisplayName() + ": ");
			sb.append(NEW_LINE).append("     - ");
			sb.append(d.getPairs().size() + " words");
			sb.append(NEW_LINE).append("     - ");
			sb.append(d.getTrash().size() + " removed words");
		}
		
		jTextArea1.setText(sb.toString());
		jTextArea1.setEditable(false);
		jTextArea1.setPreferredSize(new Dimension(546, 157));
		

		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");
		getRootPane().getActionMap().put("Cancel", new AbstractAction() { 
				private static final long serialVersionUID = 1L;

					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});

		// on close window the close method is called
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				setVisible(false);
			}
		});
	}

}
