package com.aillusions.dictionary.view.components;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import com.aillusions.dictionary.view.listener.MainDictPanelListener;

public class KeyBoardButton extends JButton{

	private static final long serialVersionUID = 1L;

	KeyBoardButton(String text, final MainDictPanelListener mainDictPanelListener){
		setText(text);
		setHorizontalTextPosition(0);
		setMargin(new Insets(1, 1, 1, 1));
		setFont(new Font("Arial", 0, 11));
		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent paramMouseEvent) {
				mainDictPanelListener.literalButtonsMouseReleased(paramMouseEvent);
			}
		});
	}
}
