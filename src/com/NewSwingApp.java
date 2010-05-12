package com;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;
import javax.swing.table.TableColumn;

public class NewSwingApp extends javax.swing.JFrame {


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				NewSwingApp inst = new NewSwingApp();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public NewSwingApp() {
		super();
		SimpleTableExample();
	}
	
	// Instance attributes used in this example
	private	JPanel		topPanel;
	private	JTable		table;
	private	JScrollPane scrollPane;

	// Constructor of main frame
	public void SimpleTableExample()
	{
		// Set the frame characteristics
		setTitle( "Simple Table Application" );
		setSize( 300, 200 );
		setBackground( Color.gray );

		// Create a panel to hold all other components
		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

		// Create columns names
		String columnNames[] = { "Column 1", "Column 2" };

		// Create some data
		String dataValues[][] =
		{
			{ "12", "234"},
			{ "-123", "43" },
			{ "93", "89.2" },
			{ "279", "9033" }
		};

		// Create a new table instance
		table = new JTable( dataValues, columnNames );
		
	    TableColumn includeColumn = table.getColumnModel().getColumn(0);
        includeColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));


		// Add the table to a scrolling pane
		scrollPane = new JScrollPane( table );
		topPanel.add( scrollPane, BorderLayout.CENTER );
	}


}
