package se.com.package1;

import javax.swing.SwingUtilities;

import se.com.ui.MainWindow;

public class Main {
	
	public static void main (String[] args){
        SwingUtilities.invokeLater(new Runnable() {
        	 
            @Override
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
	}
}
