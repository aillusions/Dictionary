
package com.myjavaserver.aillusions;

import javax.swing.SwingUtilities;

import com.myjavaserver.aillusions.view.TopEditor;


public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TopEditor inst = new TopEditor();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
    }    
}
