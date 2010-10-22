
package com.aillusions.dictionary;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.aillusions.dictionary.util.VersionChecker;
import com.aillusions.dictionary.view.TopEditor;


public class Main {

    public static void main(String[] args) {
    	
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
		        TopEditor localTopEditor = new TopEditor();
		        localTopEditor.setLocationRelativeTo(null);
		        localTopEditor.setVisible(true);
		        localTopEditor.setSize(570, 320);
		        
		    	Logger l = Logger.getLogger(Main.class);
		    	l.log(Priority.INFO, "Started.");
		    	
		    	VersionChecker.checkVersionInSeparateThread(localTopEditor);
		    	
			}
		});
    }    
}
