package com.aillusions.dictionary.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.aillusions.dictionary.model.TrainerViewListener;
import com.aillusions.dictionary.util.VersionChecker;
import com.aillusions.dictionary.view.layout.AnchorConstraint;
import com.aillusions.dictionary.view.layout.AnchorLayout;

public class About extends JFrame {
	private static final long serialVersionUID = 1L;
	private TrainerViewListener viewListener;
	private JScrollPane jScrollPane1;
	private JTextArea jTextArea1;
	private JPanel topPanel;
	private VersionChecker vch = new VersionChecker();

	public void frameConstructor() {

		setTitle("About Top Dictionary");
		setSize(648, 277);
		move(300, 300);
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
		this.jTextArea1
				.setText("Top Dictionary "
						+ vch.getCurrentVersion()
						+ " is a handy tool to keep and manage your foreging words its translation and transcription. \r\nauthor: Oleksandr Zalizniak (Alex).\r\nemail: aillusions@gmail.com\r\nEnjoy for free. If you would like to make some contribution:\r\nWeb Money:\r\n USD: Z230577301140\r\n EUR: E297731784822\r\n UAH: U295474096238\r\n\r\nHelp facility is comming soon. See news on http://aillusions.blogspot.com \r\n\r\nThank you for using Top Dictionary.");
		this.jTextArea1.setPreferredSize(new Dimension(546, 157));
	}

	private void ClearResultsBtnMouseReleased(MouseEvent paramMouseEvent) {
		this.viewListener.onClearResults();
	}
}
