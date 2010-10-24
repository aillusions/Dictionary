package com.aillusions.dictionary.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;

import com.aillusions.dictionary.view.layout.AnchorConstraint;
import com.aillusions.dictionary.view.layout.AnchorLayout;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class TrainerView extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	private TrainerViewListener viewListener;
	private JLabel TRANSCR;
	private JButton ClearResultsBtn;

	private String[] currentVariants;

	private int lastSelected = -1;

	public TrainerView(TrainerViewListener defaultListener) {
		super();
		viewListener = defaultListener;
		frameConstructor();
	}

	public void setPairtForQuastion(String q, String transcr,  String[] variants) {

		
		quastionLable.setText(q);
		TRANSCR.setText(transcr);
		
		currentVariants = variants;

		optionsList.setVisible(false);
		viewRefresh();
		optionsList.setVisible(true);

		optionsList.repaint();
		optionsList.repaint();
	}

	private void viewRefresh() {
		ListModel optionsListModel = new DefaultComboBoxModel(currentVariants);

		{
			getContentPane().add(TRANSCR, new AnchorConstraint(57, 768, 149, 500, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			{				
				getContentPane().add(optionsList, new AnchorConstraint(185, 981, 960, 24, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
				optionsList.addMouseMotionListener(new MouseMotionAdapter() {
					public void mouseMoved(MouseEvent evt) {
						optionsListMouseMoved(evt);
					}
				});
				optionsList.addMouseListener(new MouseAdapter() {
					public void mouseReleased(MouseEvent evt) {
						optionsListMouseReleased(evt);
					}
					
				public void mouseExited(MouseEvent evt) {
					optionsListMouseExited(evt);
				}
				
				public void mouseEntered(MouseEvent evt) {
					optionsListMouseEntered(evt);
				}
				});
			}
			TRANSCR.setPreferredSize(new java.awt.Dimension(149, 15));
		}
		optionsList.setModel(optionsListModel);
		optionsList.setPreferredSize(new java.awt.Dimension(531, 127));

		//quastionLable.setText(currentPair.getEnglish());

		//TRANSCR.setText(currentPair.getTranscription());
		optionsList.setEnabled(true);

	}

	// Instance attributes used in this example
	private JPanel topPanel;
	private JLabel quastionLable;
	private JList optionsList;

	// Constructor of main frame
	public void frameConstructor() {
		// Set the frame characteristics
		setTitle("Words Trainer");
		this.setSize(563, 191);
		setBackground(Color.gray);
		AnchorLayout thisLayout = new AnchorLayout();
		getContentPane().setLayout(thisLayout);
		{
			ClearResultsBtn = new JButton();
			getContentPane().add(ClearResultsBtn, new AnchorConstraint(45, 979, 155, 804, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));
			ClearResultsBtn.setText("Clear");
			ClearResultsBtn.setPreferredSize(new java.awt.Dimension(97, 18));
			ClearResultsBtn.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent evt) {
					ClearResultsBtnMouseReleased(evt);
				}
			});
		}
		{
			quastionLable = new JLabel();
			getContentPane().add(quastionLable, new AnchorConstraint(57, 489, 149, 31, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL, AnchorConstraint.ANCHOR_REL));

			quastionLable.setPreferredSize(new java.awt.Dimension(254, 15));
		}

		// Create a panel to hold all other components
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(
				topPanel,
				new AnchorConstraint(329, 146, 329, 146,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL,
						AnchorConstraint.ANCHOR_REL));

		topPanel.setPreferredSize(new java.awt.Dimension(0, 0));

		TRANSCR = new JLabel();

		TRANSCR.setFont(new java.awt.Font("Lucida Sans Unicode", 0, 14));
		
		optionsList = new JList();
	}

	private void ClearResultsBtnMouseReleased(MouseEvent evt) {
		viewListener.onClearResults();
	}

	private void optionsListMouseEntered(MouseEvent evt) {
		if (optionsList.isEnabled()) {
			int curr = optionsList.locationToIndex(evt.getPoint());

			lastSelected = curr;
			optionsList.setSelectionBackground(Color.yellow);
			optionsList.setSelectedIndex(optionsList.locationToIndex(evt
					.getPoint()));
		}
	}

	private void optionsListMouseExited(MouseEvent evt) {
		if (optionsList.isEnabled()) {
			int curr = optionsList.locationToIndex(evt.getPoint());

			if (lastSelected != curr) {
				lastSelected = curr;

				optionsList.setSelectedIndex(optionsList.locationToIndex(evt
						.getPoint()));
			}
			optionsList.setSelectionBackground(Color.white);
		}
	}

	private void optionsListMouseMoved(MouseEvent evt) {
		if (optionsList.isEnabled()) {
			int curr = optionsList.locationToIndex(evt.getPoint());

			if (lastSelected != curr) {
				lastSelected = curr;
				optionsList.setSelectionBackground(Color.yellow);
				optionsList.setSelectedIndex(optionsList.locationToIndex(evt
						.getPoint()));
			}
		}
	}

	private void optionsListMouseReleased(MouseEvent evt) {

		if (optionsList.isEnabled()) {
			if (optionsList.getSelectedIndex() != -1) {
				optionsList.setSelectionBackground(Color.white);
				lastSelected = optionsList.getSelectedIndex();
				boolean result = false;
				result = viewListener.onChuseAnsversVariant(optionsList
						.getSelectedValue().toString());

				if (!result) {
					optionsList.setSelectionBackground(Color.red);

				} else {
					optionsList.setSelectionBackground(Color.green);
					optionsList.setEnabled(false);
					viewListener.onNextReady();
				}
			}
		}
	}

}
